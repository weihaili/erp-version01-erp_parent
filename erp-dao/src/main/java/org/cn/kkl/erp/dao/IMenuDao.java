package org.cn.kkl.erp.dao;

import java.util.List;

import org.cn.kkl.erp.entity.Menu;

public interface IMenuDao extends IBaseDao<Menu> {
	
	/**
	 * query employee permission(menu) by employee uuid
	 * @param empuuid
	 * @return
	 */
	List<Menu> getMenusByEmpuuid(Long empuuid);
	
}
