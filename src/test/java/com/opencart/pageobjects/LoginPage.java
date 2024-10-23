package com.opencart.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage{

	 public LoginPage(WebDriver driver)
	 {
		super(driver); 
	 }
	
	 @FindBy(xpath="//input[@id='input-email']")
	 WebElement txtEmail;
	
	 @FindBy(xpath="//input[@id='input-password']")
	 WebElement txtPwd;
	 
	 @FindBy(xpath="//input[@value='Login']")
	 WebElement btnLogin;
	 
	 @FindBy(xpath="//a[@title='My Account']")
	 WebElement lnkMyacc;
	 
	 @FindBy(xpath="//h1[normalize-space()='Account Logout']")
	 WebElement confirmLogout;
	 
	 public void lnkMyAcc()
	 {
		 lnkMyacc.click();
	 }
	 
	 public void setEmail(String Email)
	 {
		 txtEmail.sendKeys(Email);
	 }
	 
	 public void setPwd(String pwd)
	 {
		 txtPwd.sendKeys(pwd);
	 }
	 
	 public void clickLoginbtn()
	 {
		 btnLogin.click();
	 }
	
	 
	 public String getConfirmLogout()
	 {
		 try {
		    return confirmLogout.getText();
		}catch(Exception e)
		 {
			return(e.getMessage());
		 }
	 }
	 
	 
	 
	 
	 
	 
	 
	 
	 
}
    





















