package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IOrderDetailBiz;
import org.cn.kkl.erp.dao.IOrderDetailDao;
import org.cn.kkl.erp.entity.OrderDetail;

public class OrderDetailBiz extends BaseBiz<OrderDetail> implements IOrderDetailBiz {

	private IOrderDetailDao orderDetailDao;

	public void setOrderDetailDao(IOrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
		super.setBaseDao(this.orderDetailDao);
	}

	
	
}
