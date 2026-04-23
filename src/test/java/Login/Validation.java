package Login;

import java.time.Duration;

import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class Validation {

	public static void main(String[] args) {
	ChromeDriver	driver=new ChromeDriver();
	
     driver.manage().window().maximize();
     driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

  WebDriverWait   wait = new WebDriverWait(driver, Duration.ofSeconds(20));

		// TODO Auto-generated method stub
        //  Title Validation
        String actualTitle = driver.getTitle();
        String expectedTitle = "OrangeHRM";

        Assert.assertEquals(actualTitle, expectedTitle, "Title is not matching");

        // Logo Visibility
    
		WebElement logo = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//img[contains(@src,'orangehrm-logo')]")));

        Assert.assertTrue(logo.isDisplayed(), "Logo is not visible");

        // Username Textbox Clickable
        WebElement username = wait.until(ExpectedConditions.elementToBeClickable(
                By.name("username")));

        Assert.assertTrue(username.isDisplayed(), "Username textbox not visible");

        // Password Textbox Clickable
        WebElement password = wait.until(ExpectedConditions.elementToBeClickable(
                By.name("password")));

        Assert.assertTrue(password.isDisplayed(), "Password textbox not visible");

        //  5. Login Button Clickable
        WebElement loginBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[@type='submit']")));

        Assert.assertTrue(loginBtn.isDisplayed(), "Login button not visible");

        System.out.println("All Login Page Validations Passed ");
    
}
	

}
