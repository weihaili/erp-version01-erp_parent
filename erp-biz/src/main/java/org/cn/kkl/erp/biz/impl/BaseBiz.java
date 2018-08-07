package org.cn.kkl.erp.biz.impl;

import java.util.List;

import org.cn.kkl.erp.biz.IBaseBiz;
import org.cn.kkl.erp.dao.IBaseDao;

public class BaseBiz<T> implements IBaseBiz<T> {

	private IBaseDao<T> baseDao;

	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public List<T> getList(T t1,T t2,Object param) {
		
		return baseDao.getList(t1,t2, param);
	}

	@Override
	public List<T> getList(T t1, T t2, Object param, Integer firstResult, Integer maxResult) {
		
		return baseDao.getList(t1,t2,param,firstResult,maxResult);
	}

	@Override
	public Long getTotalRecords(T t1,T t2,Object param) {
		
		return baseDao.getTotalRecords(t1,t2,param);
	}

	@Override
	public void add(T t) {
		baseDao.add(t);
		
	}

	@Override
	public void delete(Long uuid) {
		baseDao.delete(uuid);
	}

	@Override
	public T get(Long uuid) {
		return baseDao.get(uuid);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}
}
