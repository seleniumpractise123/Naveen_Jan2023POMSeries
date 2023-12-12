package com.qa.opencart.tests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ExcelUtil;

public class RegisterPageTest extends BaseTest{
	
	@BeforeClass
	public void regSetup() {
		registerPage = loginPage.navigateToRegisterPage();
	}
	
	public String getRandomEmailId() {
		 return "testautomationlearning"+System.currentTimeMillis()+"@gmail.com";
	}
	/*
	@DataProvider(name="regData")
	public Object[][] getUserRegTestData() {
		return new Object[][] {
			{"Naveen","AutomationEngineer","987865467", "test@123", "yes"},
			{"Ashok","AutomationLearner","98786546786", "test@1234", "no"},
			{"Sirivella","AutomationEngineer123","9878654677", "test@12345", "yes"},
		};
	}
	*/
	@DataProvider(name="regExcelData")
	public Object[][] getRegExcelTestData() {
		Object regData[][] = ExcelUtil.getTestData(AppConstants.REGISTER_SHEET_NAME);
		return regData;
	}
	@Test(dataProvider = "regExcelData")
	public void userRegisterTest(String firstName, String lastName, String telephone, String password, String subscribe) {
		
		String actualRegSuccMessg = 
				registerPage.registerUser(firstName, lastName, getRandomEmailId(), telephone, password, subscribe);
		
		Assert.assertEquals(actualRegSuccMessg, AppConstants.USER_RESG_SUCCESS_MESSG);
		
		
	}

}
