package org.cn.kkl.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IRoleMenuDao;
import org.cn.kkl.erp.entity.RoleMenu;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class RoleMenuDao extends BaseDao<RoleMenu> implements IRoleMenuDao {

	@Override
	public DetachedCriteria getDetachedCriteria(RoleMenu roleMenu1, RoleMenu roleMenu2, Object param) {
		DetachedCriteria criteria =DetachedCriteria.forClass(RoleMenu.class);
		if (null!=roleMenu1) {
			if (null!=roleMenu1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", roleMenu1.getUuid()));
			}
			if (null!=roleMenu1.getRoleuuid()) {
				criteria.add(Restrictions.eq("roleuuid", roleMenu1.getRoleuuid()));
			}
			if (StringUtils.isNotBlank(roleMenu1.getMenuuuid())) {
				criteria.add(Restrictions.like("menuuuid", roleMenu1.getMenuuuid(), MatchMode.ANYWHERE));
			}
		}
		return criteria;
	}
	
	

}
