package pages;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class WalletHubReviewPage {
	WebDriver driver;

	public WalletHubReviewPage(WebDriver driver) {
		this.driver = driver;

	}

	@FindBy(xpath=".//*[@id='wh-body-inner']/div[2]/div[3]/span")
	WebElement ratingstar;
	
	@FindBy(xpath = "//*[@id='wh-body-inner']/div[2]/div[3]/div[1]/div/a[5]")
	WebElement star;
	
	@FindBy(xpath = ".//*[@id='reviewform']/div[1]/div/div")
	WebElement policydropdown;
	
	@FindBy(xpath = ".//ul[@class='drop-el']//li/a")
	List <WebElement> selectPolicyDropDown;
	
	@FindBy(xpath = ".//*[@id='review-content']")
	WebElement reviewTextBox;
	
	@FindBy(xpath=".//*[@id='overallRating']/a[4]")
	WebElement overallRatingStar;
	
	@FindBy(xpath=".//input[@type='submit']")
	WebElement submitButton;
	
	@FindBy(xpath="//*[@id='review']/div[1]/h1/span/a")
	WebElement reviewPosted;
	
	@FindBy(xpath=".//*[@id='viewport']/header/div/nav[3]/div[1]/a[9]")
	WebElement profileMenu;
	
	@FindBy(xpath="//*[@id='m-user']/ul/li[1]/a")
	WebElement profile;
	
	@FindBy(xpath=".//*[@id='wh-body-inner']/div[1]/div[1]/div[2]/ul/li[3]/a")
	WebElement reviewTab;
	
	@FindBy(xpath=".//*[@id='viewport']/header/div/nav[3]/div[2]/a[1]")
	WebElement login;
	
	@FindBy(xpath=".//*[@id='join-login']/form/div[1]/input")
	WebElement user;
	
	@FindBy(xpath=".//*[@id='join-login']/form/div[2]/input")
	WebElement pass;
	
	@FindBy(xpath="//*[@id='join-login']/form/div[4]/button[2]")
	WebElement loginButton;
	
	
	public void mouseHover()

	{
		Actions action=new Actions(driver);
		action.moveToElement(ratingstar).build().perform();
		
	}

	public void clickOnStar()

	{
		star.click();
	}

	public void clickOnPolicyDropDown()

	{
		policydropdown.click();
	}

	public String getApplicationTitle() {
		return driver.getTitle();

	}

	public void selectPolicy() 
	{
		for (WebElement policyValue: selectPolicyDropDown )
		{
			String pValue=policyValue.getAttribute("innerHTML");
			
			if(pValue.contentEquals("Health"))
			{
				policyValue.click();
			}
		}
		
	}
	
	public void submitReviews()
	{
		overallRatingStar.click();
		reviewTextBox.clear();
		reviewTextBox.sendKeys("SampleTest SampleTest SampleTest SampleTest SampleTest "
				+ "SampleTestSampleTestSampleTestSampleTestSampleTestSampleTestSampleTestSampleTest"
				+ "SampleTestSampleTestSampleTestSampleTestSampleTestSampleTestSampleTestSampleTestSampleTest");
		submitButton.click();
	}
	
	public void reviewPosted()
	{
		reviewPosted.isDisplayed();
		String str=reviewPosted.getAttribute("innerHTML");
		System.out.println("review posted message"+str);
	}
	
	public void clickOnProfileMenu()
	{
		profileMenu.click();
	}
	
	public void clickProfile()
	{
		profile.click();
	}
	
	public void clickOnReviewTab()
	{
		reviewTab.click();
	}
	
	public boolean ReviewTextPosted()
	{
		return driver.getPageSource().contains("SampleTest SampleTest");
	}
	
	public void clickOnLogin()
	{
		login.click();
	}
	
	public void loginApplication(String userid,String password)
	{
		user.sendKeys(userid);
		pass.sendKeys(password);
		loginButton.click();
	}
		
	
	

}
