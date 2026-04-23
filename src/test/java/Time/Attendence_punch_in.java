package Time;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class Attendence_punch_in  extends base_setup {
	 @BeforeClass
	    public void setupFunction() {
	        setup();
	        login("Admin","admin123");
	        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Time']"))).click();
	        System.out.println("Dashboard Title: " + driver.getTitle());
	        Assert.assertTrue(driver.getTitle().contains("OrangeHRM"), "Dashboard title is incorrect");
	   
	        Actions actions = new Actions(driver);

	        WebElement timeMenu = wait.until(ExpectedConditions.visibilityOfElementLocated(
	                By.xpath("//span[text()='Time']")));
	        actions.moveToElement(timeMenu).perform();

	      
	        wait.until(ExpectedConditions.elementToBeClickable(
	                By.xpath("//span[normalize-space()='Attendance']"))).click();
	        
	        List<WebElement> elements = driver.findElements(By.xpath("//*"));

	        boolean found = false;

	        for (WebElement el : elements) {
	            try {
	                String text = el.getText().trim();

	                if (text.equalsIgnoreCase("Punch In/Out")) {
	                    System.out.println("Found punch in/out menu");

	                    ((JavascriptExecutor) driver)
	                            .executeScript("arguments[0].click();", el);

	                    found = true;
	                    break;
	                }

	            } catch (Exception e) {
	               
	            }
	        }
	    }
	    
    

 @Test
 public void punchInAndOut() throws Exception {
	 wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName("body")));

	JavascriptExecutor js = (JavascriptExecutor) driver;
	js.executeScript("window.scrollTo(0, document.body.scrollHeight);");


	By punchInBtn = By.xpath("//button[.//span[contains(text(),'Punch In')]]");

	WebElement punchIn = wait.until(ExpectedConditions.elementToBeClickable(punchInBtn));
	punchIn.click();


	By noteField = By.xpath("//div[contains(@class,'oxd-dialog')]//textarea");

	WebElement note = wait.until(ExpectedConditions.elementToBeClickable(noteField));
	note.click();
	note.clear();
	note.sendKeys("Starting work");

	
	By confirmIn = By.xpath("//div[contains(@class,'oxd-dialog')]//button[.//span[text()='In']]");

	WebElement inBtn = wait.until(ExpectedConditions.elementToBeClickable(confirmIn));
	inBtn.click();
	 Thread.sleep(3000);


	
	
	 By punchOutBtn = By.xpath("//button[.//span[text()='Punch Out']]");
	 wait.until(ExpectedConditions.elementToBeClickable(punchOutBtn)).click();

	
	 WebElement note2 = wait.until(ExpectedConditions.visibilityOfElementLocated(noteField));
	 note2.clear();
	 note2.sendKeys("Ending work");

	
	 By timeInput = By.xpath("//input[@placeholder='hh:mm']");
	 WebElement outTimeField = wait.until(ExpectedConditions.elementToBeClickable(timeInput));

	
	
	 
	 By confirmPunchOut = By.xpath("//button[.//span[text()='Out']]");
	 wait.until(ExpectedConditions.elementToBeClickable(confirmPunchOut)).click();

	
 }
	  @AfterClass
	    public void close() {
	        tearDown();
	    }
	}

