package org.cn.kkl.erp.entity;

import java.io.Serializable;

public class GoodsType implements Serializable {

	/**
	 * persistence
	 */
	private static final long serialVersionUID = 5776701490511665148L;
	
	private Long uuid;
	private String name;
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

}
