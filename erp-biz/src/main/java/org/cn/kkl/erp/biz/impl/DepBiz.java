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

	/* 
	 * query department list
	 */
	public List<Dep> getDepList() {
		List<Dep> depList = depDao.getDepList();
		return depList;
	}

}
