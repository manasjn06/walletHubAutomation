package dataprovider;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

public class ConfigDataProvider 
{
	Properties pro;

	public ConfigDataProvider() 
	{
		File src = new File("./Configuration/config.properties");

		try {
			FileInputStream fis = new FileInputStream(src);
			pro = new Properties();
			pro.load(fis);
		} catch (Exception e) {
			System.out.println("Exception is: " + e.getMessage());

		}

	}
	
	
	public String getApplicationUrl()
	{
		String url1=pro.getProperty("url1");
		return url1;
		
	}
	
	public String getReviewUrl()
	{
		String url2=pro.getProperty("url2");
		return url2;
		
	}
	
	public String getFBUrl()
	{
		String url3=pro.getProperty("url3");
		return url3;
		
	}
	
	public String getchromepath()
	{
		String driverpath=pro.getProperty("chromepath");
		return driverpath;
		
	}
	
	public String getiepath()
	{
		String driverpath=pro.getProperty("iepath");
		return driverpath;
		
	}
	
	
	

}
