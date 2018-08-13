package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.Date;

public class Order implements Serializable {

	/**
	 * persistence and version controller
	 */
	private static final long serialVersionUID = -3075189372193294889L;
	
	private Long uuid;
	private Date createTime;
	private Date checkTime;
	private Date startTime;
	private Date endTime;
	private char type;//1 represent purchase, 2 represent sale
	private Long creater;
	private Long checker;
	private Long starter;
	private Long ender;
	private Long supplierUuid;
	private Double totalMoney;
	//purchase state 0 represent unReviewed(unCheck) 1.represent reviewed 2. represent confirmed 3 represent wareHousing
	//sale state  a: represent not out of stock b.out of stock
	private char state;
	private Long wayBillsn;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getCheckTime() {
		return checkTime;
	}
	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}
	public Long getCreater() {
		return creater;
	}
	public void setCreater(Long creater) {
		this.creater = creater;
	}
	public Long getChecker() {
		return checker;
	}
	public void setChecker(Long checker) {
		this.checker = checker;
	}
	public Long getStarter() {
		return starter;
	}
	public void setStarter(Long starter) {
		this.starter = starter;
	}
	public Long getEnder() {
		return ender;
	}
	public void setEnder(Long ender) {
		this.ender = ender;
	}
	public Long getSupplierUuid() {
		return supplierUuid;
	}
	public void setSupplierUuid(Long supplierUuid) {
		this.supplierUuid = supplierUuid;
	}
	public Double getTotalMoney() {
		return totalMoney;
	}
	public void setTotalMoney(Double totalMoney) {
		this.totalMoney = totalMoney;
	}
	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}
	public Long getWayBillsn() {
		return wayBillsn;
	}
	public void setWayBillsn(Long wayBillsn) {
		this.wayBillsn = wayBillsn;
	}
	
	
	

}
