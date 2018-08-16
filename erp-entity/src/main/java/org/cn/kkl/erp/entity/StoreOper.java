package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.Date;

public class StoreOper implements Serializable {

	/**
	 * need network transmit and version control
	 */
	private static final long serialVersionUID = -1190660325215047146L;
	
	public static final char TYPE_IN_STORE='1';  //inStore
	
	public static final char TYPE_OUT_STORE='2';  // outStore
	
	
	  private Long uuid;              //'编号'
	  private Long empuuid;              //'操作员工编号'
	  private Date opertime;              //'操作日期'
	  private Long storeuuid;              //'仓库编号'
	  private Long goodsuuid;              //'商品编号'
	  private Double num;              //'数量'
	  private char type;              //'1：入库 2：出库'
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getEmpuuid() {
		return empuuid;
	}
	public void setEmpuuid(Long empuuid) {
		this.empuuid = empuuid;
	}
	public Date getOpertime() {
		return opertime;
	}
	public void setOpertime(Date opertime) {
		this.opertime = opertime;
	}
	public Long getStoreuuid() {
		return storeuuid;
	}
	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}
	public Long getGoodsuuid() {
		return goodsuuid;
	}
	public void setGoodsuuid(Long goodsuuid) {
		this.goodsuuid = goodsuuid;
	}
	public Double getNum() {
		return num;
	}
	public void setNum(Double num) {
		this.num = num;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	}

}
