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
import com.alibaba.fastjson.JSONObject;

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
	
	private Dep dep;
	
	public Dep getDep() {
		return dep;
	}

	public void setDep(Dep dep) {
		this.dep = dep;
	}

	/**
	 * add department
	 */
	public void add(){
		//front-point demand data style {"success":true,"message":""}
		try {
			depBiz.add(dep);
			ajaxReturn(true, "add "+dep.getName() + " success");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "add "+dep.getName() +" fail, please try again later");
		}
	}

	
	/***************************************************************************/
	private Long id;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * delete department by id 
	 */
	public void delete(){
		try {
			depBiz.delete(id);
			ajaxReturn(true, "delete department id is "+id+" success");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(true, "delete department id is "+id+" fail,please try again later");
		}
	}
	
	/**
	 * query department information by id
	 */
	public void get(){
		Dep dep3 = depBiz.get(id);
		String jsonString = JSON.toJSONString(dep3);
		write(mapData(jsonString,"dep"));
	}
	
	/**
	 * update department 
	 */
	public void update(){
		try {
			depBiz.update(dep);
			ajaxReturn(true, "update "+dep.getName()+" success");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "update the "+dep.getName()+" fail,please try again later");
		}
	}
	/***************************************************************************/

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
	 * @param jsonStr : json style string
	 * @param prefix : add prefix (like dep)
	 * @return
	 * example: jsonStr : {"name":"管理员组","tele":"000000","uuid":1}
	 * return : {"dep.name":"管理员组","dep.tele":"000000","dep.uuid":1}
	 */
	private String mapData(String jsonStr,String prefix){
		Map<String, Object> map=JSON.parseObject(jsonStr);
		Map<String, Object> dataMap=new HashMap<>();
		for (String key : map.keySet()) {
			dataMap.put(prefix+"."+key, map.get(key));
		}
		return JSON.toJSONString(dataMap);
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
