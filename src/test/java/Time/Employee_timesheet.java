package Time;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Employee_timesheet extends base_setup {
	 @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin","admin123");
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");
	    }

	   @Test
	    public void testfunction() {

	     
	        By empInput = By.xpath("//input[@placeholder='Type for hints...']");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(empInput))
	                .sendKeys("Peter Anderson");

	 
	        By searchBtn = By.xpath("//button[@type='submit']");
	        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

	       
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h6[contains(text(),'Timesheet')]")));

	        
	        By createBtn = By.xpath("//button[.//span[text()='Create Timesheet']]");
	        wait.until(ExpectedConditions.elementToBeClickable(createBtn)).click();

	    
	        By calendarInput = By.xpath("//input[@placeholder='Select Date']");
	        wait.until(ExpectedConditions.elementToBeClickable(calendarInput)).click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[@role='gridcell' and text()='10']"))).click();

	     
	        By addRow = By.xpath("//button[.//i[contains(@class,'plus')]]");

	        WebElement addBtn = wait.until(ExpectedConditions.presenceOfElementLocated(addRow));

	        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", addBtn);
	        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", addBtn);

	    
	        By projectInput = By.xpath("//input[@placeholder='Type for hints...']");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(projectInput))
	                .sendKeys("Test Project");

	      
	        By duration = By.xpath("//input[@placeholder='0.00']");
	        wait.until(ExpectedConditions.visibilityOfElementLocated(duration))
	                .sendKeys("8");

	      
	        By saveBtn = By.xpath("//button[.//span[text()='Save']]");
	        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

	     
	        By approveBtn = By.xpath("//button[.//span[text()='Approve']]");
	        wait.until(ExpectedConditions.elementToBeClickable(approveBtn)).click();

	       
	        By successMsg = By.cssSelector(".oxd-toast-content-text");
	        String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();

	        Assert.assertTrue(msg.contains("Success"), "Timesheet action failed");
	    }

	    @AfterClass
	    public void close() {
	        tearDown();
	    }
	}