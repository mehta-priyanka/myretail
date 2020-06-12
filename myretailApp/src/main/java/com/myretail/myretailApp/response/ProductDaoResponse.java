package com.myretail.myretailApp.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class ProductDaoResponse {
	@JsonProperty(value="id")
	String productId;
	
	@JsonProperty(value="name")
	String name;
	
	@JsonProperty(value="current_price")
	CurrentPriceResponse currentprice;

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CurrentPriceResponse getCurrentprice() {
		return currentprice;
	}

	public void setCurrentprice(CurrentPriceResponse currentprice) {
		this.currentprice = currentprice;
	}

	@Override
	public String toString() {
		return "ProductResponse [productId=" + productId + ", name=" + name + ", currentprice=" + currentprice + "]";
	}

}
