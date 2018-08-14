package org.cn.kkl.erp.dao.impl;


import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IReturnOrderDetailDao;
import org.cn.kkl.erp.entity.ReturnOrderDetail;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class ReturnOrderDetailDao extends BaseDao<ReturnOrderDetail> implements IReturnOrderDetailDao {

	@Override
	public DetachedCriteria getDetachedCriteria(ReturnOrderDetail returnOrderDetail1, ReturnOrderDetail returnOrderDetail2, Object param) {
		DetachedCriteria criteria = DetachedCriteria.forClass(ReturnOrderDetail.class);
		if (returnOrderDetail1!=null) {
			if (null!=returnOrderDetail1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", returnOrderDetail1.getUuid()));
			}
			if (null!=returnOrderDetail1.getGoodsuuid()) {
				criteria.add(Restrictions.eq("goodsuuid", returnOrderDetail1.getGoodsuuid()));
			}
			if (StringUtils.isNotBlank(returnOrderDetail1.getGoodsname())) {
				criteria.add(Restrictions.like("goodsname", returnOrderDetail1.getGoodsname(), MatchMode.ANYWHERE));
			}
			if (null!=returnOrderDetail1.getPrice()) {
				criteria.add(Restrictions.ge("price", returnOrderDetail1.getPrice()));
			}
			if (null!=returnOrderDetail1.getNum()) {
				criteria.add(Restrictions.ge("num", returnOrderDetail1.getNum()));
			}
			if (null!=returnOrderDetail1.getMoney()) {
				criteria.add(Restrictions.ge("money", returnOrderDetail1.getMoney()));
			}
			if (null!=returnOrderDetail1.getEndtime()) {
				criteria.add(Restrictions.ge("endtime", returnOrderDetail1.getEndtime()));
			}
			if (null!=returnOrderDetail1.getEnder()) {
				criteria.add(Restrictions.eq("ender", returnOrderDetail1.getEnder()));
			}
			if (null!=returnOrderDetail1.getStoreuuid()) {
				criteria.add(Restrictions.eq("storeuuid", returnOrderDetail1.getStoreuuid()));
			}
			if (StringUtils.isNotBlank(String.valueOf(returnOrderDetail1.getState()).trim())) {
				criteria.add(Restrictions.eq("state", returnOrderDetail1.getState()));
			}
			if (null!=returnOrderDetail1.getOrdersuuid()) {
				criteria.add(Restrictions.eqOrIsNull("ordersuuid", returnOrderDetail1.getOrdersuuid()));
			}
		}
		if (null!=returnOrderDetail2) {
			if (null!=returnOrderDetail2.getPrice()) {
				criteria.add(Restrictions.le("price", returnOrderDetail2.getPrice()));
			}
			if (null!=returnOrderDetail2.getNum()) {
				criteria.add(Restrictions.le("num", returnOrderDetail2.getNum()));
			}
			if (null!=returnOrderDetail2.getMoney()) {
				criteria.add(Restrictions.le("money", returnOrderDetail2.getMoney()));
			}
			if (null!=returnOrderDetail2.getEndtime()) {
				criteria.add(Restrictions.le("endtime", returnOrderDetail2.getEndtime()));
			}
		}
		
		return criteria;
	}

	
}
