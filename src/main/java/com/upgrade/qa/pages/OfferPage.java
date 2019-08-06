package com.upgrade.qa.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.upgrade.qa.base.TestBase;

public class OfferPage extends TestBase{
	
	@FindBy(xpath="//span[@data-auto='userLoanAmount']")
	WebElement userLoanAmount;
	
	@FindBy(xpath="//div[@data-auto='defaultMoreInfoAPR']")
	WebElement defaultMoreInfoAPR;
	
	@FindBy(xpath="//span[@data-auto='defaultMonthlyPayment']")
	WebElement defaultMonthlyPayment;
	
	@FindBy(css="div > header > div > label[class*='toggle']")
	WebElement Menu;
	
	@FindBy(xpath="//a[contains(@href,'logout')]")
	WebElement Signout;
	
	@FindBy(xpath="//h1[@data-auto='logoutMessage']")
	WebElement logOutMsg;
	
	@FindBy(xpath="//input[@data-auto='username']")
	WebElement userName;
	
	@FindBy(xpath="//input[@data-auto='password']")
	WebElement Password;
	
	@FindBy(xpath="//button[@data-auto='login']")
	WebElement loginButton;
	
	//Initializing the Page Objects:
	public OfferPage(){
		PageFactory.initElements(driver, this);
	}
	
	//Actions:
	public String getLoanAmount(){
		return userLoanAmount.getText();
	} //getLoanAmount method
	
	public String getdefaultMoreInfoAPR(){
		return defaultMoreInfoAPR.getText();
	} //getdefaultMoreInfoAPR method
	
	public String getdefaultMonthlyPayment(){
		return defaultMonthlyPayment.getText();
	} //defaultMonthlyPayment method
	
	public boolean clickMenu() {
		boolean flag = false;
		clickMenuItem(Menu);
		//Menu.click();
		WebDriverWait wait = new WebDriverWait(driver,10);
		flag = wait.until(ExpectedConditions.invisibilityOf(Menu));
		return flag;
	} //end of clickMenu method
	
	public BasicInfoPage login(String user,String pass) {
		userName.sendKeys(user.trim());
		Password.sendKeys(pass.trim());
		loginButton.click();
		return new BasicInfoPage();
	} //end of login method
		
	public boolean Signout() {
		boolean flag = false;
		WebDriverWait wait = new WebDriverWait(driver,5);
		Signout.click();
		/*
		 * try { Thread.sleep(3000); } catch (InterruptedException e) {
		 * e.printStackTrace(); }
		 */
		wait.until(ExpectedConditions.invisibilityOf(Signout)); 
		//wait.until(ExpectedConditions.visibilityOf(logOutMsg));
		if(logOutMsg.isDisplayed()) {
	    	flag = true;
		}
		return flag;
	 } //end of Signout method
	
}
