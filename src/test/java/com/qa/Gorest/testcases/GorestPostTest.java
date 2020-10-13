package com.qa.Gorest.testcases;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.AssertJUnit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.Gorest.RestClient.RestClient;
import com.qa.Gorest.Utils.PojoTemplate;
import com.qa.Gorest.Utils.ReadExcelData;

import io.restassured.response.Response;

public class GorestPostTest 
{
   
	  String baseUrl="https://gorest.co.in";
	  String basepath="/public-api/users";
	  String tokens="zl7dERMQgPpAuehIapszLrPukc_cGMVi_fhl";
		/*
		 * @Test() public void CallPostAPITestCase() { SoftAssert asserts=new
		 * SoftAssert();
		 * 
		 * PojoTemplate pojo=new PojoTemplate("Daphney",
		 * "Jerly","female","04-04-1992","knkn@yahoo.com",
		 * "+9845281948","https://www.naveenautomation.com","123,new Jercy","active");
		 * Response responsedata=null; Map<String,String> token=new
		 * HashMap<String,String>(); token.put("Authorization","Bearer "+tokens);
		 * responsedata=RestClient.dopost("JSON", baseUrl, basepath, token, null, true,
		 * pojo); int i=responsedata.getStatusCode(); AssertJUnit.assertEquals(i, 200);
		 * System.out.println(responsedata.prettyPrint()); asserts.assertAll();
		 * 
		 * }
		 */
   
   @DataProvider(name="readit")
   public Object [][] readdata() throws InvalidFormatException, IOException
   {
	   Object [][] obj=ReadExcelData.Readdata("CreateData");
	   
	   return obj;
   }
   
  @Test(dataProvider= "readit")
   public void CallPostAPITestCasewith_DataProvider(String firstname, String Secondname, String gender, String dob, String emailid, 
		                                             String phonumber, String url, String address, String status) 
   {
	   PojoTemplate pojo=new PojoTemplate(firstname,Secondname,gender,dob,emailid,phonumber,url,address,status);
	   SoftAssert asserts=new SoftAssert();
	   Response  responsedata=null;
	   Map<String,String> token=new HashMap<String,String>();
	   token.put("Authorization","Bearer "+tokens);
	   responsedata=RestClient.dopost("JSON", baseUrl, basepath, token, null, true,pojo);
	   int i=responsedata.getStatusCode();
	   asserts.assertEquals(i, 302);
	   System.out.println(responsedata.prettyPrint());
	   asserts.assertAll();
	   
   }
}
