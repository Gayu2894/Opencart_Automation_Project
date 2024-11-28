package com.opencart.pageobjects;

import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ProductsPage extends BasePage {

	public ProductsPage(WebDriver driver)
	{
		super(driver);
	}
	
	@FindBy(xpath="//button[@class='btn btn-link dropdown-toggle']")
	WebElement drpdowncurrency;
	
	@FindBy(xpath="//ul[@class='dropdown-menu']/li ")
	List<WebElement> optioncurrencyValues;
	
	@FindBy(xpath="//ul[@class='nav navbar-nav']/li")
	List<WebElement> categories; // tabs 
	
	@FindBy(xpath="//li/a[contains(@href,'path=33')]")
	WebElement CamerasLink;
	
	@FindBy(xpath="//button[@id='button-cart']")
	WebElement CamerasAddToCartbtn;
	
	@FindBy(xpath="//div[@class='text-danger']")
	WebElement dropdownErrorMsg;
	
	@FindBy(xpath="//img[@title='Canon EOS 5D']")
	WebElement canon5DCamera;
	
	
	//i[@class='fa fa-home'] -- home
	
	
	
	
	
	public void clickCurrency()
	{
		drpdowncurrency.click();
	}
	
	public void currencyValues(String selectcurrency) throws InterruptedException
	{
	  //int CurrencyCount=optioncurrencyValues.size();
	  for(WebElement cv:optioncurrencyValues)
	  {
		  String currency=cv.getText();
		  System.out.println(currency);
		  System.out.println("From property file " +selectcurrency);
		  if(currency.contains(selectcurrency))
	      {
			  Thread.sleep(8000);
			  cv.click(); 
			  break;
	      }
		  else {
			  System.out.println("Currency not present");
		  } 
	  }
	}
	
	public void clickCameras()
	{
		canon5DCamera.click();
	}
	
	public void orderCamera() throws InterruptedException
	{
		//canon5DCamera.click();
		Thread.sleep(5000);
		JavascriptExecutor js=(JavascriptExecutor) driver;
		js.executeScript("arguments[0].scrollIntoView;", CamerasAddToCartbtn);
		CamerasAddToCartbtn.click();	
	}
	public String ConfirmdrpdownErrorMsg()
	{
		try {
		String errMsg=dropdownErrorMsg.getText();
		return errMsg;
		}catch(Exception e)
		{
			return e.getMessage();
		}
		
	}
	

}
