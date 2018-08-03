package org.cn.kkl.erp.entity;

import java.io.Serializable;

public class Dep implements Serializable {

	private static final long serialVersionUID = 4932488308798498560L;
	
	private Long uuid;
	
	private String name;
	
	private String tele;

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

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	
	

}
