package Leave;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Entitlment_Employee extends base_setup{
	  @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin", "admin123");

	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

	        
	        Actions actions = new Actions(driver);
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Leave']"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Entitlements ']"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[text()='Add Entitlements']"))).click();
	    
}
	
		     
		     

	@Test
	public void testfunction()
	{

	    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(20));

	  
	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//span[text()='Leave']"))).click();

	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[text()='Entitlements ']"))).click();

	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//a[text()='Employee Entitlements']"))).click();

	   
	    wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h5[contains(text(),'Employee Entitlements')]")));

	  
	    WebElement empName = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//input[@placeholder='Type for hints...']")));
	    empName.sendKeys("John");

	   
	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@role='listbox']//span"))).click();

	   
	    WebElement leaveType = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]")));
	    leaveType.click();

	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@role='listbox']//span"))).click();

	  
	    wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[@type='submit' or contains(.,'Search')]"))).click();

	
	    WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.cssSelector(".oxd-table-card")));

	    Assert.assertTrue(result.isDisplayed(), "Search failed - No record found");

	    System.out.println("Employee Entitlement Search Successful");
	}
		
  
@AfterClass
public void close()
{
	tearDown();
}
	




}
