package org.cn.kkl.erp.biz.impl;

import java.util.Date;
import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.cn.kkl.erp.biz.IOrderDetailBiz;
import org.cn.kkl.erp.dao.IOrderDetailDao;
import org.cn.kkl.erp.dao.IStoreDetailDao;
import org.cn.kkl.erp.dao.IStoreOperDao;
import org.cn.kkl.erp.entity.Order;
import org.cn.kkl.erp.entity.OrderDetail;
import org.cn.kkl.erp.entity.StoreDetail;
import org.cn.kkl.erp.entity.StoreOper;
import org.cn.kkl.erp.selfdifexception.ErpException;

public class OrderDetailBiz extends BaseBiz<OrderDetail> implements IOrderDetailBiz {

	private IOrderDetailDao orderDetailDao;
	private IStoreDetailDao storeDetailDao;
	private IStoreOperDao storeOperDao;

	public void setStoreDetailDao(IStoreDetailDao storeDetailDao) {
		this.storeDetailDao = storeDetailDao;
	}

	public void setStoreOperDao(IStoreOperDao storeOperDao) {
		this.storeOperDao = storeOperDao;
	}

	public void setOrderDetailDao(IOrderDetailDao orderDetailDao) {
		this.orderDetailDao = orderDetailDao;
		super.setBaseDao(this.orderDetailDao);
	}

	/**
	 * inStore operation 
	 * @param orderId
	 * @param empUuid
	 * @param warehouseId
	 * table orderDetail operation
	 *   1. get order detail
	 *   2. judgment orderDetail must be not in store
	 *   3. update orderDetail state and set ender enderTime set warehouseId
	 * table storeDetail operation
	 *   1. judgment if there is the same goods in warehouse,if there is increase this kinds of 
	 *   	goods quantity.if there is not , add the goods records 
	 * table storeOper operation
	 * 	 insert new records save operate process
	 * table order operation
	 * 	 confirm all orderDetail has been in store then update table order state warehousing
	 */
	@Override
	@RequiresPermissions("purchase order warehousing")
	public void doInstore(Long orderId, Long empUuid, Long warehouseId) {
		/**1. table orderDetail operation====================================*/
		OrderDetail orderDetail = orderDetailDao.get(orderId);
		//judgment orderDetail must be not in store
		if (orderDetail.getState()!=OrderDetail.STATE_NOT_IN) {
			throw new ErpException("dear can not inStore repeat");
		}
		Date date = new Date();
		orderDetail.setState(OrderDetail.STATE_IN);
		orderDetail.setEnder(empUuid);
		orderDetail.setEndtime(date);
		orderDetail.setStoreuuid(warehouseId);
		
		/**2. table storeDetail operation====================================*/
		StoreDetail storeDetail = new StoreDetail();
		storeDetail.setStoreuuid(warehouseId);
		storeDetail.setGoodsuuid(orderDetail.getGoodsuuid());
		List<StoreDetail> storeList = storeDetailDao.getList(storeDetail, null, null);
		if (null!=storeList && storeList.size()>0) {
			Double newQuantity=0d;
			Double existQuantity=0d;
			StoreDetail existGoods = storeList.get(0);
			if (null!=orderDetail.getNum()) {
				newQuantity=orderDetail.getNum();
			}
			if (null!=existGoods.getNum()) {
				existQuantity=existGoods.getNum();
			}
			existGoods.setNum(existQuantity+newQuantity);
		}else{
			storeDetail.setNum(orderDetail.getNum());
			storeDetailDao.add(storeDetail);
		}
		
		/**3. table storeOper operation++++++++++++++++++++++++++++++++++++*/
		StoreOper storeOper = new StoreOper();
		storeOper.setEmpuuid(empUuid);
		storeOper.setGoodsuuid(orderDetail.getGoodsuuid());
		storeOper.setNum(orderDetail.getNum());
		storeOper.setOpertime(date);
		storeOper.setStoreuuid(warehouseId);
		storeOper.setType(StoreOper.TYPE_IN_STORE);
		storeOperDao.add(storeOper);
		
		//**4. table order operation=========================================*/
		OrderDetail queryParam = new OrderDetail();
		queryParam.setState(OrderDetail.STATE_NOT_IN);
		Order order = orderDetail.getOrder();
		queryParam.setOrder(order);
		long records = orderDetailDao.getTotalRecords(queryParam, null, null);
		if (0==records) {
			order.setEnder(empUuid);
			order.setEndTime(date);
			order.setState(Order.STATE_END);
		}
	}

	/**
	 * outStorage operation
	 * @param orderId
	 * @param empUuid
	 * @param warehouseId
	 * @description 
	 *  operate table orderDetail
	 *     1. judgment sale order detail weather achieve out storage
	 *     2. update the sale order detail state out of state(1) out of storage date operator storageId
	 *     
	 *  operate table storeDetail
	 *     1.  pick out storeDetail by storeUuid
	 *     2.  judgment if the quantity of storage is enough.if enough then out of storage ,decrease the quantity set new quantity 
	 *     	   of this kind of goods.if not enough, throw new ErpException message is inventory shortage
	 *  
	 *   operate table storeOper
	 *         insert new operation record
	 *   
	 *   operate table oder
	 *   
	 */        
	@Override
	public void doOutStore(Long orderId, Long empUuid, Long warehouseId) {
		OrderDetail detail = orderDetailDao.get(orderId);
		if (OrderDetail.STATE_NOT_OUT!=detail.getState()) {
			throw new ErpException("dear the sale order detail already out of storage,can not operate repeatly");
		}
		Date date = new Date();
		detail.setState(OrderDetail.STATE_OUT);
		detail.setEnder(empUuid);
		detail.setEndtime(date);
		detail.setStoreuuid(warehouseId);
		
		/**********storeDetail******************************/
		Double saleQuantity=0d;
		if (null!=detail.getNum()) {
			saleQuantity=detail.getNum();
		}
		Double storageQuantity=0d;
		
		StoreDetail storeDetail = new StoreDetail();
		storeDetail.setGoodsuuid(detail.getGoodsuuid());
		storeDetail.setStoreuuid(warehouseId);
		List<StoreDetail> storeDetails  = storeDetailDao.getList(storeDetail, null, null);
		if (null!=storeDetails && storeDetails.size()>0) {
			storageQuantity=storeDetails.get(0).getNum();
			StoreDetail sd = storeDetails.get(0);
			Double newStorageQuantity=storageQuantity-saleQuantity;
			if (newStorageQuantity<0) {
				throw new ErpException("dear inventory stortage");
			}
			sd.setNum(newStorageQuantity);
		}else{
			throw new ErpException("dear inventory shortage");
		}
		
		StoreOper log = new StoreOper();
		log.setEmpuuid(empUuid);
		log.setGoodsuuid(detail.getGoodsuuid());
		log.setNum(detail.getNum());
		log.setOpertime(date);
		log.setStoreuuid(warehouseId);
		log.setType(StoreOper.TYPE_OUT_STORE);
		storeOperDao.add(log);
		
		
		OrderDetail orderDetail = new OrderDetail();
		Order order = detail.getOrder();
		orderDetail.setOrder(order);
		orderDetail.setState(OrderDetail.STATE_NOT_OUT);
		Long totalRecords = orderDetailDao.getTotalRecords(orderDetail, null, null);
		if (0==totalRecords) {
			order.setEndTime(date);
			order.setState(Order.STATE_OUT);
			order.setEnder(empUuid);
		}
		
	}
	

	
	
}
