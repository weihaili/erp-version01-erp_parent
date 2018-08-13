package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IOrderBiz;
import org.cn.kkl.erp.dao.IOrderDao;
import org.cn.kkl.erp.entity.Order;

public class OrderBiz extends BaseBiz<Order> implements IOrderBiz {
	
	private IOrderDao orderDao;

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
		super.setBaseDao(this.orderDao);
	}
	
	

}
