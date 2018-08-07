package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IDepBiz;
import org.cn.kkl.erp.entity.Dep;

/**
 *department manager action
 */
public class DepAction extends BaseAction<Dep>{
	
	private IDepBiz depBiz;
	
	public void setDepBiz(IDepBiz depBiz) {
		this.depBiz = depBiz;
		super.setBaseBiz(this.depBiz);
	}
	
}
