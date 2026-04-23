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

public class Project_info_project extends base_setup {

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

                if (text.equalsIgnoreCase("Projects")) {
                   
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
    public void addProject() {
      
        By addBtn = By.xpath("//button[.//span[text()='Add']]");
        wait.until(ExpectedConditions.elementToBeClickable(addBtn)).click();

      
        By nameInput = By.xpath("//label[text()='Project Name']/following::input[1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys("Automation Project");

        By descInput = By.xpath("//label[text()='Description']/following::textarea[1]");
        wait.until(ExpectedConditions.visibilityOfElementLocated(descInput)).sendKeys("Project created via automation testing.");

       
        By saveBtn = By.xpath("//button[.//span[text()='Save']]");
        wait.until(ExpectedConditions.elementToBeClickable(saveBtn)).click();

    }

    @Test(priority = 2)
    public void searchProject() {
        
        By searchInput = By.xpath("//input[@placeholder='Type for hints...']");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(searchInput));
        input.clear();
        input.sendKeys("Automation Project");

        By searchBtn = By.xpath("//button[.//span[text()='Search']]");
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

      
    }
    @Test(priority = 3)
    public void searchProjectByCustomer() {
        By customerSearch = By.xpath("//label[text()='Customer']/following::input[1]");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(customerSearch));
        input.clear();
        input.sendKeys("Test Customer");

        By searchBtn = By.xpath("//button[.//span[text()='Search']]");
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

        By firstRowCustomer = By.xpath("(//div[contains(@class,'oxd-table-row')])[1]//div[contains(@class,'oxd-table-cell')][2]");
        String name = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowCustomer)).getText();
        Assert.assertEquals(name, "Test Customer", "Customer search failed");
    }
    @Test(priority = 4)
    public void searchProjectByAdmin() {
        By adminSearch = By.xpath("//label[text()='Project Admin']/following::input[1]");
        WebElement input = wait.until(ExpectedConditions.visibilityOfElementLocated(adminSearch));
        input.clear();
        input.sendKeys("Peter Anderson");

        By searchBtn = By.xpath("//button[.//span[text()='Search']]");
        wait.until(ExpectedConditions.elementToBeClickable(searchBtn)).click();

        By firstRowAdmin = By.xpath("(//div[contains(@class,'oxd-table-row')])[1]//div[contains(@class,'oxd-table-cell')][4]");
        String admin = wait.until(ExpectedConditions.visibilityOfElementLocated(firstRowAdmin)).getText();
        Assert.assertEquals(admin, "Peter Anderson", "Project Admin search failed");
    }
    @Test(priority = 5)
    public void editProject() {
       
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
        Assert.assertTrue(msg.contains("Successfully"), "Project edit failed");
    }

    @Test(priority = 6)
    public void deleteProject() {
       
        By deleteBtn = By.xpath("(//button[.//span[text()='Delete']])[1]");
        wait.until(ExpectedConditions.elementToBeClickable(deleteBtn)).click();

      
        By confirmDelete = By.xpath("//button[.//span[text()='Yes, Delete']]");
        wait.until(ExpectedConditions.elementToBeClickable(confirmDelete)).click();

      
       
    }

    @AfterClass
    public void close() {
        tearDown();
    }
}



