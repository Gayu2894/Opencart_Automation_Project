package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.pageobjects.AccountRegistration;
import com.opencart.pageobjects.HomePage;
import com.opencart.testbase.BaseTest1;


public class TC001_AccRegistrationTest extends BaseTest1{

	@Test(groups= {"Sanity"})
	public void verify_accountRegistration() throws InterruptedException
	{
		HomePage hp= new HomePage(driver);
		hp.clickMyAccount();
		hp.clickRegister();
		AccountRegistration acc=new AccountRegistration(driver);
		acc.setFirstName(randomString());
		acc.setLastName(randomString());
		acc.setEmail(randomString()+"@gmail.com");
		acc.setTelephone(randomNumber());
		String password=randomAlphaNumeric();
		acc.setPassword(password);
		acc.setConfirmpwd(password);
		acc.clickChkbxAgree();
		acc.clicksubmit();
		String confirmmsg=acc.getConfirmMsg();
		Assert.assertEquals(confirmmsg, "Your Account Has Been Created!");
	}
}
