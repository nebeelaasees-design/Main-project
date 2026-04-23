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

public class Attendence_My_Records extends base_setup{
	 @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin","admin123");
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");
	   
	        Actions actions = new Actions(driver);

	      
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[normalize-space()='Attendance']"))).click();
	        
	        List<WebElement> elements = driver.findElements(By.xpath("//*"));

	        boolean found = false;

	        for (WebElement el : elements) {
	            try {
	                String text = el.getText().trim();

	                if (text.equalsIgnoreCase("MY Records")) {
	                    System.out.println("Found MY Records menu");

	                    ((JavascriptExecutor) driver)
	                            .executeScript("arguments[0].click();", el);

	                    found = true;
	                    break;
	                }

	            } catch (Exception e) {
	               
	            }
	        }

}
	  

	 
	    @Test(priority = 1)
	    public void viewRecord() {
	      

	    	 wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	            By.cssSelector(".oxd-table-body")));

	    	 
	    	    if (driver.findElements(By.xpath("//div[text()='No Records Found']")).size() > 0) {
	    	        System.out.println("No records - skipping");
	    	        return;
	    	    }

	    	    WebElement view = wait.until(ExpectedConditions.elementToBeClickable(
	    	            By.xpath("(//button[contains(.,'View')])[1]")));

	    	    ((JavascriptExecutor) driver).executeScript("arguments[0].click();", view);
   Assert.assertTrue(driver.getCurrentUrl().contains("attendance"),
	    	            "View action failed");

	    	    System.out.println("View clicked successfully");
	    	}
	    
	   
	    @Test(priority = 2)
	    public void editRecord() {
	      
	    	  // Handle no records
	        if (driver.findElements(By.xpath("//div[text()='No Records Found']")).size() > 0) {
	            System.out.println("No records - skipping edit");
	            return;
	        }

	        // Get Edit buttons
	        List<WebElement> edits = driver.findElements(By.xpath("//button[contains(.,'Edit')]"));

	        if (edits.size() == 0) {
	            System.out.println("No Edit button available");
	            return;
	        }

	        WebElement edit = edits.get(0);

	        // Scroll
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].scrollIntoView(true);", edit);

	        // Force click
	        ((JavascriptExecutor) driver).executeScript(
	                "arguments[0].click();", edit);

	        // Wait for textarea
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//textarea")));

	        WebElement note = driver.findElement(By.xpath("//textarea"));
	        note.clear();
	        note.sendKeys("Updated work note");

	        // Save
	        driver.findElement(By.xpath("//button[@type='submit']")).click();

	        // Verify
	        String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector(".oxd-toast-content-text"))).getText();

	        Assert.assertTrue(msg.toLowerCase().contains("success"));
	    }
	    

	    @Test(priority = 3)
	    public void deleteRecord() {
	    	   if (driver.findElements(By.xpath("//div[text()='No Records Found']")).size() > 0) {
	    	        System.out.println("No records");
	    	        return;
	    	    }

	    	   
	    	    WebElement del = wait.until(ExpectedConditions.presenceOfElementLocated(
	    	            By.xpath("(//button[contains(@class,'bi-trash')])[1]")));

	    	    ((JavascriptExecutor) driver).executeScript(
	    	            "arguments[0].click();", del);

	    	  
	    	    By popup = By.xpath("//div[contains(@class,'oxd-dialog-container')]");
	    	    wait.until(ExpectedConditions.visibilityOfElementLocated(popup));

	    	 
	    	    System.out.println("Popup opened");

	    	  
	    	    WebElement confirm = wait.until(ExpectedConditions.presenceOfElementLocated(
	    	            By.xpath("//div[contains(@class,'oxd-dialog-container')]//button[contains(.,'Yes')]")));

	    	    ((JavascriptExecutor) driver).executeScript(
	    	            "arguments[0].click();", confirm);

	    	   
	    	    wait.until(ExpectedConditions.invisibilityOfElementLocated(popup));

	    	  
	    	    String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	            By.cssSelector(".oxd-toast-content-text"))).getText();

	    	    Assert.assertTrue(msg.toLowerCase().contains("success"));

	    	    System.out.println("Delete success");
	    	}
	    	
	    
	    

	    @AfterClass
	    public void close() {
	        tearDown();
	    }
	}
	 



