package org.cn.kkl.erp.biz.impl;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.biz.IBaseBiz;
import org.cn.kkl.erp.dao.IBaseDao;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.dao.IGoodsDao;
import org.cn.kkl.erp.dao.IStoreDao;
import org.cn.kkl.erp.dao.ISupplierDao;
import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.entity.Goods;
import org.cn.kkl.erp.entity.Store;

public class BaseBiz<T> implements IBaseBiz<T> {

	private IBaseDao<T> baseDao;

	public void setBaseDao(IBaseDao<T> baseDao) {
		this.baseDao = baseDao;
	}
	
	@Override
	public List<T> getList(T t1,T t2,Object param) {
		
		return baseDao.getList(t1,t2, param);
	}

	@Override
	public List<T> getList(T t1, T t2, Object param, Integer firstResult, Integer maxResult) {
		
		return baseDao.getList(t1,t2,param,firstResult,maxResult);
	}

	@Override
	public Long getTotalRecords(T t1,T t2,Object param) {
		
		return baseDao.getTotalRecords(t1,t2,param);
	}

	@Override
	public void add(T t) {
		baseDao.add(t);
		
	}

	@Override
	public void delete(Long uuid) {
		baseDao.delete(uuid);
	}

	@Override
	public T get(Long uuid) {
		return baseDao.get(uuid);
	}
	
	@Override
	public T get(String uuid) {
		return baseDao.get(uuid);
	}

	@Override
	public void update(T t) {
		baseDao.update(t);
	}
	
	/**
	 * get goods name by uuid and add cache
	 * @param uuid
	 * @param cacheMap
	 * @param goodsDao
	 * @return
	 */
	public String getGoodsName(Long uuid,Map<Long, String> cacheMap,IGoodsDao goodsDao){
		if(null==uuid){
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
	 * get employee name by uuid and add cache
	 * @param uuid
	 * @param cacheMap
	 * @param empDao
	 * @return
	 */
	public String getEmpName(Long uuid,Map<Long, String> cacheMap,IEmpDao empDao){
		if (null==uuid) {
			return null;
		}
		String name ="";
		if (StringUtils.isNotBlank(cacheMap.get(uuid))) {
			name=cacheMap.get(uuid);
		}else{
			Emp emp = empDao.get(uuid);
			name=emp.getName();
			cacheMap.put(uuid, name);
		}
		return name;
	}
	
	/**
	 * get store name by uuid and add cache 
	 * @param uuid
	 * @param cacheMap
	 * @param storeDao
	 * @return
	 */
	public String getStoreName(Long uuid,Map<Long,String> cacheMap,IStoreDao storeDao){
		if (null==uuid) {
			return null;
		}
		String name="";
		if (StringUtils.isNotEmpty(cacheMap.get(uuid))) {
			name=cacheMap.get(uuid);
		}else{
			Store store = storeDao.get(uuid);
			name=store.getName();
			cacheMap.put(uuid, name);
		}
		return name;
	}
	
	/**
	 * get supplier  client name
	 * @param uuid :people uuid
	 * @param cacheMap :cache map
	 * @return people name
	 */
	public String getSupplierOrClentName(Long uuid,Map<Long, String> cacheMap,ISupplierDao supplierDao){
		if (null==uuid) {
			return null;
		}
		//get name from cache
		String name = cacheMap.get(uuid);
		//if there is not query database then add cache
		if (null==name) {
			name = supplierDao.get(uuid).getName();
			cacheMap.put(uuid, name);
		}
		return name;
	}
}

