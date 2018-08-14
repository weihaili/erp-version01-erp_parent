package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IRoleMenuBiz;
import org.cn.kkl.erp.dao.IRoleMenuDao;
import org.cn.kkl.erp.entity.RoleMenu;

public class RoleMenuBiz extends BaseBiz<RoleMenu> implements IRoleMenuBiz {
	
	private IRoleMenuDao roleMenuDao;

	public void setRoleMenuDao(IRoleMenuDao roleMenuDao) {
		this.roleMenuDao = roleMenuDao;
		super.setBaseDao(this.roleMenuDao);
	}
	

}
