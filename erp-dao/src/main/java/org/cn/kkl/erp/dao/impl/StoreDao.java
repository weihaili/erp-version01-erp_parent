package org.cn.kkl.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IStoreDao;
import org.cn.kkl.erp.entity.Store;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class StoreDao extends BaseDao<Store> implements IStoreDao {

	@Override
	public DetachedCriteria getDetachedCriteria(Store store1, Store store2, Object param) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Store.class);
		if (null!=store1) {
			if (null != store1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", store1.getUuid()));
			}
			if (StringUtils.isNotBlank(store1.getName())) {
				criteria.add(Restrictions.like("name", store1.getName(), MatchMode.ANYWHERE));
			}
			if (null!=store1.getEmpuuid()) {
				criteria.add(Restrictions.eq("empuuid", store1.getEmpuuid()));
			}
		}
		return criteria;
	}
	
	


}
