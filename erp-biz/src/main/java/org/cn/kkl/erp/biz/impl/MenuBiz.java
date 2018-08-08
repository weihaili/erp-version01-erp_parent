package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IMenuBiz;
import org.cn.kkl.erp.dao.IMenuDao;
import org.cn.kkl.erp.entity.Menu;

public class MenuBiz extends BaseBiz<Menu> implements IMenuBiz {
	
	private IMenuDao menuDao;

	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao(this.menuDao);
	}

	

}
