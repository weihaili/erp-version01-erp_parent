package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IReturnOrderBiz;
import org.cn.kkl.erp.entity.ReturnOrder;

public class ReturnOrderAction extends BaseAction<ReturnOrder> {
	
	private IReturnOrderBiz returnOrderBiz;

	public void setReturnOrderBiz(IReturnOrderBiz returnOrderBiz) {
		this.returnOrderBiz = returnOrderBiz;
		super.setBaseBiz(this.returnOrderBiz);
	}
	
	

}
