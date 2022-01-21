package com.liveutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.livebase.TestBase;

public class TestUtility extends TestBase{

	public static int pageLoadTimeOut = 20;
	public static int implicitwait = 10;
	
	public  static void waitalertaccept()
	{
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		try
		{
		if(wait.until(ExpectedConditions.alertIsPresent())!=null)
		driver.switchTo().alert().accept();
		}
		catch(Exception e)
		{
			//System.out.println("There is no alert present");
		}
	}
	
	
	public static Object[][] dataFromExcel() throws IOException
	{
		
		String excelpath = "C:\\Users\\Windows 7\\git\\Guru99LiveProject\\LiveProject\\src\\com\\liveutility\\TestData.xlsx";
		FileInputStream fp = new FileInputStream(excelpath);
		XSSFWorkbook wb = new XSSFWorkbook(fp);
		XSSFSheet sheet = wb.getSheetAt(0);
		int rowcount = sheet.getPhysicalNumberOfRows();
		XSSFRow row = sheet.getRow(0);
		int colcount = row.getLastCellNum();
		
		Object data[][] = new Object[rowcount-1][colcount];
		
		for(int i=0;i<rowcount-1;i++)
		{
			row = sheet.getRow(i+1);
			for(int j=0;j<colcount;j++)
			{
				//String ss = row.getCell(j).toString();
				data [i][j] = row.getCell(j).toString();
			}
		}
		
		return data;
	}
	
	
	public static void screenshot() throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		FileHandler.copy(src, new File("C:\\Users\\Windows 7\\git\\Guru99LiveProject\\LiveProject\\Screenshots\\Screen.png"));
	}
	
}
