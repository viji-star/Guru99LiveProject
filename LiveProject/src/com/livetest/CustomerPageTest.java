package com.livetest;

import java.io.IOException;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.livebase.TestBase;
import com.livepage.HomePageObjects;
import com.livepage.LoginPageObjects;
import com.liveutility.TestUtility;

public class CustomerPageTest extends TestBase {

	public LoginPageObjects loginobj;
	public HomePageObjects hp;
	
	@BeforeMethod()
	public void start() throws IOException
	{
		TestBase.initialization();
		loginobj = new LoginPageObjects();
		hp =loginobj.login(prob.getProperty("user"), prob.getProperty("pswd"));
		hp.clickOnNewCustomer();
		TestUtility.waitsometime(hp.addnewcustomertext);
		
	}
	
	@DataProvider(name = "CustomerData")
	public Object [][] cusdata() throws IOException
	
	{
		return TestUtility.dataFromExcel(1);
	}
	
	@Test(dataProvider = "CustomerData")
	public void addNewCustomerTest(String cusname,String cusgender,String cusaddrs,String cusstate,String cuspin,String cusmob, String cusemail,String cuspass)
	{
		
	}
}
