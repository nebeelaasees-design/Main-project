package PIM;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class addemployee extends base_setup {
	
	   private By fileInput = By.cssSelector("input.oxd-file-input");
	  
	        @BeforeClass
	        public void setupFunction() {
	        	setup(); 
	        	   login("Admin", "admin123");
	        }

	        @Test(priority = 1)
	        public void addEmployee() throws Exception {

	            
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Add Employee']"))).click();

	            String firstName = "John";
	            String MiddleName = "Honai";
	            String lastName = "Doe";

	            WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.name("firstName")));
	            firstNameInput.clear();
	            firstNameInput.sendKeys(firstName);
	            
	            
	            WebElement middleNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.name("middleName")));
	            middleNameInput .clear();
	            middleNameInput.sendKeys(MiddleName);

	            WebElement lastNameInput = driver.findElement(By.name("lastName"));
	            lastNameInput.clear();
	            lastNameInput.sendKeys(lastName);
	            
	            WebElement employeeIdInput = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.cssSelector(".oxd-input.oxd-input--active")));

	            employeeIdInput.clear();
	            employeeIdInput.sendKeys("5432");
	 
	//Image upload
	         // File path 
	         
	            String filePath = "D:\\Photos\\My Phone Gallary\\20241116_122219.jpg";

	          

	            WebElement fileUpload = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
	            fileUpload.sendKeys(filePath);


	            Actions actions = new Actions(driver);

	            WebElement loginSwitch = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.cssSelector(".oxd-switch-wrapper")));

	         
	            actions.moveToElement(loginSwitch).perform();

	          
	            wait.until(ExpectedConditions.elementToBeClickable(loginSwitch)).click();

	           
	            WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//label[text()='Username']/following::input[1]")));
	            usernameInput.clear();
	            usernameInput.sendKeys("Rizaayin");

	            WebElement statusEnabled = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//label[normalize-space()='Enabled']")));

	            statusEnabled.click();

	          
	            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.cssSelector("input[type='password']")));
	            passwordField.sendKeys("Admin@123");

	           
	            WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//label[text()='Confirm Password']/following::input[1]")));
	            confirmPasswordField.sendKeys("Admin@123");

	          
	            WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[normalize-space()='Save']")));
	            saveBtn.click();
	            Thread.sleep(3000);
	        }

	        @Test(priority = 2)
	        public void addEmployeecncelscenario() throws Exception {

	            
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
	            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Add Employee']"))).click();

	            String firstName = "John";
	            String MiddleName = "Honai";
	            String lastName = "Doe";

	            WebElement firstNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.name("firstName")));
	            firstNameInput.clear();
	            firstNameInput.sendKeys(firstName);
	            
	            
	            WebElement middleNameInput = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.name("middleName")));
	            middleNameInput .clear();
	            middleNameInput.sendKeys(MiddleName);

	            WebElement lastNameInput = driver.findElement(By.name("lastName"));
	            lastNameInput.clear();
	            lastNameInput.sendKeys(lastName);
	            
	            WebElement employeeIdInput = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.cssSelector(".oxd-input.oxd-input--active")));

	            employeeIdInput.clear();
	            employeeIdInput.sendKeys("5432");
	 
	//Image upload
	         // File path 
	         
	            String filePath = "D:\\Photos\\My Phone Gallary\\20241116_122219.jpg";

	          

	            WebElement fileUpload = wait.until(ExpectedConditions.presenceOfElementLocated(fileInput));
	            fileUpload.sendKeys(filePath);


	            Actions actions = new Actions(driver);

	            WebElement loginSwitch = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.cssSelector(".oxd-switch-wrapper")));

	         
	            actions.moveToElement(loginSwitch).perform();

	          
	            wait.until(ExpectedConditions.elementToBeClickable(loginSwitch)).click();

	           
	            WebElement usernameInput = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//label[text()='Username']/following::input[1]")));
	            usernameInput.clear();
	            usernameInput.sendKeys("Rizaayin");

	            WebElement statusEnabled = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//label[normalize-space()='Enabled']")));

	            statusEnabled.click();

	          
	            WebElement passwordField = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.cssSelector("input[type='password']")));
	            passwordField.sendKeys("Admin@123");

	           
	            WebElement confirmPasswordField = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//label[text()='Confirm Password']/following::input[1]")));
	            confirmPasswordField.sendKeys("Admin@123");

	          
	            WebElement cancelbutton = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[normalize-space()='Cancel']")));
	            cancelbutton.click();
	            Thread.sleep(3000);
	        }
@Test(priority=3)
public void validation()
{
	 
    //  NAVIGATION VALIDATION
   
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Add Employee']"))).click();

    Assert.assertTrue(wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h6[text()='Add Employee']"))).isDisplayed(), "Add Employee page not loaded");

   
    // UI ELEMENT VALIDATION
    // =============================
    Assert.assertTrue(driver.findElement(By.name("firstName")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.name("middleName")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.name("lastName")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.cssSelector("input[type='file']")).isDisplayed());
    Assert.assertTrue(driver.findElement(By.cssSelector(".oxd-switch-wrapper")).isDisplayed());

 
    // NEGATIVE VALIDATION - EMPTY SAVE
  
    driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

    List<WebElement> errors = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//span[text()='Required']")));

    Assert.assertTrue(errors.size() > 0, "Required field validation failed");

    //  ENTER VALID DATA
  
    driver.findElement(By.name("firstName")).sendKeys("John");
    driver.findElement(By.name("middleName")).sendKeys("A");
    driver.findElement(By.name("lastName")).sendKeys("Doe");

    // Employee ID
    WebElement empId = driver.findElement(By.xpath("(//input[contains(@class,'oxd-input')])[2]"));
    empId.clear();
    empId.sendKeys("12345");

 
    // IMAGE UPLOAD VALIDATION
   
    String filePath = "D:\\Photos\\My Phone Gallary\\20241116_122219.jpg";
    driver.findElement(By.cssSelector("input[type='file']")).sendKeys(filePath);

   
    // NEGATIVE VALIDATION - PASSWORD MISMATCH
   
    driver.findElement(By.cssSelector(".oxd-switch-wrapper")).click();

    WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[text()='Username']/following::input[1]")));
    username.sendKeys("TestUser123");

    driver.findElement(By.cssSelector("input[type='password']")).sendKeys("Admin123");

    driver.findElement(By.xpath("//label[text()='Confirm Password']/following::input[1]"))
            .sendKeys("Wrong123");

    driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

    WebElement pwdError = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//span[contains(text(),'Passwords do not match')]")));

    Assert.assertTrue(pwdError.isDisplayed(), "Password mismatch error not shown");

 
    //  CORRECT PASSWORD
  
    WebElement confirmPwd = driver.findElement(
            By.xpath("//label[text()='Confirm Password']/following::input[1]"));
    confirmPwd.clear();
    confirmPwd.sendKeys("Admin123");

   
    //  SAVE VALIDATION
   
    WebElement saveBtn = driver.findElement(By.xpath("//button[normalize-space()='Save']"));
    Assert.assertTrue(saveBtn.isEnabled(), "Save button disabled");

    saveBtn.click();

  
    //  FINAL SUCCESS VALIDATION
   
    WebElement personalDetails = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h6[text()='Personal Details']")));

    Assert.assertTrue(personalDetails.isDisplayed(), "Employee creation failed");

    System.out.println(" FULL ADD EMPLOYEE VALIDATION PASSED");
}
	        @AfterClass
	        public void teardown() {
	            driver.quit();
	        }
	    }
	    
	




