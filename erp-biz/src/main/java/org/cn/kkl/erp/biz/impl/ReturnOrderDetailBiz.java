package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IReturnOrderDetailBiz;
import org.cn.kkl.erp.dao.IReturnOrderDetailDao;
import org.cn.kkl.erp.entity.ReturnOrderDetail;

public class ReturnOrderDetailBiz extends BaseBiz<ReturnOrderDetail> implements IReturnOrderDetailBiz {
	
	private IReturnOrderDetailDao returnOrderDetailDao;

	public void setReturnOrderDetailDao(IReturnOrderDetailDao returnOrderDetailDao) {
		this.returnOrderDetailDao = returnOrderDetailDao;
		super.setBaseDao(this.returnOrderDetailDao);
	}
	
	
}
