package W3validetor;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class W3Validator {
public static void main(String[] args) {
	
//	m1();
	System.setProperty("webdriver.chrome.driver",
			"C:\\Users\\Admin\\Desktop\\sa\\SeleniumProgram\\drivers\\chromedriver.exe");
	ChromeDriver driver = new ChromeDriver();
	driver.get("http://localhost:8888/");
	ChromeOptions b= new ChromeOptions();
	b.addArguments("headless");
	String pagesource = driver.getPageSource();
	
	driver.get("https://validator.w3.org");

	driver.findElement(By.xpath("//a[@href='#validate_by_input']")).click();
	
	WebElement w3valTEXTAREA = driver.findElement(By.xpath("//textarea[@id='fragment']"));
	
	JavascriptExecutor java = (JavascriptExecutor)driver;
	java.executeScript("arguments[0] Document.getElement by id.value='"+pagesource+"'",w3valTEXTAREA);
	
	

}
// public static void m1() {
	// System.setProperty("webdriver.chrome.driver",
			// "C:\\Users\\Admin\\Desktop\\sa\\SeleniumProgram\\drivers\\chromedriver.exe");
	// ChromeDriver driver = new ChromeDriver();
	// driver.get("http://localhost:8888/");
	// String pagesource = driver.getPageSource();
	// System.out.println(pagesource);
//	return pagesource;
}
}