/*
 * @author Naveen Khunteta
 * 
 */

package com.qa.tests.web;

import java.io.IOException;
import org.apache.log4j.PropertyConfigurator;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.upgrade.qa.base.TestBase;
import com.upgrade.qa.pages.CheckRatePage;
import com.upgrade.qa.pages.BasicInfoPage;
import com.upgrade.qa.pages.OfferPage;

public class CheckRatePageTest extends TestBase{

	CheckRatePage checkRatePage;
	String sheetName = "CheckRate";
	
	public CheckRatePageTest()
	{
		super();
	}

		
	@DataProvider
	public Object[][] getLoanTestData(){
		Object data[][] = getTestData(sheetName);
		return data;
	}

	@Test(priority=1) 
	public void verifyCheckRate()
	{ 
		checkRatePage = new CheckRatePage();
		checkRatePage.verifyloanAmountInput();
	}
	
	@Test(priority=2)
	public void verifyCheckRateTitleTest(){
		String title = driver.getTitle();
		System.out.println("title is: " + title);
		if (title.contains("Check Your Rate")) {
			System.out.println("Title matched.Application launch is successful");
			Assert.assertTrue(true);
		} else {
			System.out.println("Title matched.Application launch is unsuccessful");
			Assert.assertTrue(false);
		}
	} //end of verifyHomePageTitleTest
	
	@Test(priority=3, dataProvider="getLoanTestData")
	public void EnterLoanDetails(String loanAmount,String loanPurpose)
	{
		checkRatePage.applyNewLoan(loanAmount,loanPurpose);
	}

	
}
