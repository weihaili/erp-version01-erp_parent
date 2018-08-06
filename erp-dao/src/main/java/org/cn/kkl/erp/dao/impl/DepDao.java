package org.cn.kkl.erp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IDepDao;
import org.cn.kkl.erp.dao.JedisClient;
import org.cn.kkl.erp.entity.Dep;
import org.cn.kkl.erp.utils.IdGenertorUtil;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * @author Admin
 * department data access layer;
 *
 */
/**
 * @author Admin
 *
 */
public class DepDao extends HibernateDaoSupport implements IDepDao {
	
	@Autowired
	private JedisClient jedisClient;
	
	@Value("REDIS_DEP_ID_KEY")
	private String depIdkey;
	
	@Value("DEP_IN_INIT_VALUE")
	private int depIdValue;

	@Override
	public List<Dep> getDepList(Integer page, Integer rows) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Dep.class);
		//return (List<Dep>) super.getHibernateTemplate().find(" FROM Dep ");
		int firstResult=(page-1)*rows;
		int maxResult =rows;
		return (List<Dep>) super.getHibernateTemplate().findByCriteria(criteria,firstResult,maxResult);
	}

	public List<Dep> getDepList(Dep dep1, Dep dep2, Object param, Integer page, Integer rows) {
		
		DetachedCriteria criteria = getDetachedCriteria(dep1);
		 
		int firstResult=(page-1)*rows;
		int maxResult=rows;
		return (List<Dep>) this.getHibernateTemplate().findByCriteria(criteria,firstResult,maxResult);
	}

	@Override
	public Long getDepTotalRecords(Dep paramDep) {
		DetachedCriteria criteria = getDetachedCriteria(paramDep);
		criteria.setProjection(Projections.rowCount());
		return (Long) this.getHibernateTemplate().findByCriteria(criteria).get(0);
	}
	
	/* 
	 * add new department
	 */
	@Override
	public void add(Dep dep) {
		/*if (!jedisClient.exists(depIdkey)) {
			jedisClient.set(depIdkey, String.valueOf(depIdValue));
		}
		System.out.println("department id: "+jedisClient.incr(depIdkey));*/
		long id = IdGenertorUtil.getId();
		dep.setUuid(id);
		this.getHibernateTemplate().save(dep);
	}

	/**
	 * @param paramDep
	 * @return
	 */
	private DetachedCriteria getDetachedCriteria(Dep paramDep) {
		DetachedCriteria criteria =DetachedCriteria.forClass(Dep.class);
		if (null!=paramDep) {
			if (StringUtils.isNotBlank(paramDep.getName())) {
				criteria.add(Restrictions.like("name", paramDep.getName(),MatchMode.ANYWHERE));
			}
			if (StringUtils.isNoneBlank(paramDep.getTele())) {
				criteria.add(Restrictions.like("tele", paramDep.getTele(), MatchMode.START));
			}
		}
		return criteria;
	}

	/* 
	 * delete department dependent id
	 */
	@Override
	public void delete(Long uuid) {
		Dep dep = this.getHibernateTemplate().get(Dep.class, uuid);
		this.getHibernateTemplate().delete(dep);
	}

	/* 
	 * query department information by id
	 */
	@Override
	public Dep get(Long uuid) {
		return this.getHibernateTemplate().get(Dep.class,uuid);
	}

	/* 
	 * update department
	 */
	@Override
	public void update(Dep dep) {
		this.getHibernateTemplate().update(dep);
	}
	
	
	


}
