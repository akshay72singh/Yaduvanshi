package newCollectionFramework;

import org.openqa.selenium.WebElement;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

public class GenericMethodTask_By_Roshan  {
	
	static GenericMethod genericObj = new GenericMethod();

	public static void main(String[] args) {
	
	ExtentReports reports= genericObj.toGenerateReport("TestReports","Automation_Report");
	
	genericObj.toCreateTest(reports, "TC001");
	genericObj.ToLaunchBrowserAndHitUrl("Chrome","http://localhost:8888/");
	testCaseNo1();

	genericObj.toCreateTest(reports, "TC002");
	testCaseNo2();
	
	genericObj.toCreateTest(reports, "TC003");
	testCaseNo3();
	
//	genericObj.toFlushTheReport();
	}
	public static void testCaseNo1() {
		

		WebElement userName = genericObj.toSearchTheElementByLocaters("name", "user_name");
		genericObj.toGetSizeOfElement(userName,"user name box ");
		genericObj.toValidateSizeOfElement(userName, "user name box",22 , 140);	
		
		WebElement passwordBox = genericObj.toSearchTheElementByLocaters("name", "user_password");
		genericObj.toGetSizeOfElement(passwordBox,"password box ");
		genericObj.toValidateSizeOfElement(passwordBox, "user Password box", 23, 140);

		WebElement loginButton =genericObj. toSearchTheElementByLocaters("name", "Login");
		genericObj.toGetSizeOfElement(loginButton,"Login Button");
		genericObj.toValidateSizeOfElement(loginButton, "Login Button", 40, 138);
		
	}
	public static void testCaseNo2() {
		

		WebElement usernamebox = genericObj.toSearchTheElementByLocaters("name", "user_name");
		genericObj.toCheckLocationOfElement(usernamebox,"user Name");
		genericObj.toValidateLocationOfElement(usernamebox, "User Name box", 640, 230);
		
		WebElement userpasswordbox =genericObj. toSearchTheElementByLocaters("name", "user_password");
		genericObj.toCheckLocationOfElement(userpasswordbox,"user Password box");
		genericObj.toValidateLocationOfElement(userpasswordbox, "User Password", 640, 230);
		
		WebElement LoginButton = genericObj.toSearchTheElementByLocaters("name", "Login");
		genericObj.toCheckLocationOfElement(LoginButton,"Login Button");
		genericObj.toValidateLocationOfElement(LoginButton, "Login Button", 640, 350);

	}
	public static void testCaseNo3() {
		

		WebElement username = genericObj.toSearchTheElementByLocaters("name","user_name"); 
		genericObj.toSendValueInInputBox(username, "admin", "user name");
	
		WebElement userpassword = genericObj.toSearchTheElementByLocaters("name","user_password"); 
		genericObj.toSendValueInInputBox(userpassword, "admin", "user password");
	
		WebElement loginButton = genericObj.toSearchTheElementByLocaters("name","Login"); 
		genericObj.ToClickAnyButton(loginButton, "Login Button");
	
		WebElement marketing = genericObj.toSearchTheElementByLocaters("linkText","Marketing"); 
		genericObj.toMouseOver(marketing, "mouse Over Marketing");
		
		WebElement Leads = genericObj.toSearchTheElementByLocaters("linkText","Leads"); 
		genericObj.ToClickAnyButton(Leads, "Leads");
		
		
		WebElement creatLeads = genericObj.toSearchTheElementByLocaters("xpath","//img[@title='Create Lead...']"); 
		genericObj.ToClickAnyButton(creatLeads, "Creat Leads");
		
		WebElement firstName = genericObj.toSearchTheElementByLocaters("name","firstname"); 
		genericObj.toSendValueInInputBox(firstName, "Akshay kumar","Firsr Name box");
		
		WebElement lastName = genericObj.toSearchTheElementByLocaters("name","lastname"); 
		genericObj.toSendValueInInputBox(lastName, "Yadav","Last Name box");
		
		WebElement companyName = genericObj.toSearchTheElementByLocaters("name","company"); 
		genericObj.toSendValueInInputBox(companyName, "Google","Company Name Box");

		WebElement titleBox = genericObj.toSearchTheElementByLocaters("id","designation"); 
		genericObj.toSendValueInInputBox(titleBox, "I am QA Akshay kumar","title box");
		
		WebElement phoneNumber = genericObj.toSearchTheElementByLocaters("id","phone"); 
		genericObj.toSendValueInInputBox(phoneNumber, "7860xxxxx7","Phone Number box");
		
		WebElement email = genericObj.toSearchTheElementByLocaters("id","email"); 
		genericObj.toSendValueInInputBox(email, "akshay@gmail.com","Email id");
		
		WebElement streetBox = genericObj.toSearchTheElementByLocaters("name","lane"); 
		genericObj.toSendValueInInputBox(streetBox, "I am from Bhadohi","Street Box");
		
		WebElement countryName = genericObj.toSearchTheElementByLocaters("id","country"); 
		genericObj.toSendValueInInputBox(countryName, "INDIA","Country Name");
		
		WebElement saveButton = genericObj.toSearchTheElementByLocaters("xpath","//input[@value='  Save  ']"); 
		genericObj.toSendValueInInputBox(saveButton, "INDIA","Click on Save button");

	}
	
	
	
}
