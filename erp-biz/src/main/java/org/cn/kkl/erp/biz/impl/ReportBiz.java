package org.cn.kkl.erp.biz.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.cn.kkl.erp.biz.IReportBiz;
import org.cn.kkl.erp.dao.IReportDao;

public class ReportBiz implements IReportBiz {
	
	private IReportDao reportDao;
	

	public void setReportDao(IReportDao reportDao) {
		this.reportDao = reportDao;
	}

	/* 
	 * sales statistics report
	 */
	@Override
	public List<?> ordersReport(Date startDate,Date endDate) {
		
		return reportDao.ordersReport(startDate,endDate);
	}

	/**
	 * get sum of every month money by year
	 * @param year
	 * @return
	 */
	@Override
	public List<Map<String, Object>> getYearTrendMoney(Integer year) {
		List<Map<String,Object>> yearTrendMoney = reportDao.getYearTrendMoney(year);
		
		List<Map<String,Object>> result = new ArrayList<>();
		
		Map<String, Map<String,Object>> map = new HashMap<>();
		
		for (Map<String, Object> m : yearTrendMoney) {
			map.put((String)m.get("month"), m);
		}
		
		Map<String,Object> data=null;
		for (int i = 0; i < 12; i++) {
			data=map.get(i+"month");
			if (null==data) {
				data=new HashMap<>();
				data.put("month", i+"month");
				data.put("y", 0);
			}
			result.add(data);
		}
		return result;
	}
}
