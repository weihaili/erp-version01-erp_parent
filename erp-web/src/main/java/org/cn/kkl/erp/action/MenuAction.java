package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IMenuBiz;
import org.cn.kkl.erp.entity.Menu;

public class MenuAction extends BaseAction<Menu> {

	private IMenuBiz menuBiz;

	public void setMenuBiz(IMenuBiz menuBiz) {
		this.menuBiz = menuBiz;
		super.setBaseBiz(this.menuBiz);
	}
	
	
}
