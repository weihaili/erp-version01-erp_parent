package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IStoreOperBiz;
import org.cn.kkl.erp.dao.IStoreOperDao;
import org.cn.kkl.erp.entity.StoreOper;

public class StoreOperBiz extends BaseBiz<StoreOper> implements IStoreOperBiz {
	
	private IStoreOperDao storeOperDao;

	public void setStoreOperDao(IStoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
		super.setBaseDao(this.storeOperDao);
	}
	

}
