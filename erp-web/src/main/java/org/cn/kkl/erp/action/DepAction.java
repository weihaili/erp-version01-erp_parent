package org.cn.kkl.erp.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.cn.kkl.erp.biz.IDepBiz;
import org.cn.kkl.erp.entity.Dep;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;

/**
 *department manager action
 */
public class DepAction {
	
	private IDepBiz depBiz;
	
	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
	}

	
	/**
	 * query depList 
	 */
	public void list(){
		List<Dep> depList = depBiz.getDepList();
		String jsonString = JSON.toJSONString(depList);
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(MediaType.TEXT_PLAIN_VALUE+";charset=utf-8");;
		try {
			response.getWriter().write(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}




}
