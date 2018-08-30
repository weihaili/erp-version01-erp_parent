package org.cn.kkl.erp.utils;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;

public class KklResult implements Serializable {

	/**
	 * persistence
	 */
	private static final long serialVersionUID = -671251434405202058L;
	
	/** 
	 * ajax return value
	 * @param success
	 * @param message
	 */
	public void ajaxReturn(boolean success,String message,HttpServletResponse response) {
		Map<String, Object> rtn=new HashMap<>();
		rtn.put("success", success);
		rtn.put("message", message);
		write(JSON.toJSONString(rtn),response);
	}
	
	/**
	 * common method
	 * @param jsonStr need to write to return page string
	 */
	public void write(String  jsonStr,HttpServletResponse response){
		response.setContentType(MediaType.TEXT_PLAIN_VALUE+";charset=utf-8");;
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
