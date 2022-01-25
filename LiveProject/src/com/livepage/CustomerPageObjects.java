package com.livepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.livebase.TestBase;
import com.liveutility.TestUtility;

public class CustomerPageObjects extends TestBase {

	@FindBy (xpath = "//input[@type='text' and @name='name']")
	WebElement customername;
	
	@FindBy (xpath = "//input[@type='radio' and @value='m']")
	WebElement gendermale;
	
	@FindBy (xpath = "//input[@type='radio' and @value='f']")
	WebElement genderfemale;
	
	@FindBy (xpath = "//textarea[@name='addr']")
	WebElement address;
	
	@FindBy (xpath = "//input[@name='city']")
	WebElement city;
	
	@FindBy (xpath = "//input[@name='state']")
	WebElement state;
	
	@FindBy (xpath = "//input[@name='pinno']")
	WebElement pin;
	
	@FindBy (xpath = "//input[@name='telephoneno']")
	WebElement mobile;
	
	@FindBy (xpath = "//input[@name='emailid']")
	WebElement email;
	
	@FindBy (xpath = "//input[@name='password']")
	WebElement password;
	
	@FindBy (xpath = "//input[@value='Submit']")
	WebElement submitbutton;
	
	
	public CustomerPageObjects()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String addnewcustomer(String cusname,String cusgender,String cusaddrs,String cusstate,String cuspin,String cusmob, String cusemail,String cuspass)
	{
		customername.sendKeys(cusname);
		if (cusgender.equalsIgnoreCase("male"))
		{
			gendermale.click();
		}
		else if(cusgender.equalsIgnoreCase("Female"))
		{
			genderfemale.click();
		}
		customername.sendKeys(cusname);
		customername.sendKeys(cusaddrs);
		customername.sendKeys(cusstate);
		customername.sendKeys(cuspin);
		customername.sendKeys(cusmob);
		customername.sendKeys(cusemail);
		customername.sendKeys(cuspass);
		
		submitbutton.click();
		
		String cusalertdata = TestUtility.waitalertaccept();
		
		return cusalertdata;
	}
	
	
}
