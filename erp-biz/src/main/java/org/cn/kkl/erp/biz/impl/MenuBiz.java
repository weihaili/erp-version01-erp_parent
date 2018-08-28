package org.cn.kkl.erp.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.cn.kkl.erp.biz.IMenuBiz;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.dao.IMenuDao;
import org.cn.kkl.erp.entity.Menu;

public class MenuBiz extends BaseBiz<Menu> implements IMenuBiz {
	
	private IMenuDao menuDao;

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao(this.menuDao);
	}

	/**
	 * query employee permission(menu) by employee uuid
	 * @param empuuid
	 * @return
	 */
	public List<Menu> getMenusByEmpuuid(Long empuuid){
		return menuDao.getMenusByEmpuuid(empuuid);
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
		Menu root = menuDao.get("0");
		List<Menu> menusByEmpuuid = menuDao.getMenusByEmpuuid(empuuid);
		Menu menu = cloneMenu(root);
		
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
