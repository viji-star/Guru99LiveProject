package com.livetest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.livebase.TestBase;
import com.livepage.LoginPageObjects;
import com.liveutility.TestUtility;


public class LoginPageTest extends TestBase {

	
	public LoginPageObjects lp;
	public HomePageTest hp;
	
	
	@BeforeTest
	public void setup() throws IOException
	{
		browserlaunch();
		lp = new LoginPageObjects();
		
	}
	
	
	@DataProvider(name = "Differentsetofdataforlogin")
	public Object[][] data() throws IOException
	{

//		Object [][] combodata = {
//				{"btnLogin","UmYgepY"},
//				{"mngr381722","btnLogin"},
//				{" "," "},
//				{"mngr381722","UmYgepY"}		
//	};

		return TestUtility.dataFromExcel();
		//return combodata;
	}
	
	
	@Test(priority =0)
	public void LogoPagelogoTest() throws IOException
	{
		
		Assert.assertTrue(lp.validatelogo());
		//Assert.assertFalse(lp.validatelogo());
		//TestUtility.screenshot("LogoPagelogoTest");
		System.out.println("Logo is displayed");
	}
	
	@Test(priority =1,dataProvider="Differentsetofdataforlogin")
	public void LogoPageloginTest(String user,String pswd,String scenario)
	{
		
		String expectedtitle = "Guru99 Bank Manager HomePage";
		String expecteddata = "User or Password is not valid";
		//String actual = lp.loginsuccess(prob.getProperty("user"), prob.getProperty("pswd"));
		String actualvalue = lp.loginsuccess(user, pswd,scenario);
		if(scenario.equalsIgnoreCase("Both Correct"))
		{
		//Assert.assertEquals(actual, expected);
		Assert.assertEquals(actualvalue, expectedtitle);
		System.out.println("Login Sucessfull for valid data");
		}
		else
		{
			Assert.assertEquals(actualvalue, expecteddata);
			System.out.println("Login not Sucessfull for invalid data");
		}
		
	}
	
	
	@AfterTest
	public void tearDown() {
	
		driver.quit();
	}
}
