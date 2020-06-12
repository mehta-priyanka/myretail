package com.myretail.myretailApp.util;

import org.springframework.stereotype.Component;


import com.myretail.myretailApp.dao.*;
import com.myretail.myretailApp.exception.MyRetailAppException;
import com.myretail.myretailApp.response.*;

@Component
public class ProductUtility {
	public ProductUtility() {
		// TODO Auto-generated constructor stub
	}

	public ProductDaoResponse generateProductResponse(Product product, String productName) throws MyRetailAppException {


		ProductDaoResponse prodResponse = new ProductDaoResponse();
		CurrentPriceResponse currentPriceResponse= new CurrentPriceResponse();
		try{
			currentPriceResponse.setCurrencyCode(product.getCurrentPrice().getCurrencyCode());
			currentPriceResponse.setValue(product.getCurrentPrice().getValue());

			prodResponse.setProductId(product.getProductId());
			prodResponse.setCurrentprice(currentPriceResponse);
			prodResponse.setName(productName);
		}
		catch(Exception e) {
			throw new MyRetailAppException();
		}
		return prodResponse;
	}

	public Product getProductDomainObject(ProductDaoResponse productRes) {
		Product product = new Product();
		CurrentPrice currentPrice = new CurrentPrice();
		product.setProductId(productRes.getProductId());
		currentPrice.setCurrencyCode(productRes.getCurrentprice().getCurrencyCode());
		currentPrice.setValue(productRes.getCurrentprice().getValue());
		product.setCurrentPrice(currentPrice);
		return product;
	}

}
