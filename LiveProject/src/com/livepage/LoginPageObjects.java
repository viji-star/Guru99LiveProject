package com.livepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.livebase.TestBase;
import com.livetest.HomePageTest;
import com.liveutility.TestUtility;

public class LoginPageObjects extends TestBase{

	@FindBy(xpath="//img[@src='/logo.png']")
	WebElement logo;
	
	@FindBy(xpath="//input[@type='text' and @name='uid']")
	WebElement username;
	
	@FindBy(xpath="//input[@type='password' and @name='password']")
	WebElement password;
	
	@FindBy(name="btnLogin")
	WebElement login;
	
	public LoginPageObjects()
	{
		PageFactory.initElements(driver, this);
	
	}
	
	public boolean validatelogo()
	{
		
		return (logo.isDisplayed());
	
	}
	
	public String loginsuccess(String us, String ps)
	{
		username.sendKeys(us);
		password.sendKeys(ps);
		login.click();
		TestUtility.waitalertaccept();
		String actualhomepagetitle = driver.getTitle();
		return actualhomepagetitle;
	}
}
