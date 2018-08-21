package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IStoreBiz;
import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.entity.Store;

public class StoreAction extends BaseAction<Store> {
	
	private IStoreBiz storeBiz;

	public void setStoreBiz(IStoreBiz storeBiz) {
		this.storeBiz = storeBiz;
		super.setBaseBiz(this.storeBiz);
	}
	
	public void myList(){
		Store store = getT1();
		if (null==store) {
			store=new Store();
			setT1(store);
		}
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			ajaxReturn(false, "dear please login first");
			return ;
		}
		store.setEmpuuid(loginUser.getUuid());
		super.list();
	}
	
	
}
