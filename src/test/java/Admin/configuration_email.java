package Admin;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class configuration_email  extends base_setup {

    @BeforeClass
    public void beforeClass() {
        setup();
        login("Admin", "admin123");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();
        System.out.println("Dashboard Title: " + driver.getTitle());


}
    
    @Test(priority=1)
    public void Test()
    {
    	   Actions actions = new Actions(driver);
    	   WebElement configurationMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
    		        By.xpath("//span[text()='Configuration']")));
    		actions.moveToElement(configurationMenu).pause(Duration.ofSeconds(1)).click().perform();

    		
    		WebElement emailSubscriptionOption = wait.until(ExpectedConditions.elementToBeClickable(
    		        By.xpath("//a[text()='Email Configuration']")));
    		emailSubscriptionOption.click();
    	 
        WebElement title = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.cssSelector(".orangehrm-main-title")));
        System.out.println("Page Title: " + title.getText());

       
        WebElement mailSentAsInput = driver.findElement(By.cssSelector("input.oxd-input"));
        mailSentAsInput.clear();
        mailSentAsInput.sendKeys("test@example.com");
        System.out.println("Mail Sent As set to: " + mailSentAsInput.getAttribute("value"));

     
        WebElement secureSmtpRadio = driver.findElement(By.xpath("//input[@value='smtps']"));
        secureSmtpRadio.click();
        System.out.println("Secure SMTP selected: " + secureSmtpRadio.isSelected());

       
        WebElement smtpRadio = driver.findElement(By.xpath("//input[@value='smtp']"));
        smtpRadio.click();
        System.out.println("SMTP selected: " + smtpRadio.isSelected());

        
        WebElement sendmailRadio = driver.findElement(By.xpath("//input[@value='sendmail']"));
        sendmailRadio.click();
        System.out.println("Sendmail selected: " + sendmailRadio.isSelected());

       
        WebElement sendTestMailCheckbox = driver.findElement(By.xpath("//input[@type='checkbox']"));
        sendTestMailCheckbox.click();
        System.out.println("Send Test Mail checkbox selected: " + sendTestMailCheckbox.isSelected());

      
        WebElement saveButton = driver.findElement(By.xpath("//button[contains(text(),'Save')]"));
        saveButton.click();
        System.out.println("Clicked Save button.");

       
       wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//p[contains(text(),'Successfully')]")));

     
        WebElement resetButton = driver.findElement(By.xpath("//button[contains(text(),'Reset')]"));
        resetButton.click();
        System.out.println("Clicked Reset button.");

 
    }
    }

