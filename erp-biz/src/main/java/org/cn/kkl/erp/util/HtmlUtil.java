package org.cn.kkl.erp.util;

import java.util.List;

public class HtmlUtil {
	
	public StringBuilder getStoreAlertHtml(String subject,List<String> titles,String content){
		StringBuilder sb = new StringBuilder();
		sb.append( "<!DOCTYPE html> " );
		sb.append( "<html> " );
		sb.append( "<head>  " );
		sb.append( "<meta charset='utf-8'>  " );
		sb.append( "<title>"+subject+"</title>  " );
		sb.append( "</head> " );
		sb.append( "<body> " );
		sb.append( "<table border='1' cellspacing='0'> " );
		sb.append( "<tr> " );
		if (null!=titles && titles.size()>0) {
			for (int i = 0; i < titles.size(); i++) {
				sb.append( "  <td>"+titles.get(i)+"</td> " );
				
			}
		}
		sb.append( "</tr> " );
		sb.append( "<tr> ");
		/*if (null!=titles && contents.size()>0) {
			for (int i = 0; i < contents.size(); i++) {
				sb.append( "  <td>"+contents.get(i)+"</td> " );
			}
		}*/
		sb.append(content);
		sb.append( "</tr> ");
		sb.append( "</table> " );
		sb.append( "</body> " );
		sb.append( "</html> " );
		return sb;
	}

}
