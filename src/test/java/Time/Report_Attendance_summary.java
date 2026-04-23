package Time;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Report_Attendance_summary extends base_setup
{
	   @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin", "admin123");

	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

	        
	        Actions actions = new Actions(driver);

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

	    
	     wait.until(ExpectedConditions.elementToBeClickable(
	             By.xpath("//span[normalize-space()='Reports']"))).click();
	     
	     List<WebElement> elements = driver.findElements(By.xpath("//*"));

	     boolean found = false;

	     for (WebElement el : elements) {
	         try {
	             String text = el.getText().trim();

	             if (text.equalsIgnoreCase("Attendence Summary")) {
	                
	                 ((JavascriptExecutor) driver)
	                         .executeScript("arguments[0].click();", el);

	                 found = true;
	                 break;
	             }

	         } catch (Exception e) {
	            
	         }
	     }
	     }
	     @Test(priority = 1)
	     public void fillForm() {

	         // Employee Name
	         WebElement emp = wait.until(ExpectedConditions.elementToBeClickable(
	                 By.xpath("//input[@placeholder='Type for hints...']")));
	         emp.sendKeys("John Doe");

	         Assert.assertEquals(emp.getAttribute("value"), "John Doe");

	         // Job Title dropdown
	         driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text')])[1]")).click();
	         wait.until(ExpectedConditions.elementToBeClickable(
	                 By.xpath("//span[text()='QA Engineer']"))).click();

	         // Sub Unit dropdown
	         driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text')])[2]")).click();
	         wait.until(ExpectedConditions.elementToBeClickable(
	                 By.xpath("//span[text()='Engineering']"))).click();

	         // Employment Status dropdown
	         driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text')])[3]")).click();
	         wait.until(ExpectedConditions.elementToBeClickable(
	                 By.xpath("//span[text()='Full-Time Permanent']"))).click();

	         // Date From
	         WebElement from = driver.findElement(By.xpath("//input[@placeholder='From']"));
	         from.sendKeys("2024-01-01");

	         // Date To
	         WebElement to = driver.findElement(By.xpath("//input[@placeholder='To']"));
	         to.sendKeys("2024-01-31");

	         Assert.assertEquals(from.getAttribute("value"), "2024-01-01");
	         Assert.assertEquals(to.getAttribute("value"), "2024-01-31");

	         System.out.println("Form filled successfully");
	     }

	     @Test(priority = 2)
	     public void clickViewButton() {

	         WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                 By.xpath("//button[.//span[text()='View']]")));

	         viewBtn.click();

	         // Validate result
	         WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                 By.cssSelector(".oxd-table")));

	         Assert.assertTrue(result.isDisplayed());

	         System.out.println("Report generated successfully");
	     }

	     @Test(priority = 3)
	     public void negativeTestEmptyEmployee() {

	         driver.navigate().refresh();

	         // Only dates
	         wait.until(ExpectedConditions.visibilityOfElementLocated(
	                 By.xpath("//input[@placeholder='From']"))).sendKeys("2024-01-01");

	         driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("2024-01-31");

	         driver.findElement(By.xpath("//button[.//span[text()='View']]")).click();

	         // Validate message OR safe response
	         WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                 By.xpath("//*[contains(text(),'Required') or contains(text(),'No Records') or contains(text(),'Invalid')]")));

	         Assert.assertTrue(msg.isDisplayed());

	         System.out.println("Negative test passed");
	     }

	     @AfterClass
	     public void tearDown() {
	         driver.quit();
	     }
	 }

	 
