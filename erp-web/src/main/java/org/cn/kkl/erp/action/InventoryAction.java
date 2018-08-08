package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IInventoryBiz;
import org.cn.kkl.erp.entity.Inventory;

public class InventoryAction extends BaseAction<Inventory> {
	
	private IInventoryBiz inventoryBiz;

	public void setInventoryBiz(IInventoryBiz inventoryBiz) {
		this.inventoryBiz = inventoryBiz;
		super.setBaseBiz(this.inventoryBiz);
	}
	

}
