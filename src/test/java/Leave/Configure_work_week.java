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

public class Configure_work_week  extends base_setup
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

	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Work Week']"))).click();
	    }
	    @Test
	    public void configureWorkWeek() throws InterruptedException {

	       

	      
	        WebElement monday = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//label[contains(text(),'Monday')]/preceding-sibling::div")));
	        monday.click();

	     
	        driver.findElement(By.xpath("//label[contains(text(),'Tuesday')]/preceding-sibling::div")).click();

	       
	        driver.findElement(By.xpath("//label[contains(text(),'Wednesday')]/preceding-sibling::div")).click();

	       
	        driver.findElement(By.xpath("//label[contains(text(),'Thursday')]/preceding-sibling::div")).click();

	       
	        driver.findElement(By.xpath("//label[contains(text(),'Friday')]/preceding-sibling::div")).click();

	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        System.out.println(" Work Week Configured Successfully");
	    }
	  @AfterClass
	  public void close()
	  {
	  	tearDown();
	  }

}
