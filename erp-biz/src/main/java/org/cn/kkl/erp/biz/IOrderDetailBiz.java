package org.cn.kkl.erp.biz;

import org.cn.kkl.erp.entity.OrderDetail;

public interface IOrderDetailBiz extends IBaseBiz<OrderDetail> {
	
	/**
	 * inStore operation 
	 * @param orderId
	 * @param empUuid
	 * @param warehouseId
	 */
	void doInstore(Long orderId,Long empUuid,Long warehouseId );
	
	/**
	 * outStorage operation
	 * @param orderId
	 * @param empUuid
	 * @param warehouseId
	 */
	void doOutStore(Long orderId,Long empUuid,Long warehouseId);

}
