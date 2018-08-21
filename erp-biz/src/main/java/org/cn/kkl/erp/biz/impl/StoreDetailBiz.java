package org.cn.kkl.erp.biz.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.biz.IStoreDetailBiz;
import org.cn.kkl.erp.dao.IBaseDao;
import org.cn.kkl.erp.dao.IGoodsDao;
import org.cn.kkl.erp.dao.IStoreDao;
import org.cn.kkl.erp.dao.IStoreDetailDao;
import org.cn.kkl.erp.entity.Goods;
import org.cn.kkl.erp.entity.Store;
import org.cn.kkl.erp.entity.StoreDetail;

public class StoreDetailBiz extends BaseBiz<StoreDetail> implements IStoreDetailBiz {

	private IStoreDetailDao storeDetailDao;
	private IStoreDao storeDao;
	private IGoodsDao goodsDao;

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setStoreDetailDao(IStoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
		super.setBaseDao(this.storeDetailDao);
	}
	
	/**
	 * page query
	 * @param sd1
	 * @param sd2
	 * @param param
	 * @param firstResult
	 * @param maxResult
	 * @return
	 */
	@Override
	public List<StoreDetail> getList(StoreDetail sd1,StoreDetail sd2,Object param,Integer firstResult,Integer maxResult){
		List<StoreDetail> list = super.getList(sd1, sd2, param, firstResult, maxResult);
		Map<Long, String> cacheGoodsIdAndName =new HashMap<>();
		Map<Long, String> cacheStoreIdAndName =new HashMap<>();
		for (StoreDetail storeDetail : list) {
			storeDetail.setGoodsname(getGoodsName(storeDetail.getGoodsuuid(), cacheGoodsIdAndName));
			storeDetail.setStorename(getStoreName(storeDetail.getStoreuuid(), cacheStoreIdAndName));
		}
		return list;
	}
	
	/**
	 * query goods name by id and add cache
	 * @param uuid
	 * @param cacheMap
	 * @return
	 */
	public String getGoodsName(Long uuid,Map<Long, String> cacheMap){
		if (null==uuid) {
			return null;
		}
		String name="";
		if (StringUtils.isNotBlank(cacheMap.get(uuid))) {
			name=cacheMap.get(uuid);
		}else{
			Goods goods = goodsDao.get(uuid);
			name=goods.getName();
			cacheMap.put(uuid, name);
		}
		return name;
	}
	
	/**
	 * query store name by id and add cache
	 * @param uuid
	 * @param cacheMap
	 * @return
	 */
	public String getStoreName(Long uuid,Map<Long, String> cacheMap){
		if (null==uuid) {
			return null;
		}
		String name="";
		if (StringUtils.isNotBlank(cacheMap.get(uuid))) {
			name=cacheMap.get(uuid);
		}else{
			Store store = storeDao.get(uuid);
			name=store.getName();
			cacheMap.put(uuid, name);
		}
		return name;
	}
	
	/**
	 * use reflect query 
	 * query name by id then create id and name cache
	 * @param uuid
	 * @param cacheMap
	 * @param dao
	 * @param methodName
	 * @return
	 */
	/*public String getName(Long uuid,Map<Long, String> cacheMap,IBaseDao<?> dao,String methodName){
		
	}*/
	
	
}
