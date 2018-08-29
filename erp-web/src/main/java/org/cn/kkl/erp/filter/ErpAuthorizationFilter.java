package org.cn.kkl.erp.filter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class ErpAuthorizationFilter extends AuthorizationFilter {

	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue)
			throws Exception {
		boolean isPermitted = false;
		
		//get subject
		Subject subject = getSubject(request, response);
		//get permission list in configuration
		String[] permissions = (String[]) mappedValue;
		//if permission list is null ,or do not set permission in configuration directly pass
		if (null== permissions || permissions.length == 0) {
			isPermitted = true;
		}
		if (null!=permissions && permissions.length>0) {
			//permission check
			for (String p : permissions) {
				//pass at least has a kind of permission
				if (subject.isPermitted(p)) {
					isPermitted = true;
				}
			} 
		}
		return isPermitted;
	}
}
