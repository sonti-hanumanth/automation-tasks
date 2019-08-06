package com.upgrade.ExtentReportListener;

import java.nio.charset.Charset;
import java.util.Random;


import org.apache.commons.lang3.RandomStringUtils;

public class testmain {

	public static void main(String[] args) {
		//System.out.println(System.getProperty("user.dir"));
		
		//randomString("test");
		

		Random rand = new Random();

		// Obtain a number between [0 - 49].
		int n = rand.nextInt(1000);

		// Add 1 to the result to get a number from the required range
		// (i.e., [1 - 50]).
		n += 1;
		 System.out.println(n);

	}
	
	public static String randomString(String inputStr) {
		int length = 10;
	    boolean useLetters = true;
	    boolean useNumbers = false;
	    String generatedString = RandomStringUtils.random(length, useLetters, useNumbers);
	 
	    System.out.println(inputStr+generatedString);
	    return inputStr+generatedString;
	}
	
	/*
	 * public String randomString() { int leftLimit = 97; // letter 'a' int
	 * rightLimit = 122; // letter 'z' int targetStringLength = 10; Random random =
	 * new Random(); StringBuilder buffer = new StringBuilder(targetStringLength);
	 * for (int i = 0; i < targetStringLength; i++) { int randomLimitedInt =
	 * leftLimit + (int) (random.nextFloat() * (rightLimit - leftLimit + 1));
	 * buffer.append((char) randomLimitedInt); } String generatedString =
	 * buffer.toString();
	 * 
	 * System.out.println(generatedString); return generatedString;
	 * 
	 * } //generate random string
	 */
}
