package org.cn.kkl.erp.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.cn.kkl.erp.biz.IEmpBiz;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.dao.IRoleDao;
import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.entity.Role;
import org.cn.kkl.erp.entity.Tree;
import org.cn.kkl.erp.selfdifexception.ErpException;

public class EmpBiz extends BaseBiz<Emp> implements IEmpBiz {
	
	private Integer hashIterations=2;

	private IEmpDao empDao;
	
	private IRoleDao roleDao;

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao(this.empDao);
	}

	public void setHashIterations(Integer hashIterations) {
		this.hashIterations = hashIterations;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
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

	/**
	 * get employee roles
	 * @param empId
	 * @return
	 */
	@Override
	public List<Tree> readEmpRoles(Long empId) {
		List<Tree> trees = new ArrayList<>();
		Emp emp = empDao.get(empId);
		List<Role> roles = emp.getRoles();
		List<Role> allRoles = roleDao.getList(null, null, null);
		Tree t1=null;
		for (Role role : allRoles) {
			t1=new Tree();
			t1.setId(String.valueOf(role.getUuid()));
			t1.setText(role.getName());
			if (roles.contains(role)) {
				t1.setChecked(true);
			}
			trees.add(t1);
		}
		return trees;
	}

	/**
	 * update employee role
	 * @param empId
	 * @param checkedRoleIds
	 */
	@Override
	public void updateEmpRoles(Long empId, String checkedRoleIds) {
		if (null==empId || StringUtils.isBlank(checkedRoleIds)) {
			System.out.println("parameter is null,please check");
			return;
		}
		Emp emp = empDao.get(empId);
		if (null==emp) {
			System.out.println("the employee uuid does not exist");
			return;
		}
		emp.setRoles(new ArrayList<Role>());
		String[] roleIds = checkedRoleIds.split(",");
		for (String roleId : roleIds) {
			Role role = roleDao.get(Long.valueOf(roleId));
			emp.getRoles().add(role);
		}
	}

	
}
