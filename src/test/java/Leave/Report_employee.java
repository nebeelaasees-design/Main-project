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

public class Report_employee  extends base_setup {
	  @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin", "admin123");

	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

	        
	        Actions actions = new Actions(driver);
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[text()='Leave']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Reports ']"))).click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//a[text()='Leave Entitlements and Usage Report']"))).click();
	  }
	  @Test
	    public void generateReport() throws InterruptedException {

	      

	        
	        WebElement reportType = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("(//div[contains(@class,'oxd-select-text')])[1]")));
	        reportType.click();

	        driver.findElement(By.xpath("//span[contains(text(),'Employee')]")).click();

	       
	        WebElement emp = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
	        emp.sendKeys("Linda Anderson");

	        Thread.sleep(2000);
	        driver.findElement(By.xpath("//div[@role='listbox']//span")).click();

	     
	        WebElement leaveType = driver.findElement(
	                By.xpath("(//div[contains(@class,'oxd-select-text')])[2]"));
	        leaveType.click();

	        driver.findElement(By.xpath("//span[contains(text(),'CAN - Vacation')]")).click();

	      
	        WebElement fromDate = driver.findElement(By.xpath("(//input[@placeholder='yyyy-mm-dd'])[1]"));
	        fromDate.clear();
	        fromDate.sendKeys("2026-01-01");

	      
	        WebElement toDate = driver.findElement(By.xpath("(//input[@placeholder='yyyy-mm-dd'])[2]"));
	        toDate.clear();
	        toDate.sendKeys("2026-12-31");

	       
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	       
	        boolean result = driver.getPageSource().contains("Linda Anderson");

	        System.out.println(" Report Generated Successfully: " + result);
	    }
	@AfterClass
	public void close()
	{
		tearDown();
	}
	}


