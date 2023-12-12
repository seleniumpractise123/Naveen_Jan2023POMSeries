package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.frameworkexception.FrameException;


public class DriveryFactory {

	WebDriver driver;
	OptionsManager optionsManager;
	//public static String highlightElement;
	public Properties prop;

	public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();
	public static Logger log = LogManager.getLogger(DriveryFactory.class);
	//info,warn,error,fatal

	public WebDriver initDriver(Properties prop) {
		String browserName = prop.getProperty("browser").trim();

		// String browserName = System.getProperty("browser");

		System.out.println("browser name is : " + browserName);

		//highlightElement = prop.getProperty("highlight");

		optionsManager = new OptionsManager(prop);

		switch (browserName.toLowerCase()) {
		case "chrome":
			tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
			break;

		case "firefox":
			tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));

			break;

		case "edge":
			tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));

			break;
		case "safari":
			tlDriver.set(new SafariDriver());
			break;

		default:
			log.error("plz pass the right browser ...." + browserName);
			System.out.println("plz pass the right browser ...." + browserName);
			throw new FrameException("NOBROWSERFOUNDEXCEPTION");
		}

		getDriver().manage().deleteAllCookies();
		getDriver().manage().window().maximize();
		getDriver().get(prop.getProperty("url"));
		return getDriver();

	}

	

	// return the thread local copy of the driver
	public synchronized static WebDriver getDriver() {
		return tlDriver.get();
	}

	public Properties initProp() {

		// mvn clean install -Denv="stage" -- cmd line, jenkins
		// mvn clean install
		prop = new Properties();
		FileInputStream ip = null;

		String envName = System.getProperty("env");
		System.out.println("env name is: " + envName);

		try {
			if (envName == null) {
				System.out.println("no env is given...hence running it on QA env...");
				ip = new FileInputStream("./src/main/resource/config/qa.config.properties");

			} else {
				System.out.println("Running test cases on env: " + envName);
				switch (envName.toLowerCase().trim()) {
				case "qa":
					ip = new FileInputStream("./src/main/resource/config/qa.config.properties");
					break;
				case "dev":
					ip = new FileInputStream("./src/main/resource/config/dev.config.properties");
					break;
				case "stage":
					ip = new FileInputStream("./src/main/resource/config/stage.config.properties");
					break;
				case "uat":
					ip = new FileInputStream("./src/main/resource/config/uat.config.properties");
					break;
				case "prod":
					ip = new FileInputStream("./src/main/resource/config/config.properties");
					break;

				default:
					System.out.println("plz pass the right env name...." + envName);
					throw new FrameException("NOVALIDENVGIVEN");
				}
			}
		}

		catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			prop.load(ip);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return prop;

	}

	/**
	 * take screenshot
	 */
	public static String getScreenshot() {
		File srcFile = ((TakesScreenshot) getDriver()).getScreenshotAs(OutputType.FILE);
		String path = System.getProperty("user.dir") + "/screenshot/" + System.currentTimeMillis() + ".png";
		File destination = new File(path);

		try {
			FileUtils.copyFile(srcFile, destination);
		} catch (IOException e) {
			e.printStackTrace();
		}

		return path;
	}

}