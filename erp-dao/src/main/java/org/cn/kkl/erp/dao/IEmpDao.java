package org.cn.kkl.erp.dao;

import org.cn.kkl.erp.entity.Emp;

public interface IEmpDao extends IBaseDao<Emp> {
	
	Emp findByUsernameAndPwd(String username,String password);

}
