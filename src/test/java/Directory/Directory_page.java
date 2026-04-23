package Directory;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import Driver_setup.base_setup;

public class Directory_page extends  base_setup{

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Directory_page dp=new Directory_page();
		dp.setup();
		dp.login("Admin","admin123");
		
	   
wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Directory']"))).click();

       
wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//h6[text()='Directory']")));

        System.out.println("Directory page opened");

//search by name       
        WebElement searchBox = wait.until(ExpectedConditions.visibilityOfElementLocated( By.xpath("//input[@placeholder='Type for hints...']")));

        searchBox.sendKeys("Ravi M B");

//search       
        driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
        Thread.sleep(3000);
//reset
        driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]")).click();
       Thread.sleep(3000);
       
// search by title
       wait.until(ExpectedConditions.elementToBeClickable( By.xpath("(//div[contains(@class,'oxd-select-text')])[1]"))).click();

//selecting job from dropdown
       wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//span[text()='HR Manager']"))).click();

// Search
       driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
       Thread.sleep(3000);
//reset
       driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]")).click();
       Thread.sleep(3000);
       
//search by title 2
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//div[contains(@class,'oxd-select-text')])[1]"))).click();
       
// Select job from dropdown
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Chief Financial Officer']"))).click();
//search
       driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
       Thread.sleep(3000);
//reset
       driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]")).click();
       Thread.sleep(3000);
       
 // search by Location dropdown 
       WebElement locationDropdown = wait.until(ExpectedConditions.elementToBeClickable( By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[1]/div/div[3]/div/div[2]/div/div/div[1]")));

       locationDropdown.click();

// Select Location from dropdown
       WebElement locationOption = wait.until(ExpectedConditions.elementToBeClickable(
               By.xpath("//div[@role='listbox']//span[text()='New York Sales Office']")));

       locationOption.click();

//serch
      driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
       Thread.sleep(2000);
//reset
       driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[2]/form/div[2]/button[1]")).click();
       Thread.sleep(3000);

       
 //search all together
     
      WebElement searchBox3 = wait.until(ExpectedConditions.elementToBeClickable(
               By.xpath("//input[contains(@placeholder,'Type for hints')]")
       ));
     searchBox.click();
       searchBox3.sendKeys("Ravi M B");

    // Select job from dropdown
       wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[text()='Chief Financial Officer']"))).click();
//search
       driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();
       Thread.sleep(3000);

      
      
      

       WebElement locationOption2 = wait.until(ExpectedConditions.visibilityOfElementLocated(
               By.xpath("//div[@role='listbox']//span[text()='New York Sales Office']")
       ));
      locationOption2.click();


    
       WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
               By.xpath("//button[normalize-space()='Search']")
       ));
       searchBtn.click();


     
       WebElement resetBtn = wait.until(ExpectedConditions.elementToBeClickable(
               By.xpath("//button[normalize-space()='Reset']")
       ));
       resetBtn.click();
     //sidebutton
       driver.findElement(By.xpath("//*[@id=\"app\"]/div[1]/div[2]/div[2]/div/div[1]/div[1]/div[2]/div[3]/button/i")).click();
//Records
       List<WebElement> cards = driver.findElements(
    	        By.cssSelector(".orangehrm-directory-card"));

    	for (WebElement card : cards) {

    	    String name = card.findElement(
    	            By.cssSelector(".orangehrm-directory-card-header")).getText();

    	    System.out.println("Employee Name: " + name);
    	}
       
      
        dp.tearDown();
	}

}
