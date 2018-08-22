package org.cn.kkl.erp.biz;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author Admin
 * report business interface
 */
public interface IReportBiz {
	
	/**
	 * sales report
	 * @return
	 */
	List<?> ordersReport(Date startDate,Date endDate);
	
	/**
	 * get sum of every month money by year
	 * @param year
	 * @return
	 */
	List<Map<String, Object>> getYearTrendMoney(Integer year);

}
