package Time;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class MY_Timesheet extends base_setup {
	 @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin","admin123");
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");
	        
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[normalize-space()='Timesheets']"))).click();

	        System.out.println("Opened Timesheets menu");
	    }

	    @Test(priority = 1)
	    public void openMyTimesheet() {

	        
	        WebElement myTimesheet = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[.//span[normalize-space()='My Timesheets']]")));

	        myTimesheet.click();

	      
	        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector("h6")));

	        Assert.assertTrue(title.getText().toLowerCase().contains("timesheet"));

	        System.out.println("My Timesheet page opened");
	    }

	    @Test(priority = 2)
	    public void viewTimesheet() {

	      
	        WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[.//span[text()='View']]")));

	        viewBtn.click();

	        System.out.println("Timesheet viewed");
	    }

	    @Test(priority = 3)
	    public void validateTimesheetTable() {

	      
	        WebElement table = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector(".oxd-table")));

	        Assert.assertTrue(table.isDisplayed());

	        System.out.println("Timesheet data displayed successfully");
	    }

	    @AfterClass
	    public void tearDown() {
	        driver.quit();
	    }
	}



