package com.upgrade.qa.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import com.upgrade.qa.base.TestBase;

public class CheckRatePage extends TestBase {

	//Loan Amount
	@FindBy(xpath = "//input[@name='desiredAmount']")
	WebElement loanAmountInput;
	
	//Loan Purpose
	String locator = "//select[@data-auto='dropLoanPurpose']";
	
	//Check Rate
	@FindBy(xpath = "//button[@data-auto='CheckYourRate']")
	WebElement checkYourRateButton;
	
    // Initializing the Page Objects:
	public CheckRatePage() {
		PageFactory.initElements(driver, this);
	}
	
	public boolean verifyloanAmountInput(){
		return loanAmountInput.isDisplayed();
	} //end of verifyloanAmountInput method
	
	
	public void applyNewLoan(String loanAmount,String loanPurpose) 
	{
		loanAmountInput.sendKeys(loanAmount);
		Select listbox = new Select(driver.findElement(By.xpath(locator)));
		listbox.selectByVisibleText(loanPurpose);
		//selectDropDownValue(locator,loanPurpose);
		checkYourRateButton.click();
	 } //end of applyNewLoan method
	
	
	
}
