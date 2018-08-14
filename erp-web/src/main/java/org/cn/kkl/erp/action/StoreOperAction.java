package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IStoreOperBiz;
import org.cn.kkl.erp.entity.StoreOper;

public class StoreOperAction extends BaseAction<StoreOper> {
	
	private IStoreOperBiz storeOperBiz;

	public void setStoreOperBiz(IStoreOperBiz storeOperBiz) {
		this.storeOperBiz = storeOperBiz;
		super.setBaseBiz(this.storeOperBiz);
	}
	

}
