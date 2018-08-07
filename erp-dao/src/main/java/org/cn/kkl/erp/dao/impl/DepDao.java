package org.cn.kkl.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IDepDao;
import org.cn.kkl.erp.entity.Dep;
import org.cn.kkl.erp.redis.dao.JedisClient;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * @author Admin
 * department data access layer;
 *
 */
/**
 * @author Admin
 *
 */
public class DepDao extends BaseDao<Dep> implements IDepDao {
	
	private JedisClient jedisClient;
	
	public void setJedisClient(JedisClient jedisClient) {
		this.jedisClient = jedisClient;
	}


	/**
	 * @param paramDep
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Dep dep1,Dep dep2,Object param) {
		DetachedCriteria criteria =DetachedCriteria.forClass(Dep.class);
		if (null!=dep1) {
			if (StringUtils.isNotBlank(dep1.getName())) {
				criteria.add(Restrictions.like("name", dep1.getName(),MatchMode.ANYWHERE));
			}
			if (StringUtils.isNoneBlank(dep1.getTele())) {
				criteria.add(Restrictions.like("tele", dep1.getTele(), MatchMode.START));
			}
		}
		return criteria;
	}


	
	


}
