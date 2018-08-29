package org.cn.kkl.erp.action;

import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.cn.kkl.erp.biz.IOrderDetailBiz;
import org.cn.kkl.erp.entity.Emp;
import org.cn.kkl.erp.entity.OrderDetail;
import org.cn.kkl.erp.selfdifexception.ErpException;

public class OrderDetailAction extends BaseAction<OrderDetail> {

	private IOrderDetailBiz orderDetailBiz;

	public void setOrderDetailBiz(IOrderDetailBiz orderDetailBiz) {
		this.orderDetailBiz = orderDetailBiz;
		super.setBaseBiz(this.orderDetailBiz);
	}
	
	private Long storeuuid;//warehouseId
	
	public Long getStoreuuid() {
		return storeuuid;
	}

	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}


	/**
	 * purchase order wareHousing
	 */
	public void doInStore(){
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			ajaxReturn(false, "dear please login first");
			return ;
		}
		Long orderId = getId();
		if (null==orderId) {
			ajaxReturn(false, "dear no data submitted,please check");
			return ;
		}
		try {
			orderDetailBiz.doInstore(orderId, loginUser.getUuid(),storeuuid);
			ajaxReturn(true, "check pass,keep it up");
		}catch (UnauthorizedException u) {
			ajaxReturn(false, "insufficient permissions");
		}catch (ErpException e) {
			ajaxReturn(false, "order has been wareHoused please do not operation repeat");
			e.printStackTrace();
		} catch (Exception e) {
			ajaxReturn(false, "system busy,please try again later");
			e.printStackTrace();
		}
	}
	
	/**
	 * sale order out of wareHousing
	 */
	public void doOutStore(){
		Emp loginUser = getLoginUser();
		if (null==loginUser) {
			ajaxReturn(false, "dear please login first");
			return ;
		}
		Long orderId = getId();
		if (null==orderId) {
			ajaxReturn(false, "dear no data submitted,please check");
			return ;
		}
		try {
			orderDetailBiz.doOutStore(orderId, loginUser.getUuid(),storeuuid);
			ajaxReturn(true, "check pass,keep it up");
		}catch (ErpException e) {
			ajaxReturn(false, "order has been out of wareHoused please do not operation repeat");
			e.printStackTrace();
		} catch (Exception e) {
			ajaxReturn(false, "system busy,please try again later");
			e.printStackTrace();
		}
	}
	
}
