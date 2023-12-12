package com.qa.opencart.tests;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.qa.opencart.base.BaseTest;
import com.qa.opencart.utils.AppConstants;

public class AccountsPageTest extends BaseTest {
	
	@BeforeClass
	public void accPageSetup() {
		accPage = loginPage.doLogin(prop.getProperty("username"), prop.getProperty("password"));
		
	}
	
	@Test
	public void accPageTitleTest() {
		String actTitle = accPage.getAccPageTitle();
		Assert.assertEquals(actTitle, "My Account");
	}
	
	@Test
	public void isLogoutLinkExistTest() {
		Assert.assertTrue(accPage.isLogoutLinkExist());
	}
	
	@Test
	public void ismyAccLinkExistTest() {
		Assert.assertTrue(accPage.ismyAccLinkExist());
	}
	@Test
	public void PageHeadersCountTest() {
		List<String> actPageHeaders = accPage.getAccPageHeaders();
		Assert.assertEquals(actPageHeaders.size(), 4);
	}
	
	@Test
	public void pageHeadersTest() {
		List<String> actPageHeaders = accPage.getAccPageHeaders();
		List<String> expPageHeaders = AppConstants.EXP_ACCOUNTS_HEADERS_LIST;
		Assert.assertEquals(actPageHeaders, expPageHeaders);
		
	}
	
	

}
