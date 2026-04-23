package Admin;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Corporate_branding extends base_setup{
	
	
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
	   public void corporatebranding() throws InterruptedException {
		  Actions actions = new Actions(driver);
	       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();
	       WebElement brandingMenu = wait.until(ExpectedConditions.elementToBeClickable(
                   By.xpath("//a[text()='Corporate Branding']")));
           brandingMenu.click();

         
           WebElement logoInput = wait.until(ExpectedConditions.presenceOfElementLocated(
        	        By.xpath("//input[@type='file']")));

        	logoInput.sendKeys("\"D:\\Photos\\‪+91 96052 41813‬ 20151218_194159.jpg\"");

        	System.out.println(" Logo uploaded");
        	
        	// Open Primary Color picker
        	WebElement primaryColor = wait.until(ExpectedConditions.elementToBeClickable(
        	        By.xpath("//label[text()='Primary Color']/following::input[1]")));

        	actions.moveToElement(primaryColor).click().perform();

        	// Select a color (example: first swatch)
        	WebElement colorPick = wait.until(ExpectedConditions.elementToBeClickable(
        	        By.xpath("(//div[contains(@class,'color-picker')]//div)[1]")));

        	actions.moveToElement(colorPick).click().perform();

        	System.out.println("Primary Color selected");

          
           WebElement secondaryColor = driver.findElement(By.xpath("//label[text()='Secondary Color']"));
           secondaryColor.click();
           System.out.println("Secondary Color Label: " + secondaryColor.getText());

         
           WebElement primaryFontColor = driver.findElement(By.xpath("//label[text()='Primary Font Color']"));
           primaryFontColor.click();
           System.out.println("Primary Font Color Label: " + primaryFontColor.getText());

           
           WebElement secondaryFontColor = driver.findElement(By.xpath("//label[text()='Secondary Font Color']"));
secondaryFontColor.click();
           System.out.println("Secondary Font Color Label: " + secondaryFontColor.getText());

         
           WebElement primaryGradient1 = driver.findElement(By.xpath("//label[text()='Primary Gradient Color 1']"));
           primaryGradient1.click();
           System.out.println("Primary Gradient Color 1 Label: " + primaryGradient1.getText());

          
           WebElement primaryGradient2 = driver.findElement(By.xpath("//label[text()='Primary Gradient Color 2']"));
           primaryGradient2.click();
           System.out.println("Primary Gradient Color 2 Label: " + primaryGradient2.getText());

          
   
         
           
           WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
                   By.cssSelector("button.oxd-button--secondary")));
           saveBtn.click();

          
        
       

       }
         
     @AfterClass
     public void close()
     {
    	 tearDown();
     }
   }
	       
	   

