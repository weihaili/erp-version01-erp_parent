package org.cn.kkl.erp.biz;

import java.io.OutputStream;

import org.cn.kkl.erp.entity.Order;

public interface IOrderBiz extends IBaseBiz<Order> {
	
	/**
	 * order check
	 * @param uuid :order uuid
	 * @param empUuid :checker uuid
	 */
	void doCheck(Long uuid,Long empUuid);
	
	/**
	 * order confirm
	 * @param uuid
	 * @param empUuid
	 */
	void doStart(Long uuid,Long empUuid);
	
	/**export order detail by style excel
	 * @param os
	 * @param uuid
	 */
	void exportById(OutputStream os,Long uuid) throws Exception;
	

}
