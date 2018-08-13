package org.cn.kkl.erp.biz.impl;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.cn.kkl.erp.biz.IEmpBiz;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.selfdifexception.ErpException;

public class EmpBiz extends BaseBiz<Emp> implements IEmpBiz {
	
	private Integer hashIterations=2;

	private IEmpDao empDao;

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(this.empDao);
	}

	@Override
	public Emp findByUsernameAndPwd(String username, String password) {
		password=shiroMd5Encryption(password, username);
		return empDao.findByUsernameAndPwd(username, password);
	}

	/* 
	 * encryption password then add
	 */
	@Override
	public void add(Emp emp) {
		emp.setPwd(shiroMd5Encryption(emp.getPwd(), emp.getUsername()));
		super.add(emp);
	}

	/* 
	 * update password by uuid
	 */
	@Override
	public void updatePwd(Long uuid, String oldPwd, String newPwd) {
		Emp emp = empDao.get(uuid);
		String originalDbPwd = shiroMd5Encryption(oldPwd, emp.getUsername());
		if (!originalDbPwd.equals(emp.getPwd())) {
			//self dinfination exception
			throw new ErpException("original password entered is incorrect");
		}
		empDao.updatePwd(uuid, shiroMd5Encryption(newPwd, emp.getUsername()));
		
	}
	
	/**
	 * administrator reset password
	 */
	public void updatePwd_reset(Long uuid,String newPwd){
		Emp emp = empDao.get(uuid);
		empDao.updatePwd(uuid, shiroMd5Encryption(newPwd, emp.getUsername()));
	}
	
	/* 
	 * update employee information
	 */
	@Override
	public void update(Emp emp) {
		emp.setPwd(shiroMd5Encryption(emp.getPwd(), emp.getUsername()));
		super.update(emp);
	}

	/**
	 * shiro md5 encryption
	 * @param emp
	 * @param password
	 * @return
	 */
	private String shiroMd5Encryption( String param,String salt) {
		//the Md5Hash has three parameters
		//1. source : represent need encryption string
		//2. salt: represent scramble code
		//3. hashIterations :represent encryption times or number of operations or number of hash
		Md5Hash encryptoion=new Md5Hash(param,salt,hashIterations);
		return encryptoion.toString();
	}

}
