/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ekotaservice.productwebservice.Product;
import com.ekotaservice.productwebservice.exception.ProductServiceException;

/**
 * 
 * @author joshua
 *
 */
@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO<Product> productDAO;
	
	@Override
	public Product createProduct(Product product) throws ProductServiceException {
		product.setId(UUID.randomUUID().toString());  
		if ( product.getProductName() == null )
		  product.setProductName("Product-" + product.getId());
		if ( product.getProductDescription() == null )
		  product.setProductDescription("Default SimpleDescription.");
		boolean status = productDAO.create(product);
		if (!status) 
			throw new ProductServiceException("Failed to create new product.");
		return product;
	}

	@Override
	public Product retrieveProduct(String id) throws ProductServiceException {
		
		Product product = productDAO.findOne(id);
		if ( product == null) {
			throw new ProductServiceException("Product with id " + id + " does not exist.");
		}
		return product;
	}

	@Override
	public List<Product> retrieveAllProduct() {
        return productDAO.findAll();
		
	}

	@Override
	public Product updateProduct(Product product) throws ProductServiceException {
		
  	boolean status = productDAO.update(product);
		
		if( !status) 
			throw new ProductServiceException("Product with id " + product.getId() + " does not exist.");

		return product;
	}

	@Override
	public Product deleteProduct(String id) throws ProductServiceException {
       
		Product product = productDAO.findOne(id);
		if ( product == null) {
			throw new ProductServiceException("Product with id " + id + " does not exist.");
		}
		
	   boolean status = productDAO.deleteById(id);
		
		if( !status) 
			throw new ProductServiceException("Product with id " + product.getId() + " could not be deleted.");

		return product;
	}

}
