package com.upgrade.qa.base;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;


public class TestBase {

	//public static WebDriver driver;
	protected static RemoteWebDriver driver;
	public static Properties prop;
	public static long PAGE_LOAD_TIMEOUT = 20;
	public static long IMPLICIT_WAIT = 20;
	public static String TESTDATA_SHEET_PATH = System.getProperty("user.dir")+
			"\\src\\main\\java\\com\\upgrade\\qa\\testdata\\WebTestData.xlsx";
	static Workbook book;
	static Sheet sheet;

	public TestBase(){
		try {
			prop = new Properties();
			FileInputStream ip = new FileInputStream(System.getProperty("user.dir")+ 
					"\\src\\main\\java\\com\\upgrade\\qa\\config\\config.properties");
			prop.load(ip);
			//initialization();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@BeforeTest
	public static void initialization(){
		//String browserName = prop.getProperty("browser");
		
		/*
		 * if(browserName.equals("chrome")){
		 
		 * } else if(browserName.equals("FF")){
		 * System.setProperty("webdriver.gecko.driver",
		    driver = new
		 * FirefoxDriver(); }
		 */
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		//driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		//driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get(prop.getProperty("url"));
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		
	   }//end of initializatio method
	
     public static Object[][] getTestData(String sheetName) {
		FileInputStream file = null;
		try {
			file = new FileInputStream(TESTDATA_SHEET_PATH);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		try {
			book = WorkbookFactory.create(file);
		} catch (InvalidFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = book.getSheet(sheetName);
		Object[][] data = new Object[sheet.getLastRowNum()][sheet.getRow(0).getLastCellNum()];
		for (int i = 0; i < sheet.getLastRowNum(); i++) {
			for (int k = 0; k < sheet.getRow(0).getLastCellNum(); k++) {
				data[i][k] = sheet.getRow(i + 1).getCell(k).toString();
				// System.out.println(data[i][k]);
			}
		}
		return data;
	} //end of getTestData method


    public static boolean checkRadioButtondefault(String locator) {
		 boolean flag = false;
    	 //Get all checkbox elements in a list
        List<WebElement> list = driver.findElements(By
                .cssSelector("input[type='radio']"));
        // Loops through all radio buttons
        for (int i = 0; i < list.size(); i++) {
            // Checking 
            if ((list.get(i).isSelected()))
           {
                // Print selection status to console
                System.out.println("radio button is selected by default:"+list.get(i).isSelected());
                flag = true;
               
            }
        }
    	 return flag;
      } //end of checkRadioButtondefault
    
    public static String randomString(String inputStr) {
		int length = 10;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    System.out.println(inputStr+generatedString);
	    return inputStr+generatedString;
	} //end of randomString method
    
    public static int randomNumber() {
    	Random rand = new Random();
    	// Obtain a number between [0 - 49].
    	int n = rand.nextInt(1000);
    	// Add 1 to the result to get a number from the required range
     	n += 1;
    	System.out.println(n);
    	return n;
    } //end of randomNumber
    
    public static void reInitialization()
    {
		
		System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir")+"\\src\\main\\resources\\chromedriver.exe");	
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		driver.get("https://www.credify.tech/portal/login");
		driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT, TimeUnit.SECONDS);
		
	   }//end of reInitialization method
    
  //Click Top Level Menu
 	public static boolean clickMenuItem(WebElement element) 
 	{
 		try {
				 Actions actions = new Actions(driver);
				 actions.moveToElement(element).click().perform();		
				 return true;
			} catch (Exception e) {			
				e.printStackTrace();	
				return false;
		}
		
	}  //end of method click_Menu	
    
	
	@AfterTest
	public void tearDown(){
		driver.quit();
	} //end of teardown method

}
