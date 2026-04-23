package Time;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Project_info_customer extends base_setup{
	
@BeforeClass
public void setupFunction() {
    setup();
    login("Admin", "admin123");

    wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
    System.out.println("Dashboard Title: " + driver.getTitle());
    Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");
    wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//span[normalize-space()='Project Info']"))).click();
    
    List<WebElement> elements = driver.findElements(By.xpath("//*"));

    boolean found = false;

    for (WebElement el : elements) {
        try {
            String text = el.getText().trim();

            if (text.equalsIgnoreCase("Customers")) {
               
                ((JavascriptExecutor) driver)
                        .executeScript("arguments[0].click();", el);

                found = true;
                break;
            }

        } catch (Exception e) {
           
        }
    }

}

@Test
public void addCustomer() {
 //ADD   
    By addBtn = By.xpath("//button[.//span[text()='Add']]");
    wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();

  
    By nameInput = By.xpath("//label[text()='Customer Name']/following::input[1]");
    wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys("Test Customer");

  
    By descInput = By.xpath("//label[text()='Description']/following::textarea[1]");
    wait.until(ExpectedConditions.visibilityOfElementLocated(descInput)).sendKeys("This is a test customer created via automation.");

  
    By saveBtn = By.xpath("//button[.//span[text()='Save']]");
    wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();
}
@Test(priority = 3)
public void editCustomer() {
   
    By editBtn = By.xpath("(//button[.//span[text()='Edit']])[1]");
    wait.until(ExpectedConditions.elementToBeClickable(editBtn)).click();

   
    By descInput = By.xpath("//label[text()='Description']/following::textarea[1]");
    WebElement descField = wait.until(ExpectedConditions.visibilityOfElementLocated(descInput));
    descField.clear();
    descField.sendKeys("Edited by automation.");

    
    By saveBtn = By.xpath("//button[.//span[text()='Save']]");
    wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

  
    By successMsg = By.cssSelector(".oxd-toast-content-text");
    String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();
    Assert.assertTrue(msg.contains("Successfully"), "Customer edit failed");
}

@Test(priority = 4)
public void deleteCustomer() {
   
    By deleteBtn = By.xpath("(//button[.//span[text()='Delete']])[1]");
    wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();

 
    By confirmDelete = By.xpath("//button[.//span[text()='Yes, Delete']]");
    wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();

  
    By successMsg = By.cssSelector(".oxd-toast-content-text");
    String msg = wait.until(ExpectedConditions.visibilityOfElementLocated(successMsg)).getText();
    Assert.assertTrue(msg.contains("Successfully"), "Customer delete failed");
}
@AfterClass
public void close() {
    tearDown();
}
}


