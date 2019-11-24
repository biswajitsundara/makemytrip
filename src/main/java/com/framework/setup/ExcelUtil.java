package com.framework.setup;

import java.io.File;
import java.io.FileInputStream;

import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtil {
	
	private static XSSFWorkbook workbook;
	private static XSSFSheet worksheet;
	private static XSSFRow row;
	private static XSSFCell cell;
	private static FileInputStream excelFile;
	private static String filePath;
	
	public static void setExcelFile(String workbookName)
	{
		String filename=workbookName+".xlsx";
		try
		{
			filePath= TestCaseSetup.dataPath+File.separator+filename;	
			excelFile= new FileInputStream(filePath);
			System.out.println("Excel File Location = "+filePath);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void setWorkBook()
	{
		try
		{
			workbook= new XSSFWorkbook(excelFile);
			System.out.println("Workbook is created");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static void setWorkSheet(String sheetName)
	{
		try
		{
			worksheet=workbook.getSheet(sheetName);
			System.out.println("Work Sheet is created");
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	public static int getRowNum()
	{
		try
		{
			
			System.out.println("Total Row Count="+worksheet.getLastRowNum());
			return worksheet.getLastRowNum(); 
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public static int getColumnNum()
	{
		try
		{
			System.out.println("Total Column Count="+worksheet.getRow(0).getLastCellNum());
			return worksheet.getRow(0).getLastCellNum();
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		return 0;
	}
	public static Object[][] getExcelData(String workbookName, String sheetName)
	{
		setExcelFile(workbookName);
		setWorkBook();
		setWorkSheet(sheetName);
		
		int rowNum=getRowNum();
		int colNum=getColumnNum();
		
		Object data[][] = new Object[rowNum][colNum];
		
		for(int i=1;i<=rowNum;i++)
		{
			row=worksheet.getRow(i);
			
			for(int j=0;j<colNum;j++)
			{
				cell=row.getCell(j);
				CellType type = cell.getCellType();
				if(type == CellType.STRING)
				{
					data[i-1][j]=cell.getStringCellValue();
				}
				
				else if(type == CellType.NUMERIC)
				{
					data[i-1][j]=cell.getNumericCellValue();
				}
			}
		}
		
		return data;
	}

	
	public static void main(String[] args)
	{
		Object[][] data= getExcelData("MakeMyTrip","FlightData");
		
		System.out.println(data[0][1]);
	}
}
