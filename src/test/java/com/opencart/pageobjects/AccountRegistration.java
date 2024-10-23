package com.opencart.pageobjects;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AccountRegistration extends BasePage {

	public AccountRegistration(WebDriver driver)
	{
		super(driver);
	}

	@FindBy(xpath="//input[@id='input-firstname']")
	WebElement txt_Fname;
	
	@FindBy(xpath="//input[@id='input-lastname']")
	WebElement txt_Lname;
	
	@FindBy(xpath="//input[@id='input-email']")
	WebElement txt_Email;
	
	@FindBy(xpath="//input[@id='input-telephone']")
	WebElement txt_telephone;
	
	@FindBy(xpath="//input[@id='input-password']")
	WebElement txt_Password;
	
	@FindBy(xpath="//input[@id='input-confirm']")
	WebElement txt_ConfirmPwd;
	
	@FindBy(xpath="//input[@name='agree']")
	WebElement chkbx_Agree;
	
	@FindBy(xpath="//input[@value='Continue']")
	WebElement btn_Continue;

	@FindBy(xpath="//h1[normalize-space()='Your Account Has Been Created!']")
	WebElement confirmMsg;
	
	public void setFirstName(String fname)
	{
		txt_Fname.sendKeys(fname);
	}
	
	public void setLastName(String lname)
	{
		txt_Lname.sendKeys(lname);
	}
	
	public void setEmail(String email)
	{
		txt_Email.sendKeys(email);
	}
	
	public void setTelephone(String telephone)
	{
		txt_telephone.sendKeys(telephone);
	}
	
	public void setPassword(String pwd)
	{
		txt_Password.sendKeys(pwd);
	}
	
	public void setConfirmpwd(String confirmpwd)
	{
		txt_ConfirmPwd.sendKeys(confirmpwd);
	}
	
	
	public void clickChkbxAgree() throws InterruptedException
	{
		//Thread.sleep(8000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView();", chkbx_Agree);
		//js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		chkbx_Agree.click();
	}
	
	public void clicksubmit()
	{
		btn_Continue.click();
	}
	
	public String getConfirmMsg()
	{
		try {
			return confirmMsg.getText();	
		}catch(Exception e)
		{return (e.getMessage());}
    }
}
	
