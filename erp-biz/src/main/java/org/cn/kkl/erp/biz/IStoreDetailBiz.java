package org.cn.kkl.erp.biz;

import java.util.List;

import javax.mail.MessagingException;

import org.cn.kkl.erp.entity.StoreDetail;
import org.cn.kkl.erp.entity.StoreQuantityWarning;

public interface IStoreDetailBiz extends IBaseBiz<StoreDetail> {
	
	/**
	 * inventory warning
	 * @return
	 */
	List<StoreQuantityWarning> getStorealertList(Double quantity);
	
	/**
	 * send store quantity warning  email 
	 */
	void sendStoreAlertMail() throws MessagingException;

}
