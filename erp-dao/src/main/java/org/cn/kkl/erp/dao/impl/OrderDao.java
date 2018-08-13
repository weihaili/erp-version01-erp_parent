package org.cn.kkl.erp.dao.impl;


import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IOrderDao;
import org.cn.kkl.erp.entity.Order;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class OrderDao extends BaseDao<Order> implements IOrderDao {

	@Override
	public DetachedCriteria getDetachedCriteria(Order order1, Order order2, Object param) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Order.class);
		if (null!=order1) {
			if (null!=order1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", order1.getUuid()));
			}
			if (null!=order1.getCreateTime()) {
				criteria.add(Restrictions.ge("createTime", order1.getCreateTime()));
			}
			if (null!=order1.getCheckTime()) {
				criteria.add(Restrictions.ge("checkTime", order1.getCheckTime()));
			}
			if (null!=order1.getStartTime()) {
				criteria.add(Restrictions.ge("startTime", order1.getStartTime()));
			}
			if (null!=order1.getEndTime()) {
				criteria.add(Restrictions.ge("endTime", order1.getEndTime()));
			}
			if (StringUtils.isNotBlank(String.valueOf(order1.getType()).trim())) {
				criteria.add(Restrictions.eq("type", order1.getType()));
			}
			if (null!=order1.getCreater()) {
				criteria.add(Restrictions.eq("creater", order1.getCreater()));
			}
			if (null!=order1.getChecker()) {
				criteria.add(Restrictions.eq("checker", order1.getChecker()));
			}
			if (null!=order1.getStarter()) {
				criteria.add(Restrictions.ge("starter", order1.getStarter()));
			}
			if (null!=order1.getEnder()) {
				criteria.add(Restrictions.eq("ender", order1.getEnder()));
			}
			if (null!=order1.getSupplierUuid()) {
				criteria.add(Restrictions.eq("supplierUuid", order1.getSupplierUuid()));
			}
			if (null!=order1.getTotalMoney()) {
				criteria.add(Restrictions.eq("totalMoney", order1.getTotalMoney()));
			}
			if (StringUtils.isNotBlank(String.valueOf(order1.getState()).trim())) {
				criteria.add(Restrictions.eq("state", order1.getState()));
			}
			if (null!=order1.getWayBillsn()) {
				criteria.add(Restrictions.eq("wayBillsn", order1.getWayBillsn()));
			}
			
		}
		if (null!=order2) {
			if (null!=order2.getCreateTime()) {
				criteria.add(Restrictions.ge("createTime", order2.getCreateTime()));
			}
			if (null!=order2.getCheckTime()) {
				criteria.add(Restrictions.ge("checkTime", order2.getCheckTime()));
			}
			if (null!=order2.getStartTime()) {
				criteria.add(Restrictions.ge("startTime", order2.getStartTime()));
			}
			if (null!=order2.getEndTime()) {
				criteria.add(Restrictions.ge("endTime", order2.getEndTime()));
			}
		}
		return criteria;
	}
	
	

}
