package com.liveutility;

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
	
	
	public void dataFromExcel()
	{
		
	}
	
}
