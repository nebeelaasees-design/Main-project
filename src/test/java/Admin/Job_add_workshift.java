package Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Job_add_workshift  extends base_setup{
	
	
	   @BeforeClass
	   public void beforeclass()
	   {
	   
		  
		setup();
		login("Admin","admin123");
		wait.until(ExpectedConditions.elementToBeClickable(
			    By.xpath("//span[text()='Admin']")
			)).click();
		System.out.println("The Title is  "+driver.getTitle());
	  
	   }
	   
	  @Test(priority = 1)
	   public void addworkshift() throws InterruptedException {
		  
	       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();
	       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Job']"))).click();
	       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Work Shifts']"))).click();

	    wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button.oxd-button--secondary"))) .click();
	     
	   }
	  @Test( priority=2)
	  public void addworkshift2() throws Exception
	  {
		
		  WebElement shift = wait.until(ExpectedConditions.elementToBeClickable(
		          By.cssSelector("input.oxd-input")));
		  shift.sendKeys("Morning Shift");

		 
		  WebElement fromTime = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
		          By.cssSelector("input[placeholder='hh:mm']"))).get(0);
		  fromTime.sendKeys("09:00");

		  
		  WebElement toTime = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
		          By.cssSelector("input[placeholder='hh:mm']"))).get(1);
		  toTime.sendKeys("17:00");

		  
		  WebElement employee = wait.until(ExpectedConditions.elementToBeClickable(
		          By.cssSelector("input[placeholder='Type for hints...']")));
		  employee.sendKeys("Linda Anderson");

		
		  WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
		          By.xpath("//div[@role='option']")));
		  option.click();

	
		  WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
		          By.cssSelector("button.oxd-button--secondary")));
		  saveBtn.click();
	        Thread.sleep(3000);

	       
	        if (driver.getPageSource().contains("Success")) {
	            System.out.println("Work Shift added successfully ");
	        } else {
	            System.out.println("Work Shift not added ");
	        }

	        driver.quit();
	    }
	
	  }



