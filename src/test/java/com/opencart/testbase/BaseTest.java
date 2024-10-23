package com.opencart.testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

public class BaseTest {

	public Properties p;
	public WebDriver driver;
	private static ITestContext context;

	@BeforeClass(groups = { "Functional", "Sanity", "Regression" })
	public void setup(ITestContext iTestContext) throws IOException {
		FileReader file = new FileReader(".//src//test//resources//config.properties");
		p = new Properties();
		p.load(file);
		driver = new ChromeDriver();
		this.context = setContext(iTestContext, driver); 
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.navigate().to(p.getProperty("appUrl"));
		// driver.navigate().to("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}

	@AfterClass(groups = { "Functional", "Sanity", "Regression" })
	public void tearDown() {
		driver.close();
	}

	public String randomString() {
		String generatedString = RandomStringUtils.randomAlphabetic(5);
		return generatedString;
	}

	public String randomNumber() {
		String generatedNumber = RandomStringUtils.randomNumeric(10);
		return generatedNumber;
	}

	public String randomAlphaNumeric() {
		String generatedCharacters = RandomStringUtils.randomAlphanumeric(10);
		return generatedCharacters;
	}

	public String captureScreen(WebDriver driver,String tname) throws IOException {
		String timeStamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        
		TakesScreenshot takeScreenShot = (TakesScreenshot) driver;
		File sourceFile = takeScreenShot.getScreenshotAs(OutputType.FILE);

		String targetFilePath = System.getProperty("user.dir") + "\\screenshots\\" + tname + "_" + timeStamp + ".png";
		File targetFile = new File(targetFilePath);
		sourceFile.renameTo(targetFile);

		return targetFilePath;
		
		
	}
	public static ITestContext setContext(ITestContext iTestContext, WebDriver driver) {
        iTestContext.setAttribute("driver", driver);

        return iTestContext;
    }

}
