package Admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class qualification_language_add  extends base_setup{
	
	
	   @BeforeClass
	   public void beforeclass()
	   {
	   
		  
		setup();
		login("Admin","admin123");
		wait.until(ExpectedConditions.elementToBeClickable(
			    By.xpath("//span[text()='Admin']")
			)).click();
		System.out.println("The Title is  "+driver.getTitle());
		WebElement qualification = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//span[text()='Qualifications']")));
		qualification.click();
		WebElement language = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//a[text()='Languages']")));
		language.click();
		WebElement add = wait.until(ExpectedConditions.elementToBeClickable(
		        By.xpath("//button[normalize-space()='Add']")));
		add.click();
	  
	   }
	   @Test(priority = 1)
	   public void addLanguage() {

	       String language = "Lang_" + System.currentTimeMillis();

	       // Click Add
	       WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//button[normalize-space()='Add']")));
	       Assert.assertTrue(addBtn.isEnabled(), "Add button not clickable");
	       addBtn.click();

	       // Enter Language Name
	       WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
	               By.xpath("//label[text()='Name']/following::input[1]")));
	       Assert.assertTrue(nameField.isDisplayed(), "Name field not visible");
	       nameField.sendKeys(language);

	       // Save
	       WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//button[@type='submit']")));
	       saveBtn.click();

	       // Toast validation
	       WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	               By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	       Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	               "Language not added");

	       System.out.println(" Language Added: " + language);
	   }
	   @Test(priority = 2)
	   public void listLanguages() {

	       List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	               By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

	       Assert.assertTrue(rows.size() > 0, "No languages found");

	       System.out.println("=== LANGUAGE LIST ===");

	       for (WebElement row : rows) {
	           String lang = row.findElement(By.xpath(".//div[2]")).getText();
	           System.out.println(lang);
	       }
	   }
	   @Test(priority = 3)
	   public void editLanguage() {

	       String updatedLang = "Hindi_" + System.currentTimeMillis();

	       // Select first record
	       WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//div[@role='row'][1]//input[@type='checkbox']")));
	       checkbox.click();
	       Assert.assertTrue(checkbox.isSelected(), "Checkbox not selected");

	       // Click Edit
	       WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//button[.//i[contains(@class,'bi-pencil')]]")));
	       editBtn.click();

	       // Edit Name
	       WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
	               By.xpath("//label[text()='Name']/following::input[1]")));
	       nameField.clear();
	       nameField.sendKeys(updatedLang);

	       // Save
	       WebElement saveBtn = driver.findElement(
	               By.xpath("//button[@type='submit']"));
	       saveBtn.click();

	       // Toast validation
	       WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	               By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	       Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	               "Edit failed");

	       // Verify update
	       WebElement updatedRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
	               By.xpath("//div[@role='row'][1]//div[2]")));

	       Assert.assertTrue(updatedRow.getText().contains(updatedLang),
	               "Language not updated");

	       System.out.println("Language Edited");
	   }
	   @Test(priority = 4)
	   public void deleteLanguage() {

	       // Select record
	       WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//div[@role='row'][1]//input[@type='checkbox']")));
	       checkbox.click();

	       Assert.assertTrue(checkbox.isSelected(), "Checkbox not selected");

	       // Click Delete
	       WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//button[normalize-space()='Delete Selected']")));
	       Assert.assertTrue(deleteBtn.isEnabled(), "Delete button not enabled");
	       deleteBtn.click();

	       // Confirm delete
	       WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
	               By.xpath("//button[normalize-space()='Yes, Delete']")));
	       confirmBtn.click();

	       // Toast validation
	       WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	               By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	       Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	               "Delete failed");

	       System.out.println(" Language Deleted");
	   }
@AfterClass
public void shutup()
{
	tearDown();
}
}
