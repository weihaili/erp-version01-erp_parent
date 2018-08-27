package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

public class Menu implements Serializable {

	/**
	 * persistence
	 */
	private static final long serialVersionUID = 251230937641123992L;
	
	private String menuid;
	private String menuname;
	private String icon;
	private String url;
	private List<Menu> menus;
	public String getMenuid() {
		return menuid;
	}
	public void setMenuid(String menuid) {
		this.menuid = menuid;
	}
	public String getMenuname() {
		return menuname;
	}
	public void setMenuname(String menuname) {
		this.menuname = menuname;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public List<Menu> getMenus() {
		return menus;
	}
	public void setMenus(List<Menu> menus) {
		this.menus = menus;
	}
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	/* 
	 * 1. judgment current object if is the object ,if it is ,it must be the same object
	 * 2. current object if it is null and it is the same class as compared instance
	 * 3. judgment attribute value if equal
	 */
	/*@Override
	public boolean equals(Object obj) {
		if (this==obj) {
			return true;
		}
		if (obj==null || getClass()!=obj.getClass()) {
			return false;
		}
		Menu menu =(Menu) obj;
		Objects.equals("name", menu.)
		return super.equals(obj);
	}*/
	
	

}
