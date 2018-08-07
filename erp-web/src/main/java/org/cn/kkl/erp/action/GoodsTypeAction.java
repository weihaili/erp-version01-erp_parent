package org.cn.kkl.erp.action;

import org.cn.kkl.erp.biz.IGoodsTypeBiz;
import org.cn.kkl.erp.entity.GoodsType;

public class GoodsTypeAction extends BaseAction<GoodsType> {
	
	private IGoodsTypeBiz goodsTypeBiz;

	public void setGoodsTypeBiz(IGoodsTypeBiz goodsTypeBiz) {
		this.goodsTypeBiz = goodsTypeBiz;
		super.setBaseBiz(this.goodsTypeBiz);
	}
	

}
