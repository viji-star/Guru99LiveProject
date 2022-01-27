package com.livetest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.livebase.TestBase;
import com.livepage.LoginPageObjects;
import com.liveutility.TestUtility;


public class LoginPageTest extends TestBase {

	
	public LoginPageObjects lp;
	public HomePageTest hp;
	
	
	@BeforeClass
	public void setup() throws IOException
	{
		TestBase.browserlaunch();
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

		return TestUtility.dataFromExcel(0);
		//return combodata;
	}
	
	
	@Test(priority =0,enabled = false)
	public void LogoPagelogoTest() throws IOException
	{
		
		Assert.assertTrue(lp.validatelogo());
		//Assert.assertFalse(lp.validatelogo());
		//TestUtility.screenshot("LogoPagelogoTest");
		System.out.println("Logo is displayed");
	}
	
	@Test(priority =1,dataProvider="Differentsetofdataforlogin")
	public void LogoPageloginTest(String user,String pswd,String scenario) throws IOException
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
		//TestUtility.dataWriteToExcel("Login Sucessfull for valid data",0,3,scenario);
		}
		else
		{
			Assert.assertEquals(actualvalue, expecteddata);
			System.out.println("Login not Sucessfull for invalid data");
			//TestUtility.dataWriteToExcel("Login Sucessfull for invalid data",0,3,scenario);
		}
		
	}
	
	
	@AfterClass
	public void tearDown() {
	
		driver.quit();
	}
}
