package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.Date;

public class ReturnOrder implements Serializable {

	/**
	 * network transmit and version controller
	 */
	private static final long serialVersionUID = 3335865331666416035L;

	  private Long uuid        ;        //'编号'
	  private Date createtime  ;        //'生成日期'
	  private Date checktime   ;        //'检查日期'
	  private Date endtime     ;        //'结束日期'
	  private char type        ;        //'订单类型'
	  private Long creater     ;        //'下单员'
	  private Long checker     ;        //'审核员工编号'
	  private Long ender       ;        //'库管员'
	  private Long supplieruuid;        //供应商及客户编号'
	  private Double totalmoney  ;        //'合计金额'
	  private char state       ;        //'订单状态'
	  private Long waybillsn   ;        //'运单号'
	  private Long ordersuuid  ;        //'原订单编号'
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Date getCreatetime() {
		return createtime;
	}
	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}
	public Date getChecktime() {
		return checktime;
	}
	public void setChecktime(Date checktime) {
		this.checktime = checktime;
	}
	public Date getEndtime() {
		return endtime;
	}
	public void setEndtime(Date endtime) {
		this.endtime = endtime;
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
	public Long getEnder() {
		return ender;
	}
	public void setEnder(Long ender) {
		this.ender = ender;
	}
	public Long getSupplieruuid() {
		return supplieruuid;
	}
	public void setSupplieruuid(Long supplieruuid) {
		this.supplieruuid = supplieruuid;
	}
	public Double getTotalmoney() {
		return totalmoney;
	}
	public void setTotalmoney(Double totalmoney) {
		this.totalmoney = totalmoney;
	}
	public char getState() {
		return state;
	}
	public void setState(char state) {
		this.state = state;
	}
	public Long getWaybillsn() {
		return waybillsn;
	}
	public void setWaybillsn(Long waybillsn) {
		this.waybillsn = waybillsn;
	}
	public Long getOrdersuuid() {
		return ordersuuid;
	}
	public void setOrdersuuid(Long ordersuuid) {
		this.ordersuuid = ordersuuid;
	}
	  
	  
}
