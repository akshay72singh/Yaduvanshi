package newCollectionFramework;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Wait {

	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\Users\\Admin\\Desktop\\sa\\SeleniumProgram\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();

		driver.get("http://localhost:8888");
		//		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(7));
		//		driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
		//      Thread.sleep(6000);

		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
		WebElement name = driver.findElement(By.name("user_name"));
		name.sendKeys("admin");
		wait.until(ExpectedConditions.textToBePresentInElementValue(name,"admin"));
		System.out.println("ramji");
		WebElement password = driver.findElement(By.name("user_password"));
		password.sendKeys("admin");
        System.out.println(password.getAttribute("value"));
	}

}