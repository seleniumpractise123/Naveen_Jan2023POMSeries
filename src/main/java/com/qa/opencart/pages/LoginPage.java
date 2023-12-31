package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.AppConstants;
import com.qa.opencart.utils.ElementUtil;

import io.qameta.allure.Step;

public class LoginPage {
	
	private WebDriver driver;
	private ElementUtil eleUtil;
	//1.const. of the page class
	public LoginPage(WebDriver driver) {
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	//2. private By locators:
	private By emailId = By.id("input-email");
	private By password = By.id("input-password");
	private By loginBtn = By.xpath("//input[@value='Login']");
	private By forgotPwdLink = By.linkText("Forgotten Password");
	private By footerLinks = By.xpath("//footer//a");
	private By loginerrorMes = By.cssSelector("div.alert.alert-danger.alert-dismissible");
	private By registerLink = By.linkText("Register");
	
	@Step("getting login page title")
	//3.Public page actions/Methods :
	public String getLoginPageTitle() {
		return eleUtil.waitForTitleIsAndCapture(AppConstants.LOGIN_PAGE_TITLE_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
	}
	
	@Step("getting login page url")
	public String getLoginPageURL() {
		return eleUtil.waitForURLContainsAndCapture(AppConstants.LOGIN_PAGE_URL_FRACTION_VALUE, AppConstants.SHORT_DEFAULT_WAIT);
		
	}
	
	@Step("checking forgot pwd link exist on the login page")
	public boolean isForgotPwdLinkExist() {
		return eleUtil.checkElementIsDisplayed(forgotPwdLink);
	}
	
	@Step("getting footer links")
	public List<String> getFooterElementsList() {
		List<WebElement> footerList = eleUtil.waitForElementsVisible(footerLinks, AppConstants.MEDIUM_DEFAULT_WAIT);
		List<String> footerTextList = new ArrayList<String>();
		for(WebElement e:footerList) {
			String text = e.getText();
			footerTextList.add(text);
		}
		return footerTextList;
	}
	
	@Step("login with correct username {0} and password {1}")
	public AccountsPage doLogin(String userName, String pwd) {
		System.out.println("Cred are : " + userName + ":" +  pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId,userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		//return the next landing page --- AccountsPage -- page Chaining model
		return new AccountsPage(driver);
		
	}
	
	@Step("login with incorrect username {0} and password {1}")
	public boolean doLoginwithWrongCred(String userName, String pwd) {
		System.out.println("Wrong Cred are : " + userName + ":" +  pwd);
		eleUtil.waitForElementVisible(emailId, AppConstants.MEDIUM_DEFAULT_WAIT);
		eleUtil.doSendKeys(emailId,userName);
		eleUtil.doSendKeys(password, pwd);
		eleUtil.doClick(loginBtn);
		String errorMessage = eleUtil.doGetElementText(loginerrorMes);
		System.out.println(errorMessage);
		if(errorMessage.contains(AppConstants.LOGIN_ERROR_MESSAGE)) {
			return true;
		}
		
		return false;

	}
	
	public RegisterPage navigateToRegisterPage() {
		eleUtil.doClick(registerLink);
		return new RegisterPage(driver);
	}
	
	

}
