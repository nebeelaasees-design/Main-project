package Admin;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;

import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Nationalities extends base_setup{
  
	 @BeforeClass
	    public void setupmethod() {
		 Nationalities n=new Nationalities();
		 n.setup();
		 n.login("Admin", "admin123");;
		   wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();
		   WebElement nationalities = wait.until(ExpectedConditions.elementToBeClickable(
			        By.xpath("//a[contains(text(),'Nationalities')]")));

			nationalities.click();

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(.,'Add')]"))).click();
}

@Test(priority =1)
public void addNationalityTest() {

   
    
	   String nationality = "QATestNation_" + System.currentTimeMillis();

	    // Click Add
	    WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Add']")));
	    Assert.assertTrue(addBtn.isEnabled(), "Add button not clickable");
	    addBtn.click();

	    // Enter nationality
	    WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//label[text()='Name']/following::input[1]")));
	    Assert.assertTrue(nameField.isDisplayed(), "Name field not visible");
	    nameField.sendKeys(nationality);

	    // Save
	    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[@type='submit']")));
	    saveBtn.click();

	    // Toast validation
	    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	            "Nationality not added");

	    System.out.println("Nationality Added: " + nationality);
	}

 

@Test(priority = 2)
public void listNationalities() {

    List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

    Assert.assertTrue(rows.size() > 0, "No nationalities found");

    System.out.println("=== NATIONALITIES LIST ===");

    for (WebElement row : rows) {
        String name = row.findElement(By.xpath(".//div[2]")).getText();
        System.out.println(name);
    }
}
@Test(priority = 3)
public void editNationality() {

    String updatedName = "Turkey_" + System.currentTimeMillis();

    // Select first record checkbox
    WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//div[@role='row'][1]//input[@type='checkbox']")));
    checkbox.click();
    Assert.assertTrue(checkbox.isSelected(), "Checkbox not selected");

    // Click Edit
    WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[.//i[contains(@class,'bi-pencil')]]")));
    editBtn.click();

    // Edit field
    WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[text()='Name']/following::input[1]")));
    nameField.clear();
    nameField.sendKeys(updatedName);

    // Save
    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@type='submit']")));
    saveBtn.click();

    // Toast validation
    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'oxd-toast-content')]")));
    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
            "Edit failed");

    // Verify update
    WebElement updatedRow = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[@role='row'][1]//div[2]")));

    Assert.assertTrue(updatedRow.getText().contains(updatedName),
            "Nationality not updated");

    System.out.println(" Nationality Edited");
}

@Test(priority = 4)
public void deleteNationality() {

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

    // Confirm delete
    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[normalize-space()='Yes, Delete']")));
    confirmBtn.click();

    // Toast validation
    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'oxd-toast-content')]")));
    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
            "Delete failed");

    System.out.println(" Nationality Deleted");
}
@AfterMethod
public void tearDown() {
    driver.quit();
}
}


