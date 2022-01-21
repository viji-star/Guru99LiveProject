package com.livebase;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import com.liveutility.TestUtility;


public class TestBase {

	public static WebDriver driver =null;
	public static Properties prob;
	
	public static void initialization() throws IOException
	{
		 prob = new Properties();
		FileInputStream ip = new FileInputStream("C:\\Users\\Windows 7\\test-workspace\\"
				+ "LiveProject\\src\\com\\liveutility\\config.properties");
		prob.load(ip);
		
	}
	
	public static void browserlaunch() throws IOException
	{
		initialization();
		if((prob.getProperty("browser")).equalsIgnoreCase("chrome"))
				{
					System.setProperty("webdriver.chrome.driver","D:\\Selenium\\Drivers\\chromedriver.exe");
					driver = new ChromeDriver();
					
				}
		else if((prob.getProperty("browser")).equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver",prob.getProperty("DriverLocation"));
			
		}
		
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		driver.manage().timeouts().pageLoadTimeout(TestUtility.pageLoadTimeOut, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(TestUtility.implicitwait, TimeUnit.SECONDS);
		driver.get(prob.getProperty("url"));
		
	}
}
