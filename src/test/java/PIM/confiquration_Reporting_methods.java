package PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class confiquration_Reporting_methods extends base_setup {
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

		  wait.until(ExpectedConditions.elementToBeClickable(
				    By.xpath("//a[text()='Reporting Methods']")
				)).click();

}
	
	@Test
	public void testfunction()
	{//add 
		driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();

		driver.findElement(By.xpath("//label[text()='Name']/following::input[1]"))
		      .sendKeys("Direct");

		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
	//Edit
		driver.findElement(By.xpath("(//i[contains(@class,'bi-pencil')])[1]")).click();

		WebElement name = driver.findElement(
		    By.xpath("//label[text()='Name']/following::input[1]")
		);
		name.clear();
		name.sendKeys("New Report");

		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
	//delete
		driver.findElement(By.xpath("(//i[contains(@class,'bi-trash')])[1]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[text()='Yes, Delete']")
		)).click();
	}
}
