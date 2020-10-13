package com.qa.Gorest.testcases;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import com.qa.Gorest.RestClient.RestClient;
import com.qa.Gorest.Utils.tokenutil;

import io.restassured.response.Response;


public class TokenGenerationTestcases
{
	
	/* declaring the variable which need to use in the  another method to resuse the run time generated token */
	
	String accesstoken=null;
	String username=null;
	@BeforeMethod
	public void generatetoken()
  {
	 // Here your calling the generate token method from token util class and reading the response from the map
	 Map<Object,Object> tokensvalue= tokenutil.generateToken();
	 accesstoken=tokensvalue.get("access_token").toString();
	 String refreshtoken=tokensvalue.get("refresh_token").toString();
	 String accout_id=tokensvalue.get("account_id").toString();
	  username=tokensvalue.get("account_username").toString(); 
	 
	 
  }
    @Test
    public void callGetAPI()
    {
    	
    	SoftAssert asserts=new SoftAssert(); 
    	Map<String,String> tokenmap=new HashMap<String,String>();
    	 tokenmap.put("Authorization", "Bearer "+accesstoken);
    	 Response res=RestClient.doGet(null, "https://api.imgur.com", "/account/v1/"+username+"/block", tokenmap, null, true);
    	 System.out.print(res.prettyPrint());
    	 int i=res.getStatusCode();
    	 asserts.assertEquals(1, 200);
    }
    
    @Test
    public void callclientIDAPI()
    {
    	
    	SoftAssert asserts=new SoftAssert(); 
    	String clientidnumber=tokenutil.readclientid(); 
    	Map<String, String> clientid=new HashMap<String,String>();
    	 clientid.put("Authorization","Client-ID "+clientidnumber);
    	 Response res=RestClient.doGet(null, "https://api.imgur.com", "/3/account/"+username+"/gallery_favorites", clientid, null, true);
    	 System.out.print(res.prettyPrint());
    	 int i=res.getStatusCode();
    	 asserts.assertEquals(i, 200);
    }
    
    @Test
    public void callImpageAPI()
    {
    	
    	SoftAssert asserts=new SoftAssert(); 
    	String clientidnumber=tokenutil.readclientid(); 
    	Map<String, String> clientid=new HashMap<String,String>();
    	clientid.put("Authorization","Client-ID "+clientidnumber);
    	Map<String, String> formdataitems=new HashMap<String,String>();
    	formdataitems.put("title","We are Testing Upload File");
    	formdataitems.put("description","Testing Upload File");
    	Response res=RestClient.dopost("multipart", "https://api.imgur.com", "/3/image", clientid, null, true, formdataitems);
    	System.out.print(res.prettyPrint());
    	int i=res.getStatusCode();
    	asserts.assertEquals(i, 200);
    }
    @Test
    public void CallDeleteAPI()
    {
    	
    	SoftAssert asserts=new SoftAssert(); 
    	Map<String,String> tokenmap=new HashMap<String,String>();
    	 tokenmap.put("Authorization", "Bearer "+accesstoken);
    	 Response res=RestClient.DoDelete(null, "https://api.imgur.com", "/3/account/"+username+"/comment/1", tokenmap, null, true);
    	 System.out.print(res.prettyPrint());
    	 int i=res.getStatusCode();
    	 asserts.assertEquals(1, 200);
    }
    
  
    
    
}
