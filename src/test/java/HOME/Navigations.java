package HOME;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;

import Driver_setup.base_setup;

public class Navigations extends base_setup {
	   public static void main(String[] args) {

	        Navigations obj = new Navigations();

	        obj.setup();
	        obj.login("Admin","admin123");
	        obj.validateDashboard();

	        obj.tearDown();
	    }

	 

	  

	   
	    public void validateDashboard() {

	        //  Dashboard Heading
	        WebElement dashboard = wait.until(
	                ExpectedConditions.visibilityOfElementLocated(
	                        By.xpath("//h6[contains(text(),'Dashboard')]")));

	        Assert.assertTrue(dashboard.isDisplayed());
	        System.out.println("Dashboard visible ");

	        //  TIME AT WORK
	        validate("//p[text()='Time at Work']", "Time at Work");

	        // QUICK LAUNCH
	        validate("//p[text()='Quick Launch']", "Quick Launch");

	        // MY ACTIONS
	        validate("//p[text()='My Actions']", "My Actions");

	        listMyActions();

	        //BUZZ
	        validate("//p[text()='Buzz Latest Posts']", "Buzz Posts");

	        listBuzzPosts();

	        //  EMPLOYEE LEAVE
	        validate("//p[contains(text(),'Employees on Leave Today')]", "Leave Today");

	        // DISTRIBUTION
	        validate("//p[contains(text(),'Employee Distribution by Sub Unit')]", "Sub Unit");
	        validate("//p[contains(text(),'Employee Distribution by Location')]", "Location");

	        //  QUICK LAUNCH COUNT 
	        List<WebElement> quickBtns = driver.findElements(
	                By.xpath("//div[contains(@class,'quick-access')]//button"));

	        System.out.println("Quick Launch buttons: " + quickBtns.size());

	        for (WebElement btn : quickBtns) {
	            System.out.println("Quick Launch item clickable: " + btn.isEnabled());
	        }

	        // SCROLL 
	        JavascriptExecutor js = (JavascriptExecutor) driver;

	        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
	        System.out.println("Scrolled bottom ");

	        js.executeScript("window.scrollTo(0, 0);");
	        System.out.println("Scrolled top ");

	        System.out.println("Dashboard validation completed ");
	    }

	    //MY ACTIONS LIST =
	    public void listMyActions() {

	        try {
	            List<WebElement> actions = driver.findElements(
	                    By.xpath("//div[contains(@class,'orangehrm-todo-list')]//p | //div[contains(@class,'oxd-grid-item')]//p"));

	            System.out.println("________MY ACTIONS LIST_________");

	            if (actions.size() == 0) {
	                System.out.println("No My Actions found ");
	            }

	            for (WebElement a : actions) {
	                System.out.println("Action: " + a.getText());
	            }

	        } catch (Exception e) {
	            System.out.println("My Actions not available ");
	        }
	    }

	    // BUZZ LIST
	    public void listBuzzPosts() {

	        try {
	            List<WebElement> posts = driver.findElements(
	                    By.xpath("//div[contains(@class,'orangehrm-buzz-post')]//p"));

	            System.out.println("BUZZ POSTS LIST");

	            if (posts.size() == 0) {
	                System.out.println("No Buzz posts found ");
	            }

	            for (WebElement post : posts) {
	                System.out.println("Buzz: " + post.getText());
	            }

	        } catch (Exception e) {
	            System.out.println("Buzz posts not available ");
	        }
	    }

	    //REUSABLE VALIDATION 
	    public void validate(String xpath, String name) {

	        try {
	            WebElement element = wait.until(
	                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

	            Assert.assertTrue(element.isDisplayed());
	            System.out.println(name + " visible ");

	        } catch (Exception e) {
	            System.out.println(name + " NOT visible ");
	        }
	    }

	   
	}


