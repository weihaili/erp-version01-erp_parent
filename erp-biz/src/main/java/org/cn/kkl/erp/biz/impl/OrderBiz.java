package org.cn.kkl.erp.biz.impl;

import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.cn.kkl.erp.biz.IOrderBiz;
import org.cn.kkl.erp.dao.IEmpDao;
import org.cn.kkl.erp.dao.IOrderDao;
import org.cn.kkl.erp.dao.ISupplierDao;
import org.cn.kkl.erp.entity.Order;
import org.cn.kkl.erp.entity.OrderDetail;
import org.cn.kkl.erp.selfdifexception.ErpException;

public class OrderBiz extends BaseBiz<Order> implements IOrderBiz {
	
	private IOrderDao orderDao;
	private IEmpDao empDao;
	private ISupplierDao supplierDao;

	public void setEmpDao(IEmpDao empDao) {
		this.empDao = empDao;
	}

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	public void setOrderDao(IOrderDao orderDao) {
		this.orderDao = orderDao;
		super.setBaseDao(this.orderDao);
	}

	/* 
	 * add order
	 */
	@Override
	public void add(Order order) {
		Subject subject = SecurityUtils.getSubject();
		//purchase order apply
		if (Order.TYPE_IN==order.getType()) {
			boolean hasPurchaseApplyPermission = subject.isPermitted("purchase requisition");
			if (!hasPurchaseApplyPermission) {
				throw new ErpException("insufficient permissions");
			}
		}else
		
		//sale order entry
		if (Order.TYPE_OUT==order.getType()) {
			boolean hasSaleEntryPermission = subject.isPermitted("sales order entry");
			if (!hasSaleEntryPermission) {
				throw new ErpException("insufficient permissions");
			}
		}else {
			throw new ErpException("Illegal parameter");
		}
		
		Date date = new Date();
		//order.setType(Order.TYPE_IN);
		order.setState(Order.STATE_CREATE);
		order.setCreateTime(date);
		
		double total=0;
		List<OrderDetail> orderDetails = order.getOrderDetails();
		for (OrderDetail orderDetail : orderDetails) {
			total+=orderDetail.getMoney();
			orderDetail.setState(OrderDetail.STATE_NOT_IN);
			orderDetail.setOrder(order);
		}
		order.setTotalMoney(total);
		orderDao.add(order);
	}

	/* 
	 * query order by page(condition)
	 */
	@Override
	public List<Order> getList(Order order1, Order order2, Object param, Integer firstResult, Integer maxResult) {
		//get order list after page
		List<Order> orderList = super.getList(order1, order2, param, firstResult, maxResult);
		//traversing get employee and supplier name only this in list
		//cache employee or supplier id and name use map(key is id value is name)
		//get employee name from cache 
		//if there is not  get employee name from dataBase .then add cache
		Map<Long, String> cacheEmpMap=new HashMap<>();//employee cache map
		Map<Long, String> cacheSCMap=new HashMap<>();//supplier and client cache map
		for (Order order : orderList) {
			order.setCreaterName(getEmpName(order.getCreater(), cacheEmpMap,empDao));
			order.setCheckerName(getEmpName(order.getChecker(), cacheEmpMap,empDao));
			order.setEnderName(getEmpName(order.getEnder(), cacheEmpMap,empDao));
			order.setStarterName(getEmpName(order.getStarter(), cacheEmpMap,empDao));
			order.setSupplierName(getSupplierOrClentName(order.getSupplierUuid(), cacheSCMap,supplierDao));
		}
		return orderList;
	}
	
	/* 
	 * order check
	 * update order state set checker and checkTime
	 */
	@Override
	@RequiresPermissions("purchase order review")
	public void doCheck(Long uuid, Long empUuid) {
		if (null==uuid) {
			return ;
		}
		Order order = orderDao.get(uuid);
		if (order.getState()!=Order.STATE_CREATE) {
			throw new ErpException("dear the order has been checked");
		}
		order.setChecker(empUuid);
		order.setCheckTime(new Date());
		order.setState(Order.STATE_CHECKED);
	}

	/* 
	 * order confirm
	 * update order state set starter and startTime
	 */
	@Override
	@RequiresPermissions("purchase order confirmation")
	public void doStart(Long uuid, Long empUuid) {
		if (null==uuid) {
			return;
		}
		Order order = orderDao.get(uuid);
		if (order.getState()!=Order.STATE_CHECKED) {
			throw new ErpException("dear the order has been confirmed");
		}
		order.setStarter(empUuid);
		order.setStartTime(new Date());
		order.setState(Order.STATE_START);
	}
	
	/**export order detail by style excel
	 * @param os
	 * @param uuid
	 */
	public void exportById(OutputStream os,Long uuid) throws Exception{
		if (null==uuid) {
			return ;
		}
		Order order = orderDao.get(uuid);
		
		HSSFWorkbook book = new HSSFWorkbook();
		HSSFSheet sheet = book.createSheet("purchase order");

		HSSFCellStyle cellStyle = book.createCellStyle();
		cellStyle.setBorderTop(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		cellStyle.setBorderRight(HSSFCellStyle.BORDER_THIN);
		
		//content vertical center and align center
		cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		
		//set content font
		HSSFFont font = book.createFont();
		font.setFontName("SimSun");
		font.setFontHeightInPoints((short) 11);
		cellStyle.setFont(font);
		
		//title style
		HSSFCellStyle titleStyle = book.createCellStyle();
		titleStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		titleStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		HSSFFont titleFont = book.createFont();
		titleFont.setFontName("boldface");
		titleFont.setBold(true);
		titleFont.setFontHeightInPoints((short) 18);
		titleStyle.setFont(titleFont);
		
		//set date format
		HSSFCellStyle dateStyle = book.createCellStyle();
		dateStyle.cloneStyleFrom(cellStyle);
		HSSFDataFormat dataFormat = book.createDataFormat();
		dateStyle.setDataFormat(dataFormat.getFormat("yyyy-MM-dd HH:mm:ss"));
		
		
		// the first four columns of the first row are merged
		sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, 3));

