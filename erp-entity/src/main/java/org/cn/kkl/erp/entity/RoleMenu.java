package org.cn.kkl.erp.entity;

import java.io.Serializable;

public class RoleMenu implements Serializable {

	/**
	 * need network transmit and version control
	 */
	private static final long serialVersionUID = 6857495537480444382L;
	
	private Long uuid;
	private Long roleuuid;
	private String menuuuid;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getRoleuuid() {
		return roleuuid;
	}
	public void setRoleuuid(Long roleuuid) {
		this.roleuuid = roleuuid;
	}
	public String getMenuuuid() {
		return menuuuid;
	}
	public void setMenuuuid(String menuuuid) {
		this.menuuuid = menuuuid;
	}
	
	

}
