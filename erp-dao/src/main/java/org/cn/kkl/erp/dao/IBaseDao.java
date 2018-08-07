package org.cn.kkl.erp.dao;

import java.util.List;

public interface IBaseDao<T> {

	List<T> getList(T t1,T t2,Object param);

	List<T> getList(T t1, T t2, Object param, Integer firstResult, Integer maxResult);

	Long getTotalRecords(T t1, T t2, Object param);

	/**
	 * add 
	 * 
	 * @param t
	 */
	void add(T t);

	/**
	 * delete 
	 * @param uuid
	 */
	void delete(Long uuid);

	/**
	 * query information by id
	 * 
	 * @param uuid
	 * @return
	 */
	T get(Long uuid);

	/**
	 * update 
	 * 
	 * @param 
	 */
	void update(T t);
}
