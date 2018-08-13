package org.cn.kkl.erp.dao.impl;

import org.cn.kkl.erp.dao.IEmpRoleDao;
import org.cn.kkl.erp.entity.EmpRole;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class EmpRoleDao extends BaseDao<EmpRole> implements IEmpRoleDao {

	@Override
	public DetachedCriteria getDetachedCriteria(EmpRole er1, EmpRole er2, Object param) {
		DetachedCriteria criteria= DetachedCriteria.forClass(EmpRole.class);
		if (null!=er1) {
			if (null!=er1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", er1.getUuid()));
			}
			if (null!=er1.getEmpUuid()) {
				criteria.add(Restrictions.eq("empUuid", er1.getEmpUuid()));
			}
			if (null!=er1.getRoleUuid()) {
				criteria.add(Restrictions.eq("roleUuid", er1.getRoleUuid()));
			}
		}
		return criteria;
	}
	
	

}
