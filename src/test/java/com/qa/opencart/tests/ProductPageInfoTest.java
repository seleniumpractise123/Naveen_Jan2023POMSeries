package com.qa.opencart.tests;

import java.util.Map;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class ProductPageInfoTest extends BaseTest{
	
	@BeforeClass
	public void searchSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
	}
	//Use below fields in product class and use in below code
	@Test
	public void productInfoTest() {
		resultsPage = accPage.doSearch("Macbook");
		productInfoPage = resultsPage.selectProduct("MacBook Pro");
		Map<String,String> productInfoMap = productInfoPage.getProductInfo();
		System.out.println(productInfoMap);
		//{Brand=Apple, Availability=In Stock, extaxprice=$2,000.00, Product Code=Product 18, 
		//productname=MacBook Pro, Reward Points=800, productprice=$2,000.00} --- Hash Map
		
		//{Brand=Apple, Product Code=Product 18, Reward Points=800, Availability=In Stock, 
		//productprice=$2,000.00, extaxprice=$2,000.00, productname=MacBook Pro} --- LinkedHashMap
		
		//{Availability=In Stock, Brand=Apple, Product Code=Product 18, Reward Points=800, 
	    //extaxprice=$2,000.00, productname=MacBook Pro, productprice=$2,000.00} --- Tree Map
		
		softAssert.assertEquals(productInfoMap.get("Brand"), "Apple");
		softAssert.assertEquals(productInfoMap.get("Availability"), "In Stock");
		softAssert.assertEquals(productInfoMap.get("productprice"), "$2,000.00");
		softAssert.assertEquals(productInfoMap.get("productname"), "MacBook Pro");
		softAssert.assertEquals(productInfoMap.get("Product Code"), "Product 18");
		softAssert.assertAll();
		
	}
	
	

}
