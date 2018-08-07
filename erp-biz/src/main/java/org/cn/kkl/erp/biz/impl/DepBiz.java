package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IDepBiz;
import org.cn.kkl.erp.dao.IDepDao;
import org.cn.kkl.erp.entity.Dep;

public class DepBiz extends BaseBiz<Dep> implements IDepBiz {
	
	private IDepDao depDao;

	public void setDepDao(IDepDao depDao) {
		this.depDao = depDao;
		super.setBaseDao(this.depDao);
	}
	


}
