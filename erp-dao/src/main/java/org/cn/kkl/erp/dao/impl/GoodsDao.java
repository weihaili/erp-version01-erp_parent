package org.cn.kkl.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IGoodsDao;
import org.cn.kkl.erp.entity.Goods;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class GoodsDao extends BaseDao<Goods> implements IGoodsDao {

	@Override
	public DetachedCriteria getDetachedCriteria(Goods goods1, Goods goods2, Object param) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Goods.class);
		if (null!=goods1) {
			if (StringUtils.isNotBlank(goods1.getName())) {
				criteria.add(Restrictions.like("name", goods1.getName(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(goods1.getOrigin())) {
				criteria.add(Restrictions.like("origin", goods1.getOrigin(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(goods1.getProducer())) {
				criteria.add(Restrictions.like("producer", goods1.getProducer(), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(goods1.getUnit())) {
				criteria.add(Restrictions.like("unit", goods1.getUnit(),MatchMode.ANYWHERE));
			}
			if (null!=goods1.getGoodsType() && null!=goods1.getGoodsType().getUuid()) {
				criteria.add(Restrictions.eq("goodsType", goods1.getGoodsType()));
			}
			if (null!=goods1.getInprice()) {
				criteria.add(Restrictions.ge("inprice", goods1.getInprice()));
			}
			if (null!=goods1.getOutprice()) {
				criteria.add(Restrictions.ge("outprice", goods1.getOutprice()));
			}
		}
		if (null!=goods2) {
			if (null!=goods2.getInprice()) {
				criteria.add(Restrictions.le("inprice", goods2.getInprice()));
			}
			if (null!=goods2.getOutprice()) {
				criteria.add(Restrictions.le("outprice", goods2.getOutprice()));
			}
		}
		return criteria;
	}

	
}
