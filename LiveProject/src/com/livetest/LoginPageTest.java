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
	public Object[][] data()
	{
		Object [][] combodata = {
				{"btnLogin","UmYgepY"},
				{"mngr381722","btnLogin"},
				{" "," "},
				{"mngr381722","UmYgepY"}		
		};
		
		return combodata;
	}
	
	
	@Test(priority =0)
	public void LogoPagelogoTest()
	{
		Assert.assertTrue(lp.validatelogo());
		System.out.println("Logo is displayed");
	}
	
	@Test(priority =1,dataProvider="Differentsetofdataforlogin")
	public void LogoPageloginTest(String user,String pswd)
	{
		String expected = "Guru99 Bank Manager HomePage";
		//String actual = lp.loginsuccess(prob.getProperty("user"), prob.getProperty("pswd"));
		String actual = lp.loginsuccess(user, pswd);
		Assert.assertEquals(actual, expected);
		System.out.println("Login Sucessfull");
		
	}
	
	
	
	@AfterTest
	public void tearDown() {
		driver.quit();
	}
}
