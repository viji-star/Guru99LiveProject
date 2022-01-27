package com.livebase;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

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
					//ChromeOptions options = new ChromeOptions();
					//option.setExperimentalOption("excludeSwitches", Arrays.asList("disable-popup-blocking"));
					//option.addArguments("disable-popup-blocking");
					//option.addArguments("disable-infobar");
					//driver = new ChromeDriver(option);
				
//					options.addExtensions(new File("D:\\Selenium\\AdBlocker extension\\extension_4_43_0_0.crx")); 
//					DesiredCapabilities capabilities = new DesiredCapabilities();
//					capabilities.setCapability(ChromeOptions.CAPABILITY, options);
//					options.merge(capabilities);
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
