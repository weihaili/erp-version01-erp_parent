package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IStoreDetailBiz;
import org.cn.kkl.erp.dao.IStoreDetailDao;
import org.cn.kkl.erp.entity.StoreDetail;

public class StoreDetailBiz extends BaseBiz<StoreDetail> implements IStoreDetailBiz {

	private IStoreDetailDao storeDetailDao;

	public void setStoreDetailDao(IStoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
		super.setBaseDao(this.storeDetailDao);
	}
	
	
}
