package Recruitment;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Candidates extends base_setup {

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
    @Test(priority = 2)
    public void addCandidate() throws Exception {

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()=' Add ']"))).click();

        WebElement firstName = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.name("firstName")));
        firstName.sendKeys("Akhil");
        driver.findElement(By.name("lastName")).sendKeys("Kiran");
       driver.findElement(By.name("middleName")).sendKeys("vishnu");
     
        driver.findElement(By.xpath("//label[text()='Vacancy']/following::div[1]")).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[contains(text(),'Engineer')]"))).click();

        driver.findElement(By.xpath("//label[text()='Email']/following::input[1]"))
                .sendKeys("akhil@test.com");
        driver.findElement(By.xpath("//label[text()='Contact Number']/following::input[1]"))
        .sendKeys("9876543210");

     
      
       
      
        	 WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                     By.xpath("(//input[@placeholder='Select Date'])[1]")));
             dateField.sendKeys("2026-04-14");


       
        	
        WebElement upload = driver.findElement(By.xpath("//input[@type='file']"));
        upload.sendKeys("D:\\Testing\\abstraction.docx");

      

        WebElement notes = driver.findElement(By.xpath("//textarea"));
        notes.sendKeys("Good candidate with strong skills.");

      
 
        WebElement checkbox = driver.findElement(By.xpath("//input[@type='checkbox']"));

        try {
            checkbox.click();
        } catch (Exception e) {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", checkbox);
        }

      
    
        //  Click Save
        WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")));
        saveBtn.click();

        // VALIDATION 1: Toast Message
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast-content')]")));

        // VALIDATION 2: Candidates Page Loaded
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//h6[text()='Candidates']")));

        System.out.println(" Candidate added successfully");
        
    }

   @Test(priority=1)
   public void validations()
   {
	   WebElement name = driver.findElement(By.xpath("//input[@name='firstName']"));
	   name.sendKeys("John123");

	 
	   String value = name.getAttribute("value");

	   if (value.matches(".\\d.")) {
	       System.out.println(" Name accepts numbers (BUG)");
	   } else {
	       System.out.println(" Name validation working");
	   }
	   WebElement email = driver.findElement(By.xpath("//label[text()='Email']/following::input[1]"));
	   email.sendKeys("abc@");

	   // Click outside or Save
	   driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();

	   // Validation message
	   WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
	       By.xpath("//span[contains(text(),'Valid')]")
	   ));

	   System.out.println("Email validation displayed");
	   WebElement contact = driver.findElement(
			    By.xpath("//label[text()='Contact Number']/following::input[1]")
			);

			contact.sendKeys("abc123");

			// Validate
			String phone = contact.getAttribute("value");

			if (phone.matches("[0-9]+")) {
			    System.out.println(" Valid number");
			} else {
			    System.out.println("Invalid number accepted (BUG)");
			}
   }
   
   @Test
   public void listallcandidates()
   {
	 
	   wait.until(ExpectedConditions.elementToBeClickable(
	       By.xpath("//a[contains(text(),'Candidates')]")
	   )).click();

	
	   wait.until(ExpectedConditions.visibilityOfElementLocated(
	       By.xpath("//div[@role='table']")
	   ));

	
	   List<WebElement> candidates = driver.findElements(
	       By.xpath("//div[@role='rowgroup'][2]//div[@role='row']//div[@role='cell'][3]")
	   );

	
	   System.out.println("---- Candidate List ----");

	   for (int i = 0; i < candidates.size(); i++) {
	       String name = candidates.get(i).getText().trim();
	       if (!name.isEmpty()) {
	           System.out.println((i + 1) + ". " + name);
	       }
	   }
   }
    
    @Test(priority =3 )
    public void deleteCandidate() {

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("(//i[contains(@class,'bi-trash')])[1]"))).click();

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[text()=' Yes, Delete ']"))).click();
    }
    @AfterClass
    public void close() {
        tearDown();
    }

}
 


