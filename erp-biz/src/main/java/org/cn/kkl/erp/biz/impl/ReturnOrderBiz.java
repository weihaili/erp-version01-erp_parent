package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IReturnOrderBiz;
import org.cn.kkl.erp.dao.IReturnOrderDao;
import org.cn.kkl.erp.entity.ReturnOrder;

public class ReturnOrderBiz extends BaseBiz<ReturnOrder> implements IReturnOrderBiz {

	private IReturnOrderDao returnOrderDao;

	public void setReturnOrderDao(IReturnOrderDao returnOrderDao) {
		this.returnOrderDao = returnOrderDao;
		super.setBaseDao(this.returnOrderDao);
	}
	
	
}
