package com.opencart.utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;




public class DataProviders {

	@DataProvider(name="LoginData")
	public String[][] getData() throws IOException{
		String path=".//testData//OpenCart_LoginData.xlsx";
		ExcelUtils obj=new ExcelUtils(path);
		int rows=obj.getRowCount("Sheet1");
		int columns=obj.getCellCount("Sheet1", 1);
		String [][]logindata =new String[rows][columns];
		for(int i=1;i<=rows;i++)
		{	
			for(int j=0;j<columns;j++)
			{
			  logindata[i-1][j]=obj.getCellData("Sheet1", i, j);
			}
		}
		return logindata;
		
	}
}
