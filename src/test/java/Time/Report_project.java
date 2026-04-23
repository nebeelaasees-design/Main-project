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

public class Report_project extends base_setup
{
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
	             By.xpath("//span[normalize-space()='Reports']"))).click();
	     
	     List<WebElement> elements = driver.findElements(By.xpath("//*"));

	     boolean found = false;

	     for (WebElement el : elements) {
	         try {
	             String text = el.getText().trim();

	             if (text.equalsIgnoreCase("Project Reports")) {
	                
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
	   @Test(priority = 1)
	    public void fillProjectReportForm() {

	      
	        WebElement projectName = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//input[@placeholder='Type for hints...']")));
	        projectName.sendKeys("Test Project");

	        Assert.assertEquals(projectName.getAttribute("value"), "Test Project");

	       
	        WebElement fromDate = driver.findElement(
	                By.xpath("//input[@placeholder='From']"));
	        fromDate.sendKeys("2024-01-01");

	        Assert.assertEquals(fromDate.getAttribute("value"), "2024-01-01");

	       
	        WebElement toDate = driver.findElement(
	                By.xpath("//input[@placeholder='To']"));
	        toDate.sendKeys("2024-01-31");

	        Assert.assertEquals(toDate.getAttribute("value"), "2024-01-31");

	       
	        WebElement checkbox = driver.findElement(
	                By.xpath("//label[contains(.,'Include')]//input"));

	        if (!checkbox.isSelected()) {
	            checkbox.click();
	        }

	        Assert.assertTrue(checkbox.isSelected());

	        System.out.println("Form filled successfully");
	    }

	    @Test(priority = 2)
	    public void clickViewButton() {

	        WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[.//span[text()='View']]")));

	        viewBtn.click();

	       
	        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.cssSelector(".oxd-table")));

	        Assert.assertTrue(result.isDisplayed());

	        System.out.println("Report generated successfully");
	    }

	    @Test(priority = 3)
	    public void negativeTestEmptyProjectName() {

	        driver.navigate().refresh();

	       
	        wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//input[@placeholder='From']"))).sendKeys("2024-01-01");

	        driver.findElement(By.xpath("//input[@placeholder='To']")).sendKeys("2024-01-31");

	       
	        driver.findElement(By.xpath("//button[.//span[text()='View']]")).click();

	       
	        WebElement validation = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//*[contains(text(),'Required') or contains(text(),'Invalid')]")));

	        Assert.assertTrue(validation.isDisplayed());

	        System.out.println("Negative test passed");
	    }

	    @AfterClass
	    public void tearclose() {
	      tearDown();
	    }
	}

