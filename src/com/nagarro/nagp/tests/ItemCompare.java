package com.nagarro.nagp.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.nagarro.nagp.core.ManageDriver;
import com.nagarro.nagp.enums.WebElements;

import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;

public class ItemCompare {

	WebDriver wd;
	WebElement element;
	Actions action;
	WebDriverWait wait;

	@Test
	public void f() {
		try {
			
			//Hover over to the Category Women element which ultimately draws the sub category section
			wait.until(ExpectedConditions.elementToBeClickable(ManageDriver.getElement(WebElements.categoryWomen)));
			element = ManageDriver.getElement(WebElements.categoryWomen);
			action.moveToElement(element).perform();
			
			//Clicks on the sub category "T-Shirt" under Women category
			ManageDriver.getElement(WebElements.subCategoryTShirt).click();
			
			//Store the name of the first item of the category 
			wait.until(ExpectedConditions.elementToBeClickable(ManageDriver.getElement(WebElements.firstItem)));
			String categoryItemName = ManageDriver.getElement(WebElements.firstItem).getText();

			//searches the name of the first item that we stored previously
			ManageDriver.getElement(WebElements.searchTextBox).sendKeys(categoryItemName);
			ManageDriver.getElement(WebElements.searchButton).click();

			//stores the name of the first item after searching 
			wait.until(ExpectedConditions.elementToBeClickable(ManageDriver.getElement(WebElements.firstItem)));
			String searchItemName = ManageDriver.getElement(WebElements.firstItem).getText();

			//Compares the name of the items from category and search page.
			Assert.assertEquals(categoryItemName, searchItemName);
			System.out.println("categoryItemName: " + categoryItemName + " >> searchItemName: " + searchItemName);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@BeforeTest
	public void beforeTest() throws IOException {
		try {
			ManageDriver.init();
			wd = ManageDriver.getDriver();
			action = new Actions(wd);
			wait = new WebDriverWait(wd, 20);
			Thread.sleep(5000);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@AfterTest
	public void afterTest() {
		try {
			ManageDriver.quitDriver();

		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
