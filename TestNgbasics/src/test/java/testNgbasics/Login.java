package testNgbasics;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;


public class Login
{
	public WebDriver driver;
	public void testInitialize(String browser) throws Exception
	{
		if (browser.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver","E:\\seleniumfiles\\driverfiles\\chromedriver_win32\\chromedriver.exe");
			driver=new ChromeDriver();
		}
		else if(browser.equalsIgnoreCase("firefox")) 
		{
			System.setProperty("webdriver.gecko.driver","E:\\seleniumfiles\\driverfiles\\geckodriver-v0.29.1-win32\\geckodriver.exe");
			driver=new FirefoxDriver();
		}
		else if (browser.equalsIgnoreCase("edge"))
		{
			System.setProperty("webdriver.edge.driver","E:\\seleniumfiles\\driverfiles\\edgedriver_win32\\edgedriver.exe");
			driver=new EdgeDriver();
		}
		else
		{
			throw new Exception("Invalid browser");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		//driver.get("http://demo.guru99.com/test/newtours/");
		//driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
	}

	@BeforeMethod
	@Parameters({"url","browser"})
	public void setUp(String urlValue,String browserName) throws Exception
	{
		testInitialize(browserName);
		driver.get(urlValue);
	}
	@AfterMethod
	public void tearDown(ITestResult result) throws IOException 
	{
	if(ITestResult.FAILURE==result.getStatus())
	{
		TakesScreenshot takescreenshot=(TakesScreenshot)driver;
		File screenshot=takescreenshot.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(screenshot, new File("./Screenshots/"+result.getName()+".png"));
	}
	}
	{
		//driver.close();
		// driver.quit();
	}
	
	@Test(enabled=true,dataProvider="userCredentials")
	public void verifyLogin(String uname,String pass)
	{
		
		WebElement username = driver.findElement(By.name("userName"));
		WebElement password = driver.findElement(By.name("password"));
		WebElement submit = driver.findElement(By.name("submit"));

		username.sendKeys(uname);
		password.sendKeys(pass);
		submit.click();
	}
	@DataProvider(name="userCredentials")
	public Object[][] userCredentials()
	{
		Object[][] data=new Object[2][2];
		data[0][0]="test123";
		data[0][1]="12345";
		
		data[1][0]="invalid user";
		data[1][1]="invalid password";
		
		return data;
	}
	
	@Test(enabled = false)
	public void verifyPageTitle() {
		
		String expectedPageTitle = "Welcome: Mercury Tours123";
		String actualTitle = driver.getTitle();
		Assert.assertEquals(actualTitle, expectedPageTitle, "page title mismatch found");
	}
}