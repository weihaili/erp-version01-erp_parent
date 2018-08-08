package org.cn.kkl.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.cn.kkl.erp.biz.IEmpBiz;
import org.cn.kkl.erp.entity.Emp;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;
import com.opensymphony.xwork2.ActionContext;

public class LoginAction {
	
	private IEmpBiz empBiz;
	private String username;
	private String pwd;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public void setEmpBiz(IEmpBiz empBiz) {
		this.empBiz = empBiz;
	}
	
	/**
	 * user login verification
	 */
	public void checkUser(){
		try {
			Emp loginUser = empBiz.findByUsernameAndPwd( username,pwd);
			if (null!=loginUser) {
				ActionContext.getContext().getSession().put("loginUser", loginUser);
				ajaxReturn(true, "congratulations login success");
			}else {
				ajaxReturn(false, "username or password is incorrect,please check");
			}
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "the system is busy,please try again later");
		}
	}
	
	/**
	 * display login userName 
	 */
	public void showName(){
		//get loginUser currently
		Emp loginUser = (Emp) ActionContext.getContext().getSession().get("loginUser");
		if (null!=loginUser) {
			ajaxReturn(true, loginUser.getUsername());
		}else{
			ajaxReturn(false, "no operaton for a long time,login time out,please login again");
		}
	}
	
	/**
	 * logout  
	 */
	public void logout(){
		ActionContext.getContext().getSession().remove("loginUser");
	}
	
	
	/**
	 * common method
	 * @param jsonStr need to write to return page string
	 */
	private void write(String  jsonStr){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(MediaType.TEXT_PLAIN_VALUE+";charset=utf-8");;
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/** 
	 * ajax return value
	 * @param success
	 * @param message
	 */
	private void ajaxReturn(boolean success,String message) {
		Map<String, Object> rtn=new HashMap<>();
		rtn.put("success", success);
		rtn.put("message", message);
		write(JSON.toJSONString(rtn));
	}

}
