package org.cn.kkl.erp.action;

import java.util.List;

import org.cn.kkl.erp.biz.IOrderBiz;
import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.entity.Order;
import org.cn.kkl.erp.entity.OrderDetail;
import org.cn.kkl.erp.selfdifexception.ErpException;

import com.alibaba.fastjson.JSON;

public class OrderAction extends BaseAction<Order> {
	
	private IOrderBiz orderBiz;
	
	//used receive orderDetail json style string which is array and is orderDetail every element
	private String json;   

	public void setOrderBiz(IOrderBiz orderBiz) {
		this.orderBiz = orderBiz;
		super.setBaseBiz(this.orderBiz);
	}

	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	/* 
	 * add order
	 */
	@Override
	public void add() {
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			//user not login or session expired
			ajaxReturn(false, "dear please login first ");
			return;
		}
		try {
			Order order = getT();
			order.setCreater(loginUser.getUuid());//set order creater
			List<OrderDetail> details = JSON.parseArray(json, OrderDetail.class);
			order.setOrderDetails(details); //set order detail
			orderBiz.add(order);
			ajaxReturn(true, "add order successful");
		} catch (Exception e) {
			e.printStackTrace();
			ajaxReturn(false, "server is busy,please try again later");
		}
	}
	
	/**
	 * purchase order checked
	 */
	public void doCheck(){
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			ajaxReturn(false, "dear please login first");
			return ;
		}
		Long orderId = getId();
		if (null==orderId) {
			ajaxReturn(false, "dear no data submitted,please check");
			return ;
		}
		try {
			orderBiz.doCheck(orderId, loginUser.getUuid());
			ajaxReturn(true, "check pass,keep it up");
		}catch (ErpException e) {
			ajaxReturn(false, "order has been checked please do not operation repeat");
			e.printStackTrace();
		} catch (Exception e) {
			ajaxReturn(false, "system busy,please try again later");
			e.printStackTrace();
		}
	}
	
	/**
	 * purchase order confirm
	 */
	public void doStart(){
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			ajaxReturn(false, "dear please login first");
			return ;
		}
		Long orderId = getId();
		if (null==orderId) {
			ajaxReturn(false, "dear no data submitted,please check");
			return ;
		}
		try {
			orderBiz.doStart(orderId, loginUser.getUuid());
			ajaxReturn(true, "check pass,keep it up");
		}catch (ErpException e) {
			ajaxReturn(false, "order has been confirmed please do not operation repeat");
			e.printStackTrace();
		} catch (Exception e) {
			ajaxReturn(false, "system busy,please try again later");
			e.printStackTrace();
		}
	}
	
	/**
	 * my purchase orders
	 */
	public void myListByPage(){
		if(null==getT1()){
			setT1(new Order());
		}
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			ajaxReturn(false, "dear please login first");
			return ;
		}
		getT1().setCreater(loginUser.getUuid());
		super.listByCondition();
	}
	
	
}
