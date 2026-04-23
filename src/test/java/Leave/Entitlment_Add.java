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

public class Entitlment_Add extends base_setup{
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

		   
		    wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//h6[contains(text(),'Add Entitlements')]")));

		   
		    wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//label[contains(.,'Individual Employee')]//span"))).click();

		  
		    WebElement emp = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//input[@placeholder='Type for hints...']")));
		    emp.sendKeys("John");

		    wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//div[@role='listbox']//span"))).click();

		   
		    WebElement leaveType = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("(//div[contains(@class,'oxd-select-text-input')])[1]")));
		    leaveType.click();

		    wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//div[@role='listbox']//span"))).click();

		  
		    WebElement leavePeriod = wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("(//div[contains(@class,'oxd-select-text-input')])[2]")));
		    leavePeriod.click();

		    wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//div[@role='listbox']//span"))).click();

		   
		    WebElement entitlement = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.xpath("//label[text()='Entitlement']/following::input[1]")));
		    entitlement.sendKeys("12");

		
		    wait.until(ExpectedConditions.elementToBeClickable(
		            By.xpath("//button[@type='submit']"))).click();

		 
		    WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
		            By.cssSelector(".oxd-toast-content-text")));

		    Assert.assertTrue(msg.getText().contains("Success"));
		
    }
@AfterClass
public void close()
{
	tearDown();
}
	}


