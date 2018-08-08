package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.Date;

public class Inventory implements Serializable {

	/**
	 * persistence
	 */
	private static final long serialVersionUID = 2618024174701398560L;
	
	private Long uuid;
	
	private Long goodsUuid;
	
	private Long storeUuid;
	
	private Double num;
	
	private char type;
	
	private Date createTime;
	
	private Date checkTime;
	
	private Long creater;
	
	private Long checker;
	
	private char state;
	
	private String remark;

	public Long getUuid() {
		return uuid;
	}

	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}

	public Long getGoodsUuid() {
		return goodsUuid;
	}

	public void setGoodsUuid(Long goodsUuid) {
		this.goodsUuid = goodsUuid;
	}

	public Long getStoreUuid() {
		return storeUuid;
	}

	public void setStoreUuid(Long storeUuid) {
		this.storeUuid = storeUuid;
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

	public char getState() {
		return state;
	}

	public void setState(char state) {
		this.state = state;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	

}
