package org.cn.kkl.erp.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.cn.kkl.erp.biz.IEmpBiz;
import org.cn.kkl.erp.entity.Emp;

public class ErpRealm extends AuthorizingRealm {
	
	private IEmpBiz empBiz;
	

	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
		System.out.println("authorization admin");
		return null;
	}

	/**
	 * @param arg0
	 * @return null:represent authentication fail ,can not login.
	 * 		   AuthenticationInfo  instance :represent authentication success ,login success
	 * @throws AuthenticationException
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		System.out.println("authentication pass you can login success");
		UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken)token;
		
		String pwd=new String(usernamePasswordToken.getPassword());
		
		Emp emp = empBiz.findByUsernameAndPwd(usernamePasswordToken.getUsername(), pwd);
		
		if (null!=emp) {
			System.out.println(pwd);
			/**
			 * parameter1 : loginUser
			 * parameter2: authentication code
			 * parameter3: the realm name
			 */
			SimpleAuthenticationInfo info=new SimpleAuthenticationInfo(emp,pwd,getName());
			return info;
		}
		
		return null;
	}

}
