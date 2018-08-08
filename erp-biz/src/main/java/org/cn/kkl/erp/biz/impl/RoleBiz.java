package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IRoleBiz;
import org.cn.kkl.erp.dao.IRoleDao;
import org.cn.kkl.erp.entity.Role;

public class RoleBiz extends BaseBiz<Role> implements IRoleBiz {
	
	private IRoleDao roleDao;

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(this.roleDao);
	}
	
	

}
