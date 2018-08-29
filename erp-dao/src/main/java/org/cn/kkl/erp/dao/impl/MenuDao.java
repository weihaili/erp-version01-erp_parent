package org.cn.kkl.erp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IMenuDao;
import org.cn.kkl.erp.entity.Menu;
import org.cn.kkl.erp.redis.dao.JedisClient;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.alibaba.fastjson.JSON;

public class MenuDao extends BaseDao<Menu> implements IMenuDao {
	
	private JedisClient jedisClient;
	
	public void setJedisClient(JedisClient jedisClient) {
		this.jedisClient = jedisClient;
	}

	@Override
	public DetachedCriteria getDetachedCriteria(Menu menu1, Menu menu2, Object param) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Menu.class);
		if (null!=menu1) {
			if (StringUtils.isNotBlank(menu1.getMenuname())) {
				criteria.add(Restrictions.like("menuname", menu1.getMenuname(),MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(menu1.getUrl())) {
				criteria.add(Restrictions.like("url", menu1.getUrl(), MatchMode.START));
			}
		}
		return criteria;
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
		
		String hql="select m from Emp e join e.roles r join r.menus m where e.uuid=? ";
		
		menus=(List<Menu>) this.getHibernateTemplate().find(hql, empuuid);
		
		try {
			jedisClient.hset("permission", String.valueOf(empuuid), JSON.toJSONString(menus));
		} catch (Exception e) {
			System.out.println("permission save in redis exception,please check redis server");
			e.printStackTrace();
		}
		return menus;
	}
}
