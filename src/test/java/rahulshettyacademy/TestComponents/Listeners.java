package rahulshettyacademy.TestComponents;

import java.io.File;
import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import rahulshettyacademy.resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	ThreadLocal<ExtentTest> extentTestThread = new ThreadLocal<ExtentTest>();
	
	
	@Override
	public void onTestStart(ITestResult result) {
		
		test = extent.createTest(result.getMethod().getMethodName());
		extentTestThread.set(test);
	}
	
	@Override
    public void onTestSuccess(ITestResult result) {
    	
		extentTestThread.get().log(Status.PASS, "Test Passed");
    }
    
	@Override
	public void onTestFailure(ITestResult result) {
    	
		extentTestThread.get().fail(result.getThrowable());
    	
    	String destinationFilePath = null;
    	
    	String testName = result.getMethod().getMethodName();
    	
    	try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (IllegalArgumentException | IllegalAccessException | NoSuchFieldException | SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	try {
			destinationFilePath = getScreenshot(testName, driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	extentTestThread.get().addScreenCaptureFromPath(destinationFilePath, testName);
    }
	


	@Override
    public void onFinish(ITestContext context) {
    	
		extent.flush();
    }



}
