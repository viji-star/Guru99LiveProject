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
	
	@FindBy(id="dob")
	WebElement DOB;
	
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
	
	@FindBy(xpath="//input[@type='reset']")
	WebElement resetbutton;
	
	@FindBy(xpath ="//p[text()='Customer Registered Successfully!!!']")
	WebElement sucess;
	
	public CustomerPageObjects()
	{
		PageFactory.initElements(driver, this);
	}
	
	public String addnewcustomer(String cusname,String cusgender,String dob,String cusaddrs,String cuscity,String cusstate,String cuspin,String cusmob, String cusemail,String cuspass)
	{
		resetbutton.click();
		
		customername.sendKeys(cusname);
		if (cusgender.equalsIgnoreCase("male"))
		{
			gendermale.click();
		}
		else if(cusgender.equalsIgnoreCase("Female"))
		{
			genderfemale.click();
		}
		
		DOB.sendKeys(dob);
		address.sendKeys(cusaddrs);
		city.sendKeys(cuscity);
		state.sendKeys(cusstate);
		pin.sendKeys(cuspin);
		mobile.sendKeys(cusmob);
		email.sendKeys(cusemail);
		password.sendKeys(cuspass);
		
		submitbutton.click();
		
		String cusalertdata = TestUtility.waitalertaccept();
		
		if(cusalertdata.equals("There is no alert present"))
		return sucess.getText();
		else
		return cusalertdata;
	}
	
	
}

