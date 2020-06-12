package com.myretail.myretailApp.dao;

//This is a data object that has product price and its currency code details.

public class CurrentPrice {
	
	private Double value;
	private String currencyCode;

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public String getCurrencyCode() {
		return currencyCode;
	}

	public void setCurrencyCode(String currencyCode) {
		this.currencyCode = currencyCode;
	}

	/**
	 * @param value
	 * @param currencyCode
	 */
	public CurrentPrice(Double value, String currencyCode) {
		this.value = value;
		this.currencyCode = currencyCode;
	}

	public CurrentPrice() {
		// TODO Auto-generated constructor stub
	}

}
