package org.cn.kkl.erp.biz;

import java.util.List;

import org.cn.kkl.erp.entity.Menu;

public interface IMenuBiz extends IBaseBiz<Menu> {
	
	/**
	 * query employee permission(menu) by employee uuid
	 * @param empuuid
	 * @return
	 */
	List<Menu> getMenusByEmpuuid(Long empuuid);
	
	/**
	 * query employee permission(menu) by employee uuid
	 * @param empuuid
	 * @return
	 */
	Menu readMenusByEmpuuid(Long empuuid);

}
