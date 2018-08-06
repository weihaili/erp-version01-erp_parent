package org.cn.kkl.erp.dao;

import java.util.List;

import org.cn.kkl.erp.entity.Dep;

public interface IDepDao {
	
	List<Dep> getDepList(Integer page, Integer rows);
	
	List<Dep> getDepList(Dep dep1, Dep dep2, Object param, Integer page, Integer rows);
	
	Long getDepTotalRecords(Dep dep1);
	
	/**
	 * add new department
	 * @param dep
	 */
	void add(Dep dep);
	
	
	/**
	 * delete department by id
	 * @param uuid
	 */
	void delete(Long uuid);
	
	/**
	 * query department information by id
	 * @param uuid
	 * @return
	 */
	Dep get(Long uuid);
	
	/**
	 * update department
	 * @param dep
	 */
	void update(Dep dep);

}
