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

public class Assign_Leave  extends base_setup
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
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[text()='Assign Leave']"))).click();

	  }
	 
	    @Test
	    public void assignLeave() throws InterruptedException {

	    

	      
	        WebElement emp = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[@placeholder='Type for hints...']")));
	        emp.sendKeys("Linda Anderson");

	        Thread.sleep(2000); 
	        driver.findElement(By.xpath("//div[@role='listbox']//span")).click();

	        WebElement leaveType = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[contains(@class,'oxd-select-text')]")));
	        leaveType.click();

	        driver.findElement(By.xpath("//span[contains(text(),'Vacation')]")).click();

	       
	        WebElement fromDate = driver.findElement(By.xpath("(//input[@placeholder='yyyy-mm-dd'])[1]"));
	        fromDate.clear();
	        fromDate.sendKeys("2026-04-20");

	     
	        WebElement toDate = driver.findElement(By.xpath("(//input[@placeholder='yyyy-mm-dd'])[2]"));
	        toDate.clear();
	        toDate.sendKeys("2026-04-22");

	     
	        driver.findElement(By.xpath("//textarea")).sendKeys("Assigned by Admin");

	       
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	     
	        try {
	            WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(
	                    By.xpath("//button[.='Confirm']")));
	            confirm.click();
	        } catch (Exception e) {
	            System.out.println("No confirmation popup");
	        }

	        System.out.println("✅ Leave Assigned Successfully");
	    }

	    @AfterClass
	    public void close() {
	       tearDown();
	    }
	}
	       
	    