		// second columns to fourth columns of third row are merged
		sheet.addMergedRegion(new CellRangeAddress(2, 2, 1, 3));

		// first column to fourth column of eighth row are merged
		sheet.addMergedRegion(new CellRangeAddress(7, 7, 0, 3));
		
		int rowCnt=10 + order.getOrderDetails().size();
		for (int i = 2; i < rowCnt; i++) {
			HSSFRow row = sheet.createRow(i);
			for (int j = 0; j < 4; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(cellStyle);
			}
		}
		
		sheet.createRow(0).createCell(0).setCellValue("purchase order");
		sheet.getRow(0).getCell(0).setCellStyle(titleStyle);
		
		sheet.getRow(2).getCell(0).setCellValue("supplier");

		sheet.getRow(3).getCell(0).setCellValue("order date");
		sheet.getRow(3).getCell(2).setCellValue("manager");

		sheet.getRow(4).getCell(0).setCellValue("check date");
		sheet.getRow(4).getCell(2).setCellValue("manager");

		sheet.getRow(5).getCell(0).setCellValue("purchase date");
		sheet.getRow(5).getCell(2).setCellValue("manager");

		sheet.getRow(6).getCell(0).setCellValue("inStore date");
		sheet.getRow(6).getCell(2).setCellValue("manager");

		sheet.getRow(7).getCell(0).setCellValue("order detail");

		sheet.getRow(8).getCell(0).setCellValue("goods name");
		sheet.getRow(8).getCell(1).setCellValue("quantity");
		sheet.getRow(8).getCell(2).setCellValue("price");
		sheet.getRow(8).getCell(3).setCellValue("amount");
		
		sheet.getRow(0).setHeight((short) 1000);
		for (int i = 2; i < rowCnt; i++) {
			sheet.getRow(i).setHeight((short) 500);
		}
		for (int i = 0; i < 4; i++) {
			sheet.setColumnWidth(i, 7000);
		}
		
		for (int i = 3; i < 7; i++) {
			sheet.getRow(i).getCell(1).setCellStyle(dateStyle);
		}
		
		//set supplier value
		sheet.getRow(2).getCell(1).setCellValue(supplierDao.get(order.getSupplierUuid()).getName());
		//set create time
		if (null!=order.getCreateTime()) {
			sheet.getRow(3).getCell(1).setCellValue(order.getCreateTime());
		}
		//set check time
		if (null!=order.getCheckTime()) {
			sheet.getRow(4).getCell(1).setCellValue(order.getCheckTime());
		}
		//set confirm time
		if (null!=order.getStartTime()) {
			sheet.getRow(5).getCell(1).setCellValue(order.getStartTime());
		}
		//set inStore time
		if (null!=order.getEndTime()) {
			sheet.getRow(6).getCell(1).setCellValue(order.getEndTime());
		}
		
		//set manager
		if (null!=order.getCreater()) {
			sheet.getRow(3).getCell(3).setCellValue(empDao.get(order.getCreater()).getName());
		}
		if (null!=order.getChecker()) {
			sheet.getRow(4).getCell(3).setCellValue(empDao.get(order.getChecker()).getName());
		}
		if (null!=order.getStarter()) {
			sheet.getRow(5).getCell(3).setCellValue(empDao.get(order.getStarter()).getName());
		}
		if (null!=order.getEnder()) {
			sheet.getRow(6).getCell(3).setCellValue(empDao.get(order.getEnder()).getName());
		}
		
		//set order detail
		int rowIndex=9;
		HSSFRow row =null;
		for (OrderDetail detail : order.getOrderDetails()) {
			row = sheet.getRow(rowIndex);
			row.getCell(0).setCellValue(detail.getGoodsname());
			row.getCell(1).setCellValue(detail.getNum());
			row.getCell(2).setCellValue(detail.getPrice());
			row.getCell(3).setCellValue(detail.getMoney());
			rowIndex++;
		}
		sheet.getRow(rowIndex).getCell(0).setCellValue("total money");
		sheet.getRow(rowIndex).getCell(3).setCellValue(order.getTotalMoney());
		
		book.write(os);
		book.close();
	}

	/**
	 * get employee name
	 * @param uuid :employee uuid
	 * @param cacheMap :cache map
	 * @return people name
	 */
	/*private String getEmpName(Long uuid,Map<Long, String> cacheMap){
		if (null==uuid) {
			return null;
		}
		//get name from cache
		String name = cacheMap.get(uuid);
		//if there is not query database then add cache
		if (null==name) {
			name = empDao.get(uuid).getName();
			cacheMap.put(uuid, name);
		}
		return name;
	}*/
	
	/**
	 * get supplier  client name
	 * @param uuid :people uuid
	 * @param cacheMap :cache map
	 * @return people name
	 */
	/*private String getSCName(Long uuid,Map<Long, String> cacheMap){
		if (null==uuid) {
			return null;
		}
		//get name from cache
		String name = cacheMap.get(uuid);
		//if there is not query database then add cache
		if (null==name) {
			name = supplierDao.get(uuid).getName();
			cacheMap.put(uuid, name);
		}
		return name;
	}*/
	
	
	

}
