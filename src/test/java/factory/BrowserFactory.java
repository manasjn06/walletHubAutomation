package factory;


import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;


public class BrowserFactory 
{
	static WebDriver driver;
	
	public static WebDriver getBrowser(String browserName)
	
	{
		
		
		if (browserName.equalsIgnoreCase("firefox"))
		{
		    driver=new FirefoxDriver();
				        
		}
		else if(browserName.equalsIgnoreCase("Chrome"))
		{
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--disable-notifications");
			System.setProperty("webdriver.chrome.driver", DataProviderFactory.getConfig().getchromepath());
			driver=new ChromeDriver(options);
		}
		
		else if(browserName.equalsIgnoreCase("IE"))
		{
			System.setProperty("webdriver.ie.driver", DataProviderFactory.getConfig().getiepath());
			driver=new InternetExplorerDriver();
		}
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		return driver;
		
		
	}
	
	
	public static void closeBrowser()
	{
		driver.quit();
	}
	
	
	

}
