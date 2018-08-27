package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class Emp implements Serializable {

	/**
	 * need network transmit
	 */
	private static final long serialVersionUID = -1253538966138125331L;
	
	private Long uuid;
	private String username;
	@JSONField(serialize=false)
	private String pwd;
	private String name;
	private char gender;
	private String email;
	private String tele;
	private String address;
	private Date birthday;
	private Dep dep;
	@JSONField(serialize=false)
	private List<Role> roles;
	
	public Dep getDep() {
		return dep;
	}
	public void setDep(Dep dep) {
		this.dep = dep;
	}
	public Long getUuid() {
		return uuid;
	}
	public void setUuid(Long uuid) {
		this.uuid = uuid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getTele() {
		return tele;
	}
	public void setTele(String tele) {
		this.tele = tele;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public List<Role> getRoles() {
		return roles;
	}
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	

}
