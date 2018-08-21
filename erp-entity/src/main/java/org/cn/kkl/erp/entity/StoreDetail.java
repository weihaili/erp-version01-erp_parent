package org.cn.kkl.erp.entity;

import java.io.Serializable;

public class StoreDetail implements Serializable {

	/**
	 * need network transmit and conversion control
	 */
	private static final long serialVersionUID = 5208283182600441273L;
	
	  private Long uuid;         //'编号'
	  private Long storeuuid;        //'仓库编号'
	  private String storename;
	  private Long goodsuuid;         //'商品编号'
	  private String goodsname;
	  public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	private Double num;         //'数量'
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
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
	  
	  

}
