package com.qa.Gorest.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.Gorest.RestClient.RestClient;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GorestGetTestcases 
{
   String baseUrl="https://gorest.co.in";
   String basepath="/public-api/users";
   String tokens="lWaU5Xk-s-m-Z6V0coZ1XnP6i6ycE82Pkxfs";
   
	@Test
   public Response CallAPIforGet()
   {
	   SoftAssert asserts=new SoftAssert();
		Response response=null;
		Map<String,String> token=new HashMap<String,String>();
		token.put("Authorization", "Bearer "+"lWaU5Xk-s-m-Z6V0coZ1XnP6i6ycE82Pkxfs");
	   response=RestClient.doGet("JSON", baseUrl, basepath, token, null, true);
	   System.out.println(response.prettyPrint());
	   System.out.println(response.getStatusCode());
	   int i=response.getStatusCode();
	   asserts.assertEquals(i, 200);
	   asserts.assertAll();
	   
	   return response;
	   
   }

	@Test
	   public void CallAPIforGet_withquery_parameter()
	   {
		   
		 SoftAssert asserts=new SoftAssert();
		 Map<String,String> token=new HashMap<String,String>();
		 Map<String,String> querymaps=new HashMap<String,String>();
			Response response=null;
			RequestSpecification request=null;
			token.put("Authorization", "Bearer "+"lWaU5Xk-s-m-Z6V0coZ1XnP6i6ycE82Pkxfs");
			querymaps.put("first_name", "Christopher");
			querymaps.put("gender", "male");
			response=RestClient.doGet("JSON", baseUrl, basepath, token, querymaps, true);
		    System.out.println(response.prettyPrint());
		    System.out.println(response.getStatusCode());
		   int i=response.getStatusCode();
		  String firstname= response.jsonPath().getString("result.first_name");
		  System.out.println("Name:::::"+firstname);
		  asserts.assertTrue(firstname.contains("Christopher"));
		   asserts.assertEquals(i, 200);
		   asserts.assertAll();
		   
		   
	   }
   
}
