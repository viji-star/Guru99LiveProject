package com.livepage;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.livebase.TestBase;
import com.liveutility.TestUtility;

public class DeleteCusPageObjects extends TestBase{

	@FindBy(id="message14")
	WebElement displaymessage;
	
	@FindBy(name ="cusid")
	WebElement customeridbox;
	
	@FindBy(xpath="//p[text()='Delete Customer Form']")
	WebElement heading;
	
	@FindBy(name="AccSubmit")
	WebElement submitbtn;
	
	public DeleteCusPageObjects()
	{
		PageFactory.initElements(driver, this);
	}
	
	public boolean validateMessageDisplayed()
	{
		customeridbox.click();
		submitbtn.click();
		return displaymessage.isDisplayed();
	}
	
	public String validateNotACustomer(String ID)
	{
		customeridbox.sendKeys(ID);
		submitbtn.click();
		TestUtility.waitalertaccept();
		TestUtility.waitalertaccept();
		return heading.getText();
		
	}
	
	public void validateDeleteCus(String cusID)
	{
		customeridbox.sendKeys(cusID);
		TestUtility.waitalertaccept();
	}
	
}
