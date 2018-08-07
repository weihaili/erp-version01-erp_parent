package org.cn.kkl.erp.dao;

import org.cn.kkl.erp.entity.Dep;

public interface IDepDao extends IBaseDao<Dep>{
	
	String REDIS_DEP_ID_KEY="DEP_ID";
	
	Integer REDIS_ID_INIT_VALUE=19;
	

}
