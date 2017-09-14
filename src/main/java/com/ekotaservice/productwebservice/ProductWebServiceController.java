/**
 *  EKOTA Services Inc.
 */
package com.ekotaservice.productwebservice;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ekotaservice.productwebservice.exception.ProductServiceException;
import com.ekotaservice.productwebservice.service.ProductService;

import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author joshua
 *
 */
@RestController
@RequestMapping("/productwebservice/v1")
public class ProductWebServiceController {
	
	private static final String SUCCESSFUL= "SUCCESSFUL";
	private static final String FAILURE = "FAILURE";
	
	@Autowired
	private ProductService productService;
	
    @RequestMapping(value="/newproduct", method = RequestMethod.POST, 
    		produces={"application/json"}, consumes="application/json")
    @ResponseBody
    public ProductWebServiceResponse  createNewProduct
      (@RequestBody Product product) {
    	ProductWebServiceResponse response = new ProductWebServiceResponse();
    	   try {
    		   
      	Product newProduct = productService.createProduct(product);
      	response.setStatusMsg("New Product was created with id=" + newProduct.getId());
      	response.setProductMsg("Newly created " + newProduct.toString());
      	response.setStatus(SUCCESSFUL);
      	response.addProducts(productService.retrieveAllProduct());
      	response.setHttpStatus(HttpStatus.OK);
    	   } catch ( ProductServiceException pe) {
    		   response.setHttpStatus( HttpStatus.BAD_REQUEST);
    		   response.setStatus(FAILURE);
    		   response.setErrorMsg(pe.getMessage());
    	   }
    	   
    	   return response;
    }

    
    @RequestMapping(value="/{id}", method = RequestMethod.GET, 
    		produces={"application/json"}, consumes="application/json")
    @ResponseBody
    public ProductWebServiceResponse  retrieveProduct
      (@PathVariable("id") String id) {
    	
      ProductWebServiceResponse response = new ProductWebServiceResponse();
 	   try {
	   	Product product = productService.retrieveProduct(id);
	   	response.setStatusMsg(" Product with id=" + id + " was retrieved.");
	   	response.setStatus(SUCCESSFUL);
	   	response.setProductMsg(product.toString());
	   	response.addProducts(productService.retrieveAllProduct());
	   	response.setHttpStatus(HttpStatus.OK);
 	   } catch ( ProductServiceException pe) {
 		   response.setHttpStatus( HttpStatus.BAD_REQUEST);
 		   response.setStatus(FAILURE);
 		   response.setErrorMsg(pe.getMessage());
 	   }
 	   
 	   return response;
    
    }
    
    @RequestMapping(value="/all", method = RequestMethod.GET, 
    		produces={"application/json"})
    @ResponseBody
    public ProductWebServiceResponse  retrieveAllProduct()
      {
    		ProductWebServiceResponse response = new ProductWebServiceResponse();
	    response.setStatus(SUCCESSFUL);
	   	response.addProducts(productService.retrieveAllProduct());
	   	response.setHttpStatus(HttpStatus.OK);
 
 	    return response;
    }

    @RequestMapping(value="/{id}", method = RequestMethod.PUT, 
    		produces={"application/json"}, consumes="application/json")
    @ResponseBody
    public ProductWebServiceResponse  updateProduct
      (@PathVariable("id") String id, @RequestBody Product product) {
    	
      ProductWebServiceResponse response = new ProductWebServiceResponse();
 	   try {
	   	Product tobeupdatProduct = new Product();
	   	tobeupdatProduct.setId(id);
	   	tobeupdatProduct.setProductName(product.getProductName());
	   	tobeupdatProduct.setProductDescription(product.getProductDescription());
	   	
	   	Product updatedProduct = productService.updateProduct(tobeupdatProduct);
	   	response.setStatusMsg(" Product with id=" + id + " was updated.");
	   	response.setStatus(SUCCESSFUL);
	   	response.setProductMsg("Updated " + updatedProduct.toString());
	   	response.addProducts(productService.retrieveAllProduct());
	   	response.setHttpStatus(HttpStatus.OK);
 	   } catch ( ProductServiceException pe) {
 		   response.setHttpStatus( HttpStatus.BAD_REQUEST);
 		   response.setStatus(FAILURE);
 		   response.setErrorMsg(pe.getMessage());
 	   }
 	   
 	   return response;
    
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE, 
    		produces={"application/json"}, consumes="application/json")
    @ResponseBody
    public ProductWebServiceResponse  deleteProduct
      (@PathVariable("id") String id) {
    	
      ProductWebServiceResponse response = new ProductWebServiceResponse();
 	   try {
	   	
	   	Product deletedProduct = productService.deleteProduct(id);
	   	response.setStatusMsg(" Product with id=" + id + " was deleted.");
	   	response.setStatus(SUCCESSFUL);
	   	response.setProductMsg("Deleted " + deletedProduct.toString());
	   	response.addProducts(productService.retrieveAllProduct());
	   	response.setHttpStatus(HttpStatus.OK);
 	   } catch ( ProductServiceException pe) {
 		   response.setHttpStatus( HttpStatus.BAD_REQUEST);
 		   response.setStatus(FAILURE);
 		   response.setErrorMsg(pe.getMessage());
 	   }
 	   
 	   return response;
    
    }
}

