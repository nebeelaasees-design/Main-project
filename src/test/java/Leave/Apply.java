package Leave;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Apply extends base_setup
{
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
		    public void applyLeave() throws Exception {

		     
		      
		        wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//a[normalize-space()='Apply']"))).click();

	
		        WebElement leaveType = wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//label[text()='Leave Type']/following::div[contains(@class,'oxd-select-text')]")));
		        leaveType.click();

		        wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//div[@role='listbox']//span"))).click();

		        WebElement fromDate = wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//label[text()='From Date']/following::input[1]")));

		        fromDate.click();
		        fromDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		        fromDate.sendKeys(Keys.DELETE);
		        fromDate.sendKeys("2026-04-12");


		   
		        WebElement toDate = wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//label[text()='To Date']/following::input[1]")));

		        toDate.click();
		        toDate.sendKeys(Keys.chord(Keys.CONTROL, "a"));
		        toDate.sendKeys(Keys.DELETE);
		        toDate.sendKeys("2026-14-05");
		      
		       WebElement days = driver.findElement(
		                By.xpath("//label[text()='Duration']/following::input[1]"));
		        String dayValue = days.getAttribute("value");
		        System.out.println("Days selected: " + dayValue);

		       
		        WebElement durationDropdown = wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//label[text()='Duration']/following::div[contains(@class,'oxd-select-text')]")));
		        durationDropdown.click();

		        wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//div[@role='listbox']//span"))).click();
		               

		       
		        WebElement comment = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.xpath("//textarea")));
		        comment.sendKeys("Applying leave for personal work");

		       
		        WebElement applyBtn = wait.until(ExpectedConditions.elementToBeClickable(
		                By.xpath("//button[@type='submit']")));
		        applyBtn.click();

		     
		        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
		                By.cssSelector(".oxd-toast-content-text")));

		        Assert.assertTrue(toast.getText().contains("Success"),
		                "Leave application failed");

		        System.out.println(" Leave Applied Successfully");
		    }
	  
@AfterClass
public void close()
{
	tearDown();
}
}