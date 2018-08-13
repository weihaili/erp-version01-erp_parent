package org.cn.kkl.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.ISupplierDao;
import org.cn.kkl.erp.entity.Supplier;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class SupplierDao extends BaseDao<Supplier> implements ISupplierDao {

	@Override
	public DetachedCriteria getDetachedCriteria(Supplier supplier1, Supplier supplier2, Object param) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Supplier.class);
		if (null!=supplier1) {
			if (null!=supplier1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", supplier1.getUuid()));
			}
			if (StringUtils.isNotBlank(supplier1.getName())) {
				criteria.add(Restrictions.like("name", supplier1.getName(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(supplier1.getAddress())) {
				criteria.add(Restrictions.like("address", supplier1.getAddress(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(supplier1.getContact())) {
				criteria.add(Restrictions.like("contact", supplier1.getContact(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(supplier1.getTele())) {
				criteria.add(Restrictions.like("tele", supplier1.getTele(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(supplier1.getEmail())) {
				criteria.add(Restrictions.like("email", supplier1.getEmail(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNoneBlank(String.valueOf(supplier1.getType()).trim())) {
				criteria.add(Restrictions.eq("type", supplier1.getType()));
			}
		}
		return criteria;
	}
	
	

}
