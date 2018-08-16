package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.Date;

import com.alibaba.fastjson.annotation.JSONField;

public class OrderDetail implements Serializable {

	/**
	 * network transmit and version controller 
	 */
	private static final long serialVersionUID = -630843104464285072L;
	
	/********************************order state constant start*************************************/
	public static final char STATE_NOT_IN='0';  //not in stock
	public static final char STATE_IN='1';  //in stock
	/********************************order state end*************************************/

	private Long uuid;
	private Long goodsuuid;
	private String goodsname;
	private Double price;
	private Double num;
	private Double money;
	private Date endtime;
	private Long ender;
	private Long storeuuid;
	private char state; // '采购：0=未入库，1=已入库 销售：0=未出库，1=已出库',
	
	//if there is not configuration lead to stackOverFlowError
	@JSONField(serialize=false)
	private Order order; // order
	  
	public Order getOrder() {
		return order;
	}
	public void setOrder(Order order) {
		this.order = order;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getGoodsuuid() {
		return goodsuuid;
	}
	public void setGoodsuuid(Long goodsuuid) {
		this.goodsuuid = goodsuuid;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public Double getMoney() {
		return money;
	}
	public void setMoney(Double money) {
		this.money = money;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
	}
	public Long getEnder() {
		return ender;
	}
	public void setEnder(Long ender) {
		this.ender = ender;
	}
	public Long getStoreuuid() {
		return storeuuid;
	}
	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}
	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}

}
