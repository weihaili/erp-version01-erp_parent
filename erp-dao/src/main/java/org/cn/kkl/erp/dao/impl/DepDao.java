package org.cn.kkl.erp.dao.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IDepDao;
import org.cn.kkl.erp.entity.Dep;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projection;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

/**
 * @author Admin
 * department data access layer;
 *
 */
public class DepDao extends HibernateDaoSupport implements IDepDao {

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
	
	
	


}
