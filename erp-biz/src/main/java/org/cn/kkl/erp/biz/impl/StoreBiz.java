package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IStoreBiz;
import org.cn.kkl.erp.dao.IStoreDao;
import org.cn.kkl.erp.entity.Store;

public class StoreBiz extends BaseBiz<Store> implements IStoreBiz {
	
	private IStoreDao storeDao;

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
		super.setBaseDao(this.storeDao);
	}
	
	

}
