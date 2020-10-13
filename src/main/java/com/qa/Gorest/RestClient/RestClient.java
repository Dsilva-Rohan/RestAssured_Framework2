package com.qa.Gorest.RestClient;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.Map;

import com.qa.Gorest.Utils.PojoObjectUtils;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class RestClient
{
   /*Generic Method variable declaration get */ 
	public static Response doGet(String contenttype, String baseurl, String basepath,  Map<String,String> token, Map<String,String> querymap, boolean log)
   {
	   RequestSpecification request=null;
	   Response response=null;
	    if(geturl(baseurl)==true)
	    {
	    	
	    	request=Createrequest(contenttype,token,querymap,log);
			response=getresponse("GET",request, basepath);
			
	    }
	    else if(geturl(baseurl)==false)
	    {
	    	System.out.println("There is no specific Response to return in this API");
	    	return response;
	    }
		
	    return response;	
   }
	
	/* perform Post method */
	public static Response dopost(String contenttype, String baseurl, String basepath,  Map<String,String> token, Map<String,String> querymap, boolean log, Object obj)
	   {
		   RequestSpecification request=null;
		   Response response=null;
		    if(geturl(baseurl)==true)
		    {
		    	
		    	request=Createrequest(contenttype,token,querymap,log);
		    	if(obj instanceof Map)
		    	{
		    		request.formParams((Map<String, String>) obj);
		    	}
		    	else
		    	{
		    	  String jsonvalue=PojoObjectUtils.ObjectMapperforPost(obj);
		    	  request.body(jsonvalue);
				  response=getresponse("POST",request, basepath);
		    	}
		    }
		    else if(geturl(baseurl)==false)
		    {
		    	System.out.println("There is no specific Response to return in this API");
		    	return response;
		    }
			
		    return response;	
	   }
	
	 
		public static Response DoDelete(String contenttype, String baseurl, String basepath,  Map<String,String> token, Map<String,String> querymap, boolean log)
	   {
		   RequestSpecification request=null;
		   Response response=null;
		    if(geturl(baseurl)==true)
		    {
		    	
		    	request=Createrequest(contenttype,token,querymap,log);
				response=getresponse("DELETE",request, basepath);
				
		    }
		    else if(geturl(baseurl)==false)
		    {
		    	System.out.println("There is no specific Response to return in this API");
		    	return response;
		    }
			
		    return response;	
	   }
		
		
		/*Update by using Put Call*/
		public static Response doput(String contenttype, String baseurl, String basepath,  Map<String,String> token, Map<String,String> querymap, boolean log, Object obj)
		   {
			   RequestSpecification request=null;
			   Response response=null;
			    if(geturl(baseurl)==true)
			    {
			    	
			    	request=Createrequest(contenttype,token,querymap,log);
			    	 String jsonvalue=PojoObjectUtils.ObjectMapperforPost(obj);
			    	  request.body(jsonvalue);
					  response=getresponse("PUT",request, basepath);
			    }
			    
			    else if(geturl(baseurl)==false)
			    {
			    	System.out.println("There is no specific Response to return in this API");
			    	return response;
			    }
				
			    return response;	
		   }
	
		 /*Generic Method variable declaration get */
   
	 /*Base URL Method  */ 
	private static boolean geturl(String baseurl) 
   {
	   if(baseurl==null || baseurl.isEmpty())
	   {
		   System.out.println("Please enter valid Url");
		   return false;
	   }
	   else
	   {	   
		   
	     try
	    {
		   RestAssured.baseURI=baseurl;
		   return true;
        }  catch(Exception e)
	    {
        	System.out.println("Base URL Did not found");
        	e.printStackTrace();
        	return false;
	    }
      }
   }
	
	 /*Creating request for the API call  */ 
	private static RequestSpecification Createrequest(String contenttype, Map<String,String> token, Map<String,String> querymap, boolean log)
	{
		RequestSpecification request=null;
		if(log==true)
		{
			request=RestAssured.given().log().all();
			
		}
		else
		{
			request=RestAssured.given();
		}
		if(token!=null)
		{
			request.headers(token);
		}
		if(querymap!=null)
		{
			request.queryParams(querymap);
			
		}
		if(contenttype!=null)
		{
		   if(contenttype.equalsIgnoreCase("JSON"))
		   {
			  request.contentType(ContentType.JSON);
			
		   }
		   else if(contenttype.equalsIgnoreCase("XML"))
		   {
			  request.contentType(ContentType.XML);
		   }
		   else if(contenttype.equalsIgnoreCase("TEXT"))
	       {
			 request.contentType(ContentType.TEXT);
		   }
		   else if (contenttype.equalsIgnoreCase("HTML"))
		   {
			 request.contentType(ContentType.HTML);  
		   }
		   
		   else if(contenttype.equalsIgnoreCase("multipart"))
		   {
			  
			   request.multiPart("image", new File("C:\\Users\\rohan\\OneDrive\\Desktop\\Selenium Interview\\API automation.PNG"));
		   }
		}
		  return request;
					
	}
	
	private static Response getresponse(String htmlmethod, RequestSpecification request, String basepath)
	{
		Response response;
		response=executerepsonse(htmlmethod,request,basepath);
		return response;
	}
	
	
	
	
	private static Response executerepsonse(String htmlmethod, RequestSpecification request, String basepath)
	{
		Response response=null;
		switch(htmlmethod)
		{
		   case "GET":
			   response=request.get(basepath);
		   break; 
		   
		   case "POST":
			   response=request.post(basepath);
		       break;
		   case "PUT":
			   response=request.put(basepath);
		       break;
		   case "DELETE":
			   response=request.delete(basepath);
		       break;
		   case "PATCH":
			   response=request.patch(basepath);
		       break;
		   default:
		      System.out.println("There is no any httpmethods");
		      break;
		}
	      return response;	 
	}
}
