package Driver_setup;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class base_setup {
	  public static ChromeDriver driver;
	    public static WebDriverWait wait;
	  
	    public static Actions actions;

	
	      
	    
	   
	    
	    public void setup() {
	    	
	        driver = new ChromeDriver();
	        driver.manage().window().maximize();
	        driver.get("https://opensource-demo.orangehrmlive.com/web/index.php/auth/login");

	        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
	        actions = new Actions(driver); 
	    }

	    
	    public void login(String user, String pass) {
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username"))).sendKeys(user);
	        driver.findElement(By.name("password")).sendKeys(pass);
	        driver.findElement(By.xpath("//button[@type='submit']")).click();
	    }
	    public void tearDown() {
	        driver.quit();
	        System.out.println("Browser closed:");
	    }
	}


