/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice;

/**
 * 
 * @author joshua
 *
 */
public class Product {
	
	private String id;
	private String productName;
	private String productDescription;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Product: { ")
		       .append("id:" + this.getId() +";")
		       .append(" name: " + this.getProductName() +";")
		       .append(" description: " + this.getProductDescription());
		return builder.toString();
	}

}
