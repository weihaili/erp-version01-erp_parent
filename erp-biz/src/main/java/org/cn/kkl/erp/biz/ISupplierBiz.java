package org.cn.kkl.erp.biz;

import java.io.InputStream;
import java.io.OutputStream;

import org.cn.kkl.erp.entity.Supplier;

public interface ISupplierBiz extends IBaseBiz<Supplier> {
	
	/**
	 * supplier downLoad
	 * @param os :need export dataStream
	 * @param supplier :dataType
	 */
	void export(OutputStream os,Supplier t1);
	
	/**
	 * supplier information import by *.xls style file
	 * @param is
	 * @throws Exception
	 */
	void doImport(InputStream is) throws Exception;

}
