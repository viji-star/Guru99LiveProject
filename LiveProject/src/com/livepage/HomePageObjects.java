package com.livepage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.livebase.TestBase;
import com.liveutility.TestUtility;

public class HomePageObjects extends TestBase {

	@FindBy (xpath="//table[@class='layout']//table//tr[3]")
	WebElement mangerID;
	
	@FindBy (xpath= "//a[contains(text(),'New Customer')]")
	WebElement Newcustomerlink;
	public HomePageObjects()
	{
		PageFactory.initElements(driver,this);
	}
	
	@FindBy (xpath = "//p[contains(text(),'New Customer')]")
	public  WebElement addnewcustomertext;
	public String managerIDvalue()
	{
		return mangerID.getText();
	}
	
	public boolean clickOnNewCustomer()
	{
		TestUtility.waitsometime(mangerID);
		driver.switchTo().frame(0);
		driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
		Newcustomerlink.click();
		return addnewcustomertext.isDisplayed();
		
	}
}
