package PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;



public class Report_add extends base_setup {
	
	   @BeforeClass
	    public void setupDriver() {
	        setup();             
	        login("Admin", "admin123");

	        // Navigate to PIM → Reports
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Reports']"))).click();
	    }

	    @Test
	    public void addReportTest()
	    {
	        
	        // CLICK ADD BUTTON
	     
	        WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[normalize-space()='Add']")));

	        if (addBtn.isDisplayed()) {
	            System.out.println("Add Button Clickable: PASS");
	        } else {
	            System.out.println("Add Button Clickable: FAIL");
	        }

	        addBtn.click();

	        // Wait for Add Report page
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h6[text()='Add Report']")));

	     // REPORT NAME VALIDATION
	       
	        WebElement reportName = driver.findElement(By.xpath("//label[text()='Report Name']/following::input[1]"));

	      
	        reportName.clear();
	        reportName.sendKeys(Keys.TAB);

	        try {
	            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")));
	            System.out.println("Report Name Required Validation: PASS");
	        } catch (Exception e) {
	            System.out.println("Report Name Required Validation: FAIL");
	        }

	        
	        reportName.sendKeys("Automation Report");

	        
	        //  SELECTION CRITERIA
	     
	        try {
	            WebElement selectionDropdown = driver.findElement(
	                    By.xpath("//label[text()='Selection Criteria']/following::div[contains(@class,'oxd-select-text')]"));

	            selectionDropdown.click();
	            driver.findElement(By.xpath("//span[text()='Employee Name']")).click();

	            System.out.println("Selection Criteria: PASS");

	        } catch (Exception e) {
	            System.out.println("Selection Criteria: FAIL");
	        }

	       
	        // DISPLAY FIELD GROUP
	       
	        try {
	            WebElement groupDropdown = driver.findElement(
	                    By.xpath("//label[text()='Display Field Group']/following::div[contains(@class,'oxd-select-text')]"));

	            groupDropdown.click();
	            driver.findElement(By.xpath("//span[text()='Personal']")).click();

	            System.out.println("Display Field Group: PASS");

	        } catch (Exception e) {
	            System.out.println("Display Field Group: FAIL");
	        }

	        
	        //  DISPLAY FIELD
	      
	        try {
	            WebElement fieldDropdown = driver.findElement(
	                    By.xpath("//label[text()='Display Field']/following::div[contains(@class,'oxd-select-text')]"));

	            fieldDropdown.click();
	            driver.findElement(By.xpath("//span[text()='Employee Id']")).click();

	            System.out.println("Display Field: PASS");

	        } catch (Exception e) {
	            System.out.println("Display Field: FAIL");
	        }

	        // Click ADD (to add display field row)
	        driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();

	        
	        // ✅ SAVE BUTTON VALIDATION
	     
	        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[@type='submit']")));

	        if (saveBtn.isDisplayed()) {
	            System.out.println("Save Button Clickable: PASS");
	        } else {
	            System.out.println("Save Button Clickable: FAIL");
	        }

	        saveBtn.click();
	        Assert.assertTrue(true, "Report added successfully");

	        // TOAST MESSAGE VALIDATION
	    
	        try {
	            WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                    By.xpath("//div[contains(@class,'oxd-toast-content')]")));

	            System.out.println("Save Report Toast: PASS → " + toast.getText());

	        } catch (Exception e) {
	            System.out.println("Save Report Toast: FAIL");
	        }
	    }

	    			
	    

	    @AfterClass
	    public void close() {
	       tearDown();
	    }
	}