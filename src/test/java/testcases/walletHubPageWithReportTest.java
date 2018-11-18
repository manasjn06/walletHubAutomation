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
import pages.WalletHubReviewPage;
import utility.Helper;

/** 
 * <b>Wallet Hub Review Automation </b></br>
 * 
 * 1. Go to this URL: http://wallethub.com/profile/test_insurance_company/.</br>
 * 2. On the right part of the page, hover over the stars and click on the fourth star.</br>
 * 3. Your code should actually (a) do the hover and (2) make sure the stars inside get lit up when you hover over them, then (3) click on the fifth star.</br>
 * 4. Simply redirecting the WebDriver to the next page isn’t an option.</br>
 * 5. On the page you get redirected to, click on the Policy drop down and change the value to “Health”</br>
 * 6. Click on the link “Write a review” and write some random text (minimum of 200 characters) and Press submit.</br>
 * 7. If you are successful you should see a confirmation screen saying you have reviewed the institution.</br>
 * 8. You then have to go to your profile and confirm that a “review feed” got posted there.</br>
 * 9. Go to https://wallethub.com/profile/<username>/reviews/.</br>
 * 10.assertTrue() that you can see the review feed with the text you entered on the previous page.
 *  
 * @author Manas.Jena
 *
 */

public class walletHubPageWithReportTest {

	WebDriver driver;
	ExtentReports report;
	ExtentTest logger;

	@BeforeMethod
	public void setUp() {
		report = new ExtentReports(".\\Reports\\WalletHubTestWithReport.html", true);
		logger = report.startTest("Verify Wallet Hub Review Automation Test ");
		driver = BrowserFactory.getBrowser("chrome");
		driver.get(DataProviderFactory.getConfig().getApplicationUrl());
		logger.log(LogStatus.PASS, "Step-1","Application is up and running.");
	}

	@Test
	public void walletHubPageTest() throws InterruptedException

	{
		WalletHubReviewPage walletHubReviewPage = PageFactory.initElements(driver, WalletHubReviewPage.class);
		walletHubReviewPage.clickOnLogin();
		Thread.sleep(3000);
		walletHubReviewPage.loginApplication(DataProviderFactory.getExcel().getData(0, 0, 0), DataProviderFactory.getExcel().getData(0, 0, 1));
		Thread.sleep(300000);
		logger.log(LogStatus.PASS, "Step-2,Step-3 & step-4", "hover over the stars and click on the fourth star and click on fifth star.");
		Thread.sleep(3000);
		walletHubReviewPage.mouseHover();
		Thread.sleep(3000);
		walletHubReviewPage.clickOnStar();
		Thread.sleep(3000);
		logger.log(LogStatus.PASS, "Step-5", "Click on policy dropdown and select policy name Health");
		walletHubReviewPage.clickOnPolicyDropDown();
		Thread.sleep(3000);
		walletHubReviewPage.selectPolicy();
		Thread.sleep(3000);
		logger.log(LogStatus.PASS, "Step-6", "Enter random value in review text box and click on submit button");
		walletHubReviewPage.submitReviews();
		Thread.sleep(3000);
		logger.log(LogStatus.PASS, "Step-7", "Your review posted successfully");
		walletHubReviewPage.reviewPosted();
		logger.log(LogStatus.PASS, "Step-8","Go to your profile and confirm that a “review feed” got posted there");
	    walletHubReviewPage.clickOnProfileMenu();
	    walletHubReviewPage.clickProfile();
	    walletHubReviewPage.clickOnReviewTab();
	    walletHubReviewPage.ReviewTextPosted();
	    logger.log(LogStatus.PASS, "step-9", "Go to https://wallethub.com/profile/<username>/reviews/ and verified review feed with the text entered preveously");
	    driver.get(DataProviderFactory.getConfig().getReviewUrl());
	    Assert.assertTrue(walletHubReviewPage.ReviewTextPosted(), "Review Text not posted"); ;
	    logger.log(LogStatus.PASS,"Step-10","Review comment posted successfully" );
	    
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
