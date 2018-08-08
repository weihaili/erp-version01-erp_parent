package org.cn.kkl.erp.dao.impl;

import org.apache.commons.lang3.StringUtils;
import org.cn.kkl.erp.dao.IInventoryDao;
import org.cn.kkl.erp.entity.Inventory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

public class InventoryDao extends BaseDao<Inventory> implements IInventoryDao {

	@Override
	public DetachedCriteria getDetachedCriteria(Inventory inventory1, Inventory inventory2, Object param) {
		DetachedCriteria criteria =DetachedCriteria.forClass(Inventory.class);
		if (null!=inventory1) {
			if (StringUtils.isNotBlank(String.valueOf(inventory1.getType()).trim())) {
				criteria.add(Restrictions.like("type", String.valueOf(inventory1.getType()), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(String.valueOf(inventory1.getState()).trim())) {
				criteria.add(Restrictions.like("state", String.valueOf(inventory1.getState()), MatchMode.ANYWHERE));
			}
			if (StringUtils.isNotBlank(inventory1.getRemark())) {
				criteria.add(Restrictions.like("remark", inventory1.getRemark(), MatchMode.ANYWHERE));
			}
		}
		return criteria;
	}

	
}
