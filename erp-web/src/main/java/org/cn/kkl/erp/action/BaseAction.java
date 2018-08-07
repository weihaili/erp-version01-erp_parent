package org.cn.kkl.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.cn.kkl.erp.biz.IBaseBiz;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class BaseAction<T> {
	
	private IBaseBiz<T> baseBiz;
	
	private T t1;
	
	private T t2;
	
	private Object param;
	
	public T getT1() {
		return t1;
	}

	public void setT1(T t1) {
		this.t1 = t1;
	}

	public T getT2() {
		return t2;
	}

	public void setT2(T t2) {
		this.t2 = t2;
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


	public void setBaseBiz(IBaseBiz<T> baseBiz) {
		this.baseBiz = baseBiz;
	}

	/**
	 * query by condition  
	 */
	public void list(){
		List<T> tList = baseBiz.getList(t1,t2, param);
		String jsonString = JSON.toJSONString(tList,SerializerFeature.DisableCircularReferenceDetect);
		write(jsonString);
	}
	
	/**
	 * query tartment list by condition
	 */
	public void listByCondition(){
		int firstResult=(page-1)*rows;
		List<T> tList = baseBiz.getList(t1,t2,param,firstResult,rows);
		Long records = baseBiz.getTotalRecords(t1,t2,param);
		Map<String, Object> result=new HashMap<>();
		result.put("total", records);
		result.put("rows", tList);
		//DisableCircularReferenceDetect <==>forbidden use circular reference detect
		String jsonString = JSON.toJSONString(result, SerializerFeature.DisableCircularReferenceDetect);
		write(jsonString);
	}
	
	private T t;
	
	public T getT() {
		return t;
	}

	public void setT(T t) {
		this.t = t;
	}

	/**
	 * add tartment
	 */
	public void add(){
		//front-point demand data style {"success":true,"message":""}
		try {
			baseBiz.add(t);
			ajaxReturn(true, "add success");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "add fail, please try again later");
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
	 * delete tartment by id 
	 */
	public void delete(){
		try {
			baseBiz.delete(id);
			ajaxReturn(true, "delete tartment id is "+id+" success");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(true, "delete tartment id is "+id+" fail,please try again later");
		}
	}
	
	/**
	 * query tartment information by id
	 */
	public void get(){
		T t3 = baseBiz.get(id);
		String jsonString = JSON.toJSONStringWithDateFormat(t3,  "yyyy-MM-dd");
		write(mapData(jsonString,"t"));
	}
	
	/**
	 * update tartment 
	 */
	public void update(){
		try {
			baseBiz.update(t);
			ajaxReturn(true, "update success");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "update the fail,please try again later");
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
	 * @param prefix : add prefix (like t)
	 * @return
	 * example: jsonStr : {"name":"管理员组","tele":"000000","uuid":1}
	 * return : {"t.name":"管理员组","t.tele":"000000","t.uuid":1}
	 */
	private String mapData(String jsonStr,String prefix){
		Map<String, Object> map=JSON.parseObject(jsonStr);
		Map<String, Object> dataMap=new HashMap<>();
		for (String key : map.keySet()) {
			if (map.get(key) instanceof Map) {
				Map<String, Object> map2 = (Map<String, Object>) map.get(key);
				for (String key2 : map2.keySet()) {
					dataMap.put(prefix+"."+key+"."+key2,  map2.get(key2));
				}
			}else{
				dataMap.put(prefix+"."+key, map.get(key));
			}
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
