package Login;

import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Footer_links {

	public static void main(String[] args) throws InterruptedException {
		ChromeDriver driver=new ChromeDriver();
		

     
        driver.manage().window().maximize();

        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

       
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");

        String parent = driver.getWindowHandle();

        //  Links
        clickAndHandle(driver, wait, parent, "//a[contains(@href,'twitter')]");
        clickAndHandle(driver, wait, parent, "//a[contains(@href,'facebook')]");
        clickAndHandle(driver, wait, parent, "//a[contains(@href,'linkedin')]");
        clickAndHandle(driver, wait, parent, "//a[contains(@href,'youtube')]");

        //  OrangeHRM Link
        clickAndHandle(driver, wait, parent, "//a[contains(text(),'OrangeHRM')]");

        // Forgot Password - Reset
        forgotPasswordReset(driver, wait);

        // Navigate back to login page
        driver.navigate().back();

        // Forgot Password - Cancel
        forgotPasswordCancel(driver, wait);

        driver.quit();
    }

    //   method for links
    public static void clickAndHandle(WebDriver driver, WebDriverWait wait, String parent, String xpath) {

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
        element.click();

        Set<String> windows = driver.getWindowHandles();

        for (String window : windows) {
            if (!window.equals(parent)) {
                driver.switchTo().window(window);
                System.out.println("Opened: " + driver.getTitle());

                driver.close(); // close child
            }
        }

        driver.switchTo().window(parent); // back to parent
    }

    //  Forgot Password - Reset scenario
    public static void forgotPasswordReset(WebDriver driver, WebDriverWait wait) {

        WebElement forgotLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='Forgot your password?']/following-sibling::a")));
        forgotLink.click();

        WebElement username = wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));
        username.sendKeys("Admin");

        WebElement resetBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Reset Password')]")));
        resetBtn.click();

        WebElement msg = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(),'Reset password link sent')]")));

        System.out.println("Reset Message: " + msg.getText());
    }

    //  Forgot Password - Cancel scenario
    public static void forgotPasswordCancel(WebDriver driver, WebDriverWait wait) {

        WebElement forgotLink = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//p[text()='Forgot your password?']/following-sibling::a")));
        forgotLink.click();

        WebElement cancelBtn = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//button[contains(text(),'Cancel')]")));
        cancelBtn.click();

        // Validate back to login page
        WebElement loginBtn = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//button[@type='submit']")));

        if (loginBtn.isDisplayed()) {
            System.out.println("Cancel Successful - Back to Login Page ");
        } else {
            System.out.println("Cancel Failed ");
        }
    }
}