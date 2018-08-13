package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IEmpRoleBiz;
import org.cn.kkl.erp.dao.IEmpRoleDao;
import org.cn.kkl.erp.entity.EmpRole;

public class EmpRoleBiz extends BaseBiz<EmpRole> implements IEmpRoleBiz {
	
	private IEmpRoleDao empRoleDao;

	public void setEmpRoleDao(IEmpRoleDao empRoleDao) {
		this.empRoleDao = empRoleDao;
		super.setBaseDao(this.empRoleDao);
	}
	
	

}
