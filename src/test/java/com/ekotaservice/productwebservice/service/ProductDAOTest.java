/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice.service;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ekotaservice.productwebservice.Product;
import com.ekotaservice.productwebservice.ProductWebServiceApplication;

/**
 * ProductDAO Junit Testing
 * @author joshua
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ProductWebServiceApplication.class)
public class ProductDAOTest {

	@Autowired
	private ProductDAO<Product> productDAO;
	
	private static int count=0;
	
	@Test
	public void testCreate() {
		Product product = createNewProduct();
		productDAO.create(product);
		Product queryProduct = productDAO.findOne(product.getId());
		assertEquals(product.getProductName(), queryProduct.getProductName());
		assertEquals(product.getProductDescription(), queryProduct.getProductDescription());
	}
	
	
	@Test
	public void testDelete() {
		Product product = createNewProduct();
		productDAO.create(product);
		boolean status = productDAO.deleteById(product.getId());
		assertTrue(status);
		
		Product queryProduct = productDAO.findOne(product.getId());
		assertNull(queryProduct);

	}
	
	@Test 
	public void testUpdate() {
		
		Product product = createNewProduct();
		productDAO.create(product);

		product.setProductDescription("Updated description based on" + product.getProductDescription());
		product.setProductName("Updated name based on" + product.getProductName());
		
		boolean status =  productDAO.update(product);
	    assertTrue(status);
		
		Product queryProduct = productDAO.findOne(product.getId());
		assertEquals(product.getProductName(), queryProduct.getProductName());
		assertEquals(product.getProductDescription(), queryProduct.getProductDescription());

	}
	
	@Test
	public void testFindOne() {

		Product product = createNewProduct();
		productDAO.create(product);
		Product queryProduct = productDAO.findOne(product.getId());
		assertEquals(queryProduct.getProductName(), product.getProductName());
		assertEquals(queryProduct.getProductDescription(), product.getProductDescription());
		
	}
	
	@Test
	public void testFinalAll() {

		assertEquals(count, productDAO.findAll().size());
		
	}
	
	private Product createNewProduct() {
		Product product = new Product();
		product.setId(UUID.randomUUID().toString());
		product.setProductName("uniquename-" + product.getId());
		product.setProductDescription("default description");
		count++;
		return product;
	}
}
