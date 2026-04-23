package Admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Organizatin extends base_setup{
   
    JavascriptExecutor js;

    @BeforeClass
    public void setup1() {
    	Organizatin o=new Organizatin() ;
    	o.setup();
		o.login("Admin","admin123");
       
    }

    @Test(priority =1)
    public void viewGeneralInformation() throws InterruptedException {
      
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='Organization']"))).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[normalize-space()='General Information']"))).click();

       
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='General Information']")));
System.out.println("All elements are loaded.");
       
    }
@Test(priority =2)
public void editgeneralinformation()
{
	  // CLICK EDIT 
    WebElement editBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[normalize-space()='Edit']")));

    Assert.assertTrue(editBtn.isDisplayed(), "Edit button not visible");
    editBtn.click();

    // COMPANY NAME 
    WebElement companyName = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//label[text()='Company Name']/following::input[1]")));

    Assert.assertTrue(companyName.isEnabled(), "Company Name not editable");

    companyName.clear();
    String newCompanyName = "OrangeHRM QA Automation Pvt Ltd";
    companyName.sendKeys(newCompanyName);

    // REGISTRATION NUMBER 
    WebElement regNumber = driver.findElement(
            By.xpath("//label[text()='Registration Number']/following::input[1]"));

    Assert.assertTrue(regNumber.isEnabled(), "Registration field not editable");

    regNumber.clear();
    regNumber.sendKeys("QA-REG-2026");

    // TAX ID 
    WebElement taxId = driver.findElement(
            By.xpath("//label[text()='Tax ID']/following::input[1]"));

    taxId.clear();
    taxId.sendKeys("TAX123456");

    // PHONE 
    WebElement phone = driver.findElement(
            By.xpath("//label[text()='Phone']/following::input[1]"));

    phone.clear();
    phone.sendKeys("+91 9876543210");

    // EMAIL 
    WebElement email = driver.findElement(
            By.xpath("//label[text()='Email']/following::input[1]"));

    email.clear();
    email.sendKeys("qa@orangehrm.com");

    // SAVE BUTTON 
    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
            By.xpath("//button[@type='submit']")));

    Assert.assertTrue(saveBtn.isEnabled(), "Save button not clickable");

    saveBtn.click();

    // TOAST VALIDATION 
    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
            By.xpath("//div[contains(@class,'oxd-toast-content')]")));

    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
            "General Information not updated");

    System.out.println(" General Information Updated Successfully");
}


    @AfterClass
    public void teardown() {
        driver.quit();
    }
}

