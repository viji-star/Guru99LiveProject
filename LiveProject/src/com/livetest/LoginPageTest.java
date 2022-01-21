package com.livetest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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
		TestUtility.screenshot();
		System.out.println("Logo is displayed");
	}
	
	@Test(priority =1,dataProvider="Differentsetofdataforlogin")
	public void LogoPageloginTest(String user,String pswd,String scenario)
	{
		String expected = "Guru99 Bank Manager HomePage";
		//String actual = lp.loginsuccess(prob.getProperty("user"), prob.getProperty("pswd"));
		String actual = lp.loginsuccess(user, pswd);
		if(scenario.equalsIgnoreCase("Both Correct"))
		{
		Assert.assertEquals(actual, expected);
		System.out.println("Login Sucessfull");
		}
		else
		{
			Assert.assertNotEquals(actual, expected);
		}
		
	}
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
