package PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Confiquration_Termination_Reason extends base_setup {
	@Test
	public void testfunction()
	{
		 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
			
			  Actions actions = new Actions(driver);
			  WebElement configMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
			          By.xpath("//span[contains(text(),'Configuration')]")));
			  actions.moveToElement(configMenu).perform();

			

}
	@Test
	public void Add_termination()
	{
		wait.until(ExpectedConditions.elementToBeClickable(
			    By.xpath("//a[text()='Termination Reasons']")
			)).click();

			driver.findElement(By.xpath("//button[normalize-space()='Add']")).click();

			driver.findElement(By.xpath("//label[text()='Name']/following::input[1]"))
			      .sendKeys("Resignation");

			driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
	}
	@Test
	public void EditTermination()
	{
		driver.findElement(By.xpath("(//i[contains(@class,'bi-pencil')])[1]")).click();

		WebElement reason = driver.findElement(
		    By.xpath("//label[text()='Name']/following::input[1]")
		);
		reason.clear();
		reason.sendKeys("Retirement Updated");

		driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
	}
	@Test
	public void delete()
	{
		driver.findElement(By.xpath("(//i[contains(@class,'bi-trash')])[1]")).click();

		wait.until(ExpectedConditions.elementToBeClickable(
		    By.xpath("//button[text()='Yes, Delete']")
		)).click();
	}
}