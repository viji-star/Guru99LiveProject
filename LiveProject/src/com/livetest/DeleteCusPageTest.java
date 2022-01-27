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
import com.livepage.DeleteCusPageObjects;
import com.livepage.HomePageObjects;
import com.livepage.LoginPageObjects;
import com.liveutility.TestUtility;

public class DeleteCusPageTest extends TestBase{

	public LoginPageObjects loginobj;
	public HomePageObjects hp;
	public DeleteCusPageObjects del;
	
	@BeforeClass
	public void freshup() throws IOException
	{
		TestBase.browserlaunch();
		loginobj = new LoginPageObjects();
		hp =loginobj.login(prob.getProperty("user"), prob.getProperty("pswd"));
		del = hp.clickOnDeleteLink();
	}
	
	@DataProvider(name = "DeleteData")
	public Object [][] cusdata() throws IOException
	
	{
		return TestUtility.dataFromExcel(2);
	}
	
	@Test
	public void messageDisplayedTest()
	{
		Assert.assertTrue(del.validateMessageDisplayed());
	}
	
	@Test(dataProvider = "DeleteData")
	public void validateDeleteNotACustomerTest(String ID)
	{
		String actualtext = del.validateNotACustomer(ID);
		String expectedtext = "Delete Customer Form";
		Assert.assertEquals(actualtext, expectedtext);
	}
	
	@AfterClass
	public void endup()
	{
		driver.quit();
	}
}
