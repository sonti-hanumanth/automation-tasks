package com.upgrade.qa.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.CacheLookup;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.upgrade.qa.base.TestBase;

public class BasicInfoPage extends TestBase {

	
	  @FindBy(xpath = "//div/h2[contains(text(),'Check your rate')]")
	  WebElement title;
	  
	  String locator = "//div[@class='sc-kIPQKe iFTvdT']";
	  
	  @FindBy(xpath ="//input[@name='borrowerFirstName']")
	  WebElement borrowerFirstName;
	  
	  @FindBy(xpath ="//input[@name='borrowerLastName']")
	  WebElement borrowerLastName;
	  
	  @FindBy(xpath ="//input[@name='borrowerStreet']")
	  WebElement borrowerHomeAddress;
	  
	  @FindBy(xpath ="//input[@name='borrowerCity']")
	  WebElement borrowerCity;
	  
	  @FindBy(xpath ="//input[@name='borrowerState']")
	  WebElement borrowerState;
	  
	  @FindBy(xpath ="//input[@name='borrowerZipCode']")
	  WebElement borrowerZipCode;
	  
	  @FindBy(xpath ="//input[@name='borrowerDateOfBirth']")
	  WebElement borrowerDateOfBirth;
	  
	  @FindBy(xpath ="//input[@name='borrowerIncome']")
	  WebElement borrowerIncome;
	  
	  @FindBy(xpath ="//input[@name='borrowerAdditionalIncome']")
	  WebElement borrowerAdditionalIncome;
	  
	  @FindBy(xpath ="//input[@name='username']")
	  WebElement username;
	  
	  @FindBy(xpath = "//input[@type='password']")
	  WebElement password;
	  
	  @FindBy(xpath ="//input[@name='agreements']/following::div[contains(@class,'sc')]")	  
	  WebElement agreements;
					
	  @FindBy(xpath ="//button[@data-auto='submitPersonalInfo']")
	  WebElement submitButton;
	  
	  OfferPage offerPage;
	  
	  public static String getUserName = "";
	  public static String getPassword = "";

	  
	 // Initializing the Page Objects: 
	 public BasicInfoPage() {
	    PageFactory.initElements(driver, this); 
	 }
	 
	 public String verifyPageTitle(){ 
		 return driver.getTitle(); 
     }
	 
	 public boolean checkIndividualisselectedByDefault() {
		 return checkRadioButtondefault(locator);
	 }
	 
	 public void enterBasicInfo(String fname,String lname,String homeAddress,String city
			 ,String state,String zipCode,String dob,String Income,String additionalIncome,String user,String pass) {
		  borrowerFirstName.sendKeys(randomString(fname));
		  borrowerLastName.sendKeys(randomString(lname));
		  borrowerHomeAddress.sendKeys(homeAddress);
		  borrowerCity.sendKeys(city);
		  borrowerState.sendKeys(state);
		  borrowerZipCode.sendKeys(zipCode);
		  borrowerDateOfBirth.sendKeys(dob);
		  borrowerIncome.sendKeys(Income);
		  borrowerAdditionalIncome.sendKeys(additionalIncome);
		  username.sendKeys(user+randomNumber()+"@upgrade-challenge.com");
		  getUserName  = username.getAttribute("value"); //get username;
		  password.sendKeys(pass);
		  getPassword = password.getAttribute("value");  //get password
		  WebDriverWait wait = new WebDriverWait(driver,10);	    	
	      wait.until(ExpectedConditions.visibilityOf(agreements));
		  boolean isChecked = agreements.isSelected();
		  if(isChecked==false) {
			  if(agreements.isDisplayed())
			  {
				  agreements.click(); 
			  }
			  
		  }
		  wait.until(ExpectedConditions.visibilityOf(submitButton));
		  submitButton.click();
		  		 
	 }
	 
}
