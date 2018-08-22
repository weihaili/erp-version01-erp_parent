package org.cn.kkl.erp.util;

import javax.mail.MessagingException;

import org.cn.kkl.erp.biz.IStoreDetailBiz;

public class MailJob {
	
	private IStoreDetailBiz storeDetailBiz;


	public void setStoreDetailBiz(IStoreDetailBiz storeDetailBiz) {
		this.storeDetailBiz = storeDetailBiz;
	}


	public void sendStorealertMail() throws MessagingException {
		storeDetailBiz.sendStoreAlertMail();
	}

}
