package org.cn.kkl.erp.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.biz.IMenuBiz;
import org.cn.kkl.erp.dao.IMenuDao;
import org.cn.kkl.erp.entity.Menu;
import org.cn.kkl.erp.redis.dao.JedisClient;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class MenuBiz extends BaseBiz<Menu> implements IMenuBiz {
	
	private IMenuDao menuDao;
	
	private JedisClient jedisClient;

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao(this.menuDao);
	}

	public void setJedisClient(JedisClient jedisClient) {
		this.jedisClient = jedisClient;
	}

	/**
	 * query employee permission(menu) by employee uuid
	 * @param empuuid
	 * @return
	 */
	public List<Menu> getMenusByEmpuuid(Long empuuid){
		List<Menu> menus = null;
		try {
			String hgetStr = jedisClient.hget("permission", String.valueOf(empuuid));
			if (StringUtils.isNotBlank(hgetStr)) {
				menus = JSON.parseArray(hgetStr, Menu.class);
				if (null!=menus) {
					return menus;
				}
			}
		} catch (Exception e1) {
			System.out.println("get permission(menu) from reids exception,please check reids server");
			e1.printStackTrace();
		}
		
		menus = menuDao.getMenusByEmpuuid(empuuid);
		try {
			jedisClient.hset("permission", String.valueOf(empuuid), JSON.toJSONString(menus,SerializerFeature.DisableCircularReferenceDetect));
		} catch (Exception e) {
			System.out.println("permission save in redis exception,please check redis server");
			e.printStackTrace();
		}
		
		return menus;
	}

	/**
	 * query employee permission(menu) by employee uuid
	 * @param empuuid
	 * @return
	 * @description step:
	 *  1. get all menus use as template
	 *  2. get employee`s menu
	 *  3. polling template menu find employee`s menu then clone
	 *  	when exist secondary menu then add 
	 *  	when employee`s menu add return menu
	 */
	public Menu readMenusByEmpuuid(Long empuuid){
		Menu menu = null;
		
		try {
			String hgetStr = jedisClient.get("permission:"+empuuid);
			if (StringUtils.isNotBlank(hgetStr)) {
				menu = JSON.parseObject(hgetStr, Menu.class);
				if (null!=menu) {
					return menu;
				}
			}
		} catch (Exception e) {
			System.out.println("redis server exception , please check");
			e.printStackTrace();
		}
		
		Menu root = menuDao.get("0");
		List<Menu> menusByEmpuuid = menuDao.getMenusByEmpuuid(empuuid);
		menu = cloneMenu(root);
		
		Menu _m1=null;
		Menu _m2=null;
		for (Menu m1 : root.getMenus()) {
			_m1=cloneMenu(m1);
			for (Menu m2 : m1.getMenus()) {
				if (menusByEmpuuid.contains(m2)) {
					_m2 = cloneMenu(m2);
					_m1.getMenus().add(_m2);
				}
			}
			
			if (_m1.getMenus().size()>0) {
				menu.getMenus().add(_m1);
			}
		}
		try {
			jedisClient.set("permission:"+empuuid, JSON.toJSONString(menu));
		} catch (Exception e) {
			System.out.println("redis server exception,please check");
			e.printStackTrace();
		}
		
		return menu;
	}
	
	/**
	 * copy menu
	 * @param src
	 * @return
	 */
	private Menu cloneMenu(Menu src){
		Menu menu = new Menu();
		menu.setIcon(src.getIcon());
		menu.setMenuid(src.getMenuid());
		menu.setMenuname(src.getMenuname());
		menu.setUrl(src.getUrl());
		menu.setMenus(new ArrayList<Menu>());
		return menu;
	}
}
