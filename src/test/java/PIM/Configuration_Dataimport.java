package PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import Driver_setup.base_setup;

public class Configuration_Dataimport extends base_setup
{
	@BeforeClass 
	public void start()
	{
		setup();
		login("Admin","admin123");
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
		  Actions actions = new Actions(driver);
		  WebElement configMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
		          By.xpath("//span[contains(text(),'Configuration')]")));
		  actions.moveToElement(configMenu).perform();

		
		  WebElement dataimport  = wait.until(ExpectedConditions.elementToBeClickable(
		          By.xpath("driver.findElement(By.xpath(\"//a[text()='Data Import']\")).click();")));
		  dataimport.click();
		// Upload file
		  driver.findElement(By.xpath("//input[@type='file']"))
		        .sendKeys("C:\\Users\\Test\\employees.csv");

		  // Click Upload
		  driver.findElement(By.xpath("//button[normalize-space()='Upload']")).click();

		  // Validate success message
		  wait.until(ExpectedConditions.visibilityOfElementLocated(
		      By.xpath("//div[contains(@class,'toast')]")));
	}
@AfterClass
public void close()
{
	tearDown();
}
}
