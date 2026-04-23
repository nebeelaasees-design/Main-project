package HOME;



import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import Driver_setup.base_setup;
public class Home_page extends base_setup {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//object creation
		Home_page  home = new Home_page();
		
//calling setup() method
		 home.setup();

//calling loginmehod()   
        home.login("Admin", "admin123");

 // HOME PAGE
        home.waitForHomePage();

       

//calling search() method
        home.searchMenu("Leave");
 // calling navigation
        home.navigateTo("Admin");
        home.navigateTo("PIM");
        home.navigateTo("Leave");
        home.navigateTo("Time");
        home.navigateTo("Recruitment");
        home.navigateTo("My Info");
        home.navigateTo("Performance");
        home.navigateTo("Directory");
        home.navigateTo("Claim");
        home.navigateTo("Buzz");
      
       
        


      
//calling   Close browser method
        home.tearDown();
    }

  

 // Home page load
    public void waitForHomePage() {
        wait.until(ExpectedConditions.urlContains("dashboard"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h6[text()='Dashboard']")));
        System.out.println("Home page loaded successfully");
    }


 //search function
    public void searchMenu(String menu) {
        WebElement searchBox = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//aside//input")));
        searchBox.clear();
        searchBox.sendKeys(menu);

        wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='" + menu + "']"))).click();

        System.out.println("Clicked from search: " + menu);
    }

//navigations
    public void navigateTo(String menu) {

        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(
                By.xpath("//span[text()='" + menu + "']")));
        element.click();

 // Wait for page load
        try {
            wait.until(ExpectedConditions.urlContains(menu.toLowerCase().replace(" ", "")));
        } catch (Exception e) {
           
            try {
                wait.until(ExpectedConditions.visibilityOfElementLocated(
                        By.xpath("//h6[contains(text(),'" + menu + "')]")));
            } catch (Exception ex) {
              
            }
        }

        System.out.println("Navigated to: " + menu);
    
    }
    //DASHBOARD VALIDATION
    public void validateDashboard() {

        System.out.println("______DASHBOARD VALIDATION START _____");

        validateElement("//p[text()='Time at Work']", "Time at Work");
        validateElement("//p[text()='My Actions']", "My Actions");
        validateElement("//p[text()='Quick Launch']", "Quick Launch");
        validateElement("//p[text()='Buzz Latest Posts']", "Buzz Latest Posts");
        validateElement("//p[contains(text(),'Employees on Leave Today')]", "Employees on Leave Today");
        validateElement("//p[contains(text(),'Employee Distribution by Sub Unit')]", "Sub Unit");
        validateElement("//p[contains(text(),'Employee Distribution by Location')]", "Location");

        // Scroll
        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript("window.scrollTo(0, document.body.scrollHeight);");
        System.out.println("Scrolled to bottom ");

        js.executeScript("window.scrollTo(0, 0);");
        System.out.println("Scrolled to top ");

        System.out.println("________ DASHBOARD VALIDATION END __________");
    }

    // REUSABLE VALIDATION
    public void validateElement(String xpath, String name) {

        try {
            WebElement element = wait.until(
                    ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));

            if (element.isDisplayed()) {
                System.out.println(name + " is visible ");
            }

        } catch (Exception e) {
            System.out.println(name + " NOT visible ");
        }
    }

  
    
    }
