package org.cn.kkl.erp.dao.impl;

import java.util.Calendar;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IStoreOperDao;
import org.cn.kkl.erp.entity.StoreOper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

public class StoreOperDao extends BaseDao<StoreOper> implements IStoreOperDao {

	@Override
	public DetachedCriteria getDetachedCriteria(StoreOper storeOper1, StoreOper storeOper2, Object param) {
		DetachedCriteria criteria = DetachedCriteria.forClass(StoreOper.class);
		if (null!=storeOper1) {
			if (null!=storeOper1.getUuid()) {
				criteria.add(Restrictions.eq("uuid", storeOper1.getUuid()));
			}
			if (null!=storeOper1.getEmpuuid()) {
				criteria.add(Restrictions.eq("empuuid", storeOper1.getEmpuuid()));
			}
			if (null!=storeOper1.getOpertime()) {
				Calendar calendar =Calendar.getInstance();
				calendar.setTime(storeOper1.getOpertime());
				calendar.set(Calendar.HOUR, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				criteria.add(Restrictions.ge("opertime", calendar.getTime()));
			}
			if (null!=storeOper1.getStoreuuid()) {
				criteria.add(Restrictions.eq("storeuuid", storeOper1.getStoreuuid()));
			}
			if (null!=storeOper1.getGoodsuuid()) {
				criteria.add(Restrictions.eq("goodsuuid", storeOper1.getGoodsuuid()));
			}
			if (null!=storeOper1.getNum()) {
				criteria.add(Restrictions.ge("num", storeOper1.getNum()));
			}
			if (StringUtils.isNotBlank(String.valueOf(storeOper1.getType()).trim())) {
				criteria.add(Restrictions.eq("type", storeOper1.getType()));
			}
		}
		if (null!=storeOper2) {
			if (null!=storeOper2.getOpertime()) {
				Calendar calendar =Calendar.getInstance();
				calendar.setTime(storeOper2.getOpertime());
				calendar.set(Calendar.HOUR, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				calendar.set(Calendar.MILLISECOND, 999);
				criteria.add(Restrictions.le("opertime", calendar.getTime()));
			}
			if (null!=storeOper2.getNum()) {
				criteria.add(Restrictions.le("num", storeOper2.getNum()));
			}
		}
		
		return criteria;
	}
	
	

}
