package org.cn.kkl.erp.biz;

import java.util.List;


public interface IBaseBiz<T> {

	List<T> getList(T t1,T t2,Object param);
	
	List<T> getList(T t1, T t2, Object param, Integer firstResult, Integer maxResult);
	
	Long getTotalRecords(T t1,T t2,Object param);
	
	void add(T t);
	
	void delete(Long uuid);
	
	T get(Long uuid);
	
	T get(String uuid);
	
	void update(T t);

}
