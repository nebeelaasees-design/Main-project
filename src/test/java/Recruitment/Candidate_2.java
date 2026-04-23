package Recruitment;

import java.util.List;

import org.checkerframework.common.reflection.qual.GetClass;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Candidate_2  extends base_setup {

    @BeforeClass
    public void setupFunction() {
       
	
	  setup();
      login("Admin", "admin123");

      System.out.println("Dashboard Title: " + driver.getTitle());
      Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

      
 

      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Recruitment']"))).click();
      System.out.println("Dashboard Title: " + driver.getTitle());
      Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");
      

}
@Test
public void searchcandidate()
{
	wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//a[contains(text(),'Candidates')]")
		)).click();
	driver.findElement(By.xpath("//label[text()='Job Title']/following::div[1]")).click();
	
	wait.until(ExpectedConditions.elementToBeClickable(
	    By.xpath("//span[text()='Software Engineer']")
	)).click();
	
	driver.findElement(By.xpath("//label[text()='Vacancy']/following::div[1]")).click();
	wait.until(ExpectedConditions.elementToBeClickable(
	    By.xpath("//span[text()='Engineer']")
	)).click();
	
	WebElement manager = driver.findElement(
		    By.xpath("//input[@placeholder='Type for hints...']")
		);
		manager.sendKeys("Linda");
		wait.until(ExpectedConditions.elementToBeClickable(
			    By.xpath("//span[contains(text(),'Linda')]")
			)).click();
		
		driver.findElement(By.xpath("//label[text()='Status']/following::div[1]")).click();
		wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//span[text()='Shortlisted']")
		)).click();
		
		driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"))
	      .sendKeys("Akhil");
		
		WebElement date = driver.findElement(
			    By.xpath("//input[@placeholder='From']")
			);
			date.sendKeys("2026-04-14");
			
			driver.findElement(By.xpath("//label[text()='Method of Application']/following::div[1]")).click();
			wait.until(ExpectedConditions.elementToBeClickable(
			    By.xpath("//span[text()='Online']")
			)).click();
			
			
			driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
			// VALIDATION
			//Check Results Table Loaded
			wait.until(ExpectedConditions.visibilityOfElementLocated(
			    By.xpath("//div[@role='table']")
			));
			
			
			List<WebElement> rows = driver.findElements(
			    By.xpath("//div[@role='rowgroup'][2]//div[@role='row']")
			);

			if (rows.size() > 0) {
			    System.out.println(" Search returned results: " + rows.size());
			} else {
			    System.out.println(" No records found");
			}
		
			
			// RESET FUNCTIONALITY
			
			driver.findElement(By.xpath("//button[normalize-space()='Reset']")).click();
			
		
			WebElement keywordField = driver.findElement(
			    By.xpath("//input[@placeholder='Type for hints...']")
			);

			if (keywordField.getAttribute("value").isEmpty()) {
			    System.out.println(" Reset successful");
			} else {
			    System.out.println(" Reset failed");
			}

}

@Test
public void editcandidate()
{
//download resume	
	
	wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("(//i[contains(@class,'bi-download')])[1]")
		)).click();

		System.out.println("Download clicked");
	wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("(//i[contains(@class,'bi-eye')])[1]")
		)).click();
		// Validate
		
		wait.until(ExpectedConditions.urlContains("viewCandidate"));

		String url = driver.getCurrentUrl();

		if (url.contains("viewCandidate")) {
		    System.out.println(" Application stage page is visible");
		} else {
		    System.out.println(" Page not opened correctly");
		}
		//Check Edit Button is Clickable
		
		WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[normalize-space()='Edit']")
		));

		if (editBtn.isEnabled()) {
		    System.out.println("✅ Edit button is clickable");
		}
	
		editBtn.click();
		// Validate Fields are Editable
		
		WebElement name = driver.findElement(By.name("firstName"));
		WebElement email = driver.findElement(By.xpath("//input[@type='email']"));

		if (name.isEnabled() && email.isEnabled()) {
		    System.out.println(" Fields are editable");
		} else {
		    System.out.println(" Fields are NOT editable");
		}
		
		WebElement firstName = driver.findElement(By.name("firstName"));
		firstName.clear();
		firstName.sendKeys("Anand");
		
		WebElement lastName = driver.findElement(By.name("lastName"));
		lastName.clear();
		lastName.sendKeys("Kiran");
		
		WebElement email1 = driver.findElement(By.xpath("//label[text()='Email']/following::input[1]"));
		email1.clear();
		email1.sendKeys("anand@test.com");
		
		WebElement contact = driver.findElement(By.xpath("//label[text()='Contact Number']/following::input[1]"));
		contact.clear();
		contact.sendKeys("9998887776");
		
		WebElement notes = driver.findElement(By.tagName("textarea"));
		notes.clear();
		notes.sendKeys("Updated candidate details with new info.");
	
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.xpath("//div[contains(@class,'oxd-toast-content')]")
		));
}
@GetClass
public void close()
{
	tearDown();
}
}
