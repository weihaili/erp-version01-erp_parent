package org.cn.kkl.erp.action;

import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.cn.kkl.erp.biz.IStoreDetailBiz;
import org.cn.kkl.erp.entity.StoreDetail;
import org.cn.kkl.erp.utils.KklResult;

import com.alibaba.fastjson.JSON;

public class StoreDetailAction extends BaseAction<StoreDetail> {
	
	private IStoreDetailBiz storeDetailBiz;
	
	private Double quantity;

	public void setStoreDetailBiz(IStoreDetailBiz storeDetailBiz) {
		this.storeDetailBiz = storeDetailBiz;
		super.setBaseBiz(this.storeDetailBiz);
	}

	public Double getQuantity() {
		return quantity;
	}

	public void setQuantity(Double quantity) {
		this.quantity = quantity;
	}

	public void storealertList(){
		HttpServletResponse response = ServletActionContext.getResponse();
		List<?> storealertList = storeDetailBiz.getStorealertList(quantity);
		new KklResult().write(JSON.toJSONString(storealertList), response);
	}
	
	public void sendStorealertMail(){
		try {
			storeDetailBiz.sendStoreAlertMail();
			ajaxReturn(true, "store warning mail send success");
		} catch (MessagingException e) {
			System.out.println("server busy,please try again later");
			ajaxReturn(false, "server busy,please try again later");
			e.printStackTrace();
		}
	}
}
