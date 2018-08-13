package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IEmpRoleBiz;
import org.cn.kkl.erp.entity.EmpRole;

public class EmpRoleAction extends BaseAction<EmpRole> {
	
	private IEmpRoleBiz empRoleBiz;

	public void setEmpRoleBiz(IEmpRoleBiz empRoleBiz) {
		this.empRoleBiz = empRoleBiz;
		super.setBaseBiz(this.empRoleBiz);
	}
	
	

}
