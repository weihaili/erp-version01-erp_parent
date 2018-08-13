package org.cn.kkl.erp.entity;

import java.io.Serializable;


public class EmpRole implements Serializable {

	/**
	 * persistence
	 */
	private static final long serialVersionUID = 6530439596886760648L;
	

	private Long uuid;
	private Long empUuid;
	private Long roleUuid;
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public Long getEmpUuid() {
		return empUuid;
	}
	public void setEmpUuid(Long empUuid) {
		this.empUuid = empUuid;
	}
	public Long getRoleUuid() {
		return roleUuid;
	}
	public void setRoleUuid(Long roleUuid) {
		this.roleUuid = roleUuid;
	}
	

}
