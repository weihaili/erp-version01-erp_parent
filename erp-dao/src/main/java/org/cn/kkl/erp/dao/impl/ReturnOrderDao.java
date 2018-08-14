package org.cn.kkl.erp.dao.impl;


import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IReturnOrderDao;
import org.cn.kkl.erp.entity.ReturnOrder;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class ReturnOrderDao extends BaseDao<ReturnOrder> implements IReturnOrderDao {

	@Override
	public DetachedCriteria getDetachedCriteria(ReturnOrder returnOrder1, ReturnOrder returnOrder2, Object param) {
		DetachedCriteria criteria =DetachedCriteria.forClass(ReturnOrder.class);
		if(null!=returnOrder1){
			if(null!=returnOrder1.getUuid()){
				criteria.add(Restrictions.eq("uuid", returnOrder1.getUuid()));
			}
			if (null!=returnOrder1.getCreatetime()) {
				criteria.add(Restrictions.ge("createtime", returnOrder1.getCreatetime()));
			}
			if (null!=returnOrder1.getChecktime()) {
				criteria.add(Restrictions.ge("checktime", returnOrder1.getChecktime()));
			}
			if (StringUtils.isNotBlank(String.valueOf(returnOrder1.getType()).trim())) {
				criteria.add(Restrictions.eq("type", returnOrder1.getType()));
			}
			if (null!=returnOrder1.getCreater()) {
				criteria.add(Restrictions.eqOrIsNull("creater", returnOrder1.getCreater()));
			}
			if (null!=returnOrder1.getChecker()) {
				criteria.add(Restrictions.eqOrIsNull("checker", returnOrder1.getChecker()));
			}
			if (null!=returnOrder1.getEnder()) {
				criteria.add(Restrictions.eq("ender", returnOrder1.getEnder()));
			}
			if (null!=returnOrder1.getSupplieruuid()) {
				criteria.add(Restrictions.eq("supplieruuid", returnOrder1.getSupplieruuid()));
			}
			if (null!=returnOrder1.getTotalmoney()) {
				criteria.add(Restrictions.ge("totalmoney", returnOrder1.getTotalmoney()));
			}
			if (StringUtils.isNotBlank(String.valueOf(returnOrder1.getState()).trim())) {
				criteria.add(Restrictions.eq("state", returnOrder1.getState()));
			}
			if (null!=returnOrder1.getWaybillsn()) {
				criteria.add(Restrictions.eq("waybillsn", returnOrder1.getWaybillsn()));
			}
			if (null!=returnOrder1.getOrdersuuid()) {
				criteria.add(Restrictions.eq("ordersuuid", returnOrder1.getOrdersuuid()));
			}
		}
		if (null!=returnOrder2) {
			if (null!=returnOrder2.getCreatetime()) {
				criteria.add(Restrictions.le("createtime", returnOrder2.getCreatetime()));
			}
			if (null!=returnOrder2.getChecktime()) {
				criteria.add(Restrictions.le("checktime", returnOrder2.getChecktime()));
			}
			if (null!=returnOrder2.getTotalmoney()) {
				criteria.add(Restrictions.le("totalmoney", returnOrder2.getTotalmoney()));
			}
		}
		return criteria;
	}
	
	

}
