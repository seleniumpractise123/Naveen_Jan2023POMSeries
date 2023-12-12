package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;

public class LoginPageNegitiveTest extends BaseTest {
	
	@DataProvider
	public Object[][] incorrectLoginTestData() {
		return new Object[][] {
			
			{"auto123@gmail.com", "123456"},
			{"test@@gmail.com", "123456"},
			{"auto123", "test"},
			{"auto123@gmail.com", "12345678911"},
			{"@#@#@#@#@#@#", "!@!@!@!@!@!@!@@"}
			
		};
		
	}
	
	@Test(dataProvider = "incorrectLoginTestData")
	public void loginWithWrongCredTest(String userName, String password) {
		
		Assert.assertTrue(loginPage.doLoginwithWrongCred(userName, password));
	}

}
