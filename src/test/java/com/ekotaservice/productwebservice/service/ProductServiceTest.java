/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice.service;

import java.util.List;
import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ekotaservice.productwebservice.Product;
import com.ekotaservice.productwebservice.ProductWebServiceApplication;
import com.ekotaservice.productwebservice.exception.ProductServiceException;

import static org.junit.Assert.*;
/**
 * 
 * @author joshua
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductWebServiceApplication.class)
public class ProductServiceTest {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private ProductDAO<Product> productDAO;
	
	@Test
	public void testCreateProduct() {
		Product product = createNewProduct();
		try {
		 Product newlyCreatedproduct = productService.createProduct(product);
		 
		 Product queryProduct = productDAO.findOne(newlyCreatedproduct.getId());
		 
		 assertEquals(newlyCreatedproduct.getId(),queryProduct.getId());
		 assertEquals(newlyCreatedproduct.getProductName(),queryProduct.getProductName());
		 assertEquals(newlyCreatedproduct.getProductDescription(),queryProduct.getProductDescription());
		 
		} catch ( ProductServiceException e) {
			assertTrue(false);
		}
		
	}

	
	@Test
	public void testDeleteProduct() {
		
		Product product = createNewProduct();
		try {
		 Product newlyCreatedproduct = productService.createProduct(product);
		 
		 Product deletedProduct = productService.deleteProduct(newlyCreatedproduct.getId());
		 
		 Product queryProduct = productDAO.findOne(deletedProduct.getId());
		 assertNull(queryProduct);
		} catch ( ProductServiceException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testRetrieveAllProduct() {
		List<Product> products = productService.retrieveAllProduct();
		assertNotNull(products);
	}
	
	@Test 
	public void testRetrieveProduct() {
		
		Product product = createNewProduct();
		try {
		
		 productDAO.create(product);
		 
		 Product retrievedProduct = productService.retrieveProduct(product.getId());
		 
		 assertEquals(product.getId(), retrievedProduct.getId());
		} catch ( ProductServiceException e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testUpdateProduct() {
		
		Product product = createNewProduct();
		productDAO.create(product);

		product.setProductDescription("Updated description based on" + product.getProductDescription());
		product.setProductName("Updated name based on" + product.getProductName());
		
		try {
		  productService.updateProduct(product);
		  Product updatedProduct = productDAO.findOne(product.getId());
	
		  assertEquals(product.getProductName(), updatedProduct.getProductName());
		  assertEquals(product.getProductDescription(), updatedProduct.getProductDescription());

		  
		} catch ( ProductServiceException e) {
			assertTrue(false);
		}
		
	}
	private Product createNewProduct() {
		Product product = new Product();
		product.setId(UUID.randomUUID().toString());
		product.setProductName("uniquename-" + product.getId());
		product.setProductDescription("default description");
		return product;
	}
	
	
	
}
