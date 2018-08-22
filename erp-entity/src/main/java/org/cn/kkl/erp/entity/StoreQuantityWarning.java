package org.cn.kkl.erp.entity;

import java.io.Serializable;

public class StoreQuantityWarning implements Serializable {

	/**
	 * need network transmit and version controller
	 */
	private static final long serialVersionUID = -181385961258825123L;
	
	private Long uuid;//goodsuuid
	private String name;//goodsname
	private Double storenum;//table storeDetail num
	private Double outnum;//need out store num
	
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getStorenum() {
		return storenum;
	}
	public void setStorenum(Double storenum) {
		this.storenum = storenum;
	}
	public Double getOutnum() {
		return outnum;
	}
	public void setOutnum(Double outnum) {
		this.outnum = outnum;
	}

}
