package Recruitment;

import java.util.ArrayList;
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

public class Vaacancies extends base_setup{
	 @BeforeClass
	    public void setupFunction() {
	       
		
		  setup();
	      login("Admin", "admin123");

	      System.out.println("Dashboard Title: " + driver.getTitle());
	      Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");

	      
	      Actions actions = new Actions(driver);

	      wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Recruitment']"))).click();
	      System.out.println("Dashboard Title: " + driver.getTitle());
	      Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");
	      

	
	  
     wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Vacancies']"))).click();
 }

	 @Test(priority = 1)
	 public void viewVacancy()
	 {
		 List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
			        By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

			
			List<String> vacancyList = new ArrayList<>();

			
			for (WebElement row : rows) {

			  
			    String vacancyName = row.findElement(By.xpath(".//div[2]")).getText();

			    vacancyList.add(vacancyName);
			}

			
			System.out.println("=== Vacancy List ===");
			for (String v : vacancyList) {
			    System.out.println(v);
			}
	 }
 @Test(priority = 2)
 public void addVacancy() {


	
	 WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//button[normalize-space()='Add']")));
	 Assert.assertTrue(addBtn.isDisplayed() && addBtn.isEnabled(), "Add button not clickable");
	 addBtn.click();

	
	 WebElement vacancyName = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//label[contains(text(),'Vacancy')]/parent::div//input")));
	 Assert.assertTrue(vacancyName.isDisplayed(), "Vacancy Name field not visible");
	 vacancyName.sendKeys("QA Automation Engineer");

	
	 WebElement jobTitle = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')]")));
	 Assert.assertTrue(jobTitle.isEnabled(), "Job Title dropdown not clickable");
	 jobTitle.click();

	
	 WebElement option = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[@role='listbox']//span")));
	 Assert.assertTrue(option.isDisplayed(), "Dropdown option not visible");
	 option.click();

	
	 WebElement description = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//textarea")));
	 Assert.assertTrue(description.isDisplayed(), "Description not visible");
	 description.sendKeys("This vacancy is for Selenium Automation Tester role.");

	 WebElement hiringManager = driver.findElement(
	         By.xpath("//label[text()='Hiring Manager']/following::input[1]"));
	 Assert.assertTrue(hiringManager.isDisplayed(), "Hiring Manager field missing");
	 hiringManager.sendKeys("John Doe");

	 
	 WebElement positions = driver.findElement(
	         By.xpath("//label[text()='Number of Positions']/following::input[1]"));
	 Assert.assertTrue(positions.isDisplayed(), "Positions field missing");
	 positions.sendKeys("2");

	 
	 WebElement link1 = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("(//input[@type='text'])[2]")));
	 Assert.assertTrue(link1.isDisplayed(), "Link1 not visible");
	 link1.sendKeys("https://company.com/job-details");

	 WebElement link2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("(//input[@type='text'])[3]")));
	 Assert.assertTrue(link2.isDisplayed(), "Link2 not visible");
	 link2.sendKeys("https://company.com/apply");


	 WebElement checkbox1 = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("(//span[@class='oxd-switch-input'])[1]")));
	 Assert.assertTrue(checkbox1.isDisplayed(), "Checkbox1 not visible");
	 checkbox1.click();

	 WebElement checkbox2 = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("(//span[@class='oxd-switch-input'])[2]")));
	 Assert.assertTrue(checkbox2.isDisplayed(), "Checkbox2 not visible");
	 checkbox2.click();

	
	 WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//button[@type='submit']")));
	 Assert.assertTrue(saveBtn.isDisplayed() && saveBtn.isEnabled(), "Save button not clickable");

	
	 saveBtn.click();

	 WebElement successMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	 Assert.assertTrue(successMsg.isDisplayed(), "Success message not displayed");

	 
	 WebElement vacancyHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//h5[contains(text(),'Vacancies')]")));
	 Assert.assertTrue(vacancyHeader.isDisplayed(), "Vacancy page not loaded");

	 System.out.println(" Vacancy Added and Validated Successfully");
 }

 @Test(priority = 3)
 public void searchVacancy() {

    driver.navigate().back();
	
	 WebElement jobTitle = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//label[text()='Job Title']/following::div[contains(@class,'oxd-select-text')]")));
	 Assert.assertTrue(jobTitle.isDisplayed(), "Job Title dropdown not visible");
	 jobTitle.click();

	 String expectedJobTitle = "QA Engineer";
	 WebElement jobOption = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[@role='listbox']//span[text()='" + expectedJobTitle + "']")));
	 jobOption.click();


	 WebElement vacancy = driver.findElement(
	         By.xpath("//label[text()='Vacancy']/following::div//input"));
	 Assert.assertTrue(vacancy.isDisplayed(), "Vacancy field missing");
	 String expectedVacancy = "QA Automation Engineer";
	 vacancy.sendKeys(expectedVacancy);

	
	 WebElement hiringManager = driver.findElement(
	         By.xpath("//label[text()='Hiring Manager']/following::input"));
	 Assert.assertTrue(hiringManager.isDisplayed(), "Hiring Manager field missing");
	 String expectedManager = "John Doe";
	 hiringManager.sendKeys(expectedManager);

	
	 WebElement status = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')]")));
	 Assert.assertTrue(status.isDisplayed(), "Status dropdown missing");
	 status.click();

	 String expectedStatus = "Active";
	 WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//div[@role='listbox']//span[text()='" + expectedStatus + "']")));
	 statusOption.click();

	
	 WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//button[normalize-space()='Search']")));
	 Assert.assertTrue(searchBtn.isEnabled(), "Search button not enabled");
	 searchBtn.click();


	 //VALIDATION

	
	 List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	         By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

	 Assert.assertTrue(rows.size() > 0, "No records found");

	
	 for (WebElement row : rows) {

	     String actualVacancy = row.findElement(By.xpath(".//div[2]")).getText();
	     String actualJobTitle = row.findElement(By.xpath(".//div[3]")).getText();
	     String actualManager = row.findElement(By.xpath(".//div[4]")).getText();
	     String actualStatus = row.findElement(By.xpath(".//div[5]")).getText();

	     Assert.assertTrue(actualVacancy.contains(expectedVacancy),
	             "Vacancy mismatch: " + actualVacancy);

	     Assert.assertTrue(actualJobTitle.contains(expectedJobTitle),
	             "Job Title mismatch: " + actualJobTitle);

	     Assert.assertTrue(actualManager.contains(expectedManager),
	             "Manager mismatch: " + actualManager);

	     Assert.assertTrue(actualStatus.contains(expectedStatus),
	             "Status mismatch: " + actualStatus);
	 }

	 System.out.println(" Vacancy Search Validation Passed");
 }


 @Test(priority = 4)
 public void editVacancy() {

    

  

	
	String vacancyName = "QA Automation Engineer";

	WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//label[text()='Vacancy']/following::input[1]")));
	searchBox.clear();
	searchBox.sendKeys(vacancyName);

	driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

	
	WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//div[@role='row'][1]//i[contains(@class,'bi-pencil')]")));
	Assert.assertTrue(editBtn.isDisplayed(), "Edit button not visible");
	editBtn.click();

	
	WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//h6[contains(text(),'Edit Vacancy')]")));
	Assert.assertTrue(header.isDisplayed(), "Edit page not loaded");

	
	WebElement vacancyField = driver.findElement(
	        By.xpath("//label[contains(text(),'Vacancy')]/parent::div//input"));
	Assert.assertFalse(vacancyField.getAttribute("value").isEmpty(),
	        "Vacancy field is empty (should be pre-filled)");

	
	vacancyField.clear();
	String updatedVacancy = "QA Automation Lead Updated";
	vacancyField.sendKeys(updatedVacancy);

	
	WebElement manager = driver.findElement(
	        By.xpath("//label[text()='Hiring Manager']/following::input[1]"));
	manager.clear();
	manager.sendKeys("Jane Smith");


	WebElement positions = driver.findElement(
	        By.xpath("//label[text()='Number of Positions']/following::input[1]"));
	positions.clear();
	positions.sendKeys("5");

	WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	        By.xpath("//button[@type='submit']")));
	Assert.assertTrue(saveBtn.isEnabled(), "Save button not enabled");
	saveBtn.click();

	
	WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	Assert.assertTrue(toast.isDisplayed(), "No success message");

	Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	        "Edit not successful");

	
	searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//label[text()='Vacancy']/following::input[1]")));
	searchBox.clear();
	searchBox.sendKeys(updatedVacancy);

	driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

	WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(
	        By.xpath("//div[@role='row'][1]//div[2]")));
	Assert.assertTrue(result.getText().contains(updatedVacancy),
	        "Updated vacancy not found");

	System.out.println(" Vacancy Edited Successfully");
 }

 
 @Test(priority = 5)
 public void deleteVacancy() {

   

   
	
	 WebElement vacancySearch = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//label[text()='Vacancy']/following::input[1]")));
	 Assert.assertTrue(vacancySearch.isDisplayed(), "Vacancy search field not visible");

	 String vacancyName = "QA Automation Lead";
	 vacancySearch.sendKeys(vacancyName);

	
	 WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//button[normalize-space()='Search']")));
	 Assert.assertTrue(searchBtn.isEnabled(), "Search button not clickable");
	 searchBtn.click();

	
	 WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));
	 Assert.assertTrue(row.isDisplayed(), "No records found for deletion");

	
	 WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("(//input[@type='checkbox'])[2]")));
	 Assert.assertTrue(checkbox.isDisplayed(), "Checkbox not visible");
	 checkbox.click();

	 
	 Assert.assertTrue(checkbox.isSelected(), "Checkbox not selected");

	
	 WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//button[normalize-space()='Delete Selected']")));
	 Assert.assertTrue(deleteBtn.isEnabled(), "Delete button not enabled after selection");
	 deleteBtn.click();

	
	 WebElement confirmPopup = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//div[contains(@class,'oxd-dialog-container')]")));
	 Assert.assertTrue(confirmPopup.isDisplayed(), "Delete confirmation popup not shown");

	 WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(
	         By.xpath("//button[normalize-space()='Yes, Delete']")));
	 Assert.assertTrue(confirm.isEnabled(), "Confirm delete button not clickable");
	 confirm.click();

	
	 WebElement toastMsg = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	 Assert.assertTrue(toastMsg.isDisplayed(), "Toast message not displayed");

	 String toastText = toastMsg.getText();
	 Assert.assertTrue(toastText.toLowerCase().contains("success"),
	         "Delete not successful: " + toastText);

	
	 vacancySearch = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//label[text()='Vacancy']/following::input[1]")));
	 vacancySearch.clear();
	 vacancySearch.sendKeys(vacancyName);

	 searchBtn.click();

	
	 WebElement noRecord = wait.until(ExpectedConditions.visibilityOfElementLocated(
	         By.xpath("//span[text()='No Records Found']")));
	 Assert.assertTrue(noRecord.isDisplayed(), "Record still exists after deletion");

	 System.out.println(" Vacancy Deleted Successfully with Validation");
 }

 @AfterClass
 public void close()
 {
	 tearDown();
}
}