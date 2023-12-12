package com.qa.opencart.dataproviders;

import org.testng.annotations.DataProvider;

import com.qa.opencart.pojo.Product;

public class ProductDataProvider {
	
	@DataProvider(name="productData")
	public Object[][] getProductTestData() {
		return new Object[][] {
			{new Product("Macbook", "MacBook Pro", 4)},
			{new Product("iMac" ,"iMac", 3)},
			{new Product("Samsung", "Samsung Galaxy Tab 10.1", 7)},
			{new Product("Samsung", "Samsung SyncMaster 941BW",1)},
			{new Product("Canon", "Canon EOS 5D",3)}
			
		};
		
	}
	
	@DataProvider(name="productDataWithSearchKey")
	public Object[][] getProductsearchKeyData() {
		
		return new Object[][] {
			{"Macbook"},
			{"iMac"},
			{"Samsung"},
			{"Canon"}
		};
	}
	
	@DataProvider(name="productDataWithName")
	public Object[][] getProductNameData() {
		
		return new Object[][] {
			{"Macbook", "MacBook Pro"},
			{"iMac" ,"iMac"},
			{"Samsung", "Samsung Galaxy Tab 10.1"},
			{"Samsung", "Samsung SyncMaster 941BW"},
			{"Canon", "Canon EOS 5D"}
		};
	}
	
	@DataProvider(name="productDataWithImage")
	public Object[][] getProductImagesData() {
		
		return new Object[][] {
			{"Macbook", "MacBook Pro", 4},
			{"iMac" ,"iMac", 3},
			{"Samsung", "Samsung Galaxy Tab 10.1", 7},
			{"Samsung", "Samsung SyncMaster 941BW",1},
			{"Canon", "Canon EOS 5D",3}
		};
	}

}
