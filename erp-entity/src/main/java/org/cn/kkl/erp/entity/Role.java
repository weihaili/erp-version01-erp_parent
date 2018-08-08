package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {

	/**
	 * persistence
	 */
	private static final long serialVersionUID = -7838158129712604264L;
	
	private Long uuid;
	private String name;
	private Date createTime;
	private Date updateTime;
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
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	
	

}
