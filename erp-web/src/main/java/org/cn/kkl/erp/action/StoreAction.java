package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IStoreBiz;
import org.cn.kkl.erp.entity.Store;

public class StoreAction extends BaseAction<Store> {
	
	private IStoreBiz storeBiz;

	public void setStoreBiz(IStoreBiz storeBiz) {
		this.storeBiz = storeBiz;
		super.setBaseBiz(this.storeBiz);
	}
	
	
}
