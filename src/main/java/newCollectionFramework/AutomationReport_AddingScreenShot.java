package newCollectionFramework;

import java.io.File;
import java.io.IOException;
import java.util.Date;

import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class AutomationReport_AddingScreenShot {

	public static void mainAk(String[] args) throws IOException {

		Date time = new Date();
//	    long seconds = time.getTime();

		File filePath = new File(System.getProperty("user.dir") + "//Automation_Report//reporting.html");// where do you want to keep your file(Location)" path we  will use system.getProperty
																											
																											 
		ExtentSparkReporter extntreportr = new ExtentSparkReporter(filePath);

		ExtentReports extreport = new ExtentReports();
		extreport.attachReporter(extntreportr);

		ExtentTest creatTestName = extreport.createTest("tc001"); // we give the name of test case

		WebDriverManager.chromedriver().setup();

		WebDriver driver = new ChromeDriver();
		creatTestName.log(Status.INFO, "Chreme driver launched successfully"); // we will mension that our browser has
																				// launched only for informantion
		extreport.setSystemInfo("user_name", System.getProperty("user.name"));
		extreport.setSystemInfo("os_name", System.getProperty("os.name"));
		extreport.setSystemInfo("server name","DEV");
		
		driver.get("http://localhost:8888");                                   // we will hit the URL
		creatTestName.log(Status.INFO, "url nevegated http://localhost:8888");

		driver.findElement(By.name("user_name")).sendKeys("admin");
		creatTestName.log(Status.INFO, "user_name (admin) is enterd successfully");

		driver.findElement(By.name("user_password")).sendKeys("admin");

		TakesScreenshot screenshot1 = (TakesScreenshot) driver;
		File fromScreenShot1 = screenshot1.getScreenshotAs(OutputType.FILE);
		File toScreenShot1 = new File("Automation_Report//" + time.toString().replace(":", "_") + ".png");
		Files.copy(fromScreenShot1, toScreenShot1);
		creatTestName.addScreenCaptureFromPath(toScreenShot1.getAbsolutePath());

		creatTestName.log(Status.INFO, "user_password (admin) is enterd successfully");

		driver.findElement(By.name("Login")).click();
		creatTestName.log(Status.INFO, "login button clicked successfully");

		// we will creat validatio that our test case is fail or pass like enable
		// disable

		WebElement checkMarketingOnUI = driver.findElement(By.xpath("//a[text()='Marketing']"));
	
		String marketingText = checkMarketingOnUI.getText();

		
		if (marketingText.contains("Marketings")) { // it bydifault will be true
			creatTestName.log(Status.INFO, "marketing button is visibal Tc001 is passed");
		} else {
			creatTestName.log(Status.FAIL, "marketing button is not visibal Tc001 is fail");

			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File fromScreenShot = screenshot.getScreenshotAs(OutputType.FILE);
			File toScreenShot = new File("Automation_Report//" + time.toString().replace(":", "_") + ".png");
			Files.copy(fromScreenShot, toScreenShot);
			creatTestName.addScreenCaptureFromPath(toScreenShot.getAbsolutePath());

		}
		checkMarketingOnUI.click();
		
		String marketingCompainName = driver.findElement(By.xpath("//td[@class='moduleName']")).getText();
		creatTestName.log(Status.INFO, "marketingCompainName text is passed");
		
		System.out.println(marketingCompainName);
	
		extreport.flush();
	}
	   
	
	
	
	
	
	
	
}