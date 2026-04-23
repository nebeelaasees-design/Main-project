package Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Configure_Holiday 
	 extends base_setup
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

	 	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Holidays']"))).click();

	 	  }
	 	  @Test(priority = 1)
	 	    public void addHoliday() {

	 	      

	 	   
	 	        wait.until(ExpectedConditions.elementToBeClickable(
	 	                By.xpath("//button[contains(.,'Add')]"))).click();

	 	       WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(
	 	                By.xpath("//label[text()='Name']/following::input[1]")));
	 	        name.sendKeys("New Year Holiday");

	 	      
	 	        WebElement date = driver.findElement(
	 	                By.xpath("//label[text()='Date']/following::input[1]"));
	 	        date.clear();
	 	        date.sendKeys("2026-01-01");

	 	   
	 	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	 	        System.out.println("Holiday Added Successfully");
	 	    }

	 	 
	 	    @Test(priority = 2)
	 	    public void searchHoliday() {

	 	      
	 	        boolean found = driver.getPageSource().contains("New Year Holiday");

	 	        System.out.println("Holiday Found: " + found);
	 	    }

	 	   
	 	    @Test(priority = 3)
	 	    public void editHoliday() {

	 	     

	 	    
	 	        wait.until(ExpectedConditions.elementToBeClickable(
	 	                By.xpath("(//i[contains(@class,'bi-pencil-fill')])[1]"))).click();

	 	      
	 	        WebElement name = wait.until(ExpectedConditions.visibilityOfElementLocated(
	 	                By.xpath("//label[text()='Name']/following::input[1]")));
	 	        name.clear();
	 	        name.sendKeys("Updated New Year Holiday");

	 	        
	 	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	 	        System.out.println(" Holiday Updated");
	 	    }

	 	   
	 	    @Test(priority = 4)
	 	    public void deleteHoliday() {

	 	      
	 	      
	 	        wait.until(ExpectedConditions.elementToBeClickable(
	 	                By.xpath("//span[contains(@class,'oxd-checkbox-input')]"))).click();

	 	    
	 	        driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();

	 	      
	 	        wait.until(ExpectedConditions.elementToBeClickable(
	 	                By.xpath("//button[.='Yes, Delete']"))).click();

	 	        System.out.println(" Holiday Deleted");
	 	    }


@AfterClass
public void close()
{
	tearDown();
}
}

