package Time;

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

public class Attendence_confiquration   extends base_setup {

    @BeforeClass
    public void setupFunction() {
        setup();
        login("Admin", "admin123");

        System.out.println("Dashboard Title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

        
        Actions actions = new Actions(driver);

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
        System.out.println("Dashboard Title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

    
     wait.until(ExpectedConditions.elementToBeClickable(
             By.xpath("//span[normalize-space()='Attendance']"))).click();
     
     List<WebElement> elements = driver.findElements(By.xpath("//*"));

     boolean found = false;

     for (WebElement el : elements) {
         try {
             String text = el.getText().trim();

             if (text.equalsIgnoreCase("Configuration")) {
                 System.out.println("Found Configuration menu");

                 ((JavascriptExecutor) driver)
                         .executeScript("arguments[0].click();", el);

                 found = true;
                 break;
             }

         } catch (Exception e) {
            
         }
     }

    
     Assert.assertTrue(found, "Configuration menu NOT found!");
 


    }

   @Test
   public void Testfunction() throws Exception
   {
	
	   
       By option1 = By.xpath("//label[span[contains(text(),'Option 1')]]//input");
       WebElement option1Elem = wait.until(ExpectedConditions.presenceOfElementLocated(option1));
       if (!option1Elem.isSelected()) {
           option1Elem.click();
       }

     
       By option2 = By.xpath("//label[span[contains(text(),'Option 2')]]//input");
       WebElement option2Elem = wait.until(ExpectedConditions.presenceOfElementLocated(option2));
       if (!option2Elem.isSelected()) {
           option2Elem.click();
       }

      
       By option3 = By.xpath("//label[span[contains(text(),'Option 3')]]//input");
       WebElement option3Elem = wait.until(ExpectedConditions.presenceOfElementLocated(option3));
       if (!option3Elem.isSelected()) {
           option3Elem.click();
           Actions actions = new Actions(driver);

           WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
        		    By.xpath("//button[normalize-space()='Save']")));

        		saveBtn.click();
           System.out.println("Options selected and Save clicked!");

       } 
   }

	
	
   

    @AfterClass
    public void close() {
        tearDown();
    }
}


