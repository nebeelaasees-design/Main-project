package Login;


	import Driver_setup.base_setup;
	import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;

	public class LoginTest extends base_setup {

	    public static void main(String[] args) throws Exception {
	       
	      
	        
	        base_setup setup = new base_setup();
	        setup.setup();  

	        // Optional: configure ChromeOptions to disable password warnings
	        ChromeOptions options = new ChromeOptions();
	        options.addArguments("--disable-notifications");
	        options.addArguments("--disable-save-password-bubble");
	        options.addArguments("--disable-infobars");
	        options.addArguments("--start-maximized");

	     

	       
	        ExcelUtils excel = new ExcelUtils("D:\\Testing\\oranhehrm_credentials.xlsx", "Sheet1");
	        int rows = excel.getRowCount();

	        for (int i = 1; i < rows; i++) {
	            String username = excel.getCellData(i, 0);
	            String password = excel.getCellData(i, 1);

	            System.out.println("\nTrying login: " + username + " / " + password);
	            setup.login(username, password);

	            
	            try {
	                setup.wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
	                System.out.println("Login SUCCESS for: " + username + " / " + password);

	                
	                setup.driver.findElement(By.xpath("//span[contains(@class,'oxd-userdropdown-tab')]")).click();
	                setup.driver.findElement(By.xpath("//ul[contains(@class,'oxd-dropdown-menu')]//a[normalize-space()='Logout']")).click();

	               
	                setup.wait.until(ExpectedConditions.visibilityOfElementLocated(By.name("username")));

	            } catch (Exception e) {
	                System.out.println("Login FAILED for: " + username + " / " + password);
	            }
	        }

	       
	        setup.tearDown();
	    }
	}