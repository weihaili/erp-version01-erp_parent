package org.cn.kkl.erp.biz.impl;

import java.util.List;

import org.cn.kkl.erp.biz.IDepBiz;
import org.cn.kkl.erp.dao.IDepDao;
import org.cn.kkl.erp.entity.Dep;

public class DepBiz implements IDepBiz {
	
	private IDepDao depDao;

	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
	}
	
	@Override
	public List<Dep> getDepList(Integer page, Integer rows) {
		
		return depDao.getDepList(page, rows);
	}

	@Override
	public List<Dep> getDepList(Dep dep1, Dep dep2, Object param, Integer page, Integer rows) {
		
		return depDao.getDepList(dep1,dep2,param,page,rows);
	}

	@Override
	public Long getDepTotalRecords(Dep dep1) {
		
		return depDao.getDepTotalRecords(dep1);
	}

	@Override
	public void add(Dep dep) {
		depDao.add(dep);
		
	}

	@Override
	public void delete(Long uuid) {
		depDao.delete(uuid);
	}

	@Override
	public Dep get(Long uuid) {
		return depDao.get(uuid);
	}

	@Override
	public void update(Dep dep) {
		depDao.update(dep);
	}


}
