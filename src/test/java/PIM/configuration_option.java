package PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class configuration_option extends base_setup{
	
@BeforeClass 
public void start()
{
	setup();
	login("Admin","admin123");
	   wait.until(ExpectedConditions.elementToBeClickable(
               By.xpath("//span[text()='PIM']")
       )).click();
}
@Test
public void testfunction()
{
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
		
		  Actions actions = new Actions(driver);
		  WebElement configMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
		          By.xpath("//span[contains(text(),'Configuration')]")));
		  actions.moveToElement(configMenu).perform();
	  WebElement optionalFields = wait.until(ExpectedConditions.elementToBeClickable(
		          By.xpath("//a[contains(text(),'Optional Fields')]")));
		  optionalFields.click();

		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(., 'SSN')]/span"))).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(., 'SIN')]/span"))).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(., 'OtherID')]/span"))).click();
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//label[contains(., 'Driver License')]/span"))).click();

		
		  wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[contains(text(),'Save')]"))).click();

}
@AfterClass
public void close() {
	tearDown();
}
}
