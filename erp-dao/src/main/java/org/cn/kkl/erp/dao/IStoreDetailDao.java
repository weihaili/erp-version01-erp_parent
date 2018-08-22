package org.cn.kkl.erp.dao;

import java.util.List;

import org.cn.kkl.erp.entity.StoreDetail;
import org.cn.kkl.erp.entity.StoreQuantityWarning;

public interface IStoreDetailDao extends IBaseDao<StoreDetail> {
	
	/**
	 * inventory warning
	 * @return
	 */
	List<StoreQuantityWarning> getStorealertList(Double quantity);

}
