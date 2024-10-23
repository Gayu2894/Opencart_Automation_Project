package com.opencart.utilities;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.opencart.testbase.BaseTest1;


public class ExtentReportManager implements ITestListener {

	public ExtentSparkReporter sparkReporter;//UI of the report(look and feel)
	public ExtentReports extent;//common info of the report
	public ExtentTest test;//Create the test case entries and update the test results
	String repName;
	
	//will run only once
	 public void onStart(ITestContext context) {
		 
		/* SimpleDateFormat df=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		 Date dt=new Date();
		 String currentDateTimeStamp=df.format(dt);*/

		 String timeStamp=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
		 repName ="Test-Report-"+timeStamp+".html";
		 
		 sparkReporter=new ExtentSparkReporter(".\\reports\\"+repName);
		 sparkReporter.config().setDocumentTitle("Opencart Automation Report");
		 sparkReporter.config().setReportName("Opencart Functional Testing");
		 sparkReporter.config().setTheme(Theme.STANDARD);
		 
		 extent=new ExtentReports();
		 extent.attachReporter(sparkReporter); //attaching common info to sparkreporter
		 
		 extent.setSystemInfo("Application", "Opencart");
		 extent.setSystemInfo("Module", "Admin");
		 extent.setSystemInfo("Sub Module", "Customers");
		 extent.setSystemInfo("User Name", System.getProperty("user.name"));
		 extent.setSystemInfo("Environment", "QA");
		 
		 String os=context.getCurrentXmlTest().getParameter("os");
		 extent.setSystemInfo("Operating System", os);
		 
		 String browser=context.getCurrentXmlTest().getParameter("browser");
		 extent.setSystemInfo("Browser", browser);
		 
		 List<String> includedGroups= context.getCurrentXmlTest().getIncludedGroups();
		 if(!includedGroups.isEmpty())
		 {
			 extent.setSystemInfo("Groups", includedGroups.toString());
		 }
	 }
	 
	
	public void onTestSuccess(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());//Creating a new entry in the report with method name
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.PASS, result.getName() + " got Successfully executed"); //update the status as pass with method name
	  }
	 
	 public void onTestFailure(ITestResult result) {

		test = extent.createTest(result.getTestClass().getName());//Creating a new entry in the report with method name
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.FAIL,result.getName()+" got failed"); //update the status as fail with method name
		test.log(Status.INFO,result.getThrowable().getMessage());//will return error message
		WebDriver driver = (WebDriver) result.getTestContext().getAttribute("driver");  // here we are accessing the driver object that we added in Test class  
		String imgPath;
			try {
				imgPath = new BaseTest1().captureScreen(driver,result.getName());
				test.addScreenCaptureFromPath(imgPath);
			} catch (IOException e) {
				e.printStackTrace();
			}
		  }
	 
	 public void onTestSkipped(ITestResult result) {

        test = extent.createTest(result.getTestClass().getName());//Creating a new entry in the report with method name
		test.assignCategory(result.getMethod().getGroups());
		test.log(Status.SKIP,result.getName()+" got skipped"); //update the status as skip with method name
		test.log(Status.INFO,result.getThrowable().getMessage());	//will return error message
			
	 }
	
	 //will run only once after execution is completed 
	 public void onFinish(ITestContext context) {
		
		 //must provide this method to write the report 
		 extent.flush();
		 
		 String pathOfExtentReport=System.getProperty("user.dir")+"\\reports\\"+repName;
		 File extentReport= new File(pathOfExtentReport);
		 
		 try {
			Desktop.getDesktop().browse(extentReport.toURI());
		} catch (IOException e) {
			e.printStackTrace();
		}
	 }
	
}
