package org.cn.kkl.erp.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.cn.kkl.erp.dao.IReportDao;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

public class ReportDao extends HibernateDaoSupport implements IReportDao {

	@Override
	public List<?> ordersReport(Date startDate,Date endDate) {
		List<Date> dates = new ArrayList<Date>();
		String hql2=" SELECT new Map(gt.name as name, SUM(od.money) as y) FROM "
				+ " GoodsType gt, Goods gs, OrderDetail od, Order os WHERE "+
				" gs.goodsType =gt AND od.order = os AND od.goodsuuid=gs.uuid AND os.type = 2 ";
		String hql=" SELECT gt.name, SUM(od.money) FROM "
				+ " GoodsType gt, Goods gs, OrderDetail od, Order os WHERE "+
				" gs.goodsType =gt AND od.order = os AND od.goodsuuid=gs.uuid AND os.type = 2 "
				+ " GROUP BY gt.name ";
		if (null!=startDate) {
			hql2+=" and os.createTime >= ? ";
			dates.add(startDate);
		}
		if (null!= endDate) {
			hql2+=" and os.createTime < ? ";
			dates.add(endDate);
		}
		hql2+=" GROUP BY gt.name  ";
		return getHibernateTemplate().find(hql2,dates.toArray(new Date[]{}));
	}

	@Override
	public List<Map<String, Object>> getYearTrendMoney(Integer year) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		int defaultYear = calendar.get(Calendar.YEAR);
		if (null!=year) {
			defaultYear=year;
		}
		String hql="SELECT new Map(MONTH(o.createTime) || 'month' as month,SUM(od.money) as y) FROM OrderDetail od,Order o "
				+ " WHERE od.order = o "
				+"AND o.type = 2 AND YEAR(o.createTime)=?  "
				+ "GROUP BY MONTH(o.createTime) ";
		return (List<Map<String, Object>>) getHibernateTemplate().find(hql, defaultYear);
	}
	
	

}
