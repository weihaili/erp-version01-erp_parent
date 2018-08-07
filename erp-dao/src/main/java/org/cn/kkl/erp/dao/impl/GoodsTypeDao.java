package org.cn.kkl.erp.dao.impl;


import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IGoodsTypeDao;
import org.cn.kkl.erp.entity.GoodsType;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class GoodsTypeDao extends BaseDao<GoodsType> implements IGoodsTypeDao {

	@Override
	public DetachedCriteria getDetachedCriteria(GoodsType goodsType1, GoodsType goodsType2, Object param) {
		DetachedCriteria criteria=DetachedCriteria.forClass(GoodsType.class);
		if (null!=goodsType1) {
			if (StringUtils.isNotBlank(goodsType1.getName())) {
				criteria.add(Restrictions.like("name", goodsType1.getName(),MatchMode.ANYWHERE));
			}
		}
		return criteria;
	}



}
