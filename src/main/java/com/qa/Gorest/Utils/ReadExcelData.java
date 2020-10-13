package com.qa.Gorest.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ReadExcelData
{
   public static Object[][] Readdata(String Sheetname) throws InvalidFormatException, IOException
   {
	  String path ="D:\\eclipse\\RestAssured_Framework2\\src\\main\\java\\com\\qa\\Gorest\\TestData\\GoRest_TestData.xlsx";
	  
	  FileInputStream files=new FileInputStream(path);
	   Workbook book=WorkbookFactory.create(files);
	   Sheet sheetnames= book.getSheet(Sheetname);
	   
	   Object data[][]=new Object[sheetnames.getLastRowNum()][sheetnames.getRow(0).getLastCellNum()];
	   for(int i=0;i<sheetnames.getLastRowNum();i++)
	   {
		   for(int j=0;j<sheetnames.getRow(0).getLastCellNum(); j++)
		   {
			   data[i][j]=sheetnames.getRow(i+1).getCell(j).toString();
		   }
	   }
	   
	    return data; 
   }
}
