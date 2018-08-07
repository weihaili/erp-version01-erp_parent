package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IGoodsBiz;
import org.cn.kkl.erp.entity.Goods;

public class GoodsAction extends BaseAction<Goods> {
	
	private IGoodsBiz goodsBiz;

	public void setGoodsBiz(IGoodsBiz goodsBiz) {
		this.goodsBiz = goodsBiz;
		super.setBaseBiz(this.goodsBiz);
	}
	
	

}
