package testClasses;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

import baseClasses.BaseClass;
import pomClasses.HomePage;
import pomClasses.ProfilePage;

@Listeners(ListenerClasses.Listener1.class)

public class VerifyUserCanAddNewAddress {
			
	static WebDriver driver;
	HomePage hp;
	ProfilePage pp;
	
	ExtentHtmlReporter ExtentReporter;
	ExtentReports report;
	ExtentTest test;
	
	@BeforeClass
	public void beforeClass() throws IOException
	{
		ExtentReporter = BaseClass.getHtmlReport();
		report = BaseClass.getReports();
		test = BaseClass.getExtentTest("VerifyUserCanAddNewAddress");
		
		driver = BaseClass.getDriver("chrome");
	}
	
	@BeforeMethod
	public void beforeMethod()
	{
		hp = new HomePage(driver);
	}
	
	@Test
	public void VerifyUserCanGoToProfilePage()
	{
		hp.moveToProfileName();
		hp.clickOnProfileText();
		hp.moveMouse(driver);
		
		pp = new ProfilePage(driver);
		
		Assert.assertTrue(pp.getFullProfileName());
	}
	
	@Test(priority=1)
	public void VerifyUserCanAddAddress() throws InterruptedException
	{
		pp.clickOnManageAddress();
		pp.clickOnAddNewAddress();
		
		int previouslySavedAddressCount = pp.savedAddressCount();
		System.out.println(previouslySavedAddressCount);
		
		pp.fillAddressDetails();
		pp.fillArea();
		pp.clickOnSaveBtn();
		
		Thread.sleep(1000);
		int currentAddressCount = pp.savedAddressCount();

		Assert.assertEquals(currentAddressCount, previouslySavedAddressCount + 1);
	}
	
	@AfterMethod
	public void afterMethod(ITestResult result) throws IOException
	{
		if(result.getStatus() == ITestResult.SUCCESS)
		{
			test.log(Status.PASS, result.getName() + " test passed");
		}
		else {
			
			String path = pp.getScreenShot(driver, result.getName());
			test.log(Status.FAIL, result.getName() + " test failed", MediaEntityBuilder.createScreenCaptureFromPath(path).build());
		}
	}
	
	@AfterClass
	public void afterClass()
	{
		report.flush();
	}


}
