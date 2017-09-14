/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice.service;

import java.util.List;

/**
 * 
 * @author joshua
 *
 * @param <Product>
 */
public interface ProductDAO<Product> {

	public Product findOne(String id);
	public List<Product> findAll();
	public boolean create( Product product);
	public boolean update( Product product);
	public boolean deleteById( String id);
	
	
}
