package org.cn.kkl.erp.entity;

import java.io.Serializable;

public class Supplier implements Serializable {

	/**
	 * version controller and network transmit
	 */
	private static final long serialVersionUID = -5334211185427170896L;
	
	public static final char TYPE_SUPPLIER='1';
	public static final char TYPE_CLIENT='2';
	
	private Long   uuid;
	private String name;
	private String address;  
	private String contact;  
	private String tele; 
	private String email; 
	private char   type;
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public char getType() {
		return type;
	}
	public void setType(char type) {
		this.type = type;
	} 
             
	

}
