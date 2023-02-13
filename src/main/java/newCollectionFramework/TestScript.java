package newCollectionFramework;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status; 
public class TestScript {

	public static void main(String[] args) {
		
		GenericMethod gobj = new GenericMethod();
		
		ExtentReports reports = new ExtentReports();
		
		gobj.toGenerateReport("testCase","TestScriptReport");
		
		gobj.toCreateTest(reports, "TC001");
		
		gobj.ToLaunchBrowserAndHitUrl("chrome", "http://localhost:8888/");
		
		WebElement web = gobj.toSearchTheElementByLocaters("name", "user_name");
		gobj.toSendValueInInputBox(web, "admin", "user name");
		
		WebElement password = gobj.toSearchTheElementByLocaters("name", "user_password");
//		gobj.toSendValueInInputBox(password, "admin", "user password");

		WebElement language = gobj.toSearchTheElementByLocaters("xpath", "//select[@name='login_theme']");
		gobj.ToCountOptionsOfDropdown(language, "Language");
		
//		WebElement LoginButton = gobj.toSearchTheElementByLocaters("name", "Login");
////		gobj.ToClickAnyButton(LoginButton,"Login button");
//		gobj.toClickByActions(LoginButton, "Login Button");
//
////		
//		WebElement homePage = gobj.toSearchTheElementByLocaters("xpath", "//td[@class='moduleName']");
//		gobj.toValidate_IsDisplaibility(homePage, "My Home Page is Visibal", "My Home Page > Home", "My Home Page Header");
//	
////		genericObj.toMaximizePage();
//		
//		gobj.toScrollDown( 0, 500);
////		genericObj.toUseThredSleep_Wait(5);
//		
//		gobj.toScrollUp( 0, 500);
////		genericObj.toGoOnBackPage();
//
//		WebElement marketing = gobj.toSearchTheElementByLocaters("linkText", "Marketing");
//		gobj.toClickByActions(marketing, "Markrting Link");
//		
//		WebElement Document = gobj.toSearchTheElementByLocaters("xpath", "//a[text()='Documents']//parent::td[@class='level2UnSelTab']");
//		gobj.toClickByActions(Document, "Document Link");
//		
//		WebElement creatDocument = gobj.toSearchTheElementByLocaters("xpath", "//img[@title='Create Document...']");
//		gobj.toClickByActions(creatDocument, "Creat Document");
//		
//		WebElement fileDrop = gobj.toSearchTheElementByLocaters("xpath", "//select[@name='filelocationtype']");
//		gobj.ToHandleDropDownByValue(fileDrop, "I", "File Uploading");
////		gobj.ToHandleDropDownByIndexOf(file, 0, "File Uploading");
//		
//		WebElement file = gobj.toSearchTheElementByLocaters("xpath", "//input[@type='file']");
//		gobj.toUploadFile(file, "Mahadev", "C:\\Users\\Admin\\Pictures");

//		genericObj.toFlushTheReport();
	}
}
