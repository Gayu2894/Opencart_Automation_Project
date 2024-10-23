package com.opencart.testcases;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.opencart.pageobjects.HomePage;
import com.opencart.pageobjects.LoginPage;
import com.opencart.pageobjects.MyAccountPage;
import com.opencart.testbase.BaseTest1;
import com.opencart.utilities.DataProviders;

public class TC003_LoginDDT extends BaseTest1 {

	@Test(dataProvider="LoginData",dataProviderClass=DataProviders.class,groups= {"Functional","Sanity","Regression"})
	public void verifyLoginDDT(String email,String pwd, String res) throws InterruptedException
	{
		try {
		HomePage hp=new HomePage(driver);
		hp.clickMyAccount();
		hp.clickLogin();
		
		LoginPage lp=new LoginPage(driver);
		lp.setEmail(email);
		lp.setPwd(pwd);
		lp.clickLoginbtn();
		
		MyAccountPage ap=new MyAccountPage(driver);
		boolean targetPage=ap.confirmAccount();
		
		DataProviders dp=new DataProviders();
		//dp.getData();
		if(res.equalsIgnoreCase("valid"))
		{
			if(targetPage==true)
			{
			//	ExcelUtils.setCellData(pwd, 0, 0, res);
				Assert.assertTrue(true);
				ap.clickLogout();
			}
			else
			{
				Assert.assertTrue(false);
			}
		}
		if(res.equalsIgnoreCase("invalid"))
		{
			if(targetPage==true)
			{
				Assert.assertTrue(false);
				ap.clickLogout();
			}
			else
			{
				Assert.assertTrue(true);
			}
		}
		}catch(Exception e)
		{
			Assert.fail();
		}
	}
}
