package org.cn.kkl.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IOrderDetailDao;
import org.cn.kkl.erp.entity.OrderDetail;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class OrderDetailDao extends BaseDao<OrderDetail> implements IOrderDetailDao {

	@Override
	public DetachedCriteria getDetachedCriteria(OrderDetail orderDetail1, OrderDetail orderDetail2, Object param) {
		DetachedCriteria criteria = DetachedCriteria.forClass(OrderDetail.class);
		if (orderDetail1!=null) {
			if (null!=orderDetail1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", orderDetail1.getUuid()));
			}
			if (null!=orderDetail1.getGoodsuuid()) {
				criteria.add(Restrictions.eq("goodsuuid", orderDetail1.getGoodsuuid()));
			}
			if (StringUtils.isNotBlank(orderDetail1.getGoodsname())) {
				criteria.add(Restrictions.like("goodsname", orderDetail1.getGoodsname(), MatchMode.ANYWHERE));
			}
			if (null!=orderDetail1.getPrice()) {
				criteria.add(Restrictions.ge("price", orderDetail1.getPrice()));
			}
			if (null!=orderDetail1.getNum()) {
				criteria.add(Restrictions.ge("num", orderDetail1.getNum()));
			}
			if(null!=orderDetail1.getMoney()){
				criteria.add(Restrictions.ge("money", orderDetail1.getMoney()));
			}
			if (null!=orderDetail1.getEndtime()) {
				criteria.add(Restrictions.ge("endtime", orderDetail1.getEndtime()));
			}
			if (null!=orderDetail1.getEnder()) {
				criteria.add(Restrictions.eq("ender", orderDetail1.getEnder()));
			}
			if (null!=orderDetail1.getStoreuuid()) {
				criteria.add(Restrictions.eq("storeuuid", orderDetail1.getStoreuuid()));
			}
			if (StringUtils.isNotBlank(String.valueOf(orderDetail1.getState()).trim())) {
				criteria.add(Restrictions.eq("state", orderDetail1.getState()));
			}
			if (null!=orderDetail1.getOrder() && null !=orderDetail1.getOrder().getUuid()) {
				criteria.add(Restrictions.eq("order", orderDetail1.getOrder()));
			}
		}
		if (null!=orderDetail2) {
			if (null!=orderDetail2.getPrice()) {
				criteria.add(Restrictions.le("price", orderDetail2.getPrice()));
			}
			if (null!=orderDetail2.getNum()) {
				criteria.add(Restrictions.le("num", orderDetail2.getNum()));
			}
			if(null!=orderDetail2.getMoney()){
				criteria.add(Restrictions.le("money", orderDetail2.getMoney()));
			}
			if (null!=orderDetail2.getEndtime()) {
				criteria.add(Restrictions.le("endtime", orderDetail2.getEndtime()));
			}
		}
		
		return criteria;
	}

}
