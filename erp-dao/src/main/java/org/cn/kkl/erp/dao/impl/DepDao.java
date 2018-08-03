package org.cn.kkl.erp.dao.impl;

import java.util.List;

import org.cn.kkl.erp.dao.IDepDao;
import org.cn.kkl.erp.entity.Dep;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * @author Admin
 * department data access layer;
 *
 */
public class DepDao extends HibernateDaoSupport implements IDepDao {

	public List<Dep> getDepList() {
		 
		return (List<Dep>) this.getHibernateTemplate().find(" from Dep ");
	}

}
