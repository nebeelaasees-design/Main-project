package PIM;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Configuration_custom
extends base_setup{
	 By MAIN_TITLE = By.cssSelector("h6.oxd-text--h6");
	    By FIELD_NAME_INPUT = By.cssSelector("input.oxd-input.oxd-input--active");
	    By SCREEN_SELECT = By.xpath("(//div[@class='oxd-select-text'])[1]");
	    By TYPE_SELECT = By.xpath("(//div[@class='oxd-select-text'])[2]");
	    By SAVE_BUTTON = By.xpath("//button[@type='submit']");
	    By CANCEL_BUTTON = By.xpath("//button[contains(@class,'ghost')]");

@BeforeClass 
public void start()
{
	setup();
	login("Admin","admin123");
	  
}
@Test
public void testfunction()
{
	 wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='PIM']"))).click();
		
		  Actions actions = new Actions(driver);
		  WebElement configMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
		          By.xpath("//span[contains(text(),'Configuration')]")));
		  actions.moveToElement(configMenu).perform();

		
		  WebElement customFields = wait.until(ExpectedConditions.elementToBeClickable(
		          By.xpath("//a[contains(text(),'customfield')]")));
		  customFields.click();
		 
  }
@Test
public void functionflow()
{
	//Add
	   WebElement addBtn = wait.until(ExpectedConditions.elementToBeClickable(
               By.xpath("//button[normalize-space()='Add']")));

       if (addBtn.isDisplayed()) {
           System.out.println("Add Button Clickable: PASS");
       } else {
           System.out.println("Add Button Clickable: FAIL");
       }

       addBtn.click();
       
       
       driver.findElement(By.xpath("//label[text()='Field Name']/following::input[1]"))
       .sendKeys("Blood Group");

       
       driver.findElement(By.xpath("//label[text()='Screen']/following::div[1]")).click();

       wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//span[text()='Personal Details']")
       )).click();
       
       driver.findElement(By.xpath("//label[text()='Type']/following::div[1]")).click();

       wait.until(ExpectedConditions.elementToBeClickable(
           By.xpath("//span[text()='Dropdown']")
       )).click();
       
       
       driver.findElement(By.xpath("//input[@placeholder='Type value here']"))
       .sendKeys("A+");

 driver.findElement(By.xpath("//button[text()='Add']")).click();

 driver.findElement(By.xpath("//input[@placeholder='Type value here']"))
       .sendKeys("B+");

 driver.findElement(By.xpath("//button[text()='Add']")).click();
 
 driver.findElement(By.xpath("//button[normalize-space()='Save']")).click();
 wait.until(ExpectedConditions.visibilityOfElementLocated(
		    By.xpath("//div[contains(@class,'oxd-toast-content')]")
		));
		System.out.println("Custom Field Created Successfully");
}
@Test
public void Editfunction()
{
	driver.findElement(By.xpath("(//i[contains(@class,'bi-pencil')])[1]")).click();

	WebElement field = driver.findElement(
	    By.xpath("//label[text()='Field Name']/following::input[1]")
	);
	field.clear();
	field.sendKeys("New Blood Group");

}
@Test
public void Deletefunction()
{
	driver.findElement(By.xpath("(//i[contains(@class,'bi-trash')])[1]")).click();

	wait.until(ExpectedConditions.elementToBeClickable(
	    By.xpath("//button[normalize-space()='Yes, Delete']")
	)).click();
}
@Test
public void testfunction2() throws Exception	
{
	  String title = driver.findElement(MAIN_TITLE).getText();
      Assert.assertEquals("Add Custom Field", title);

     
      WebElement fieldName = driver.findElement(FIELD_NAME_INPUT);
      Assert.assertTrue(fieldName.isDisplayed());
      fieldName.sendKeys("Test Field");

      
      Assert.assertTrue(driver.findElement(SCREEN_SELECT).isDisplayed());
      Assert.assertTrue(driver.findElement(TYPE_SELECT).isDisplayed());

     
      WebElement saveBtn = driver.findElement(SAVE_BUTTON);
      WebElement cancelBtn = driver.findElement(CANCEL_BUTTON);

      Assert.assertTrue(saveBtn.isDisplayed());
      Assert.assertTrue(cancelBtn.isDisplayed());

     
      cancelBtn.click();

      Thread.sleep(2000);
}

@AfterClass
public void close() {
	tearDown();
}
}



