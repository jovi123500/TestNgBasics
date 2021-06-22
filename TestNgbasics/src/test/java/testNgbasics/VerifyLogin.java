package testNgbasics;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;


public class VerifyLogin {
	public WebDriver driver;

	public void testinitialize(String browser) throws Exception {
		if (browser.equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver","E:\\seleniumfiles\\driverfiles\\chromedriver_win32\\chromedriver.exe");
			driver = new ChromeDriver();
		} else if (browser.equalsIgnoreCase("firefox")) {
			System.setProperty("webdriver.gecko.driver","E:\\seleniumfiles\\driverfiles\\geckodriver-v0.29.1-win32\\geckodriver.exe");
			driver = new FirefoxDriver();
		} else if (browser.equalsIgnoreCase("edge")) {
			System.setProperty("webdriver.edge.driver","E:\\seleniumfiles\\driverfiles\\edgedriver_win32\\edgedriver.exe");
			
			driver = new EdgeDriver();
		} else {
			throw new Exception("Invalid browser");
		}
		driver.manage().deleteAllCookies();
		driver.manage().window().maximize();
		
	}
	@BeforeMethod

	public void setUp() throws Exception {
		testinitialize("chrome");
	}

	@AfterMethod

	public void tearDown() {
		//driver.close();
		// driver.quit();
	}

	
	@Test
	public void login() throws IOException, InterruptedException
	{
		
		driver.get("http://demo.guru99.com/test/newtours/");
		ExcelCode obj = new ExcelCode();
		String e = obj.readdata(1, 0);
		Thread.sleep(3000);
		WebElement username = driver.findElement(By.name("userName"));
		username.sendKeys(e);
		String f = obj.readdata(1, 1);
		WebElement password = driver.findElement(By.name("password"));
		password.sendKeys(f);
		WebElement submitButton = driver.findElement(By.name("submit"));
		submitButton.click();
	}
}
