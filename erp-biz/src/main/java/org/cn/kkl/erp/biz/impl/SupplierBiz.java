package org.cn.kkl.erp.biz.impl;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.time.temporal.TemporalQueries;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.cn.kkl.erp.biz.ISupplierBiz;
import org.cn.kkl.erp.dao.ISupplierDao;
import org.cn.kkl.erp.entity.Supplier;

public class SupplierBiz extends BaseBiz<Supplier> implements ISupplierBiz {

	private ISupplierDao supplierDao;

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		super.setBaseDao(this.supplierDao);
	}

	/**
	 * supplier downLoad(excel file)
	 * 
	 * @param os
	 *            :need export dataStream
	 * @param supplier
	 *            :dataType
	 */
	public void export(OutputStream os, Supplier t1) {
		List<Supplier> list = super.getList(t1, null, null);

		HSSFWorkbook workbook = new HSSFWorkbook();
		HSSFSheet sheet = null;
		if (Supplier.TYPE_SUPPLIER==t1.getType()) {
			sheet = workbook.createSheet("supplier");
		}
		if (Supplier.TYPE_CLIENT==t1.getType()) {
			sheet = workbook.createSheet("client");
		}

		HSSFRow row = sheet.createRow(0);

		//Field[] fields = t1.getClass().getDeclaredFields();
		String[] headerNames = {"name","address","contact people","telephone","email"};
		int[] columsWidths={4,8,2,3,8};
		/*for (int i = 0; i < fields.length; i++) {
			headerNames[i] = fields[i].getName();
		}*/
		HSSFCell cell = null;
		for (int i = 0; i < headerNames.length; i++) {
			cell = row.createCell(i);
			cell.setCellValue(headerNames[i]);
			// set cell width
			sheet.setColumnWidth(i, columsWidths[i]*1000);
		}

		int i = 1;
		for (Supplier supplier : list) {
			row = sheet.createRow(i);
			row.createCell(0).setCellValue(supplier.getName());
			row.createCell(1).setCellValue(supplier.getAddress());
			row.createCell(2).setCellValue(supplier.getContact());
			row.createCell(3).setCellValue(supplier.getTele());
			row.createCell(4).setCellValue(supplier.getEmail());
			i++;
		}
		try {
			workbook.write(os);
			
		} catch (Exception e) {
			System.out.println("content write out excepiton");
			e.printStackTrace();
		}finally {
			try {
				workbook.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
