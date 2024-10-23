package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

	public HomePage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//span[normalize-space()='My Account']")
	WebElement lnkMyAccount;
	
	@FindBy(xpath="//a[normalize-space()='Register']")
	WebElement lnkRegister;
	
	@FindBy(linkText="Login")
	WebElement lkLogin;
	
	public void clickMyAccount() throws InterruptedException
	{
		//Thread.sleep(3000);
		lnkMyAccount.click();
	}
	
	public void clickRegister() throws InterruptedException
	{
		//Thread.sleep(3000);
		lnkRegister.click();
	}
	
	public void clickLogin()
	{
		lkLogin.click();
	}
	
	
}  