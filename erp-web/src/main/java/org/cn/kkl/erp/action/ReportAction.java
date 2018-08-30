package org.cn.kkl.erp.action;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.cn.kkl.erp.biz.IReportBiz;
import org.springframework.http.MediaType;

import com.alibaba.fastjson.JSON;

public class ReportAction {
	
	private IReportBiz reportBiz;
	private Date startDate;
	private Date endDate;
	private Integer year;

	public void setReportBiz(IReportBiz reportBiz) {
		this.reportBiz = reportBiz;
	}
	
	public Date getEndDate() {
		return endDate;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	/**
	 * sales statistics report
	 */
	public void orderReport(){
		List<?> ordersReportList = reportBiz.ordersReport(startDate,endDate);
		write(JSON.toJSONString(ordersReportList));
	}
	
	/**
	 * get sum of every month money by year
	 * @param year
	 * @return
	 */
	public void trendReport(){
		write(JSON.toJSONString(reportBiz.getYearTrendMoney(year)));
	}
	
	/**
	 * common method
	 * @param jsonStr need to write to return page string
	 */
	public void write(String  jsonStr){
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setContentType(MediaType.TEXT_PLAIN_VALUE+";charset=utf-8");;
		try {
			response.getWriter().write(jsonStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	

}
