package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.ISupplierBiz;
import org.cn.kkl.erp.dao.ISupplierDao;
import org.cn.kkl.erp.entity.Supplier;

public class SupplierBiz extends BaseBiz<Supplier> implements ISupplierBiz {
	
	private ISupplierDao supplierDao;

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		super.setBaseDao(this.supplierDao);
	}
	
	
}
