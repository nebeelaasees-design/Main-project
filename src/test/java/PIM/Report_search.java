package PIM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Report_search extends base_setup {

	 @BeforeClass
	    public void setUp() {
		 setup();
		 login("Admin","admin123");

	        // Navigate to PIM → Reports
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Reports']"))).click();
	        System.out.println("PIM Reports Page Loaded");

	        // LIST ALL RECORDS
	          List<WebElement> records = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	                By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

	        System.out.println("Total Records: " + records.size());

	        for (int i = 0; i < records.size(); i++) {
	            System.out.println("Record " + (i + 1) + ": " + records.get(i).getText());
	        }

	    }
	 

	    @Test
	    public void testSearchFunctionality() {
	        WebElement reportInput = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
	        reportInput.clear();
	        reportInput.sendKeys("All Employee Sub Unit Hierarchy Report");
	        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[contains(@class, 'oxd-button--secondary')]")));

	        if (searchBtn.isDisplayed()) {

	      
	        if (searchBtn.isDisplayed()) {
	            System.out.println("Search Button Clickable: PASS");
	        } else {
	            System.out.println("Search Button Clickable: FAIL");
	        }
	        }

	       
	    }

	    @Test
	    public void testResetFunctionality() {
	        WebElement reportInput = driver.findElement(By.xpath("//input[@placeholder='Type for hints...']"));
	        reportInput.clear();
	        reportInput.sendKeys("DemoE2ETest");

	        WebElement resetBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[contains(@class, 'oxd-button--ghost')]")));

	        if (resetBtn.isDisplayed()) {
	            System.out.println("Reset Button Clickable: PASS");
	        } else {
	            System.out.println("Reset Button Clickable: FAIL");
	        }

	        resetBtn.click();

	       
	        
	    }

	    @AfterClass
	    public void tearDown() {
	        tearDown();
	    }
	}

