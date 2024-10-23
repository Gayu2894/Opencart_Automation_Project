package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.LoginPage;
import com.opencart.pageobjects.MyAccountPage;
import com.opencart.testbase.BaseTest1;


public class TC002_LoginTest extends BaseTest1{
	
	@Test(groups= {"Sanity","Regression"})
	public void verifyLogin() throws InterruptedException
	{
		HomePage hp=new HomePage(driver);
		LoginPage lp=new LoginPage(driver);
		MyAccountPage ap=new MyAccountPage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		lp.setEmail(p.getProperty("email"));
		lp.setPwd(p.getProperty("password"));
		lp.clickLoginbtn();
		boolean confirmAcc=ap.confirmAccount();
		Assert.assertEquals(confirmAcc, true);
		lp.lnkMyAcc();
		ap.clickLogout();
		String confirmLogout=lp.getConfirmLogout();
		Assert.assertEquals(confirmLogout, "Account Logout");	
		}
	
}
