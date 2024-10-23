package com.opencart.testbase;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.Properties;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Platform;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;

public class BaseTest1 {
	public Properties p;
	public static WebDriver driver;
	private static ITestContext context;
	
	@BeforeClass(groups={"Sanity","Regression","Functional"})
	@Parameters({"os","browser"})
	public void setup(String os,String br,ITestContext iTestContext) throws IOException
	{
		
		FileReader file=new FileReader(".//src//test//resources//config.properties");
		p=new Properties();
		p.load(file);
		System.out.println(p.getProperty("execution_env"));
		if(p.getProperty("execution_env").equalsIgnoreCase("remote"))
		{
		   DesiredCapabilities capabilities=new DesiredCapabilities();
		   if(os.equalsIgnoreCase("windows"))
		   {
			   capabilities.setPlatform(Platform.WIN11);
		   }
		   else if(os.equalsIgnoreCase("linux"))
		   {
			   capabilities.setPlatform(Platform.LINUX);
		   }
		   else if(os.equalsIgnoreCase("mac"))
		   {
			   capabilities.setPlatform(Platform.MAC);
		   }
		   else
		   {
			   System.out.println("No Matching OS");
			   return;
		   }
		   switch(br.toLowerCase())
		   {
		     case "chrome":capabilities.setBrowserName("chrome");break;
		     case "edge":capabilities.setBrowserName("MicrosoftEdge");break;
		     case "firefox":capabilities.setBrowserName("firefox");break;
		     default: System.out.println("No Matching browser");return;
		   }
		   driver=new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);		
		}
		if(p.getProperty("execution_env").equalsIgnoreCase("local"))
		{
		  switch(br.toLowerCase())
	      {
	       case "chrome": driver=new ChromeDriver(); break;
	       case "edge": driver=new EdgeDriver(); break;
	       case "firefox":	driver=new FirefoxDriver(); break;
	       default: System.out.println("invaid browser"); return; 
	      }
	    }
	    this.context = setContext(iTestContext, driver); 
	    driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(50));
		driver.navigate().to(p.getProperty("appUrl"));
		//driver.navigate().to("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
	}
	
	@AfterClass(groups= {"Functional","Sanity","Regression"})
	public void tearDown()
	{
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

