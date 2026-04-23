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

public class Report_employee extends base_setup
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

	             if (text.equalsIgnoreCase("Project Reports")) {
	                
	                 ((JavascriptExecutor) driver)
	                         .executeScript("arguments[0].click();", el);

	                 found = true;
	                 break;
	             }

	         } catch (Exception e) {
	            
	         }
	     }

	    
	     Assert.assertTrue(found, "Configuration menu NOT found!");
	 


	    }
	   @Test(priority = 1)
	    public void fillEmployeeReportForm() {

	        // Employee Name
	        WebElement empName = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//input[@placeholder='Type for hints...']")));
	        empName.sendKeys("John Doe");

	        Assert.assertEquals(empName.getAttribute("value"), "John Doe");

	        // Project Name
	        WebElement projectName = driver.findElement(
	                By.xpath("(//input[@placeholder='Type for hints...'])[2]"));
	        projectName.sendKeys("Project Alpha");

	        Assert.assertEquals(projectName.getAttribute("value"), "Project Alpha");

	           driver.findElement(By.cssSelector(".oxd-select-text")).click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Development']"))).click();

	      
	        WebElement from = driver.findElement(
	                By.xpath("//input[@placeholder='From']"));
	        from.sendKeys("2024-01-01");

	      
	        WebElement to = driver.findElement(
	                By.xpath("//input[@placeholder='To']"));
	        to.sendKeys("2024-01-31");

	        Assert.assertEquals(from.getAttribute("value"), "2024-01-01");
	        Assert.assertEquals(to.getAttribute("value"), "2024-01-31");

	        
	        WebElement checkbox = driver.findElement(
	                By.xpath("//label[contains(.,'Approved')]//input"));

	        if (!checkbox.isSelected()) {
	            checkbox.click();
	        }

	        Assert.assertTrue(checkbox.isSelected());

	        System.out.println("Form filled successfully");
	    }

	    @Test(priority = 2)
	    public void clickViewAndValidate() {

	        WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[.//span[text()='View']]")));

	        viewBtn.click();

	      
	        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector(".oxd-table")));

	        Assert.assertTrue(result.isDisplayed());

	        System.out.println("Employee report generated successfully");
	    }

	    @Test(priority = 3)
	    public void negativeTestEmptyEmployeeName() {

	        driver.navigate().refresh();

	        // Fill only project + dates
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("(//input[@placeholder='Type for hints...'])[2]")))
	                .sendKeys("Project Alpha");

	        driver.findElement(By.xpath("//input[@placeholder='From']")).sendKeys("2024-01-01");
	        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("2024-01-31");

	        driver.findElement(By.xpath("//button[.//span[text()='View']]")).click();

	        // Validate system response (no crash / validation)
	        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//*[contains(text(),'Required') or contains(text(),'Invalid') or contains(text(),'No Records')]")));

	        Assert.assertTrue(msg.isDisplayed());

	        System.out.println("Negative test passed");
	    }

	    @AfterClass
	    public void tearDown() {
	        tearDown();
	    }
	}


