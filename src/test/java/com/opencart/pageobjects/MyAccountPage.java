package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MyAccountPage extends BasePage{

	public MyAccountPage(WebDriver driver) {
		super(driver);
	}
	
	 @FindBy(xpath="//h2[normalize-space()='My Account']")
	 WebElement confirmAcc;
	 
	 @FindBy(linkText="Logout")
	 WebElement lnkLogout;
	 
	 public void clickLogout()
	 {
		 lnkLogout.click();
	 }

	public boolean confirmAccount()
	{
		try {
		return (confirmAcc.isDisplayed());
		}catch(Exception e)
		{
		return false;
		}
	}
}
