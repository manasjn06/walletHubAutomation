package pages;

import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class FBLoginPage {

	WebDriver driver;

	public FBLoginPage(WebDriver ldriver) {
		this.driver = ldriver;
	}

	@FindBy(xpath = ".//*[@id='email']")
	WebElement username;
	
	@FindBy(xpath = ".//*[@id='pass']")
	WebElement password;
	
	@FindBy(id="loginbutton")
	WebElement loginButton;
	
	@FindBy(name="xhpc_message")
	WebElement inputStatusUpdate;
	
	@FindBy(xpath=".//*[@type='submit']")
	List <WebElement> shareButton;
	
	@FindBy(xpath=".//a[contains(@action,'cancel')]")
	WebElement notification;

	
	public void loginApplication(String user, String pass) 
	{
		username.sendKeys(user);
		password.sendKeys(pass);
		loginButton.click();
		
	}
	
	public void statusUpdate()
	{
		inputStatusUpdate.sendKeys("Hello");
	}
	
	
	
	public boolean statusPostCheck()
	{
		return driver.getPageSource().contains("Hello");
	}
	
	
	public void clickOnNotification()
	{
		Actions action= new Actions(driver);
		action.sendKeys(Keys.ESCAPE).build().perform();
	}
	
	public void clickOnShareButton()
	{
		
		for (WebElement sharebuttonElement: shareButton )
		{
			String sButtons=sharebuttonElement.getAttribute("innerHTML");
			
			if(sButtons.contentEquals("Share"))
			{
				sharebuttonElement.click();
			}
		}
	}



}
