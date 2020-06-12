package com.myretail.myretailApp.service;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import com.myretail.myretailApp.dao.Product;
import com.myretail.myretailApp.exception.MyRetailAppException;
import com.myretail.myretailApp.repository.ProductRepository;
import com.myretail.myretailApp.response.ProductDaoResponse;
import com.myretail.myretailApp.util.ProductUtility;

@Service
public class ProductService {
protected Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ProductRepository productRepository;
	
	@Autowired
	ProductUtility helperObject;
	
	public ProductService() {
		// TODO Auto-generated constructor stub
	}
	
	@Value("${product-api-endpoint}")
	private String apiEndpointURL;
	private String api_URI = "/v2/pdp/tcin/";
	private String productName= null;
	
	public ProductDaoResponse getProductById(String productId) throws MyRetailAppException, JSONException {
		logger.info("Inside ProductService().getProductById");
		
		Product product = new Product();
		String productName=null;
		try {
		//retrive product from MongoDB
		product = productRepository.findProductByproductId(productId);
		if(product == null) {
			logger.debug("Product Not Found in Database ");
			throw new MyRetailAppException(HttpStatus.NOT_FOUND.value(),"Product not found in DB");
		}
		productName = extractProductName(productId);
		
		}catch(MyRetailAppException e) {
			throw e; 
		}
		return helperObject.generateProductResponse(product,productName);

	}

	public void updateProductById(ProductDaoResponse productVO) throws MyRetailAppException{
		try {
		Product product =helperObject.getProductDomainObject(productVO);
		productRepository.save(product);
		} catch (Exception exception) {
			logger.debug("Exception while updating data, Product Not Found" + exception);
			throw new MyRetailAppException(HttpStatus.NOT_FOUND.value(),"Product not found while update");
		}
	}

	//method to call Redsky API to fetch product title
	public String extractProductName(String productId) throws MyRetailAppException, JSONException{
		String productURL = apiEndpointURL+api_URI+productId;

		try {			
			RestTemplate restTemplate = new RestTemplate();
			String jsonResponse = restTemplate.getForObject(productURL, String.class);
			
			if(jsonResponse != null) {
				JSONObject jsonObject=new JSONObject(jsonResponse);
				logger.debug("JSON Response  :" + jsonResponse.toString());

				if(jsonObject.getJSONObject("product").getJSONObject("item").getJSONObject("product_description") != null) {
					JSONObject productDescription = jsonObject.getJSONObject("product").getJSONObject("item").getJSONObject("product_description");
					productName = productDescription.getString("title");
				}
				else {
					logger.debug("Product title not available in API");
					throw new MyRetailAppException(HttpStatus.NO_CONTENT.value() ,"The title does not exists for the product" );
				}
			}
		} catch (RestClientException e) {
			logger.debug("Product API unavailable  :" + productURL);
			throw new MyRetailAppException(HttpStatus.NOT_FOUND.value() ,"Product API unavailable");
		}
		return productName;
	}

}
