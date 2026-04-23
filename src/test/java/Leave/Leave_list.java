package Leave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Leave_list  extends base_setup{
	  @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin", "admin123");

	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

	        
	        Actions actions = new Actions(driver);
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Leave']"))).click();

	     
	    
}
	 
	    @Test
	    public void searchLeave() throws InterruptedException {

	    

	        // Click Leave List
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[text()='Leave List']"))).click();

	        // Employee Name
	        WebElement emp = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[@placeholder='Type for hints...']")));
	        emp.sendKeys("Linda Anderson");

	        // Select from dropdown
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[@role='listbox']//span"))).click();

	        // Leave Status dropdown
	        WebElement status = driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text')])[1]"));
	        status.click();
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Approved']"))).click();

	        // From Date
	        WebElement fromDate = driver.findElement(By.xpath("(//input[@placeholder='yyyy-mm-dd'])[1]"));
	        fromDate.clear();
	        fromDate.sendKeys("2024-01-01");

	        // To Date
	        WebElement toDate = driver.findElement(By.xpath("(//input[@placeholder='yyyy-mm-dd'])[2]"));
	        toDate.clear();
	        toDate.sendKeys("2024-12-31");

	        // Click Search
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        //  Validation: Check table has records
	        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                By.xpath("//div[@role='row']")));

	        if (rows.size() > 1) {
	            System.out.println(" Search Results Found");
	        } else {
	            System.out.println(" No Records Found");
	        }

	        //  Validate employee name in results
	        boolean found = driver.findElements(By.xpath("//*[contains(text(),'Linda Anderson')]")).size() > 0;
	        Assert.assertTrue(found, "Employee not found in search results");

	        System.out.println(" Search Validation Passed");
	    }
	    

	   
	    @Test
	    public void approveLeave() {


	        // Select checkbox
	        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[contains(@class,'oxd-checkbox-input')]")));
	        checkbox.click();

	        // Click Approve
	        driver.findElement(By.xpath("//button[contains(.,'Approve')]")).click();

	        //  Validate success message
	        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(@class,'oxd-toast-content')]")));

	        String message = toast.getText();
	        System.out.println("Toast Message: " + message);

	        Assert.assertTrue(message.contains("Success"), "Approve failed");

	        System.out.println(" Leave Approved Successfully");
	    }

	  
	    @Test
	    public void rejectLeave() {

	    	   // Select checkbox
	        WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[contains(@class,'oxd-checkbox-input')]")));
	        checkbox.click();

	        // Click Reject
	        driver.findElement(By.xpath("//button[contains(.,'Reject')]")).click();

	        //  Validate toast message
	        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//div[contains(@class,'oxd-toast-content')]")));

	        String message = toast.getText();
	        System.out.println("Toast Message: " + message);

	        Assert.assertTrue(message.contains("Success"), "Reject failed");

	        System.out.println(" Leave Rejected Successfully");
	    }
	     
	    

	    @AfterClass
	    public void close() {
	        tearDown();
	    }
	}


