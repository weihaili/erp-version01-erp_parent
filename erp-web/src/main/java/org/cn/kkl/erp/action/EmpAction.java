package org.cn.kkl.erp.action;

import java.util.List;

import org.cn.kkl.erp.biz.IEmpBiz;
import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.entity.Tree;

import com.alibaba.fastjson.JSON;

public class EmpAction extends BaseAction<Emp>{
	
	private IEmpBiz empBiz;

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
		super.setBaseBiz(this.empBiz);
	}
	
	private String oldPwd;
	private String newPwd;
	public String getOldPwd() {
		return oldPwd;
	}
	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}
	public String getNewPwd() {
		return newPwd;
	}
	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}
	
	/**
	 * modify password
	 */
	public void updatePwd(){
		Emp loginEmp = getLoginUser();
		if (null!=loginEmp) {
			try {
				empBiz.updatePwd(loginEmp.getUuid(), oldPwd, newPwd);
				ajaxReturn(true, newPwd);
			} catch (Exception e) {
				e.printStackTrace();
				ajaxReturn(false, "modify password fail,please check");
			}
			
		}else{
			ajaxReturn(false, "no operation for a long time,so login time out,please login again ");
			return ;
		}
	}
	
	/**
	 * administrator reset password
	 */
	public void updatePwd_reset(){
		try {
			empBiz.updatePwd_reset(getId(), newPwd);
			ajaxReturn(true, "reset password successful,the new password is "+newPwd);
		} catch (Exception e) {
			ajaxReturn(false, "reset password fail,please try again later");
			e.printStackTrace();
		}
	}
	
	/**
	 * get employee role information
	 */
	public void readEmpRoles(){
		List<Tree> roles = empBiz.readEmpRoles(getId());
		write(JSON.toJSONString(roles));
	}
	
	private String checkedEmpRoleIds;
	
	public String getCheckedEmpRoleIds() {
		return checkedEmpRoleIds;
	}
	public void setCheckedEmpRoleIds(String checkedEmpRoleIds) {
		this.checkedEmpRoleIds = checkedEmpRoleIds;
	}
	/**
	 * update employee role information
	 */
	public void updateEmpRoles(){
		try {
			empBiz.updateEmpRoles(getId(), checkedEmpRoleIds);
			ajaxReturn(true, "update employee role success");
		} catch (Exception e) {
			ajaxReturn(false, "update employee role fail ,please try again later");
			e.printStackTrace();
		}
	}

	
}
