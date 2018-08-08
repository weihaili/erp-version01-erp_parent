package org.cn.kkl.erp.entity;

import java.io.Serializable;
import java.util.List;

public class Menu implements Serializable {

	/**
	 * persistence
	 */
	private static final long serialVersionUID = 251230937641123992L;
	
	private Long menuId;
	private String menuName;
	private String icon;
	private String url;
	private List<Menu> menus;
	public Long getMenuId() {
		return menuId;
	}
	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
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
	

}
