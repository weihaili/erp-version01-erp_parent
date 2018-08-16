package org.cn.kkl.erp.biz.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cn.kkl.erp.biz.IOrderBiz;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.dao.IOrderDao;
import org.cn.kkl.erp.dao.ISupplierDao;
import org.cn.kkl.erp.entity.Order;
import org.cn.kkl.erp.entity.OrderDetail;
import org.cn.kkl.erp.selfdifexception.ErpException;

public class OrderBiz extends BaseBiz<Order> implements IOrderBiz {
	
	private IOrderDao orderDao;
	private IEmpDao empDao;
	private ISupplierDao supplierDao;

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
		super.setBaseDao(this.orderDao);
	}

	/* 
	 * add order
	 */
	@Override
	public void add(Order order) {
		Date date = new Date();
		order.setType(Order.TYPE_IN);
		order.setState(Order.STATE_CREATE);
		order.setCreateTime(date);
		
		double total=0;
		List<OrderDetail> orderDetails = order.getOrderDetails();
		for (OrderDetail orderDetail : orderDetails) {
			total+=orderDetail.getMoney();
			orderDetail.setState(OrderDetail.STATE_NOT_IN);
			orderDetail.setOrder(order);
		}
		order.setTotalMoney(total);
		orderDao.add(order);
	}

	/* 
	 * query order by page(condition)
	 */
	@Override
	public List<Order> getList(Order order1, Order order2, Object param, Integer firstResult, Integer maxResult) {
		//get order list after page
		List<Order> orderList = super.getList(order1, order2, param, firstResult, maxResult);
		//traversing get employee and supplier name only this in list
		//cache employee or supplier id and name use map(key is id value is name)
		//get employee name from cache 
		//if there is not  get employee name from dataBase .then add cache
		Map<Long, String> cacheEmpMap=new HashMap<>();//employee cache map
		Map<Long, String> cacheSCMap=new HashMap<>();//supplier and client cache map
		for (Order order : orderList) {
			order.setCreaterName(getEmpName(order.getCreater(), cacheEmpMap));
			order.setCheckerName(getEmpName(order.getChecker(), cacheEmpMap));
			order.setEnderName(getEmpName(order.getEnder(), cacheEmpMap));
			order.setStarterName(getEmpName(order.getStarter(), cacheEmpMap));
			order.setSupplierName(getSCName(order.getSupplierUuid(), cacheSCMap));
		}
		return orderList;
	}
	
	/* 
	 * order check
	 * update order state set checker and checkTime
	 */
	@Override
	public void doCheck(Long uuid, Long empUuid) {
		if (null==uuid) {
			return ;
		}
		Order order = orderDao.get(uuid);
		if (order.getState()!=Order.STATE_CREATE) {
			throw new ErpException("dear the order has been checked");
		}
		order.setChecker(empUuid);
		order.setCheckTime(new Date());
		order.setState(Order.STATE_CHECKED);
	}

	/* 
	 * order confirm
	 * update order state set starter and startTime
	 */
	@Override
	public void doStart(Long uuid, Long empUuid) {
		if (null==uuid) {
			return;
		}
		Order order = orderDao.get(uuid);
		if (order.getState()!=Order.STATE_CHECKED) {
			throw new ErpException("dear the order has been confirmed");
		}
		order.setStarter(empUuid);
		order.setStartTime(new Date());
		order.setState(Order.STATE_START);
	}

	/**
	 * get employee name
	 * @param uuid :employee uuid
	 * @param cacheMap :cache map
	 * @return people name
	 */
	private String getEmpName(Long uuid,Map<Long, String> cacheMap){
		if (null==uuid) {
			return null;
		}
		//get name from cache
		String name = cacheMap.get(uuid);
		//if there is not query database then add cache
		if (null==name) {
			name = empDao.get(uuid).getName();
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
	private String getSCName(Long uuid,Map<Long, String> cacheMap){
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
