package org.cn.kkl.erp.dao.impl;

import java.util.List;

import org.cn.kkl.erp.dao.IStoreDetailDao;
import org.cn.kkl.erp.entity.StoreDetail;
import org.cn.kkl.erp.entity.StoreQuantityWarning;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class StoreDetailDao extends BaseDao<StoreDetail> implements IStoreDetailDao {

	@Override
	public DetachedCriteria getDetachedCriteria(StoreDetail storeDetail1, StoreDetail storeDetail2, Object param) {
		DetachedCriteria criteria =DetachedCriteria.forClass(StoreDetail.class);
		if (null!=storeDetail1) {
			if (null!=storeDetail1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", storeDetail1.getUuid()));
			}
			if (null!=storeDetail1.getGoodsuuid()) {
				criteria.add(Restrictions.eq("goodsuuid", storeDetail1.getGoodsuuid()));
			}
			if (null!=storeDetail1.getStoreuuid()) {
				criteria.add(Restrictions.eq("storeuuid", storeDetail1.getStoreuuid()));
			}
			if (null!=storeDetail1.getNum()) {
				criteria.add(Restrictions.ge("num", storeDetail1.getNum()));
			}
		}
		if (null!=storeDetail2) {
			if (null!=storeDetail2.getNum()) {
				criteria.add(Restrictions.le("num", storeDetail2.getNum()));
			}
		}
		
		return criteria;
	}

	/**
	 * inventory warning
	 * @return
	 */
	@Override
	public List<StoreQuantityWarning> getStorealertList(Double quantity) {
		Double limit=0d;
		if (null!=quantity) {
			limit=quantity;
		}
		String hql="from StoreQuantityWarning where (storenum-?)<= outnum ";
		return (List<StoreQuantityWarning>) getHibernateTemplate().find(hql,limit);
	}
	
	
}
