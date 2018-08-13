package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IEmpBiz;
import org.cn.kkl.erp.entity.Emp;

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
	
	

	
}
