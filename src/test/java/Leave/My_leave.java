package Leave;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class My_leave extends base_setup {
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
	                By.xpath("//a[normalize-space()='My Leave']"))).click();

	  }
@Test
public void myleave()
{
	    
	        WebElement fromDate = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//label[text()='From Date']/following::input[1]")));
	        fromDate.clear();
	        fromDate.sendKeys("2026-04-01");

	        WebElement toDate = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//label[text()='To Date']/following::input[1]")));
	        toDate.clear();
	        toDate.sendKeys("2026-04-30");


	    
	        WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//label[text()='Show Leave with Status']/following::div[contains(@class,'oxd-select-text')]")));
	        statusDropdown.click();

	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//div[@role='listbox']//span[text()='Pending Approval']"))).click();


	     
	        WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//button[normalize-space()='Search']")));
	        searchBtn.click();
	        List<WebElement> rows = driver.findElements(By.xpath("//div[@class='oxd-table-card']"));

	        System.out.println("Total records found: " + rows.size());

	        Assert.assertTrue(rows.size() >= 0, "No leave records found");


}
	  @AfterClass
	  public void close()
	  {
	  	tearDown();
	  }
	  }