package PIM;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Edit_report extends base_setup {
	
	   @BeforeClass
	    public void setupDriver() {
	        setup();             
	        login("Admin", "admin123");

	        // Navigate to PIM → Reports
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Reports']"))).click();
	    }
@Test
public void test()
{
  
    //SEARCH REPORT
   
    WebElement searchInput = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@placeholder='Type for hints...']")));
    searchInput.clear();
    searchInput.sendKeys("Employee Contact info report");

    driver.findElement(By.xpath("//button[contains(@class,'oxd-button--secondary')]")).click();

 
    // CLICK EDIT BUTTON
   
    WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//i[contains(@class,'bi-pencil-fill')]")));

    if (editBtn.isDisplayed()) {
        System.out.println("Edit Button Clickable: PASS");
    } else {
        System.out.println("Edit Button Clickable: FAIL");
    }

    editBtn.click();

    // Wait for Edit page
    wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//h6[contains(text(),'Edit Report')]")));

    
    // EDIT REPORT NAME
    
    WebElement nameField = driver.findElement(
            By.xpath("//label[text()='Report Name']/following::input[1]"));

    nameField.clear();
    nameField.sendKeys(Keys.TAB);

    // Validation (empty)
    try {
        WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//span[contains(@class,'oxd-input-field-error-message')]")));
        System.out.println("Report Name Required Validation: PASS");
    } catch (Exception e) {
        System.out.println("Report Name Required Validation: FAIL");
    }

   
    nameField.sendKeys("Employee new report");

  
    // EDIT SELECTION CRITERIA
    
    try {
        WebElement criteria = driver.findElement(
                By.xpath("//label[text()='Selection Criteria']/following::div[contains(@class,'oxd-select-text')]"));

        criteria.click();
        driver.findElement(By.xpath("//span[text()='Employee Id']")).click();

        System.out.println("Selection Criteria Edit: PASS");
    } catch (Exception e) {
        System.out.println("Selection Criteria Edit: FAIL");
    }

   
    //  EDIT DISPLAY FIELD GROUP
    //
    try {
        WebElement group = driver.findElement(
                By.xpath("//label[text()='Display Field Group']/following::div[contains(@class,'oxd-select-text')]"));

        group.click();
        driver.findElement(By.xpath("//span[text()='Job']")).click();

        System.out.println("Display Field Group Edit: PASS");
    } catch (Exception e) {
        System.out.println("Display Field Group Edit: FAIL");
    }

    
    //  EDIT DISPLAY FIELD
    
    try {
        WebElement field = driver.findElement(
                By.xpath("//label[text()='Display Field']/following::div[contains(@class,'oxd-select-text')]"));

        field.click();
        driver.findElement(By.xpath("//span[text()='Job Title']")).click();

        System.out.println("Display Field Edit: PASS");
    } catch (Exception e) {
        System.out.println("Display Field Edit: FAIL");
    }

    // Click ADD button
    driver.findElement(By.xpath("(//button[normalize-space()='Add'])[2]")).click();

   
    //  SAVE BUTTON VALIDATION
 
    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@type='submit']")));

    if (saveBtn.isDisplayed()) {
        System.out.println("Save Button Clickable: PASS");
    } else {
        System.out.println("Save Button Clickable: FAIL");
    }

    saveBtn.click();

  
    //TOAST VALIDATION
   
    try {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast-content')]")));

        System.out.println("Update Toast: PASS → " + toast.getText());
    } catch (Exception e) {
        System.out.println("Update Toast: FAIL");
    }

    
    //  VERIFY UPDATED RECORD IN LIST
 
    searchInput = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@placeholder='Type for hints...']")));
    searchInput.clear();
    searchInput.sendKeys("Employee new report");

    driver.findElement(By.xpath("//button[contains(@class,'oxd-button--secondary')]")).click();

    List<WebElement> results = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
            By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

    if (results.size() > 0) {
        System.out.println("Updated Report Found: PASS");
    } else {
        System.out.println("Updated Report Found: FAIL");
    }
}

//  DELETE REPORT VALIDATION

@Test
public void deleteReport() {

    // Navigate back to Reports page
    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Reports']"))).click();

    // Search report
    WebElement search = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@placeholder='Type for hints...']")));
    search.clear();
    search.sendKeys("Employee Job Details");

    driver.findElement(By.xpath("//button[contains(@class,'oxd-button--secondary')]")).click();

    // Click DELETE icon
    WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//i[contains(@class,'bi-trash')]")));

    if (deleteBtn.isDisplayed()) {
        System.out.println("Delete Button Clickable: PASS");
    } else {
        System.out.println("Delete Button Clickable: FAIL");
    }

    deleteBtn.click();

    // Confirm delete popup
    WebElement confirmBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[normalize-space()='Yes, Delete']")));

    confirmBtn.click();

    
    try {
        WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[contains(@class,'oxd-toast-content')]")));

        System.out.println("Delete Toast: PASS → " + toast.getText());
    } catch (Exception e) {
        System.out.println("Delete Toast: FAIL");
    }

    // Verify record deleted
    search = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@placeholder='Type for hints...']")));
    search.clear();
    search.sendKeys("Employee Job Details");

    driver.findElement(By.xpath("//button[contains(@class,'oxd-button--secondary')]")).click();

    List<WebElement> results = driver.findElements(
            By.xpath("//div[@class='oxd-table-body']//div[@role='row']"));

    if (results.size() == 0) {
        System.out.println("Report Deleted Successfully: PASS");
    } else {
        System.out.println("Report Deleted Successfully: FAIL");
    }
}

//RUN REPORT VALIDATION

@Test
public void runReport() {

    // Search report
    WebElement search = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//input[@placeholder='Type for hints...']")));
    search.clear();
    search.sendKeys( "PIM Sample Report");

    driver.findElement(By.xpath("//button[contains(@class,'oxd-button--secondary')]")).click();

    // Click RUN button
    WebElement runBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[normalize-space()='Run']")));

    if (runBtn.isDisplayed()) {
        System.out.println("Run Button Clickable: PASS");
    } else {
        System.out.println("Run Button Clickable: FAIL");
    }

    runBtn.click();

}
	

@AfterClass
public void close()
{
	tearDown();
}
}
