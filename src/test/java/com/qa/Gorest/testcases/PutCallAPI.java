package com.qa.Gorest.testcases;

import java.awt.List;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.Gorest.RestClient.RestClient;
import com.qa.Gorest.Utils.PojoTemplate;
import com.qa.Gorest.Utils.ReadExcelData;

import io.restassured.response.Response;

public class PutCallAPI
{
	
	 
	GorestGetTestcases  objectget=new  GorestGetTestcases();
	
	
	
	  @DataProvider(name="readit")
	   public Object [][] readdata() throws InvalidFormatException, IOException
	   {
		   Object [][] obj=ReadExcelData.Readdata("UpdateData");
		   
		   return obj;
	   }
	   
	  @Test(dataProvider= "readit")
	   public void callPUT_DataProvider(String firstname, String Secondname, String gender, String dob, String emailid, 
			                                             String phonumber, String url, String address, String status) 
	   {
		  PojoTemplate pojo=new PojoTemplate(firstname,Secondname,gender,dob,emailid,phonumber,url,address,status);  
		  Response resp=objectget.CallAPIforGet();
		  
		   //String list[]=new String[10]=resp.jsonPath().getString("result.id");
		   List list=(List) resp.jsonPath().getList("result.id");
		   
		   System.out.println("****************************Valuedata"+list);
		   SoftAssert asserts=new SoftAssert();
		   Response  responsedata=null;
		   Map<String,String> token=new HashMap<String,String>();
		   token.put("Authorization","Bearer lWaU5Xk-s-m-Z6V0coZ1XnP6i6ycE82Pkxfs");
		   responsedata=RestClient.doput("JSON", "https://gorest.co.in", "/public-api/users/"+list, token, null, true,pojo);
		   int i=responsedata.getStatusCode();
		   asserts.assertEquals(i, 302);
		   System.out.println(responsedata.prettyPrint());
		   asserts.assertAll();
		   
	   }
	}


