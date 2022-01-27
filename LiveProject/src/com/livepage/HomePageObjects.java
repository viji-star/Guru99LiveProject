package com.livepage;

import java.util.Set;

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
	
	@FindBy(xpath="//iframe[@id='google_ads_iframe_/24132379/INTERSTITIAL_DemoGuru99_0']")
	WebElement frameID;
	
	@FindBy(linkText="Delete Customer")
	WebElement deletelink;
	
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
		Newcustomerlink.click();
		//driver.switchTo().frame(frameID);
		
		//driver.findElement(By.xpath("//div[@id='dismiss-button']")).click();
		
		//Set<String> s = driver.getWindowHandles();
		//System.out.println(s.size());
		return addnewcustomertext.isDisplayed();
		
	}
	
	public DeleteCusPageObjects clickOnDeleteLink()
	{
		deletelink.click();
		return new DeleteCusPageObjects();
	}
}
