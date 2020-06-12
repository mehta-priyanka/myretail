package com.myretail.myretailApp.service;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.myretail.myretailApp.dao.CurrentPrice;
import com.myretail.myretailApp.dao.Product;
import com.myretail.myretailApp.exception.MyRetailAppException;
import com.myretail.myretailApp.repository.ProductRepository;

@Service
public class DataLoadService {

	@Autowired
	private ProductRepository productRepository;

	public DataLoadService() {};

	@PostConstruct
	public void init() throws MyRetailAppException{
		loadProductDataInDB();
	}

	private void loadProductDataInDB() {

		if(productRepository != null) {

			List<Product> prodList = new ArrayList<Product>();
			CurrentPrice currentPrice1 = new CurrentPrice();
			currentPrice1.setCurrencyCode("USD");
			currentPrice1.setValue(13.49);
			Product product1 = new Product("13860428",currentPrice1);
			prodList.add(product1);

			CurrentPrice currentPrice2 = new CurrentPrice();
			currentPrice2.setCurrencyCode("USD");
			currentPrice2.setValue(18.99);
			Product product2 = new Product("54456119",currentPrice2);
			prodList.add(product2);

			CurrentPrice currentPrice3 = new CurrentPrice();
			currentPrice3.setCurrencyCode("USD");
			currentPrice3.setValue(29.49);
			Product product3 = new Product("13264003",currentPrice3);
			prodList.add(product3);
			
			CurrentPrice currentPrice4 = new CurrentPrice();
			currentPrice4.setCurrencyCode("USD");
			currentPrice4.setValue(20.99);
			Product product4 = new Product("12954218",currentPrice4);
			prodList.add(product4);

			productRepository.deleteAll();
			productRepository.saveAll(prodList);
		}
	}

}