package org.cn.kkl.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	private Dep dep1;
	
	private Dep dep2;
	
	private Object param;
	
	public Dep getDep1() {
		return dep1;
	}

	public void setDep1(Dep dep1) {
		this.dep1 = dep1;
	}

	public Dep getDep2() {
		return dep2;
	}

	public void setDep2(Dep dep2) {
		this.dep2 = dep2;
	}

	public Object getParam() {
		return param;
	}

	public void setParam(Object param) {
		this.param = param;
	}

	private Integer page;
	
	private Integer rows;
	
	public Integer getPage() {
		return page;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public Integer getRows() {
		return rows;
	}

	public void setRows(Integer rows) {
		this.rows = rows;
	}


	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
	}

	/**
	 * query depList 
	 */
	public void list(){
		List<Dep> depList = depBiz.getDepList(page, rows);
		Long records = depBiz.getDepTotalRecords(null);
		Map<String, Object> map = new HashMap<>();
		map.put("total", records);
		map.put("rows", depList);
		String jsonString = JSON.toJSONString(map);
		write(jsonString);
	}
	
	/**
	 * query department list by condition
	 */
	public void listByCondition(){
		List<Dep> depList = depBiz.getDepList(dep1,dep2,param,page,rows);
		Long records = depBiz.getDepTotalRecords(dep1);
		Map<String, Object> result=new HashMap<>();
		result.put("total", records);
		result.put("rows", depList);
		String jsonString = JSON.toJSONString(result);
		write(jsonString);
	}

	/**
	 * commone method
	 * @param jsonStr need to write to return page string
	 */
	public void write(String  jsonStr){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(MediaType.TEXT_PLAIN_VALUE+";charset=utf-8");;
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
