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

public class configure_Leaveperiod   extends base_setup
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
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Configure ']"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Leave Period']"))).click();
	  }
	
	
	
	 @Test
	    public void configureLeavePeriod() {

	        

	       
	        WebElement monthDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("(//div[contains(@class,'oxd-select-text')])[1]")));
	        monthDropdown.click();

	        driver.findElement(By.xpath("//span[text()='January']")).click();

	      
	        WebElement dayDropdown = driver.findElement(
	                By.xpath("(//div[contains(@class,'oxd-select-text')])[2]"));
	        dayDropdown.click();

	        driver.findElement(By.xpath("//span[text()='1']")).click();

	     
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        System.out.println(" Leave Period Configured Successfully");
	    }

	  
@AfterClass
public void close()
{
	tearDown();
}
	}


