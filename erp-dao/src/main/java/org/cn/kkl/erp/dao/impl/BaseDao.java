package org.cn.kkl.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.cn.kkl.erp.dao.IBaseDao;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class BaseDao<T> extends HibernateDaoSupport implements IBaseDao<T> {
	
	private Class<T> entityClass;
	
	public BaseDao(){
		//get the parent class through the subclass
		Type baseDaoClass = this.getClass().getGenericSuperclass();
		
		//convert to parameterized type
		ParameterizedType pType=(ParameterizedType) baseDaoClass;
		
		//get array of parameter types
		Type[] types = pType.getActualTypeArguments();
		
		//get the T type in the generic
		//the actual type of conversion to generic
		entityClass=(Class<T>) types[0];
	}
	
	/**
	 * page query list
	 * @param page
	 * @param rows
	 * @return
	 */
	@Override
	public List<T> getList(T t1, T t2, Object param) {
		DetachedCriteria criteria=getDetachedCriteria(t1, t2, param);
		return (List<T>) super.getHibernateTemplate().findByCriteria(criteria);
	}

	/**
	 * page query list by condition or range 
	 * 
	 * @param t1
	 * @param t2
	 * @param param
	 * @param page
	 * @param rows
	 * @return
	 */
	public List<T> getList(T t1, T t2, Object param, Integer firstResult, Integer maxResult) {
		
		DetachedCriteria criteria = getDetachedCriteria(t1,t2,param);
		 
		return (List<T>) this.getHibernateTemplate().findByCriteria(criteria,firstResult,maxResult);
	}

	/**
	 * get total records
	 * @param paramT
	 * @return
	 */
	@Override
	public Long getTotalRecords(T t1, T t2, Object param) {
		DetachedCriteria criteria = getDetachedCriteria(t1,t2,param);
		criteria.setProjection(Projections.rowCount());
		return (Long) this.getHibernateTemplate().findByCriteria(criteria).get(0);
	}
	
	/* 
	 * add 
	 */
	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	/* 
	 * delete tendent id
	 */
	@Override
	public void delete(Long uuid) {
		T t = this.getHibernateTemplate().get(entityClass, uuid);
		this.getHibernateTemplate().delete(t);
	}

	/* 
	 * query  information by id
	 */
	@Override
	public T get(Long uuid) {
		return this.getHibernateTemplate().get(entityClass,uuid);
	}
	
	@Override
	public T get(String uuid) {
		return this.getHibernateTemplate().get(entityClass,uuid);
	}

	/* 
	 * update 
	 */
	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}
	
	/**
	 * implemented by subclass
	 * @param paramT
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(T t1,T t2,Object param) {
		return null;
	}

}
