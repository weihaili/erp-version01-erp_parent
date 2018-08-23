package org.cn.kkl.erp.action;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
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
	
	/**
	 * excel download 
	 */
	public void export(){
		String filename="";
		if (Supplier.TYPE_SUPPLIER==getT1().getType()) {
			filename="supplier";
		}
		if (Supplier.TYPE_CLIENT==getT1().getType()) {
			filename="client";
		}
		filename +=".xls";
		HttpServletResponse response = ServletActionContext.getResponse();
		try {
			response.setHeader("Content-Disposition", "attachment;filename="+new String(filename.getBytes(),"ISO-8859-1"));
			supplierBiz.export(response.getOutputStream(), getT1());
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	

}
