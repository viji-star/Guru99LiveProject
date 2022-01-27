package com.livetest;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.livebase.TestBase;
import com.livepage.CustomerPageObjects;
import com.livepage.HomePageObjects;
import com.livepage.LoginPageObjects;
import com.liveutility.TestUtility;

public class CustomerPageTest extends TestBase {

	public LoginPageObjects loginobj;
	public HomePageObjects hp;
	public CustomerPageObjects cusobj;
	
	@BeforeMethod()
	public void start() throws IOException
	{
		TestBase.browserlaunch();
		loginobj = new LoginPageObjects();
		hp =loginobj.login(prob.getProperty("user"), prob.getProperty("pswd"));
		hp.clickOnNewCustomer();
		TestUtility.waitsometime(hp.addnewcustomertext);
		cusobj = new CustomerPageObjects();
		
	}
	
	@DataProvider(name = "CustomerData")
	public Object [][] cusdata() throws IOException
	
	{
		return TestUtility.dataFromExcel(1);
	}
	
	@Test(dataProvider = "CustomerData")
	public void addNewCustomerTest(String cusname,String cusgender,String DOB,String cusaddrs,String cusCity,String cusstate,String cuspin,String cusmob, String cusemail,String cuspass)
	{
		String actual = cusobj.addnewcustomer(cusname,cusgender,DOB,cusaddrs,cusCity,cusstate,cuspin,cusmob,cusemail,cuspass);
		//String expected = "please fill all fields";
		String expected="Customer Registered Successfully!!!";
		Assert.assertEquals(actual, expected);
	}
	
	
	@AfterMethod()
	public void end()
	{
		driver.quit();
	}
}
