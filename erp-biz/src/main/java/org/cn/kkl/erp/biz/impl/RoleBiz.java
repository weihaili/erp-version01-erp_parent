package org.cn.kkl.erp.biz.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.biz.IRoleBiz;
import org.cn.kkl.erp.dao.IMenuDao;
import org.cn.kkl.erp.dao.IRoleDao;
import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.entity.Menu;
import org.cn.kkl.erp.entity.Role;
import org.cn.kkl.erp.entity.Tree;
import org.cn.kkl.erp.redis.dao.JedisClient;

public class RoleBiz extends BaseBiz<Role> implements IRoleBiz {
	
	private IRoleDao roleDao;
	
	private IMenuDao menuDao;
	
	private JedisClient jedisClient;

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao(this.roleDao);
	}
	
	public void setMenuDao(IMenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void setJedisClient(JedisClient jedisClient) {
		this.jedisClient = jedisClient;
	}

	/**
	 * get menus under role
	 * @return
	 */
	public List<Tree> readRoleMenus(Long roleuuid){
		ArrayList<Tree> trees = new ArrayList<Tree>();
		Menu root = menuDao.get("0");
		
		//get menus under role
		List<Menu> roleMenus = roleDao.get(roleuuid).getMenus();
		
		Tree t1= null;
		Tree t2= null;
		for (Menu m1 : root.getMenus()) {
			t1=new Tree();
			t1.setId(m1.getMenuid());
			t1.setText(m1.getMenuname());
			for (Menu m2 : m1.getMenus()) {
				t2=new Tree();
				t2.setId(m2.getMenuid());
				t2.setText(m2.getMenuname());
				if (roleMenus.contains(m2)) {
					t2.setChecked(true);
				}
				t1.getChildren().add(t2);
			}
			trees.add(t1);
		}
		return trees;
	}
	
	/**
	 * set menu permission on role
	 * @param roleUuid
	 * @param checkedMenuIds
	 */
	public void updateRoleMenus(Long roleUuid,String checkedMenuIds){
		String[] menuIds=null;
		Role role = null;
		if (StringUtils.isNotBlank(checkedMenuIds)) {
			menuIds = checkedMenuIds.split(",");
		}
		if (null!=roleUuid) {
			role = roleDao.get(roleUuid);
		}
		if (null!=role) {
			role.setMenus(new ArrayList<Menu>());
			for (String id : menuIds) {
				Menu menu = menuDao.get(id);
				role.getMenus().add(menu);
			}
		}
		
		//clear cache
		try {
			List<Emp> emps = role.getEmps();
			if (null!=emps && emps.size()>0) {
				for (Emp emp : emps) {
					jedisClient.hdel("permission", String.valueOf(emp.getUuid()));
				}
			}
		} catch (Exception e) {
			System.out.println("redis server exception,please check");
			e.printStackTrace();
		}
		
	}

}
