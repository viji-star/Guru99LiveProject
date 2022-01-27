package com.livetest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.livebase.TestBase;
import com.livepage.HomePageObjects;
import com.livepage.LoginPageObjects;

public class HomePageTest extends TestBase {

public LoginPageObjects loginobj;
public HomePageObjects hp;
	@BeforeMethod
	public void setup() throws IOException
	{
		TestBase.browserlaunch();
		loginobj = new LoginPageObjects();
		hp =loginobj.login(prob.getProperty("user"), prob.getProperty("pswd"));
	}
	
	@Test()
	public void validateManagerIDTest()
	{
		String expected = "Manger Id : mngr381722";
		String actualvalue = hp.managerIDvalue();
		Assert.assertEquals(actualvalue, expected);
	}
	
	@Test()
	public void validateNewCustomerTest()
	{
		Assert.assertTrue(hp.clickOnNewCustomer());
	}
	
	@AfterMethod
	public void tearDown() {
	
		//driver.quit();
	}
}
