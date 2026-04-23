package PIM;



import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Employee_list extends base_setup {
	 
  

    // Locators
    By employeeNameInput = By.xpath("(//input[@placeholder='Type for hints...'])[1]");
    By employeeIdInput = By.xpath("(//input[@class='oxd-input oxd-input--active'])[2]");
    By employmentStatusSelect = By.xpath("(//div[@class='oxd-select-text--active'])[1]");
    By includeSelect = By.xpath("(//div[@class='oxd-select-text--active'])[2]");
    By supervisorNameInput = By.xpath("(//input[@placeholder='Type for hints...'])[2]");
    By jobTitleSelect = By.xpath("(//div[@class='oxd-select-text--active'])[3]");
    By subUnitSelect = By.xpath("(//div[@class='oxd-select-text--active'])[4]");
    By searchButton = By.xpath("//button[contains(@class,'oxd-button--secondary')]");
    By resetButton = By.xpath("//button[contains(@class,'oxd-button--ghost')]");

    @BeforeClass
    public void setUpclass()
    {
      
    	
    	setup();
    	login("Admin","admin123");
    	
         wait.until(ExpectedConditions.elementToBeClickable(
                 By.xpath("//span[text()='PIM']")
         )).click();

        
         wait.until(ExpectedConditions.visibilityOfElementLocated(
                 By.xpath("//div[contains(@class,'orangehrm-paper-container')]")
         ));

          
    }

    @Test
    public void testEmployeeSearch() throws InterruptedException {

      
        driver.findElement(employeeNameInput).sendKeys("Thomas Kutty Benny");
        System.out.println("Entered employee name");

        
        driver.findElement(employeeIdInput).sendKeys("03694567");
        System.out.println("Entered employee ID");

   
      

    
      
        driver.findElement(searchButton).click();
        System.out.println("Clicked Search");

        Thread.sleep(3000);

     
        driver.findElement(resetButton).click();
        System.out.println("Clicked Reset");
        
    }
    @Test
    public void view_record()
    {
    	   wait.until(ExpectedConditions.visibilityOfElementLocated(
                   By.xpath("//div[@class='oxd-table-body']")));

           
           // STORE DATA IN LIST
           
           List<List<String>> employeeData = new ArrayList<>();

           List<WebElement> rows = driver.findElements(
                   By.xpath("//div[@class='oxd-table-body']//div[@role='row']"));

           System.out.println("Total Employees: " + rows.size());

           for (WebElement row : rows) {

               List<WebElement> cols = row.findElements(By.xpath(".//div[@role='cell']"));
               List<String> rowData = new ArrayList<>();

               for (WebElement col : cols) {
                   rowData.add(col.getText());
               }

               employeeData.add(rowData);
           }

        
           //  PRINT DATA
       
           System.out.println("===== EMPLOYEE LIST =====");

           for (List<String> emp : employeeData) {
               System.out.println(emp);
           }

       
     
           System.out.println("FORMATTED OUTPUT");

           for (List<String> emp : employeeData) {
               for (String value : emp) {
                   System.out.print(value + " | ");
               }
               System.out.println();
           }
    	
    }
    @Test
    public void editemployee()
    {
    	String employeeName = "manda";

 	   
	    WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//input[@placeholder='Type for hints...']")));
	    searchInput.clear();
	    searchInput.sendKeys(employeeName);

	  
	    driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

	   
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.cssSelector(".oxd-table-body")));

	    
	    WebElement employeeLink = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@class='oxd-table-card']//div[contains(text(),'" + employeeName + "')]")
	    ));
	    employeeLink.click();

	
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h6[text()='Personal Details']")));

	   
	    WebElement firstName = wait.until(ExpectedConditions.elementToBeClickable(
	            By.name("firstName")));
	    firstName.clear();
	    firstName.sendKeys("hari");
	    WebElement middleName = driver.findElement(By.name("middleName"));
        middleName.clear();
        middleName.sendKeys("varma");

        WebElement lastName = driver.findElement(By.name("lastName"));
        lastName.clear();
        lastName.sendKeys("shankar");

        // Employee ID 
        WebElement empId = driver.findElement(By.xpath("(//input[contains(@class,'oxd-input')])[2]"));
        empId.clear();
        empId.sendKeys("8888");

        // Nationality
        driver.findElement(By.xpath("//label[text()='Nationality']/following::div[1]")).click();
        driver.findElement(By.xpath("//span[text()='Indian']")).click();

        // Marital Status
        driver.findElement(By.xpath("//label[text()='Marital Status']/following::div[1]")).click();
        driver.findElement(By.xpath("//span[text()='Single']")).click();

        // DOB
        WebElement dob = driver.findElement(By.xpath("//input[@placeholder='yyyy-mm-dd'][1]"));
        dob.clear();
        dob.sendKeys("1995-05-10");

        // Gender
        driver.findElement(By.xpath("//label[text()='Male']")).click();

         // LICENSE DETAILS
        
        driver.findElement(By.xpath("//label[text()=\"License Number\"]/following::input[1]"))
                .sendKeys("DL-12345");

        driver.findElement(By.xpath("//input[@placeholder='yyyy-mm-dd'][2]")).sendKeys("2030-12-31");
     //  SAVE BUTTON 1
        WebElement saveBtn1 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@type='submit'])[1]")));
                
        System.out.println("Save Button 1 is clickable: " + saveBtn1.isDisplayed());

        saveBtn1.click();

        // Validate popup message
        WebElement toast1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast-content')]")));

        System.out.println("Popup after Save 1: " + toast1.getText());
          
    
       
        // CUSTOM FIELDS
      
        driver.findElement(By.xpath("//label[text()='-- Select --']")).click();
        driver.findElement(By.xpath("//span[text()='A+']")).click();
        WebElement testField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//label[text()='Test Field']/following::input[1]")));

        testField.clear();
        testField.sendKeys("QA_Test_123");

        System.out.println("Test Field value entered");
     //  SAVE BUTTON 2
        WebElement saveBtn2 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@type='submit'])[2]")));
                
        System.out.println("Save Button 2 is clickable: " + saveBtn2.isDisplayed());

        saveBtn2.click();

        // Validate popup message
        WebElement toast2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast-content')]")));

        System.out.println("Popup after Save 2: " + toast2.getText());

       
              

      
      
       
        // ATTACHMENTS
      
        driver.findElement(By.xpath("//a[text()='Attachments']")).click();
        driver.findElement(By.xpath("//button[text()='Add']")).click();

        driver.findElement(By.cssSelector("input[type='file']"))
                .sendKeys("D:\\files\\doc.pdf");

        driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

        // COMMENTS
     
        driver.findElement(By.xpath("//textarea")).sendKeys("Employee updated successfully");

       
        WebElement saveBtn3 = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//button[@type='submit'])[3]")));
                
        System.out.println("Save Button 3 is clickable: " + saveBtn3.isDisplayed());

        saveBtn3.click();

        // Validate popup message
        WebElement toast3 = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast-content')]")));

        System.out.println("Popup after Save 3: " + toast3.getText());

        System.out.println(" FULL EMPLOYEE EDIT FLOW COMPLETED");
    
	    
    	}
   @ Test
   public void validation_with_negative_data() {
	 // FIRST NAME VALIDATION
		   WebElement firstNameField = wait.until(ExpectedConditions.elementToBeClickable(By.name("firstName")));

		 
		   firstNameField.clear();
		   firstNameField.sendKeys("1234");

		 
		   firstNameField.sendKeys(Keys.TAB);

		 
		   WebElement nameError = wait.until(ExpectedConditions.visibilityOfElementLocated(
		           By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")));

		   System.out.println("First Name Validation Message: " + nameError.getText());

		   // Now enter valid data
		   firstNameField.clear();
		   firstNameField.sendKeys("Hari");
		  // DOB VALIDATION
		   WebElement dobField = driver.findElement(By.xpath("(//input[@placeholder='yyyy-mm-dd'])[1]"));

		   dobField.clear();
		   dobField.sendKeys("2035-01-01"); // Future date

		   dobField.sendKeys(Keys.TAB);


		   WebElement dobError = wait.until(ExpectedConditions.visibilityOfElementLocated(
		           By.xpath("//span[contains(text(),'Should be a past date')]")));

		   System.out.println("DOB Validation Message: " + dobError.getText());

		 
		   dobField.clear();
		   dobField.sendKeys("1995-05-10");
		 // LICENSE EXPIRY VALIDATION
		   WebElement licenseExpiry = driver.findElement(By.xpath("(//input[@placeholder='yyyy-mm-dd'])[2]"));

		   licenseExpiry.clear();
		   licenseExpiry.sendKeys("2020-01-01"); 

		   licenseExpiry.sendKeys(Keys.TAB);

		  
		   WebElement licenseError = wait.until(ExpectedConditions.visibilityOfElementLocated(
		           By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")));

		   System.out.println("License Expiry Validation Message: " + licenseError.getText());

		  
		   licenseExpiry.clear();
		   licenseExpiry.sendKeys("2030-12-31");
   }
    
    @Test
    public void deletfunction()
	   {
		   WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//input[@placeholder='Type for hints...']")));
		    searchInput.clear();
		   String employeeName="Amelia  Brown";
			searchInput.sendKeys(employeeName);

		 
		    WebElement searchBtn = driver.findElement(By.xpath("//button[normalize-space()='Search']"));
		    searchBtn.click();

		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.cssSelector(".oxd-table-body")));

		   
		    WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//i[contains(@class,'bi-trash')]")));
		    deleteBtn.click();

		 
		    WebElement confirmDelete = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//button[normalize-space()='Yes, Delete']")));
		    confirmDelete.click();

		  
		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//p[contains(@class,'oxd-text--toast-message')]")));
		}
	   
    @AfterClass
    public void close() {
        tearDown();
        }
}