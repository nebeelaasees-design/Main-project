package Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Configure_Leave_Type   extends base_setup
{
	  @BeforeTest
	    public void setupFunction() {
	        setup();
	        login("Admin", "admin123");

	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

	        
	       
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Leave']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[text()='Assign Leave']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Configure ']"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Leave Types']"))).click();

	  }
	  @Test(priority = 1)
	    public void addLeaveType() {

	      

	      
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[contains(.,'Add')]"))).click();

	     
	        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//label[text()='Name']/following::input[1]")));
	        name.sendKeys("Special Leave");

	        // Save
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        System.out.println(" Leave Type Added");
	    }

	   
	    @Test(priority = 2)
	    public void searchLeaveType() {

	
	        boolean found = driver.getPageSource().contains("Special Leave");

	        System.out.println(" Leave Type Found: " + found);
	    }

	  
	    @Test(priority = 3)
	    public void editLeaveType() {

	      

	    
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("(//i[contains(@class,'bi-pencil-fill')])[1]"))).click();

	        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//label[text()='Name']/following::input[1]")));
	        name.clear();
	        name.sendKeys("Updated Leave Type");

	        
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        System.out.println("Leave Type Updated");
	    }

	 
	    @Test(priority = 4)
	    public void deleteLeaveType() {

	    
	       
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[contains(@class,'oxd-checkbox-input')]"))).click();

	       
	        driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();

	      
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[.='Yes, Delete']"))).click();

	        System.out.println(" Leave Type Deleted");
	    }

@AfterClass
public void close()
{
	tearDown();
}
}
