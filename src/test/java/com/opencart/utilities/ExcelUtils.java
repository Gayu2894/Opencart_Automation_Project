package com.opencart.utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {

	public static FileInputStream fi;
	public static FileOutputStream fo;
	public static XSSFWorkbook wb;
	public static XSSFSheet ws;
	public static XSSFRow row;
	public static XSSFCell cell;
	public static CellStyle style;
    String path;
	public ExcelUtils(String path)
	{
		this.path=path;
	}
	
	public int getRowCount(String xlsheet) throws IOException
	{
		fi=new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int rowCount=ws.getLastRowNum();
		wb.close();
		fi.close();
		return rowCount ;
	}
	
	public int getCellCount(String xlsheet,int rownum) throws IOException
	{
		fi=new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		int CellCount=ws.getRow(rownum).getLastCellNum();
		wb.close();
		fi.close();
		return CellCount ;
	}
	
	public String getCellData(String xlsheet,int rownum,int cellnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb= new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(cellnum);
		
		String data;
		
		try {
		   //data=cell.toString();
		    DataFormatter format=new DataFormatter();
		    data=format.formatCellValue(cell); // returns string 
		    
		}catch(Exception e)
		{
			data=""; //even if empty data passed without throwing exception execute further
		}
		
		wb.close();
		fi.close();
		return data ;
	}
	
	public void setCellData(String xlsheet,int rownum,int cellnum,String data) throws IOException
	{
		
		File xlfile=new File(path);
		if(!xlfile.exists()) //If file not exists the create new file
		{
			wb=new XSSFWorkbook();
			fo=new FileOutputStream(path);
			wb.write(fo);
		}
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		
		if(wb.getSheetIndex(xlsheet)==-1)//If sheet not exists then create new sheet
			wb.createSheet(xlsheet);
		    ws=wb.getSheet(xlsheet);
		if(ws.getRow(rownum)==null) //If row not exists then create new row
			ws.createRow(rownum);
		    row=ws.getRow(rownum);
		
		//Creating new data
		cell=row.createCell(cellnum);
		cell.setCellValue(data);
		
		//for writing data
		fo=new FileOutputStream(path);
		wb.write(fo);
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillGreenColor(String xlfile,String xlsheet,int rownum,int cellnum) throws IOException
	{
		fi=new FileInputStream(xlfile);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(cellnum);
		//Creating style
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.GREEN.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//To Apply Green color in this particular cell
		cell.setCellStyle(style);
		
		//for writing data
		fo=new FileOutputStream(xlfile);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
	}
	
	public void fillRedColor(String xlsheet,int rownum,int cellnum) throws IOException
	{
		fi=new FileInputStream(path);
		wb=new XSSFWorkbook(fi);
		ws=wb.getSheet(xlsheet);
		row=ws.getRow(rownum);
		cell=row.getCell(cellnum);
		//Creating style
		style=wb.createCellStyle();
		
		style.setFillForegroundColor(IndexedColors.RED.getIndex());
		style.setFillPattern(FillPatternType.SOLID_FOREGROUND);
		
		//To Apply Red color in this particular cell
		cell.setCellStyle(style);
		
		
		//for writing data
		fo=new FileOutputStream(path);
		wb.write(fo);
		
		wb.close();
		fi.close();
		fo.close();
	}
}
