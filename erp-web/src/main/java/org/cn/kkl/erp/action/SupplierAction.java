package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.ISupplierBiz;
import org.cn.kkl.erp.entity.Supplier;

public class SupplierAction extends BaseAction<Supplier> {
	
	private ISupplierBiz supplierBiz;
	private String q;//automatic completion query condition 

	public void setSupplierBiz(ISupplierBiz supplierBiz) {
		this.supplierBiz = supplierBiz;
		super.setBaseBiz(this.supplierBiz);
	}

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	/* 
	 * easyui combogrid automatic completion query condition
	 * 1. judgment weather exist query condition,if do not exist ,create query condition
	 */
	public void list(){
		if (null==getT1()) {
			setT1(new Supplier());
		}
		getT1().setName(q);
		super.list();
	}
	
	
	

}
