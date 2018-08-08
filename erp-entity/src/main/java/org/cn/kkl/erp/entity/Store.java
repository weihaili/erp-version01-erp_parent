package org.cn.kkl.erp.entity;

import java.io.Serializable;

public class Store implements Serializable {

	/**
	 * persistence
	 */
	private static final long serialVersionUID = 7289146939834341298L;
	
	private Long uuid;
	
	private String name;
	
	private Long empuuid;
	
	//private Emp emp;

	public Long getEmpuuid() {
		return empuuid;
	}

	public void setEmpuuid(Long empuuid) {
		this.empuuid = empuuid;
	}

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

	/*public Emp getEmp() {
		return emp;
	}

	public void setEmp(Emp emp) {
		this.emp = emp;
	}*/

}
