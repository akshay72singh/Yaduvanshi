package newCollectionFramework;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.Color;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.google.common.io.Files;

import io.github.bonigarcia.wdm.WebDriverManager;

public class GenericMethod {

	private ExtentReports reports;
	private ExtentTest extTest;
	private WebDriver driver;

	/**
	 * 
	 */
	public void toFlushTheReport() {
		try {
			reports.flush();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void toHndleTable() {
		try {
//			boolean elementEnability=ToCheckElementEnability(webElement, elementname);
//			if(elementEnability) {
			List<WebElement> leads = driver.findElements(By.xpath("//td[contains(text(),'LEA')]"));
			for (int i = 0; i < leads.size(); i++) {
				WebElement get = leads.get(i);
				String text = get.getText();
				System.out.println(text);
				if (text.equalsIgnoreCase("Babli1")) {
					System.out.println("hiiiii");
					List<WebElement> edit = driver.findElements(By.xpath("//a[contains(text(),'edit')]"));
					for (int j = 0; j < edit.size(); j++) {
						WebElement editGet = edit.get(i);
						editGet.click();
//						editGet.click();
//						String editText = editGet.getText();
//						System.out.println(editText);

					}
				}
			}
		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void toKnowAboutUser_Window() {
		try {
			reports.setSystemInfo("user name is - ", System.getProperty("user.name"));
			reports.setSystemInfo("Os name is - ", System.getProperty("os.name"));
			reports.setSystemInfo("Server name is - ", System.getProperty("QA server"));

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to create date, which helps to generate different report
	 * and screenshot
	 * 
	 * @parameter -TestCaseID -Parameteris used for giving a name to Test case
	 * @return it returns current date which is String type
	 */
	public String dateFormate() {

		String date = "";
		try {

			DateFormat dateformate = new SimpleDateFormat("dd_MM_yyyy___hh_mm_ss");
			date = dateformate.format(new Date());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return date;
	}

	/**
	 * This method is used to generate report, which shows you all history of your
	 * program
	 * 
	 * @param TestCaseID -Parameter is used for giving a name to Test case
	 * @return It returns your generated report which is ExtentReports Type
	 */
	public ExtentReports toGenerateReport(String generatingFileName, String folderToSaveReport) {

		try {
			String date = dateFormate();

			ExtentSparkReporter exspark = new ExtentSparkReporter(
					folderToSaveReport + "\\" + generatingFileName + date + ".html");
			reports = new ExtentReports();
			reports.attachReporter(exspark);

			toKnowAboutUser_Window();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return reports;
	}

	public ExtentTest toCreateTest(ExtentReports reports, String testCaseID) {

		try {
			extTest = reports.createTest(testCaseID);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return extTest;
	}

	/**
	 * This method is used to launch Browser and hit (open) url .
	 * 
	 * @param browsername -Parameter is used for launching browser as a String type
	 * @param url         -Parameter is used for hitting or open url as a String
	 *                    type
	 */
	public void ToLaunchBrowserAndHitUrl(String browsername, String url) {

		try {

			if (browsername.equalsIgnoreCase("chrome")) {

				WebDriverManager.chromedriver().setup();

				driver = new ChromeDriver();

				extTest.log(Status.INFO, (browsername + "browser is launched"));
			} else if (browsername.equalsIgnoreCase("firefox")) {

				WebDriverManager.firefoxdriver().setup();

				driver = new FirefoxDriver();

				extTest.log(Status.INFO, browsername + "browser is launched");

			} else if (browsername.equalsIgnoreCase("edge")) {

				WebDriverManager.edgedriver().setup();
				driver = new ChromeDriver();

				extTest.log(Status.INFO, browsername + "browser is launched");

			} else {
				extTest.log(Status.INFO, "Browser name is invailid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		driver.get(url);

	}

	/**
	 * This method is used to hit (open) the url .
	 * 
	 * @param url -Parameter is used to give the url .
	 * @parameter -Browsername -Parameter is used to identify the browser name
	 */

	public void hitUrl(String browsername, String url) {

		driver.get(url);

	}

	/**
	 * This method is used to take a screenShot of accuring error place.
	 * 
	 * @param inputboxname- parameter is used to identify the element name where
	 *                      error will come which String type.
	 */
	public void toTakeScreenShot(String inputboxname) {

		try {

			TakesScreenshot screenshot = (TakesScreenshot) driver;
			File from = screenshot.getScreenshotAs(OutputType.FILE);
			File to = new File("Automation_Report\\screenshot " + inputboxname + ".png");
			Files.copy(from, to);
			extTest.addScreenCaptureFromPath(to.getAbsolutePath());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to search the element in HTML page with the help of
	 * all locaters.
	 * 
	 * @param locatername -parameter is used to identify the name of locaters and it
	 *                    is String type.
	 * @param locater     -parameter is used to give the locater value according to
	 *                    locater name and it is String type.
	 * @return This method returns the WebElement type of object in which the
	 *         location of founded element will be.
	 */
	public WebElement toSearchTheElementByLocaters(String locaterType, String locaterValue) {

		WebElement web = null;
		try {
			if (locaterType.equalsIgnoreCase("xpath")) {
				web = driver.findElement(By.xpath(locaterValue));
			} else if (locaterType.equalsIgnoreCase("name")) {
				web = driver.findElement(By.name(locaterValue));

			} else if (locaterType.equalsIgnoreCase("linkText")) {
				web = driver.findElement(By.linkText(locaterValue));
			} else if (locaterType.equalsIgnoreCase("class")) {
				web = driver.findElement(By.className(locaterValue));
			} else if (locaterType.equalsIgnoreCase("CSS selector")) {
				web = driver.findElement(By.cssSelector(locaterValue));
			} else if (locaterType.equalsIgnoreCase("id")) {
				web = driver.findElement(By.id(locaterValue));
			} else if (locaterType.equalsIgnoreCase("tagName")) {
				web = driver.findElement(By.tagName(locaterValue));
			} else if (locaterType.equalsIgnoreCase("PartialLinksText")) {
				web = driver.findElement(By.partialLinkText(locaterValue));
			} else {
				extTest.log(Status.FAIL, "the locater value is wrong");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return web;
	}

	/**
	 * This method is used to check every element enability and Displaybility.
	 * 
	 * @param web                 -Parameter is used to give the locater name and
	 *                            locater value ,it is WebElement type.
	 * @param inputBoxNameForInfo -Parameter is used give information of current
	 *                            working element.
	 * @return This method returns status which value was -false before checking
	 *         enability and Displaybility of element, and if all conditions will be
	 *         checked it will return -true ,which is boolean type.
	 */
	public boolean toCheckElementEnability(WebElement web, String inputBoxNameForInfo) {

		boolean status = false;

		if (web.isDisplayed()) {

			if (web.isEnabled()) {
				extTest.log(Status.INFO, inputBoxNameForInfo + " box is Displaying and enable");

				status = true;

			} else {
				extTest.log(Status.FAIL, inputBoxNameForInfo + " box is not enable");
			}
		} else {
			extTest.log(Status.FAIL, inputBoxNameForInfo + " box is not displaying");
		}
		return status;
	}

	/**
	 * This method is used to send the any value in input box.
	 * 
	 * @param web          -Parameter is used to search the element on UI by
	 *                     locaterType and locaterValue.
	 * @param value        -Parameter is used to send the value in input box.
	 * @param inputboxname -Parameter is used to give the message of current working
	 *                     element.
	 */
	public void toSendValueInInputBox(WebElement web, String value, String inputboxname) {

		try {
			boolean enability = toCheckElementEnability(web, inputboxname);

			web.sendKeys(value);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is use to click on any button.
	 * 
	 * @param web          -Parameter is used to search the element on UI by locater
	 *                     type and locater value.
	 * @param inputboxname -parameter is used to give the message of current working
	 *                     element.
	 */
	public void ToClickAnyButton(WebElement web, String inputboxname) {

		try {

			boolean elementEnability = toCheckElementEnability(web, inputboxname);

			if (elementEnability) {
				web.click();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This method is used to Handle the dropDown by it's visible text.
	 * 
	 * @param web                             -Parameter is used to search the
	 *                                        element by locater type and locater
	 *                                        value.
	 * @param inputboxname                    -Parameter is used to give the Info of
	 *                                        current working element.
	 * @param visibalTextOfSelectingOptInDrop -parameter is used to give the visible
	 *                                        text of window for working on that.
	 */
	public void ToHandleDropDownByVisibalText(WebElement web, String inputboxname,
			String visibalTextOfSelectingOptInDrop) {
		try {

			boolean dropDownEnability = toCheckElementEnability(web, inputboxname);

			if (dropDownEnability == true) {
				Select select = new Select(web);
				select.selectByVisibleText(visibalTextOfSelectingOptInDrop);
			}
		} catch (Exception n) {
			extTest.log(Status.FAIL, "here is a exception");

		}
	}

	/**
	 * This method is used to handle drop down with Index.
	 * 
	 * @param web                           -parameter is used to search the element
	 *                                      on UI by locaterType and locaterValue.
	 * @param endexOfSelectingOPTinDropDown -parameter is used to give the index of
	 *                                      window on which we will work.
	 * @param inputboxname                  -parameter is used for giving the
	 *                                      message of working Window.
	 */
	public void ToHandleDropDownByIndexOf(WebElement web, int endexOfSelectingOPTinDropDown, String inputboxname) {

		try {
			boolean elementLocaterEnability = toCheckElementEnability(web, inputboxname);

			if (elementLocaterEnability == true) {

				Select select = new Select(web);
				select.selectByIndex(endexOfSelectingOPTinDropDown);
				extTest.log(Status.PASS, "the option is selected in dropdown");
			}
		} catch (Exception e) {
			toTakeScreenShot(inputboxname);
		}
	}

	/**
	 * This method is used to Handle the Window by Value of Attribute value / Value
	 * attribute .
	 * 
	 * @param web                   -parameter is used to search the element on UI
	 *                              by locater type and locater value.
	 * @param valueOfAttrebuteValue -parameter is used to give the value of
	 *                              attributes which will be working window.
	 * @param inputboxname          -parameter is used to give the message of
	 *                              working on Element.
	 */
	public void ToHandleDropDownByValue(WebElement web, String valueOfAttrebuteValue, String inputboxname) {

		try {

			boolean elementEnability = toCheckElementEnability(web, inputboxname);

			if (elementEnability == true) {
				Select select = new Select(web);
				select.selectByValue(valueOfAttrebuteValue);
			}
		} catch (Exception e) {
			toTakeScreenShot(inputboxname);
		}
	}

	/**
	 * This method is used to find the inner text of element.
	 * 
	 * @param web          -parameter is used to search the element on UI by locater
	 *                     type and locater value which is WebElement type.
	 * @param inputboxname -parameter is used to give the message of working Element
	 *                     which is String type.
	 * @return this method returns founded innerText of element which is String type
	 *         .
	 */
	public String ToFindTheInnerText(WebElement web, String inputboxname) {

		String innerText = "";
		try {

			boolean elementEnability = toCheckElementEnability(web, innerText);

			if (elementEnability == true) {
				innerText = web.getText();
//				return innerText;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return innerText;
	}

	/**
	 * This method is used to find the value of any attribute.
	 * 
	 * @param web           -parameter is used to search the element on UI by
	 *                      locater type and locater value which is WebElement type.
	 * @param attributename -parameter is used to give the attribute name which
	 *                      attribute value we want and it is String Type.
	 * @return This method returns founded innerText of attribute which is String
	 *         Type.
	 */
	public String ToGetAttribute(WebElement web, String attributename) {

		String valueOfAttribute = "";
		try {

			boolean elementEnability = toCheckElementEnability(web, attributename);
			if (elementEnability) {

				valueOfAttribute = web.getAttribute(attributename);
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(attributename);
		}
		return valueOfAttribute;
	}

	/**
	 * This method is used to count the all options in dropDown.
	 * 
	 * @param web          -parameter is used to search the element on UI by locater
	 *                     type and locater value which is WebElement type.
	 * @param dropDownName -parameter is used to give the message of element, on
	 *                     which we are working and it is String type.
	 */
	public void ToCountOptionsOfDropdown(WebElement web, String dropDownName) {

		try {
			boolean elementEnability = toCheckElementEnability(web, dropDownName);

			if (elementEnability) {

				Select allOption = new Select(web);
				List<WebElement> getoptions = allOption.getOptions();
 
				for (int i = 0; i < getoptions.size(); i++) {
					WebElement get = getoptions.get(i);
					String text = get.getText();
					System.out.println(text);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(dropDownName);
		}
	}

	/**
	 * This method is used to know the Selected option in dropDown.
	 * 
	 * @param web          -parameter is used to search the element on UI by locater
	 *                     type and locater value which is WebElement type.
	 * @param dropDownName - this parameter is used to give the message of working
	 *                     Element which is String type.
	 */
	public void toGetSelectedValueInDrop(WebElement web, String dropDownName) {

		try {
			boolean elementEnability = toCheckElementEnability(web, dropDownName);

			if (elementEnability) {

				Select select = new Select(web);
				WebElement option = select.getFirstSelectedOption();
				System.out.println(option.getText());
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(dropDownName);
		}
	}

	/**
	 * This method is used to mouse Over on Element.
	 * 
	 * @param web                  -parameter is used to search the element on UI by
	 *                             locater type and locater value which is
	 *                             WebElement type.
	 * @param mouseOveringLinkName -parameter is used to give the mouseOverring Link
	 *                             which is String type.
	 */
	
	public void toMouseOver(WebElement web, String mouseOveringLinkName) {

		Actions action = null;
		try {
			boolean elementEnability = toCheckElementEnability(web, mouseOveringLinkName);

			if (elementEnability) {
				action = new Actions(driver);
				action.moveToElement(web).build().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(mouseOveringLinkName);
		}
	}

	/**                          This Method is used to click on any button by Actions class.
	 * @param web                -parameter is used to search the element on UI by
	 *                           locater type and locater value which is WebElement
	 *                           type.
	 * @param clickingElementName -parameter is used to give the element name on which working ,it is String type. 
	 */
	public void toClickByActions(WebElement web, String clickingElementName) {


		try {
			boolean elementEnability = toCheckElementEnability(web, clickingElementName);
			if (elementEnability) {
				Actions act =new Actions(driver);
				act.click(web).build().perform();
			}

		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(clickingElementName);
		}
	}

	/**                         This method is used to send a value in input box By Actions Class.
	 * @param web               -parameter is used to search the element on UI by
	 *                          locater type and locater value which is WebElement
	 *                          type.
	 * @param inputBoxName      -parameter is used to give the name of Element. 
	 * @param sendingValue      -Parameter is used to send value in input box.
	 */
	public void toSendKeysByActions(WebElement web, String inputBoxName, String sendingValue){


		try {
			boolean elementEnability = toCheckElementEnability(web, inputBoxName);

			if (elementEnability) {
				Actions act = new Actions (driver);
				act.sendKeys(web, sendingValue).build().perform();
			}

		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(inputBoxName);
		}
	}

	/**
	 * @param web
	 * @param frameNameForInfo
	 * @param indexOfFrame
	 * @throws IOException
	 */
	public void toSwitchInFrameByIndex(WebElement web, String frameNameForInfo, int indexOfFrame) throws IOException {

		try {
			boolean elementEnability = toCheckElementEnability(web, frameNameForInfo);

			if (elementEnability) {
				driver.switchTo().frame(indexOfFrame);
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(frameNameForInfo);
		}
	}

	/**
	 * @param web
	 * @param frameNameForInfo
	 * @param indexOfFrame
	 * @param FrameByNameOrId
	 * @throws IOException
	 */
	public void toSwitchInFrameByNameOrId(WebElement web, String frameNameForInfo, int indexOfFrame,
			String FrameByNameOrId) throws IOException {

		try {
			boolean elementEnability = toCheckElementEnability(web, frameNameForInfo);

			if (elementEnability) {
				driver.switchTo().frame(FrameByNameOrId);
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(frameNameForInfo);
		}
	}

	/**
	 * @param web
	 * @param frameNameForInfo
	 * @param indexOfFrame
	 * @param FrameByWebElement
	 * @throws IOException
	 */
	public void toSwitchInFrameByWebElement(WebElement web, String frameNameForInfo, int indexOfFrame,
			String FrameByWebElement) throws IOException {

		try {
			boolean elementEnability = toCheckElementEnability(web, frameNameForInfo);

			if (elementEnability) {
				driver.switchTo().frame(FrameByWebElement);
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(frameNameForInfo);
		}
	}

	/**
	 * @param web
	 * @param windowName
	 */
	public void toLaunchWindow(WebElement web, String windowName, String expectedWindowTitle_Url,
			String actualWindowTitle_Url) {
		try {
			boolean enability = toCheckElementEnability(web, windowName);
			if (enability) {
				Set<String> windows = driver.getWindowHandles();
				for (String string : windows) {
					driver.switchTo().window(string);
					if (expectedWindowTitle_Url.equalsIgnoreCase(actualWindowTitle_Url)) {

					}
				}
			}
//		driver.findElement(By.linkText("Help")).click();

//		driver.findElement(By.linkText("About Us")).click();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param windowTitle
	 * @param windowName
	 * @throws IOException
	 */
	public void toHandleWindowByTitle(WebElement web, String windowTitle, String windowName) throws IOException {

		try {
			boolean elementEnability = toCheckElementEnability(web, windowName);

			if (elementEnability) {
				Set<String> windows = driver.getWindowHandles();
				for (String string : windows) {
					String title = driver.getTitle();
					if (title.equalsIgnoreCase(windowTitle)) {
						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(windowName);
		}
	}

	/**
	 * @param web
	 * @param windowUrl
	 * @param windowName
	 * @throws IOException
	 */
	public void toHandleWindowByUrl(WebElement web, String windowUrl, String windowName) {

		try {
			boolean elementEnability = toCheckElementEnability(web, windowName);

			if (elementEnability) {
				Set<String> windows = driver.getWindowHandles();
				for (String string : windows) {
					String title = driver.getCurrentUrl();
					if (title.equalsIgnoreCase(windowUrl)) {

						break;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(windowName);
		}
	}

	/**
	 * @param web
	 * @param windowTitle
	 * @param elementNameGettingTitle
	 * @throws IOException
	 */
	public void toGetTitle(WebElement web, String windowTitle, String elementNameGettingTitle) {

		try {
			boolean elementEnability = toCheckElementEnability(web, elementNameGettingTitle);

			if (elementEnability) {
				String title = driver.getTitle();
				System.out.println(title);
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(elementNameGettingTitle);
		}
	}

	/**
	 * @param web
	 * @param elementName
	 * @param whichThingColour
	 * @throws IOException
	 */
	public void toGetCssValueOrElementColour(WebElement web, String elementName, String whichThingColour) {

		try {
			boolean elementEnability = toCheckElementEnability(web, elementName);

			if (elementEnability) {
				String colour = web.getCssValue(whichThingColour);
				String color = Color.fromString(colour).asHex();
				System.out.println(color);
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(elementName);
		}
	}

	/**
	 * @param web
	 * @param uploadingFileName
	 * @param fullXpathOfFile
	 */
	public void toUploadFile(WebElement web, String uploadingFileName, String fullXpathOfFile) {

		try {
			boolean elementEnability = toCheckElementEnability(web, uploadingFileName);

			if (elementEnability) {
				
				web.sendKeys(fullXpathOfFile);

			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(uploadingFileName);
		}
	}

	/**
	 * @param web
	 * @param checkBoxName
	 * @throws IOException
	 */
	public void toCheck_CheckBoxStatus(WebElement web, String checkBoxName) {

		try {
			boolean elementEnability = toCheckElementEnability(web, checkBoxName);

			if (elementEnability) {
				ToClickAnyButton(web, checkBoxName);
			}
		} catch (Exception e) {
			e.printStackTrace();
			toTakeScreenShot(checkBoxName);
		}

	}

	/**
	 * 
	 */
	public void toHandleCalanderDate() {

		try {

			Calendar calnderObj = Calendar.getInstance();
			calnderObj.add(Calendar.DATE, 80);
			Date date = calnderObj.getTime();
			DateFormat dateformetObj = new SimpleDateFormat("dd_MMM_yyyy");
			String dateformetInString = dateformetObj.format(date);
			System.out.println(dateformetInString);

			String[] split = dateformetInString.split("_");
			String dateArr = split[0];
			String monthArr = split[1];
			ToLaunchBrowserAndHitUrl("chrome", "https://erail.com/");

			Actions act = new Actions(driver);

			WebElement we = driver.findElement(By.xpath("//input[@title='Select Departure date for availability']"));
			act.click(we).build().perform();

			Thread.sleep(5000);

			WebElement we2 = driver.findElement(By.xpath("//td[contains(text(),'" + monthArr
					+ "')]//parent::tr/following-sibling::tr//td[text()='" + dateArr + "']"));

			act.click(we2).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param elementName
	 * @param timeDuration
	 * @param locaterOfCreatingWait
	 * @return
	 */
	public void toCreatExplicitlyWait_Presenty(WebElement web, String elementName, long timeDuration,
			String locaterOfCreatingWait) {
		try {
			boolean enability = toCheckElementEnability(web, elementName);
			if (web.isEnabled()) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
				WebElement isSelected = wait
						.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locaterOfCreatingWait)));
			}
		} catch (Exception e) {

		}

	}

	/**
	 * @param web
	 * @param elementName
	 * @param timeDuration
	 * @param locaterOfCreatingWait
	 */
	public void toCreatExplicitlyWait_Enability(WebElement web, String elementName, long timeDuration,
			String locaterOfCreatingWait) {

		try {

			boolean enability = toCheckElementEnability(web, elementName);
			if (web.isEnabled()) {
				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeDuration));
				WebElement isSelected = wait
						.until(ExpectedConditions.elementToBeClickable(By.xpath(locaterOfCreatingWait)));
			}
		} catch (Exception e) {

		}
	}
	/**
	 * @param time_InSeconds
	 */
	public void toImplicitlyWait(int time_InSeconds) {
		try {
			
			driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(time_InSeconds*1000));
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param time_InSeconds
	 */
	public void toUseThredSleep_Wait(int time_InSeconds) {
		try {
			
			Thread.sleep(time_InSeconds*=1000);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param elementName
	 */
	public void toGetSizeOfElement(WebElement web, String elementName) {

		try {

			Dimension size = web.getSize();
			int hight = size.height;
			int width = size.width;

			System.out.println("Hight of " + elementName + " = " + hight + "  / and width is = " + width);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param elementname
	 */
	public void toCheckLocationOfElement(WebElement web, String elementname) {

		try {
			Point location = web.getLocation();
			int x_Axis = location.getX();
			int y_Axis = location.getY();

			System.out.println("X_Axis of " + elementname + " is = " + x_Axis + " and y_Axis is = " + y_Axis);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param elementname
	 * @param xAxis
	 * @param yAxis
	 * @return
	 */
	public void toValidateLocationOfElement(WebElement web, String elementname, int expectedXAxis, int expectedYAxis) {

		int returnLocation = 0;

		try {
			Point location = web.getLocation();
			int x_Axis = location.getX();
			int y_Axis = location.getY();

			if (expectedXAxis == x_Axis && expectedYAxis == y_Axis) {
				extTest.log(Status.PASS, "The Location of " + elementname + " is pass with actual and expected value");
			} else {
				extTest.log(Status.FAIL, "The Location of " + elementname + " is Fail with actual and expected value");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param elementName
	 */
	public void toValidateSizeOfElement(WebElement web, String elementName, int expectedHight, int expectedWidth) {

		int returnSize = 0;

		try {

			Dimension size = web.getSize();
			int hight = size.height;
			int width = size.width;
			if (expectedHight == hight && expectedWidth == width) {
				extTest.log(Status.PASS,
						"The Hight and Width of " + elementName + "  is pass with actual and expected value");
			} else {
				extTest.log(Status.FAIL,
						"The Hight and Width of " + elementName + " is Failed with actual and expected value");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param locatername
	 * @param locaterValue
	 * @return
	 */
	public WebElement toSearchElementByFindElements(String locatername, String locaterValue) {

		WebElement web = null;

		try {
			if (locatername.equalsIgnoreCase("xpath")) {
				web.findElements(By.xpath(locaterValue));
			} else if (locatername.equalsIgnoreCase("name")) {
				web.findElements(By.name(locaterValue));
			} else if (locatername.equalsIgnoreCase("class")) {
				web.findElements(By.className(locaterValue));
			} else if (locatername.equalsIgnoreCase("id")) {
				web.findElements(By.id(locaterValue));
			} else if (locatername.equalsIgnoreCase("css Selector")) {
				web.findElements(By.cssSelector(locaterValue));
			} else if (locatername.equalsIgnoreCase("linkText")) {
				web.findElements(By.linkText(locaterValue));
			} else if (locatername.equalsIgnoreCase("partial Link Text")) {
				web.findElements(By.partialLinkText(locaterValue));
			} else if (locatername.equalsIgnoreCase("tagName")) {
				web.findElements(By.tagName(locaterValue));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return web;
	}

	/**
	 * @param web
	 * @param elementNmae
	 * @return
	 */
	public String toFindInnerText(WebElement web, String elementNmae) {
		String innerText = "";

		try {
			boolean enability = toCheckElementEnability(web, elementNmae);
			if (enability) {
				innerText = web.getText();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return innerText;
	}

	/**
	 * @param web
	 * @param elementName
	 */
	public void toClearThe_Box(WebElement web, String elementName) {

		try {
			boolean enability = toCheckElementEnability(web, elementName);
			if (enability) {
				web.clear();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param elementName
	 */
	public void toContextClick(WebElement web, String elementName) {

		try {
			boolean enability = toCheckElementEnability(web, elementName);
			if (enability) {
				Actions act = new Actions(driver);
				act.contextClick(web).build().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param draging_ValueAndType
	 * @param elementName
	 * @param droping_ValueAndType
	 */

	public void toDragAndDrop(WebElement draging_ValueAndType, String elementName, WebElement droping_ValueAndType) {

		try {
			boolean enability = toCheckElementEnability(draging_ValueAndType, elementName);
			if (enability) {
				Actions act = new Actions(driver);

				act.dragAndDrop(draging_ValueAndType, droping_ValueAndType).build().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param dragingWebElement
	 * @param dropingWebElement
	 */
	public void toClickAndHold(WebElement dragingWebElement, WebElement dropingWebElement) {
		try {
			Actions act = new Actions(driver);
			act.moveToElement(dragingWebElement).clickAndHold().moveToElement(dropingWebElement).release().build()
					.perform();
			;

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	/**
	 * @param web
	 * @param elementName
	 * @param sendingValue
	 */
	public void toSendValueByActionClass(WebElement web, String elementName, String sendingValue) {
		try {
			boolean enability = toCheckElementEnability(web, elementName);
			if (enability) {

				Actions act = new Actions(driver);
				act.sendKeys(web, sendingValue).build().perform();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param clickingElement
	 */
	public void toDoubleClickByActions(WebElement clickingElement) {
		try {

			Actions act = new Actions(driver);
			act.doubleClick(clickingElement).build().perform();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param expectedValue
	 * @param actualValue
	 * @param elementName
	 */
	public void toValidate_IsEnability(WebElement web, String expectedValue, String actualValue, String elementName) {
		try {

			if (expectedValue.equalsIgnoreCase(actualValue)) {
				extTest.log(Status.PASS, elementName + " -Enability is passed with actual and expected value");
			} else {
				extTest.log(Status.FAIL, elementName + " -Enability is Failed with actual and expected value");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * This Methi
	 * 
	 * @param web
	 * @param expectedValue
	 * @param actualValue
	 * @param elementName
	 */
	public void toValidate_IsDisplaibility(WebElement web, String expectedValue, String actualValue,
			String elementName) {
		try {

			if (expectedValue.equalsIgnoreCase(actualValue)) {
				extTest.log(Status.PASS, elementName + " -Displaybility is passed with actual and expected value");
			} else {
				extTest.log(Status.FAIL, elementName + " -Displaybility is Failed with actual and expected value");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param web
	 * @param expectedValue
	 * @param actualValue
	 * @param elementName
	 */
	public void toValidate_IsSelectibility(WebElement web, String expectedValue, String actualValue,
			String elementName) {
		try {

			if (expectedValue.equalsIgnoreCase(actualValue)) {
				extTest.log(Status.PASS, elementName + " -Displaybility is passed with actual and expected value");
			} else {
				extTest.log(Status.FAIL, elementName + " -Displaybility is Failed with actual and expected value");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * @param web
	 * @param incoginito
	 */
	public void toLaunchBrowserInIncoginitoMode(WebElement web ,String incoginito) {
		
		try {
			ChromeOptions chromeOpt = new ChromeOptions();
				
			chromeOpt.addArguments(incoginito);
			
		} catch (Exception e) {
			e.printStackTrace();		
			
		}
	}
	
	/**
	 * 
	 */
	public void toRemoveChromeHeadlines() {
		
		try {
			ChromeOptions obj = new ChromeOptions ();
			
			obj.setExperimentalOption("excludeSwitches",new String [] {"enable-automation"});
			
		} catch (Exception e) {
			e.printStackTrace();		
			}
	}
	
	/**
	 * @param web
	 * @param elementName
	 * @param sendingValue
	 */
	public void toSendValueByJavaScript(WebElement web ,String elementName,String sendingValue) {
		
		try {
			boolean enability =toCheckElementEnability(web, elementName);
			if(enability) {
				
				JavascriptExecutor javaSc =(JavascriptExecutor)driver;
				javaSc.executeScript("arguments[0].value='"+sendingValue+"'", web);
			}
		} catch (Exception e) {
			e.printStackTrace();
			}
	}
	
	/**
	 * @param web
	 * @param elementName
	 */
	public void toClickByJavaScript(WebElement web,String elementName) {
		try {
			boolean enability = toCheckElementEnability(web, elementName);
			if (enability) {
				JavascriptExecutor javaSc =(JavascriptExecutor)driver;
				javaSc.executeScript("arguments[0].click",web);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 
	 */
	public void toGoOnBackPage() {
		try {
			
			driver.navigate().back();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void toGoOnForwordPage() {
		try {
			
			driver.navigate().forward();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void toRefreshPage() {
		try {
			
			driver.navigate().refresh();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	public void toMaximizePage() {
		try {
			
			driver.manage().window().maximize();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * 
	 */
	public void toMinimizePage() {
		try {
			
			driver.manage().window().minimize();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param web
	 */
	public void toScrollWebElement(WebElement web) {
		try {
			
			JavascriptExecutor javaSc =(JavascriptExecutor)driver;
			javaSc.executeScript("arguments[0].scrollIntoView();", web);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * @param web
	 * @param startingDestinationNUM
	 * @param targetDestinationNUM
	 */
	public void toScrollDown(int startingDestinationNUM,int targetDestinationNUM ) {
		try {
			
			JavascriptExecutor javaSc =(JavascriptExecutor)driver;
			javaSc.executeScript("window.scrollBy("+startingDestinationNUM+","+targetDestinationNUM+");");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * @param startingDestinationNUM
	 * @param targetDestinationNUM
	 */
	public void toScrollUp(int startingDestinationNUM, int targetDestinationNUM) {
		try {
			
			JavascriptExecutor javaSc =(JavascriptExecutor)driver;
			javaSc.executeScript("window.scrollBy("+startingDestinationNUM+",-"+targetDestinationNUM+");");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}