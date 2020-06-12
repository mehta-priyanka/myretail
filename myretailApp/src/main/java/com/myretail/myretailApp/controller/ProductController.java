package com.myretail.myretailApp.controller;

import org.json.JSONException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.myretail.myretailApp.exception.MyRetailAppException;
import com.myretail.myretailApp.response.ProductDaoResponse;
import com.myretail.myretailApp.response.ProductResponse;

@RequestMapping ("/products")
public interface ProductController {
	
	@GetMapping(value= "/{id}", produces= { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ProductDaoResponse> getProductById(@PathVariable("id") String productId) throws MyRetailAppException, JSONException;
	
	
	@PutMapping(value= "/{id}", consumes= { MediaType.APPLICATION_JSON_VALUE })
	ResponseEntity<ProductResponse> updatePrice(@RequestBody ProductDaoResponse product,
			@PathVariable("id") String productId) throws MyRetailAppException;
	
	@ExceptionHandler(MyRetailAppException.class)
	ResponseEntity<ProductResponse> exceptionHandler(MyRetailAppException ex);
}
