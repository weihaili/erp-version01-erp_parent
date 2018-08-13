package org.cn.kkl.erp.selfdifexception;

/**
 * self definition(custom exception)
 * business logic exception
 * @author Admin
 *
 */
public class ErpException extends RuntimeException {

	/**
	 * need persistence and network transmit
	 */
	private static final long serialVersionUID = 6199209963413848419L;

	public ErpException(String message) {
		super(message);
	}
	
	

}
