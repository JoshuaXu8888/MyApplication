/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice;

import static org.junit.Assert.*;

import java.util.UUID;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.ekotaservice.productwebservice.exception.ProductServiceException;
import com.ekotaservice.productwebservice.service.ProductService;
/**
 * 
 * @author joshua
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
//@WebMvcTest(value = ProductWebServiceApplication.class, secure = false)
@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT, classes = ProductWebServiceApplication.class)
public class ProductWebServiceControllerTest {
	
	@Autowired
	private ProductService productService;

	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	public void testCreateNewProduct() {
	
		try {

	    RequestBuilder requestBuilder = MockMvcRequestBuilders.post(
					"/productwebservice/v1/newproduct")
	    		        .contentType(MediaType.APPLICATION_JSON)
					.content("{ \"productName\": \"unique-name-8ba2-83582349c196\","
							+ " \"productDescription\":\"simple description\"}");

	    runMockMvrPerform(requestBuilder);

		} catch (ProductServiceException pe) {
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(false);
		}
	
	}
	
	@Test
	public void testUpdateProduct() {
		Product product = createNewProduct();
		
		try {
		Product newlyCreatedProduct = productService.createProduct(product);
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.put(
					"/productwebservice/v1/"+ newlyCreatedProduct.getId())
	    		        .contentType(MediaType.APPLICATION_JSON)
					.content("{ \"productName\": \"new unique-name-8ba2-835ad\","
							+ " \"productDescription\":\"updated simple description\"}");

	    runMockMvrPerform(requestBuilder);

		} catch (ProductServiceException pe) {
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testDeleteProduct() {
		Product product = createNewProduct();
		
		try {
		Product newlyCreatedProduct = productService.createProduct(product);
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(
					"/productwebservice/v1/"+ newlyCreatedProduct.getId())
	    		        .contentType(MediaType.APPLICATION_JSON);

	    runMockMvrPerform(requestBuilder);

		} catch (ProductServiceException pe) {
			assertTrue(false);
		} catch (Exception e) {
			assertTrue(false);
		}
	}
	
	@Test
	public void testRetrieveAllProduct() {
		try {
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/productwebservice/v1/all")
	    		        .contentType(MediaType.APPLICATION_JSON);

	    runMockMvrPerform(requestBuilder);
          
		} catch (ProductServiceException pe) {
  			assertTrue(false);
  		} catch (Exception e) {
  			assertTrue(false);
  		}
	}
	
	@Test
	public void testRetrieveProduct() {
		Product product = createNewProduct();
		try {
		Product newlyCreatedProduct = productService.createProduct(product);
	    RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
					"/productwebservice/v1/"+ newlyCreatedProduct.getId())
	    		        .contentType(MediaType.APPLICATION_JSON);
	    
	    runMockMvrPerform(requestBuilder);
        
		} catch (ProductServiceException pe) {
  			assertTrue(false);
  		} catch (Exception e) {
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
	
	private void runMockMvrPerform(RequestBuilder requestBuilder) throws Exception{
	       mockMvc.perform(requestBuilder)
	        .andExpect(status().isOk())
		      .andExpect(content()
		      .contentTypeCompatibleWith(MediaType.APPLICATION_JSON));
	  }
}
