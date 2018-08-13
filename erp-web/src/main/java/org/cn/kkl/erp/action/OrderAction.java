package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IOrderBiz;
import org.cn.kkl.erp.entity.Order;

public class OrderAction extends BaseAction<Order> {
	
	private IOrderBiz orderBiz;

	public void setOrderBiz(IOrderBiz orderBiz) {
		this.orderBiz = orderBiz;
		super.setBaseBiz(this.orderBiz);
	}
	

}
