package Time;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Attendence_Employee_Records extends base_setup {

    @BeforeClass
    public void setupFunction() {
        setup();
        login("Admin", "admin123");

        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
        System.out.println("Dashboard Title: " + driver.getTitle());
        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");
      
        
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[normalize-space()='Attendance']"))).click();
        
        List<WebElement> elements = driver.findElements(By.xpath("//*"));

        boolean found = false;

        for (WebElement el : elements) {
            try {
                String text = el.getText().trim();

                if (text.equalsIgnoreCase("Employee Records")) {
                    System.out.println("Found Employee records menu");

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
    public void viewAllRecords() {

        By tableRows = By.xpath("//div[@role='row']");
        List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(tableRows));

        System.out.println("Total Records Displayed: " + (rows.size() - 1));
        Assert.assertTrue(rows.size() > 1, "No attendance records found");
    }

    @Test
    public void fullAttendanceFlow() throws Exception {

      
      
        WebElement nameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//input[@placeholder='Type for hints...']")));

        nameField.clear();
        nameField.sendKeys("Peter Mac Anderson");

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']"))).click();

       
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='row']")));

        System.out.println("Search by name done");


       
        WebElement dateField = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//label[text()='Date']/following::input[1]")));

        dateField.click();
      
        dateField.sendKeys(Keys.CONTROL + "a");
        dateField.sendKeys(Keys.DELETE);
        dateField.sendKeys("2026-04-08");
      
        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']"))).click();

      
        wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//div[@role='row']")));

        System.out.println("Search by date done");
        By saveBtn = By.xpath("//div[contains(@class,'orangehrm-card-container')]//button[.//span[normalize-space()='Save']]");
Thread.sleep(3000);


      
    }

    @AfterClass
    public void close() {
        tearDown();
    }
}