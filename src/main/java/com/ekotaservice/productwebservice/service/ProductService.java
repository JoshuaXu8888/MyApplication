/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice.service;

import java.util.List;

import com.ekotaservice.productwebservice.Product;
import com.ekotaservice.productwebservice.exception.ProductServiceException;

/**
 * 
 * @author joshua
 *
 */
public interface ProductService {

	public Product createProduct(Product product) throws ProductServiceException;
	
	public Product retrieveProduct(String id) throws ProductServiceException;
	
	public  List<Product> retrieveAllProduct();
	
	public Product updateProduct(Product product) throws ProductServiceException;
	
	public Product deleteProduct(String id) throws ProductServiceException;
	
}
