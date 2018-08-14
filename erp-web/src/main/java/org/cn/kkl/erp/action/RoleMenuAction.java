package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IRoleMenuBiz;
import org.cn.kkl.erp.entity.RoleMenu;

public class RoleMenuAction extends BaseAction<RoleMenu> {
	
	private IRoleMenuBiz roleMenuBiz;

	public void setRoleMenuBiz(IRoleMenuBiz roleMenuBiz) {
		this.roleMenuBiz = roleMenuBiz;
		super.setBaseBiz(this.roleMenuBiz);
	}
	

}
