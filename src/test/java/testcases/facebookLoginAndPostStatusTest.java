package testcases;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import factory.BrowserFactory;
import factory.DataProviderFactory;
import pages.FBLoginPage;
import utility.Helper;

/**
 * <b>FaceBook Login and Post Share Test</b></br>
 * 1.Open browser and open facebook url. 2.Login into Facebook with User name
 * and password. 3.Enter "Hello" in Status update box. 4.Click on Share button.
 * 5.Verify Hello status is posted.
 * 
 * @author Manas.Jena
 *
 */

public class facebookLoginAndPostStatusTest {
	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;

	@BeforeMethod
	public void setUP() {
		report = new ExtentReports(".\\Reports\\FBLoginWithReport.html", true);
		logger = report.startTest("FaceBook Loging and status post Test ");
		driver = BrowserFactory.getBrowser("chrome");
		driver.get(DataProviderFactory.getConfig().getFBUrl());
		logger.log(LogStatus.PASS, "Step-1", "Application is up and running.");
	}

	@Test
	public void faceBookLoginAndPostTest() throws InterruptedException {
		logger.log(LogStatus.PASS, "Step-2", "Login into facebook with user name and password.");
		FBLoginPage fbpage = PageFactory.initElements(driver, FBLoginPage.class);
		fbpage.loginApplication(DataProviderFactory.getExcel().getData(0, 1, 0),
				DataProviderFactory.getExcel().getData(0, 1, 1));
		Thread.sleep(3000);
		logger.log(LogStatus.PASS, "Step-3", "Enter Hello status.");
		fbpage.statusUpdate();
		Thread.sleep(3000);
		logger.log(LogStatus.PASS, "Step-4", "Click on share button.");
		fbpage.clickOnShareButton();
		logger.log(LogStatus.PASS, "Step-5", "Verify status update posted successfull.");
		Assert.assertEquals(fbpage.statusPostCheck(), true);

	}

	@AfterMethod
	public void tearDown(ITestResult result) {
		if (result.getStatus() == ITestResult.FAILURE) {
			String path = Helper.captureScreenshot(driver, result.getName());
			logger.log(LogStatus.FAIL, logger.addScreenCapture(path));
		}

		BrowserFactory.closeBrowser();
		report.endTest(logger);
		report.flush();
	}

}
