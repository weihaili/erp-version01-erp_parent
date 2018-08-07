package org.cn.kkl.erp.biz.impl;

import org.cn.kkl.erp.biz.IGoodsBiz;
import org.cn.kkl.erp.dao.IGoodsDao;
import org.cn.kkl.erp.entity.Goods;

public class GoodsBiz extends BaseBiz<Goods> implements IGoodsBiz {

	private IGoodsDao goodsDao;

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
		super.setBaseDao(this.goodsDao);
	}
	
	
}
