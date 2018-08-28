package org.cn.kkl.erp.action;


import java.util.List;

import org.cn.kkl.erp.biz.IMenuBiz;
import org.cn.kkl.erp.entity.Emp;
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
		//Menu menu = menuBiz.get("0");
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			ajaxReturn(false, "please first login");
		}
		Menu menu = menuBiz.readMenusByEmpuuid(loginUser.getUuid());
		write(JSON.toJSONString(menu,true));
	}
	
	/**
	 * query employee permission(menu) by employee uuid
	 * @param empuuid
	 * @return
	 */
	public void getMenusByEmpuuid(){
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			ajaxReturn(false, "please first login");
		}
		List<Menu> menusByEmpuuid = menuBiz.getMenusByEmpuuid(loginUser.getUuid());
		write(JSON.toJSONString(menusByEmpuuid));
	}
	
}
