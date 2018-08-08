package org.cn.kkl.erp.biz;

import org.cn.kkl.erp.entity.Emp;

public interface IEmpBiz extends IBaseBiz<Emp> {
	
	Emp findByUsernameAndPwd(String username,String password);

}
