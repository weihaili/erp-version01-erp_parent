package org.cn.kkl.erp.biz;

import java.util.List;

import org.cn.kkl.erp.entity.Dep;

public interface IDepBiz {
	
	List<Dep> getDepList(Integer page, Integer rows);
	
	List<Dep> getDepList(Dep dep1, Dep dep2, Object param, Integer page, Integer rows);
	
	Long getDepTotalRecords(Dep dep1);
	
	void add(Dep dep);
	
	void delete(Long uuid);
	
	Dep get(Long uuid);
	
	void update(Dep dep);

}
