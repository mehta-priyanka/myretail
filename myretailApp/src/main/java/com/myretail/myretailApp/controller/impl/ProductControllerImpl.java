package com.myretail.myretailApp.controller.impl;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.myretail.myretailApp.controller.ProductController;
import com.myretail.myretailApp.exception.MyRetailAppException;
import com.myretail.myretailApp.response.ProductDaoResponse;
import com.myretail.myretailApp.response.ProductResponse;
import com.myretail.myretailApp.service.ProductService;

@RestController
public class ProductControllerImpl implements ProductController {

	protected Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductService productService;

	@Override
	public ResponseEntity<ProductDaoResponse> getProductById(String productId) throws MyRetailAppException, JSONException {
		logger.info("Inside method getProductById :  " + productId);

		ProductDaoResponse productResponse= new ProductDaoResponse();
		productResponse = productService.getProductById(productId);
		logger.debug("Response " + productResponse);
		return new ResponseEntity<ProductDaoResponse>(productResponse, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<ProductResponse> updatePrice(ProductDaoResponse product,String productId) throws MyRetailAppException {
		logger.info("Inside method updatePrice");
		if (!product.getProductId().equalsIgnoreCase(productId)) {
			throw new MyRetailAppException(HttpStatus.NO_CONTENT.value(),"No Content Found");
		}
		productService.updateProductById(product);
		return new ResponseEntity<ProductResponse>(HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<ProductResponse> exceptionHandler(MyRetailAppException ex) {
		logger.info("Inside exceptionHandler");
		ProductResponse error = new ProductResponse(ex.getErrorCode(),ex.getMessage());
		logger.debug(ex.getErrorMessage(),ex);
		return new ResponseEntity<ProductResponse>(error,HttpStatus.valueOf(ex.getErrorCode()));
	}

}
