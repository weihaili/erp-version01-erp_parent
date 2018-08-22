package org.cn.kkl.erp.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * report data access
 * @author Admin
 */
public interface IReportDao {
	
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
