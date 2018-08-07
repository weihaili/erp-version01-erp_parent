package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IEmpBiz;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.entity.Emp;

public class EmpBiz extends BaseBiz<Emp> implements IEmpBiz {

	private IEmpDao empDao;

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(this.empDao);
	}
	
	

}
