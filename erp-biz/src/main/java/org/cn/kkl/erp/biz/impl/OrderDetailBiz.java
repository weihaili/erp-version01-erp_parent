package org.cn.kkl.erp.biz.impl;

import java.util.Date;
import java.util.List;

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
			StoreDetail existGoods = storeList.get(0);
			existGoods.setNum(existGoods.getNum()+orderDetail.getNum());
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
	

	
	
}
