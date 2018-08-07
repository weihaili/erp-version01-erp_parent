package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IEmpBiz;
import org.cn.kkl.erp.entity.Emp;

public class EmpAction extends BaseAction<Emp>{
	
	private IEmpBiz empBiz;

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		super.setBaseBiz(this.empBiz);
	}

	
}
