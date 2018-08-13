package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IOrderDetailBiz;
import org.cn.kkl.erp.entity.OrderDetail;

public class OrderDetailAction extends BaseAction<OrderDetail> {

	private IOrderDetailBiz orderDetailBiz;

	public void setOrderDetailBiz(IOrderDetailBiz orderDetailBiz) {
		this.orderDetailBiz = orderDetailBiz;
		super.setBaseBiz(this.orderDetailBiz);
	}
	
}
