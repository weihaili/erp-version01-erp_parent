package org.cn.kkl.erp.biz;

import org.cn.kkl.erp.entity.Emp;

public interface IEmpBiz extends IBaseBiz<Emp> {
	
	Emp findByUsernameAndPwd(String username,String password);
	
	void updatePwd(Long uuid,String oldPwd,String newPwd);
	
	void updatePwd_reset(Long uuid,String newPwd);

}
