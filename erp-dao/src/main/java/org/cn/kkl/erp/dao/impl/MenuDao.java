package org.cn.kkl.erp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IMenuDao;
import org.cn.kkl.erp.entity.Menu;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class MenuDao extends BaseDao<Menu> implements IMenuDao {

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
		String hql="select m from Emp e join e.roles r join r.menus m where e.uuid=? ";
		return (List<Menu>) this.getHibernateTemplate().find(hql, empuuid);
	}
}
