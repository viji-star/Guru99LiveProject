package com.liveutility;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.xmlbeans.impl.xb.ltgfmt.TestCase.Files;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.livebase.TestBase;

public class TestUtility extends TestBase{

	public static int pageLoadTimeOut = 30;
	public static int implicitwait = 10;
	public static ExtentSparkReporter sparkreport; // To improve look and feel
	public static ExtentReports report; //To create the report having an entry for each TC
	public static String excelpath = "C:\\Users\\Windows 7\\git\\Guru99LiveProject\\LiveProject\\src\\com\\liveutility\\LoginData.xlsx";
	
	//public int value = 10;
	public static String alerttext;
	
	public  static String waitalertaccept()
	{
		
		WebDriverWait wait = new WebDriverWait(driver,5);
		try
		{
		if(wait.until(ExpectedConditions.alertIsPresent())!=null)
			
		alerttext =driver.switchTo().alert().getText() ;
		driver.switchTo().alert().accept();
		}
		catch(Exception e)
		{
			//System.out.println("There is no alert present");
			alerttext ="There is no alert present";
		}
		return alerttext;
	}
	
	
	public static Object[][] dataFromExcel(int sheetnumber) throws IOException
	{
		
		
		FileInputStream fp = new FileInputStream(excelpath);
		XSSFWorkbook wb = new XSSFWorkbook(fp);
		XSSFSheet sheet = wb.getSheetAt(sheetnumber);
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
				//data [i][j] = row.getCell(j).toString();
				Cell cell = row.getCell(j);
				try
				{
					if(cell.getCellType() == CellType.STRING)
					{
						data [i][j]=cell.getStringCellValue();
					}
					else if(cell.getCellType() == CellType.NUMERIC)
					{
						if(DateUtil.isCellDateFormatted(cell))
							data [i][j] = String.valueOf(cell.getDateCellValue());
						else
							data [i][j] = String.valueOf((long)cell.getNumericCellValue());
					}
					else if (cell.getCellType() == CellType.BOOLEAN)
					{
						data [i][j] = String.valueOf(cell.getBooleanCellValue());
					}
					else if (cell.getCellType() == CellType.BLANK)
					{
						data [i][j] = " ";
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
			}
		}
		wb.close();
		return data;
		
	}
	
	public static void dataWriteToExcel(String da, int sheetnumber, int cellno, String scenario) throws IOException
	{
		FileOutputStream fb = new FileOutputStream(excelpath);
		XSSFWorkbook wb = new XSSFWorkbook();
		XSSFSheet sheet = wb.getSheetAt(sheetnumber);
		int rowcount = sheet.getPhysicalNumberOfRows();
		
	
		for(int i=1;i<rowcount;i++)
		{
			String s = sheet.getRow(i).getCell(2).getStringCellValue();
			if(s.equals(scenario))
			{
			Cell cell = sheet.getRow(i).createCell(cellno);
			cell.setCellValue(da);
			wb.write(fb);
			}
			
		}
		fb.close();
		
	}
	
	public static String screenshot(String methodname) throws IOException
	{
		File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
		String dest = System.getProperty("user.dir") + "//Screenshots//" + methodname + TestUtility.datename()
				+ ".png" ;
		FileHandler.copy(src, new File(dest));
		return dest;
	}
	
	public static ExtentReports extendreportsetup()
	{
		
		sparkreport = new ExtentSparkReporter (System.getProperty("user.dir") + "//ExtentReport//" + "myreport" + ".html");
		sparkreport.config().setDocumentTitle("Automation Test Results");
		sparkreport.config().setReportName("Functional report");
		sparkreport.config().setTheme(Theme.STANDARD);
		
		report = new ExtentReports();
		report.attachReporter(sparkreport); //Attach the added values to the report
		
		report.setSystemInfo("browser","Chrome");
		report.setSystemInfo("version","96 series");
		report.setSystemInfo("Operating System","Windows");
		report.setSystemInfo("Tester","Viji");
		
		return report;
	}
	
	public static String datename()
	{
		SimpleDateFormat date = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateformatname = date.format(new Date());
		return dateformatname;
	}
	
	
	public static void waitsometime(WebElement element)
	{
		WebDriverWait wait = new WebDriverWait(driver,5);
		
		wait.until(ExpectedConditions.visibilityOf(element));
	}
	
}
