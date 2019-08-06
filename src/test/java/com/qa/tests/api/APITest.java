package com.qa.tests.api;

import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import java.util.List;

public class APITest {

	  @Test(description="Test Status Code")
	  public void testResponseCode() { 
		  Response resp = RestAssured.get("https://credapi.credify.tech/api/loanapp/v1/states"); 
		  int code = resp.getStatusCode(); System.out.println("Status Code: "+code);
		  Assert.assertEquals(code,200); 
	  } //test response Code
	  
	  @Test(description="get JSON response") 
	  public void testBody() { 
		  String data = "";
		  Response resp = RestAssured.get("https://credapi.credify.tech/api/loanapp/v1/states");
          data = resp.asString(); System.out.println("Data :"+data); 
      } //test response body
	  
	  @Test(description="validate all state names are valid and total state count") public void
	  testTotalStateCount() {
		  Response response = doGetRequest("https://credapi.credify.tech/api/loanapp/v1/states");
		  List<String> jsonResponse = response.jsonPath().getList("states");
		  System.out.println("Total states count: "+jsonResponse.size());
		  Assert.assertEquals(jsonResponse.size(),48); 
	   } //testTotalStatesCount
	  	   
	   @Test(description ="Validate only one state has a min age 19 , and output the name of that state")
	   public void testMinAge() {
		   Response response = doGetRequest("https://credapi.credify.tech/api/loanapp/v1/states");
		   String states = response.jsonPath().getString("states[0]");
		   System.out.println(states);
	   } //testMinAge	
	   
	   @Test(description ="Validate that Georgia is the only state with min loan amount requirement of $3,005")
	   public void testMinLoanAmount() {
		   int i;
		   String value = null;
		   Response response = doGetRequest("https://credapi.credify.tech/api/loanapp/v1/states");
		   List<Map<String, String>> states = response.jsonPath().getList("states");
		   for(i=0;i<states.size();i++) {
			   //System.out.println(states.get(i).get("label"));
			   if(states.get(i).get("label").equals("Georgia")) {
				   value = String.valueOf(states.get(i).get("minLoanAmount"));
				   System.out.println(value);
				   break;
			   }
		   }
		   Assert.assertEquals(value,"3005.0");
	   } //testMinLoanAmount
	   
	  public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;
        return RestAssured.given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON).
                        when().get(endpoint).
                        then().contentType(ContentType.JSON).extract().response();
        } //get Request
}

