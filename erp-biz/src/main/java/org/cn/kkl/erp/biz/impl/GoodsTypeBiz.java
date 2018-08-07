package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IGoodsTypeBiz;
import org.cn.kkl.erp.dao.IGoodsTypeDao;
import org.cn.kkl.erp.entity.GoodsType;

public class GoodsTypeBiz extends BaseBiz<GoodsType> implements IGoodsTypeBiz {

	private IGoodsTypeDao goodsTypeDao;

	public void setGoodsTypeDao(IGoodsTypeDao goodsTypeDao) {
		this.goodsTypeDao = goodsTypeDao;
		super.setBaseDao(this.goodsTypeDao);
	}
	
	
}
