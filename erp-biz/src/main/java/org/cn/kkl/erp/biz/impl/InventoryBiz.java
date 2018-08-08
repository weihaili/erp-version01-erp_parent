package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IInventoryBiz;
import org.cn.kkl.erp.dao.IInventoryDao;
import org.cn.kkl.erp.entity.Inventory;

public class InventoryBiz extends BaseBiz<Inventory> implements IInventoryBiz {

	private IInventoryDao inventoryDao;

	public void setInventoryDao(IInventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
		super.setBaseDao(this.inventoryDao);
	}
	
	
}
