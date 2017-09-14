/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice.exception;

/**
 * 
 * @author joshua
 *
 */
public class ProductServiceException extends Exception {
	
	private static final long serialVersionUID=39438383838394402L;
	
	public ProductServiceException() {
		
	}

	public ProductServiceException(String message) {
		super(message);
	}
	
	public ProductServiceException(Throwable cause) {
		super(cause);
	}
	
	public ProductServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
}

	
 
