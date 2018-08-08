package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IRoleBiz;
import org.cn.kkl.erp.entity.Role;

public class RoleAction extends BaseAction<Role> {
	
	private IRoleBiz roleBiz;

	public void setRoleBiz(IRoleBiz roleBiz) {
		this.roleBiz = roleBiz;
		super.setBaseBiz(this.roleBiz);
	}
	
	

}
