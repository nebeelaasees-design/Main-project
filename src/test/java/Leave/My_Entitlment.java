package Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class My_Entitlment  extends base_setup{
	  @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin", "admin123");

	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

	        
	        Actions actions = new Actions(driver);
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Leave']"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Entitlements ']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Add Entitlements']"))).click();
	    
}
	  
	    @Test(priority = 1)
	    public void addEntitlement() throws InterruptedException {

	      

	        // Employee Name
	        WebElement emp = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[@placeholder='Type for hints...']")));
	        emp.sendKeys("Linda Anderson");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//div[@role='listbox']//span")).click();

	        // Leave Type
	        driver.findElement(By.xpath("(//div[contains(@class,'oxd-select-text')])[1]")).click();
	        driver.findElement(By.xpath("//span[text()='CAN - Vacation']")).click();

	        // Days
	        WebElement days = driver.findElement(By.xpath("//input[contains(@class,'oxd-input')]"));
	        days.sendKeys("5");

	        // Save
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        // Confirm popup
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.='Confirm']"))).click();

	        System.out.println("✅ Entitlement Added");
	    }

	    //  2. SEARCH ENTITLEMENT
	    @Test(priority = 2)
	    public void searchEntitlement() throws InterruptedException {

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Employee Entitlements']"))).click();

	        WebElement emp = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[@placeholder='Type for hints...']")));
	        emp.sendKeys("Linda Anderson");
	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//div[@role='listbox']//span")).click();

	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        boolean found = driver.getPageSource().contains("Linda Anderson");
	        System.out.println(" Search Result: " + found);
	    }

	    // EDIT ENTITLEMENT
	    @Test(priority = 3)
	    public void editEntitlement() {

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//i[contains(@class,'bi-pencil-fill')]"))).click();

	        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[contains(@class,'oxd-input')]")));

	        input.clear();
	        input.sendKeys("10");

	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        System.out.println(" Entitlement Updated");
	    }

	  
	    @Test(priority = 4)
	    public void deleteEntitlement() {

	      
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[contains(@class,'oxd-checkbox-input')]"))).click();

	       
	        driver.findElement(By.xpath("//button[contains(.,'Delete')]")).click();

	     
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[.='Yes, Delete']"))).click();

	        System.out.println(" Entitlement Deleted");
	    }

	    @AfterClass
	    public void close() {
	      tearDown();
	    }
	}


