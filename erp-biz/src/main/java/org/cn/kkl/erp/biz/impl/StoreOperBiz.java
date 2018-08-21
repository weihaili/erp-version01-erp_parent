package org.cn.kkl.erp.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cn.kkl.erp.biz.IStoreOperBiz;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.dao.IGoodsDao;
import org.cn.kkl.erp.dao.IStoreDao;
import org.cn.kkl.erp.dao.IStoreOperDao;
import org.cn.kkl.erp.entity.StoreOper;

public class StoreOperBiz extends BaseBiz<StoreOper> implements IStoreOperBiz {
	
	private IStoreOperDao storeOperDao;
	
	private IStoreDao storeDao;
	private IGoodsDao goodsDao;
	private IEmpDao empDao;

	public void setStoreOperDao(IStoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
		super.setBaseDao(this.storeOperDao);
	}

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}
	/* 
	 * override query operate storage list by page
	 * @description:
	 *  1. page query operation list by condition 
	 *  2. set operation warehouse name ,operator`s name ,goods`s name by use cache
	 */
	@Override
	public List<StoreOper> getList(StoreOper t1, StoreOper t2, Object param, Integer firstResult, Integer maxResult) {
		List<StoreOper> list = super.getList(t1, t2, param, firstResult, maxResult);
		Map<Long, String> cacheEmpNameMap = new HashMap<Long, String>();
		Map<Long, String> cacheGoodsNameMap = new HashMap<Long, String>();
		Map<Long, String> cacheStoreNameMap = new HashMap<Long, String>();
		for (StoreOper storeOper : list) {
			storeOper.setEmpName(getEmpName(storeOper.getEmpuuid(), cacheEmpNameMap, empDao));
			storeOper.setGoodsName(getGoodsName(storeOper.getGoodsuuid(), cacheGoodsNameMap, goodsDao));
			storeOper.setStoreName(getStoreName(storeOper.getStoreuuid(), cacheStoreNameMap, storeDao));
		}
		return list;
	}
	
	

}
