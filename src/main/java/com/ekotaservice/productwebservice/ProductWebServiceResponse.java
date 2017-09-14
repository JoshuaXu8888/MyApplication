/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

/**
 * 
 * @author joshua
 *
 */
public class ProductWebServiceResponse {

	private HttpStatus httpStatus;
	private String status;
	private String statusMsg;
	private String errorMsg;
	private String productMsg;
	private List<Product> products;
	

	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
	
	public List<Product> getProducts() {
		return products;
	}
	public void addProducts(List<Product> sourceproducts) {
		if ( products == null)
			 products = new ArrayList<Product>();
		
		products.addAll(sourceproducts);
	}
	
	
	public String getStatusMsg() {
		return statusMsg;
	}
	public void setStatusMsg(String statusMsg) {
		this.statusMsg = statusMsg;
	}
	
	public HttpStatus getHttpStatus() {
		return httpStatus;
	}
	public void setHttpStatus(HttpStatus httpStatus) {
		this.httpStatus = httpStatus;
	}
	
	public String getProductMsg() {
		return productMsg;
	}
	public void setProductMsg(String productMsg) {
		this.productMsg = productMsg;
	}

	
	
	
	
}
