package com.myretail.myretailApp.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.myretail.myretailApp.dao.Product;

public interface ProductRepository extends MongoRepository<Product,String> {
	
	
	/**
	 * @param productId
	 * @return
	 */
	public Product findProductByproductId(String productId);
	

}