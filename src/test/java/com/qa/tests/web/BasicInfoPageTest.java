package com.qa.tests.web;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.upgrade.qa.base.TestBase;
import com.upgrade.qa.pages.BasicInfoPage;
import com.upgrade.qa.pages.CheckRatePage;
import com.upgrade.qa.pages.OfferPage;

public class BasicInfoPageTest extends TestBase {
	
	BasicInfoPage basicInfoPage;
	CheckRatePage checkRatePage;
	OfferPage offerPage;
	String sheetName = "BasicInfo";
	String getBeforeLoanAmt ="";
	String getBeforeAPR="";
	String getBeforeMonthlyPayment="";

	public BasicInfoPageTest() {
		super();
	}
	
	@DataProvider
	public Object[][] getBorrowerTestData(){
		Object data[][] = getTestData(sheetName);
		return data;
	}

	@Test(priority=4)
	public void verifydefaultRadioButton(){
		basicInfoPage = new BasicInfoPage();
		boolean flag = basicInfoPage.checkIndividualisselectedByDefault();
		Assert.assertEquals(flag, true,"Individual is not selected by default");
	}
	
	@Test(priority=5, dataProvider="getBorrowerTestData")
	public void enterBorrowerInfo(String fname,String lname,String homeAddress,String city,String state,String zipCode,
			String dob,String Income, String additionalIncome,String user,String pass)
	{
		basicInfoPage.enterBasicInfo(fname, lname, homeAddress, city, state, zipCode, dob, Income, 
				additionalIncome, user, pass);
	}
	

	@Test(priority=6)
	public void Signout(){
		offerPage = new OfferPage();
		getBeforeLoanAmt = offerPage.getLoanAmount();
		System.out.println("Before Loan Amount:"+getBeforeLoanAmt);
		
		getBeforeAPR = offerPage.getdefaultMoreInfoAPR();
		System.out.println("Before APR:"+getBeforeAPR);
		
		getBeforeMonthlyPayment = offerPage.getdefaultMonthlyPayment();
		System.out.println("Before Monthly Payment:"+getBeforeMonthlyPayment );
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		//Click Menu
		Assert.assertEquals(offerPage.clickMenu(),true);
		
		//Click Signout
		Assert.assertEquals(offerPage.Signout(),true);
		
		driver.quit(); //close the browser
		
	}
	
	@Test(priority=7)
	public void SignIn(){
		
		reInitialization();
		
		offerPage = new OfferPage();
		
		//login to offerPage
		offerPage.login(basicInfoPage.getUserName,basicInfoPage.getPassword);
		
		//offerPage = new OfferPage();
		String getAfterLoanAmt = offerPage.getLoanAmount();
		System.out.println("After Loan Amount:"+getAfterLoanAmt);
		
		Assert.assertEquals(getAfterLoanAmt, getBeforeLoanAmt,
				"Before loan Amt: "+getBeforeLoanAmt+" After Loan APR "+ getAfterLoanAmt);
				
		String getAfterAPR = offerPage.getdefaultMoreInfoAPR();
		System.out.println("After APR:"+getAfterAPR);
		
		Assert.assertEquals(getAfterAPR, getBeforeAPR,
				"Before Loan APR: "+getBeforeAPR+" After loan Amount "+ getAfterAPR);
		
		String getAfterMonthlyPayment = offerPage.getdefaultMonthlyPayment();
		System.out.println("After Monthly Payment:"+getAfterMonthlyPayment );
		
		Assert.assertEquals(getAfterMonthlyPayment, getBeforeMonthlyPayment,
		"Before Monthly Payment: "+getBeforeMonthlyPayment+" After Monthly Payment "+ getAfterMonthlyPayment);
	}
	 
	
}
