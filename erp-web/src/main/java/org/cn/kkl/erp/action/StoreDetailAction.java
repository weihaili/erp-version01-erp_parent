package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IStoreDetailBiz;
import org.cn.kkl.erp.entity.StoreDetail;

public class StoreDetailAction extends BaseAction<StoreDetail> {
	
	private IStoreDetailBiz storeDetailBiz;

	public void setStoreDetailBiz(IStoreDetailBiz storeDetailBiz) {
		this.storeDetailBiz = storeDetailBiz;
		super.setBaseBiz(this.storeDetailBiz);
	}

	
}
