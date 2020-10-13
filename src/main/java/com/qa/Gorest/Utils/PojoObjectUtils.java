package com.qa.Gorest.Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class PojoObjectUtils 
{
   public static String ObjectMapperforPost(Object obj) 
   {
	   String Jsonvalue=null;
	   ObjectMapper mapping=new ObjectMapper();
	   try
	   {
		    Jsonvalue= mapping.writeValueAsString(obj);
		   System.out.println(Jsonvalue);
	   }catch(JsonProcessingException e)
	   {
		   System.out.println("In valid Json String");
	   }
	  
	   return Jsonvalue;
	  
	  
   }
	
}
