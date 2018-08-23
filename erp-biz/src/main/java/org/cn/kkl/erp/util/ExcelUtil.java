package org.cn.kkl.erp.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class ExcelUtil {
	
	/**
	 * get object field name
	 * @param o
	 * @return
	 */
	public String[] getFieldName(Object o){
		String[] fieldNames=null;
		if (null!=o) {
			Field[] fields = o.getClass().getDeclaredFields();
			fieldNames=new String[fields.length];
			for (int i = 0; i < fields.length; i++) {
				fieldNames[i] = fields[i].getName();
			}
		}
		return fieldNames;
	}
	
	/**
	 * get field value by fieldName
	 * @param fieldName
	 * @param o
	 * @return
	 */
	public Object getFieldValueByName(String fieldName,Object o){
		try {    
            String firstLetter = fieldName.substring(0, 1).toUpperCase();    
            String getter = "get" + firstLetter + fieldName.substring(1);    
            Method method = o.getClass().getMethod(getter, new Class[] {});    
            Object value = method.invoke(o, new Object[] {});    
            return value;    
        } catch (Exception e) {    
          
            return null;    
        } 
	}

}
