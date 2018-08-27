package org.cn.kkl.erp.biz;

import java.util.List;

import org.cn.kkl.erp.entity.Role;
import org.cn.kkl.erp.entity.Tree;


public interface IRoleBiz extends IBaseBiz<Role> {
	
	/**
	 * get menus under role
	 * @param roleuuid : role id
	 * @return
	 */
	List<Tree> readRoleMenus(Long roleuuid);
	
	/**
	 * set menu permission on role
	 * @param roleUuid
	 * @param checkedMenuIds
	 */
	void updateRoleMenus(Long roleUuid,String checkedMenuIds);

}
