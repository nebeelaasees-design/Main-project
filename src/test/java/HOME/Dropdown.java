package HOME;
import java.util.Set;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Driver_setup.base_setup;
public class Dropdown extends base_setup {

  
 public static void main(String[] args) throws InterruptedException {
  Dropdown page = new Dropdown();
   page.setup(); 
   page.login("Admin", "admin123");

       

 // Wait for loading homepage
  
   page.wait.until(ExpectedConditions.visibilityOfElementLocated(
           By.xpath("//h6[text()='Dashboard']")));
   System.out.println("Home page loaded successfully");
   WebElement dashboardHeading = wait.until(
	        ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//h6[text()='Dashboard']")));

	Assert.assertTrue(dashboardHeading.isDisplayed(), "Dashboard heading is not visible");

	System.out.println("Dashboard heading is visible ");
 

// calling functions
  
   page.clickUpgradeButton();
   page.clickMyInfo();
   page.helpIcon();
   page.support();
   page.changePassword();
   page.sideButton();
   page.logout();

   page.tearDown();
}

// UPGRADE
public void clickUpgradeButton() {

   String parentWindow = driver.getWindowHandle();

   WebElement upgradeBtn = wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//a[contains(@href,'upgrade')]//button")));
   upgradeBtn.click();

   Set<String> windows = driver.getWindowHandles();

   for (String win : windows) {
       if (!win.equals(parentWindow)) {
           driver.switchTo().window(win);
           System.out.println("Switched to Upgrade page: " + driver.getTitle());
           driver.close();
       }
   }

   driver.switchTo().window(parentWindow);
}

//  MY INFO 
public void clickMyInfo() {

   openUserDropdown();

   WebElement myInfo = wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//a[normalize-space()='My Info']")));

   myInfo.click();

   System.out.println("Clicked My Info");

   driver.navigate().back();
}



//  SUPPORT
public void support() {

   WebElement support = wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//a[contains(text(),'Support')] | //button[contains(text(),'Support')]")));

   Assert.assertTrue(support.isDisplayed(), "Support not visible");

   support.click();

   System.out.println("Support clicked");

   driver.navigate().back();
}

//  CHANGE PASSWORD 
public void changePassword() {

   openUserDropdown();

   WebElement changePwd = wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//a[text()='Change Password']")));

   changePwd.click();

   System.out.println("Change Password clicked");

   driver.navigate().back();
}

// SIDE MENU 
public void sideButton() {

   WebElement sb = wait.until(ExpectedConditions.elementToBeClickable(
           By.cssSelector(".oxd-icon-button.oxd-main-menu-button")));

   Assert.assertTrue(sb.isDisplayed(), "Side menu not visible");

   sb.click();

   System.out.println("Side menu clicked");
}

//  LOGOUT 
public void logout() {

   openUserDropdown();

   WebElement logout = wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//a[text()='Logout']")));

   logout.click();

   System.out.println("Logged out");
}

//  USER DROPDOWN
public void openUserDropdown() {

   WebElement dropdown = wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//span[contains(@class,'oxd-userdropdown-tab')]")));

   dropdown.click();
}
//HELP ICON
public void helpIcon() {

WebElement help = wait.until(ExpectedConditions.elementToBeClickable(
       By.xpath("//button[@title='Help']")));

Assert.assertTrue(help.isDisplayed(), "Help icon not visible");

help.click();

System.out.println("Help icon clicked");

driver.navigate().back();
}

}