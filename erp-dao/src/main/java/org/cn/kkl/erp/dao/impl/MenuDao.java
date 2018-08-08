package org.cn.kkl.erp.dao.impl;

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
			if (StringUtils.isNotBlank(menu1.getMenuName())) {
				criteria.add(Restrictions.like("menuName", menu1.getMenuName(),MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(menu1.getUrl())) {
				criteria.add(Restrictions.like("url", menu1.getUrl(), MatchMode.START));
			}
		}
		return criteria;
	}

	
}
