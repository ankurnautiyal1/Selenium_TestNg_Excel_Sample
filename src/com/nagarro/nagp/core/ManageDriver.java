package com.nagarro.nagp.core;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.nagarro.nagp.enums.WebElements;

/* ManageDriver class provides the methods which lets the developer
 * easily initialize the driver and start the browser as per the 
 * properties of config.properties file.
 */

public class ManageDriver {
	static WebDriver webDriver;

	/* Initializes the WebDriver object and starts the browser
	 * with the help of ReadProperties class.
	 */
	public static void init() throws IOException {
		
		System.out.println("Inside ManageDriver.init()");
		String browser = ReadProperties.getProperty("browser").toLowerCase();
		String aut = ReadProperties.getProperty("AUT");
		
		String cwd = System.getProperty("user.dir");
		String driverLocation = "\\src\\com\\nagarro\\nagp\\resources\\drivers";
		
		
		switch(browser) {
		case "chrome":{
			System.setProperty("webdriver.chrome.driver", cwd+driverLocation+"\\chromedriver.exe");
			webDriver = new ChromeDriver();
			break;
		}
		case "firefox":{
			System.setProperty("webdriver.gecko.driver", cwd+driverLocation+"\\geckodriver.exe");
			webDriver = new FirefoxDriver();
			break;
		}
		default: System.out.println("Browser property is invalid");
		}
		
		webDriver.manage().window().maximize();
		webDriver.get(aut);
	}
	
	/* returns the driver attached to the current instance of the browser
	 * 
	 */
	public static WebDriver getDriver() {
		return webDriver;
	}
	/* closes the instance of the WebDriver instance.
	 * 
	 */
	public static void quitDriver() {
		webDriver.quit();
	}
	/* Just a wrapper on the default get() of the WebDriver class
	 * It has the same functionality of opening or redirecting to the 
	 * given URL
	 */
	public static void get(String url) {
		webDriver.get(url);
	}
	
	/* Returns the WebElement object after finding the element with respect
	 * to the given WebElements Enum.
	 * It will return null if there is no element matched with the given WebElements Enum.
	 */
	public static WebElement getElement(WebElements element) throws IOException {
			
		return webDriver.findElement(By.xpath(ExcelReader.getXpath(element)));
		
	}
}
