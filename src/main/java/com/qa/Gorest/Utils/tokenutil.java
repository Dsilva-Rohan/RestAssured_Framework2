package com.qa.Gorest.Utils;

import  static io.restassured.RestAssured.*;
import  static org.hamcrest.Matchers.*;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;



public class tokenutil
{
   
	static Map<String,String> formmapping=new HashMap<String,String>();
	static Map<Object,Object> responsedata;
	public static Map<Object,Object>  generateToken()
    {
    	
    	    	
    	RestAssured.baseURI="https://api.imgur.com";
    	formmapping.put("refresh_token", "e4c51d1a728971c4422f3a756d930739750a6f1e");
    	formmapping.put("client_id", "459d48d0650d449");
    	formmapping.put("client_secret","08b3d62be472c3a171d47c22efe8eebe3eff34c7");
    	formmapping.put("grant_type","refresh_token");
    	
    	JsonPath jsonresponse=RestAssured.given().formParams(formmapping).when().post("/oauth2/token").then().extract().jsonPath();
    	System.out.println(jsonresponse.getMap(""));
    	responsedata= jsonresponse.getMap("");
    	return responsedata;
       
    }
    
	public static String  readclientid()
	{
		String clientiddata=formmapping.get("client_id").toString();
		return clientiddata;
	}
	
	public static String  readresponsedata()
	{
		String token=responsedata.get("access_token").toString();
		return token;
	}
}
