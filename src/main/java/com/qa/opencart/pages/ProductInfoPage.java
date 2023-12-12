package com.qa.opencart.pages;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;

public class ProductInfoPage {

	private WebDriver driver;
	private ElementUtil eleUtil;

	public ProductInfoPage(WebDriver driver) {
		// TODO Auto-generated constructor stub
		
		this.driver = driver;
		eleUtil = new ElementUtil(this.driver);
	}
	
	private By productHeader = By.cssSelector("div#content h1");
	private By ProductImages = By.cssSelector("ul.thumbnails img");
	private By productMetaData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=1]/li");
	private By productPriceData = By.xpath("(//div[@id='content']//ul[@class='list-unstyled'])[position()=2]/li");
	private By quantity = By.id("input-quantity");
	private By addToCartBtn = By.id("button-cart");
	private Map<String, String> productInfoMap;
	
	public String getProductHeaderName() {
		return eleUtil.doGetElementText(productHeader);
	}
	
	public int getProductImagesCount() {
		return eleUtil.waitForElementsVisible(ProductImages, 10).size();
	}
	
	public Map<String, String> getProductInfo() {
		//productInfoMap = new HashMap<String, String>();
		//productInfoMap = new LinkedHashMap<String, String>();
		productInfoMap = new TreeMap<String, String>();
		getProductMetaData();
		getProductPriceData();
		productInfoMap.put("productname", getProductHeaderName());
		return productInfoMap;
	}
	private void getProductMetaData() {
		List<WebElement> metaList = eleUtil.getElements(productMetaData);
		
		for(WebElement e: metaList) {
			String metaText = e.getText();
			String metaInfo[] = metaText.split(":");
			String Key = metaInfo[0].trim();
			String value = metaInfo[1].trim();
			productInfoMap.put(Key, value);
		}
	}
	
	private void getProductPriceData() {
		List<WebElement> priceList = eleUtil.getElements(productPriceData);
		String actPriceValue = priceList.get(0).getText();
		String exTaxPrice = priceList.get(1).getText();
		String exTaxPriceValue = exTaxPrice.split(":")[1].trim();
		productInfoMap.put("productprice", actPriceValue);
		productInfoMap.put("extaxprice", exTaxPriceValue);
	}
	

}
