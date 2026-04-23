package Admin;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class CONFIGURATION_EMAILSUBSCRIPTIONS  extends base_setup {

    @BeforeClass
    public void beforeClass() {
        setup();
        login("Admin", "admin123");
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Admin']"))).click();
        System.out.println("Dashboard Title: " + driver.getTitle());


}
    
    @Test(priority=1)
    public void Test2()
    {
    	   Actions actions = new Actions(driver);
    	   WebElement configurationMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
    		        By.xpath("//span[text()='Configuration']")));
    		actions.moveToElement(configurationMenu).pause(Duration.ofSeconds(1)).click().perform();

    		// Click Email Subscriptions
    		WebElement emailSubscriptionOption = wait.until(ExpectedConditions.elementToBeClickable(
    		        By.xpath("//a[text()='Email Subscriptions']")));
    		emailSubscriptionOption.click();
       
    
        System.out.println("Successfully navigated to Email Subscriptions page.");

    }
    @Test
    public void test() throws Exception
    {
    	  WebElement notificationTypeHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                  By.xpath("//div[contains(text(),'Notification Type')]")));
          WebElement subscribersHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                  By.xpath("//div[contains(text(),'Subscribers')]")));
          WebElement actionsHeader = wait.until(ExpectedConditions.visibilityOfElementLocated(
                  By.xpath("//div[contains(text(),'Actions')]")));

          System.out.println("Headers Loaded:");
          System.out.println(notificationTypeHeader.getText());
          System.out.println(subscribersHeader.getText());
          System.out.println(actionsHeader.getText());

      
          String[] rows = {
                  "Leave Applications",
                  "Leave Approvals",
                  "Leave Assignments",
                  "Leave Cancellations",
                  "Leave Rejections"
          };

          for (String rowName : rows) {
              WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
                      By.xpath("//div[contains(text(),'" + rowName + "')]")));
              System.out.println("Row visible: " + row.getText());
          }

         
          List<WebElement> actionButtons = wait.until(ExpectedConditions.numberOfElementsToBeMoreThan(
                  By.cssSelector(".oxd-icon-button"), 0));

          for (int i = 0; i < actionButtons.size(); i++) {
              WebElement button = wait.until(ExpectedConditions.elementToBeClickable(actionButtons.get(i)));
              System.out.println("Clicking action button for row: " + rows[i]);
              button.click();
              Thread.sleep(1000); 
          }

          System.out.println("All headers, rows, and buttons tested successfully ✅");

    }
}