package Admin;



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

public class Job_managment extends base_setup{
	
	
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
   public void addJobTitle() throws InterruptedException {
	
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Job']"))).click();
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='Job Titles']"))).click();

       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Job Titles']")));
     
   }
 
   @Test(priority = 2)
   public void AddJobTitle() throws InterruptedException {
	  
	    WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Add']")));
	    Assert.assertTrue(addBtn.isEnabled(), "Add button not clickable");
	    addBtn.click();

	    // Enter Job Title
	    String jobTitle = "QA Automation Engineer";

	    WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//label[text()='Job Title']/following::input[1]")));
	    titleField.sendKeys(jobTitle);

	    // Description
	    WebElement desc = driver.findElement(By.xpath("//textarea"));
	    desc.sendKeys("Automation Testing Role");

	    // Save
	    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Save']")));
	    saveBtn.click();

	    // Toast validation
	    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	            "Job title not added");

	    System.out.println("Job Title Added");
	}
	   
   
@Test(priority=3)
public void viewfunction() throws Exception
{

    List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

    Assert.assertTrue(rows.size() > 0, "No job titles found");

    System.out.println("_______JOB TITLES _______");

    for (WebElement row : rows) {
        String title = row.findElement(By.xpath(".//div[2]")).getText();
        String desc = row.findElement(By.xpath(".//div[3]")).getText();

        System.out.println("Title: " + title + " | Desc: " + desc);
    }
}



   @Test(priority = 4)
   public void editJobTitle() {
	   String updatedTitle = "Senior QA Engineer";

	    // Select first row checkbox
	    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@role='row'][1]//input[@type='checkbox']")));
	    checkbox.click();
	    Assert.assertTrue(checkbox.isSelected(), "Checkbox not selected");

	    // Click Edit
	    WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[.//i[contains(@class,'bi-pencil')]]")));
	    editBtn.click();

	    // Edit Title
	    WebElement titleField = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//label[text()='Job Title']/following::input[1]")));
	    titleField.clear();
	    titleField.sendKeys(updatedTitle);

	    // Save
	    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Save']")));
	    saveBtn.click();

	    //  Toast validation
	    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	            "Edit failed");

	    //  Verify updated value
	    WebElement updatedRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[@role='row'][1]//div[2]")));

	    Assert.assertTrue(updatedRow.getText().contains(updatedTitle),
	            "Updated title not reflected");

	    System.out.println(" Job Title Edited");
	}

		 
   public void deleteJobTitle() {

	    // Select checkbox
	    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@role='row'][1]//input[@type='checkbox']")));
	    checkbox.click();

	    Assert.assertTrue(checkbox.isSelected(), "Checkbox not selected");

	    // Click Delete
	    WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Delete Selected']")));
	    Assert.assertTrue(deleteBtn.isEnabled(), "Delete button not enabled");
	    deleteBtn.click();

	    // Confirm popup
	    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Yes, Delete']")));
	    confirmBtn.click();

	    //  Toast validation
	    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	            "Delete failed");

	    System.out.println(" Job Title Deleted");
	}
  

@AfterClass
public void close()
{
	tearDown();
}
}