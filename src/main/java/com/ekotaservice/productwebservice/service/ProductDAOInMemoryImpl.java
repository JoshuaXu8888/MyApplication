/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Repository;

import com.ekotaservice.productwebservice.Product;

/**
 * 
 * @author joshua
 *
 */
@Repository("productDAO")
public class ProductDAOInMemoryImpl implements ProductDAO<Product> {

	private Map<String, Product> datastore;
	
	
	@PostConstruct
	private void init() {
		datastore = new ConcurrentHashMap<String, Product>();
	}

	
	@Override
	public Product findOne(String id) {
		Product result = datastore.get(id);
		return result;
	}

	@Override
	public List<Product> findAll() {
		List<Product> results = new ArrayList<Product>();
		
		for ( String id : datastore.keySet()) {
			   results.add(datastore.get(id));
		}
		
		return results;
	}

	@Override
	public boolean create(Product product) {
	     boolean status = false;
	     
	     if(findOne(product.getId()) == null) {
	    	     datastore.put(product.getId(), product);
	    	     status = true;
	     }
	     return status;

	}

	@Override
	public boolean update(Product product) {
	     boolean status = false;
	     
	     if(findOne(product.getId()) != null) {
    	     datastore.put(product.getId(), product);
    	     status = true;
     }
	     
	     return status;
		
	}

	@Override
	public boolean deleteById(String id) {
	     boolean status = false;
	     
	     if(findOne(id) != null) {
    	       datastore.remove(id);
    	       status = true;
          }
	     
	     return status;
		
	}

}
