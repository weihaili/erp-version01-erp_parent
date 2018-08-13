package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.ISupplierBiz;
import org.cn.kkl.erp.entity.Supplier;

public class SupplierAction extends BaseAction<Supplier> {
	
	private ISupplierBiz supplierBiz;

	public void setSupplierBiz(ISupplierBiz supplierBiz) {
		this.supplierBiz = supplierBiz;
		super.setBaseBiz(this.supplierBiz);
	}
	
	

}
