package org.cn.kkl.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IRoleDao;
import org.cn.kkl.erp.entity.Role;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class RoleDao extends BaseDao<Role> implements IRoleDao {

	@Override
	public DetachedCriteria getDetachedCriteria(Role role1, Role role2, Object param) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Role.class);
		
		if (null!=role1) {
			if (StringUtils.isNotBlank(role1.getName()) ) {
				criteria.add(Restrictions.like("name", role1.getName(), MatchMode.ANYWHERE));
			}
			if(null!=role1.getCreateTime()){
				criteria.add(Restrictions.ge("createTime", role1.getCreateTime()));
			}
		}
		if (null!=role2) {
			
			if (null!=role2.getCreateTime()) {
				criteria.add(Restrictions.le("createTime", role2.getCreateTime()));
			}
		}
		
		return criteria;
	}

	
}
