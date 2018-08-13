package org.cn.kkl.erp.action;


import org.cn.kkl.erp.biz.IMenuBiz;
import org.cn.kkl.erp.entity.Menu;

import com.alibaba.fastjson.JSON;

public class MenuAction extends BaseAction<Menu> {

	private IMenuBiz menuBiz;

	public void setMenuBiz(IMenuBiz menuBiz) {
		this.menuBiz = menuBiz;
		super.setBaseBiz(this.menuBiz);
	}
	
	/**
	 * get menu data
	 */
	public void getMenuTree(){
		Menu menu = menuBiz.get("0");
		write(JSON.toJSONString(menu,true));
	}
	
	
}
