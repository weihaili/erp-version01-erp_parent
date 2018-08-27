package org.cn.kkl.erp.biz;

import java.util.List;

import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.entity.Tree;

public interface IEmpBiz extends IBaseBiz<Emp> {
	
	Emp findByUsernameAndPwd(String username,String password);
	
	void updatePwd(Long uuid,String oldPwd,String newPwd);
	
	void updatePwd_reset(Long uuid,String newPwd);
	
	/**
	 * get employee roles
	 * @param empId
	 * @return
	 */
	List<Tree> readEmpRoles(Long empId);
	
	/**
	 * update employee role
	 * @param empId
	 * @param checkedRoleIds
	 */
	void updateEmpRoles(Long empId,String checkedRoleIds);

}
