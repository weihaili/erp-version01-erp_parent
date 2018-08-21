package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

public class Order implements Serializable {

	/**
	 * persistence and version controller
	 */
	private static final long serialVersionUID = -3075189372193294889L;
	
	/********************************order state constant start*************************************/
	public static final char STATE_CREATE='0';  //unCheck
	public static final char STATE_CHECKED='1';  //checked
	public static final char STATE_START='2';    //confirmed
	public static final char STATE_END='3';      //wareHousing
	/********************************order state end*************************************/
	
	/********************************order type constant start**************************************/
	public static final char TYPE_IN='1';  //purchase
	public static final char TYPE_OUT='2'; //sale
	
	public static final char STATE_NOT_OUT='0';    //confirmed
	public static final char STATE_OUT='1';      //wareHousing
	/********************************order type end**************************************/
	
	private Long uuid;
	private Date createTime;
	private Date checkTime;
	private Date startTime;
	private Date endTime;
	private char type;//1 represent purchase, 2 represent sale
	private Long creater;
	private String createrName;//orderorName
	private Long checker;
	private String checkerName;
	private Long starter;
	private String starterName;//buyer name
	private Long ender;
	private String enderName;//librarian name
	private Long supplierUuid;
	private String supplierName;//supplier name or client name
	private Double totalMoney;
	public String getCreaterName() {
		return createrName;
	}
	public void setCreaterName(String createrName) {
		this.createrName = createrName;
	}
	public String getCheckerName() {
		return checkerName;
	}
	public void setCheckerName(String checkerName) {
		this.checkerName = checkerName;
	}
	public String getStarterName() {
		return starterName;
	}
	public void setStarterName(String starterName) {
		this.starterName = starterName;
	}
	public String getEnderName() {
		return enderName;
	}
	public void setEnderName(String enderName) {
		this.enderName = enderName;
	}
	public String getSupplierName() {
		return supplierName;
	}
	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}
	//purchase state 0 represent unReviewed(unCheck) 1.represent reviewed 2. represent confirmed 3 represent wareHousing
	//sale state  a: represent not out of stock b.out of stock
	private char state;
	private Long wayBillsn;
	
	private List<OrderDetail> orderDetails;   //order detail
	
	public List<OrderDetail> getOrderDetails() {
		return orderDetails;
	}
	public void setOrderDetails(List<OrderDetail> orderDetails) {
		this.orderDetails = orderDetails;
	}
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
