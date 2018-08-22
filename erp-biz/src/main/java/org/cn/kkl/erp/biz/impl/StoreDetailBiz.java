package org.cn.kkl.erp.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.biz.IStoreDetailBiz;
import org.cn.kkl.erp.dao.IBaseDao;
import org.cn.kkl.erp.dao.IGoodsDao;
import org.cn.kkl.erp.dao.IStoreDao;
import org.cn.kkl.erp.dao.IStoreDetailDao;
import org.cn.kkl.erp.entity.Goods;
import org.cn.kkl.erp.entity.Store;
import org.cn.kkl.erp.entity.StoreDetail;
import org.cn.kkl.erp.entity.StoreQuantityWarning;
import org.cn.kkl.erp.util.HtmlUtil;
import org.cn.kkl.erp.util.MailUtil;

public class StoreDetailBiz extends BaseBiz<StoreDetail> implements IStoreDetailBiz {

	private IStoreDetailDao storeDetailDao;
	private IStoreDao storeDao;
	private IGoodsDao goodsDao;
	private MailUtil mailUtil;
	private HtmlUtil htmlUtil;

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
	
	public void setMailUtil(MailUtil mailUtil) {
		this.mailUtil = mailUtil;
	}

	public void setHtmlUtil(HtmlUtil htmlUtil) {
		this.htmlUtil = htmlUtil;
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
	 * inventory warning
	 * @return
	 */
	public List<StoreQuantityWarning> getStorealertList(Double quantity){
		return storeDetailDao.getStorealertList(quantity);
	}

	/**
	 * send store quantity warning  email 
	 */
	@Override
	public void sendStoreAlertMail() throws MessagingException {
		List<StoreQuantityWarning> storealertList = storeDetailDao.getStorealertList(100d);
		
		if (storealertList!=null && storealertList.size()>0) {
			List<String> titles=new ArrayList<>();
			titles.add("uuid");
			titles.add("name");
			titles.add("storenum");
			titles.add("outnum");
			StringBuilder content=new StringBuilder();
			for (StoreQuantityWarning storeQuantityWarning : storealertList) {
				content.append("<td>"+storeQuantityWarning.getUuid()+"</td>");
				content.append("<td>"+storeQuantityWarning.getName()+"</td>");
				content.append("<td>"+storeQuantityWarning.getStorenum()+"</td>");
				content.append("<td>"+storeQuantityWarning.getOutnum()+"</td>");
			}
			StringBuilder builder = htmlUtil.getStoreAlertHtml("store quantity warning",titles,content.toString());
			mailUtil.sendMail("kukulongGG@hotmail.com", "store quantity warning", builder.toString());
		}
	}
	
	
	
}
