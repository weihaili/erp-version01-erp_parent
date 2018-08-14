package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IReturnOrderDetailBiz;
import org.cn.kkl.erp.entity.ReturnOrderDetail;

public class ReturnOrderDetailAction extends BaseAction<ReturnOrderDetail> {
	
	private IReturnOrderDetailBiz returnOrderDetailBiz;

	public void setReturnOrderDetailBiz(IReturnOrderDetailBiz returnOrderDetailBiz) {
		this.returnOrderDetailBiz = returnOrderDetailBiz;
		super.setBaseBiz(this.returnOrderDetailBiz);
	}
	
	

}
