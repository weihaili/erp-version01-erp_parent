package org.cn.kkl.erp.action;

import java.util.List;

import org.cn.kkl.erp.biz.IRoleBiz;
import org.cn.kkl.erp.entity.Role;
import org.cn.kkl.erp.entity.Tree;

import com.alibaba.fastjson.JSON;

public class RoleAction extends BaseAction<Role> {
	
	private IRoleBiz roleBiz;

	public void setRoleBiz(IRoleBiz roleBiz) {
		this.roleBiz = roleBiz;
		super.setBaseBiz(this.roleBiz);
	}
	
	public void readRoleMenus(){
		List<Tree> roleMenus = roleBiz.readRoleMenus(getId());
		write(JSON.toJSONString(roleMenus));
	}
	
	private String checkedMenuIds;
	
	public String getCheckedMenuIds() {
		return checkedMenuIds;
	}

	public void setCheckedMenuIds(String checkedMenuIds) {
		this.checkedMenuIds = checkedMenuIds;
	}

	public void updateRoleMenus(){
		try {
			roleBiz.updateRoleMenus(getId(), checkedMenuIds);
			ajaxReturn(true, "update success");
		} catch (Exception e) {
			ajaxReturn(false, "update fail,please try again later");
			e.printStackTrace();
		}
	}

}
