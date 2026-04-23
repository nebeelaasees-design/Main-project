package Admin;


import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;


import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import Driver_setup.base_setup;

public class User extends base_setup {
	
	@BeforeClass
	public void beforeclass()
	{
		
		setup();
		login("Admin","admin123");
		
		wait.until(ExpectedConditions.elementToBeClickable(
			    By.xpath("//span[text()='Admin']")
			)).click();
		System.out.println("The Title is  "+driver.getTitle());
	}
	
	 @Test(priority = 1)
	    public void AddUser()
      { 
		
		
		 WebElement addButton = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//button[normalize-space()='Add']")));
		 Assert.assertTrue(addButton.isDisplayed() && addButton.isEnabled(), "Add button not clickable");
		 addButton.click();

		
		 WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
		         By.xpath("//h6[contains(text(),'Add User')]")));
		 Assert.assertTrue(header.isDisplayed(), "Add User page not opened");


		 
		 WebElement userRoleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')][1]")));
		 userRoleDropdown.click();

		 String expectedRole = "Admin";
		 WebElement roleOption = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//div[@role='listbox']//span[text()='" + expectedRole + "']")));
		 roleOption.click();

		 Assert.assertTrue(userRoleDropdown.getText().contains(expectedRole),
		         "User role not selected");


		
		 WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')][1]")));
		 statusDropdown.click();

		 String expectedStatus = "Enabled";
		 WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//div[@role='listbox']//span[text()='" + expectedStatus + "']")));
		 statusOption.click();

		 Assert.assertTrue(statusDropdown.getText().contains(expectedStatus),
		         "Status not selected");


	
		 WebElement empNameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
		         By.xpath("//label[text()='Employee Name']/following::input[1]")));
		 Assert.assertTrue(empNameField.isDisplayed(), "Employee field not visible");

		 String empName = "Thomas Kutty Benny";
		 empNameField.sendKeys(empName);

		
		 WebElement empSuggestion = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//div[@role='listbox']//span[text()='" + empName + "']")));
		 empSuggestion.click();


	
		 WebElement usernameField = driver.findElement(
		         By.xpath("//label[text()='Username']/following::input[1]"));

		 String username = "thomas_" + System.currentTimeMillis(); // unique username
		 usernameField.sendKeys(username);


		 WebElement passwordField = driver.findElement(
		         By.xpath("//label[text()='Password']/following::input[1]"));

		 WebElement confirmPasswordField = driver.findElement(
		         By.xpath("//label[text()='Confirm Password']/following::input[1]"));

		 String password = "Admin@123";
		 passwordField.sendKeys(password);
		 confirmPasswordField.sendKeys(password);

		 // Validate passwords match
		 Assert.assertEquals(passwordField.getAttribute("value"),
		         confirmPasswordField.getAttribute("value"),
		         "Passwords do not match");


		 // Save Button
		 WebElement saveButton = wait.until(ExpectedConditions.elementToBeClickable(
		         By.xpath("//button[normalize-space()='Save']")));
		 Assert.assertTrue(saveButton.isEnabled(), "Save button not enabled");

		 saveButton.click();


		 // Toast Message Validation 
		 WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
		         By.xpath("//div[contains(@class,'oxd-toast-content')]")));
		 Assert.assertTrue(toast.isDisplayed(), "No success toast shown");

		 String toastText = toast.getText().toLowerCase();
		 Assert.assertTrue(toastText.contains("success"),
		         "User not added successfully: " + toastText);


		 //  Verify User Created 
		 WebElement searchUsername = wait.until(ExpectedConditions.visibilityOfElementLocated(
		         By.xpath("//label[text()='Username']/following::input[1]")));
		 searchUsername.clear();
		 searchUsername.sendKeys(username);

		 driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

		 WebElement resultUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
		         By.xpath("//div[@role='row'][1]//div[2]")));

		 Assert.assertTrue(resultUser.getText().contains(username),
		         "User not found after creation");

		 System.out.println(" User Added & Validated Successfully");
      }

  
	    @Test(priority = 2)
	    public void searchUser()
	    {
	    	String expectedUsername = "thomas_test";
	    	String expectedRole = "Admin";
	    	String expectedStatus = "Enabled";
	    	String expectedEmployee = "Thomas Kutty Benny";


	    	//  Username 
	    	WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("//label[text()='Username']/following::input[1]")));
	    	Assert.assertTrue(usernameField.isDisplayed(), "Username field not visible");
	    	usernameField.clear();
	    	usernameField.sendKeys(expectedUsername);


	    	//User Role 
	    	WebElement roleDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//label[text()='User Role']/following::div[contains(@class,'oxd-select-text')]")));
	    	Assert.assertTrue(roleDropdown.isDisplayed(), "Role dropdown missing");
	    	roleDropdown.click();

	    	WebElement roleOption = wait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//div[@role='listbox']//span[text()='" + expectedRole + "']")));
	    	roleOption.click();

	    	// Validate selection
	    	Assert.assertTrue(roleDropdown.getText().contains(expectedRole),
	    	        "Role not selected");


	    	// Employee Name
	    	WebElement empField = wait.until(ExpectedConditions.visibilityOfElementLocated(
	    	        By.xpath("//label[text()='Employee Name']/following::input[1]")));
	    	Assert.assertTrue(empField.isDisplayed(), "Employee field missing");
	    	empField.sendKeys(expectedEmployee);

	    	
	    	WebElement empOption = wait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//div[@role='listbox']//span[text()='" + expectedEmployee + "']")));
	    	empOption.click();


	    	// Status
	    	WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')]")));
	    	Assert.assertTrue(statusDropdown.isDisplayed(), "Status dropdown missing");
	    	statusDropdown.click();

	    	WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//div[@role='listbox']//span[text()='" + expectedStatus + "']")));
	    	statusOption.click();

	    	Assert.assertTrue(statusDropdown.getText().contains(expectedStatus),
	    	        "Status not selected");


	    	//  Search Button
	    	WebElement searchBtn = wait.until(ExpectedConditions.elementToBeClickable(
	    	        By.xpath("//button[normalize-space()='Search']")));
	    	Assert.assertTrue(searchBtn.isEnabled(), "Search button disabled");
	    	searchBtn.click();


	    	// RESULT VALIDATION 
	    	List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
	    	        By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

	    	Assert.assertTrue(rows.size() > 0, "No records found");

	    	for (WebElement row : rows) {

	    	    String actualUsername = row.findElement(By.xpath(".//div[2]")).getText();
	    	    String actualRole = row.findElement(By.xpath(".//div[3]")).getText();
	    	    String actualEmployee = row.findElement(By.xpath(".//div[4]")).getText();
	    	    String actualStatus = row.findElement(By.xpath(".//div[5]")).getText();

	    	    Assert.assertTrue(actualUsername.contains(expectedUsername),
	    	            "Username mismatch: " + actualUsername);

	    	    Assert.assertTrue(actualRole.contains(expectedRole),
	    	            "Role mismatch: " + actualRole);

	    	    Assert.assertTrue(actualEmployee.contains(expectedEmployee),
	    	            "Employee mismatch: " + actualEmployee);

	    	    Assert.assertTrue(actualStatus.contains(expectedStatus),
	    	            "Status mismatch: " + actualStatus);
	    	}

	    	System.out.println(" User Search Validation Passed");
	     
	        
	    }
	  
	@Test
	public void viewdata()
	{

		// Wait for rows
		List<WebElement> rows = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
		        By.xpath("//div[@class='oxd-table-body']//div[@role='row']")));

		List<String> userList = new ArrayList<>();

		for (WebElement row : rows) {
		    String rowText = row.getText(); // full row text
		    userList.add(rowText);
		}

		// Print
		System.out.println("____-USER DETAILS_____");
		for (String user : userList) {
		    System.out.println(user);
		}
	}

	@Test
	public void editUserTest() {

	    String username = "thomas_test";  
	    String updatedStatus = "Disabled";

	    //  Search user
	    WebElement searchUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//label[text()='Username']/following::input[1]")));
	    searchUser.clear();
	    searchUser.sendKeys(username);

	    driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

	    // Validate result present
	    WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[@role='row'][1]")));
	    Assert.assertTrue(row.isDisplayed(), "User not found");

	    //  Click Edit
	    WebElement editBtn = row.findElement(By.xpath(".//i[contains(@class,'bi-pencil')]"));
	    Assert.assertTrue(editBtn.isDisplayed(), "Edit button missing");
	    editBtn.click();

	    // Validate Edit page
	    WebElement header = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//h6[contains(text(),'Edit User')]")));
	    Assert.assertTrue(header.isDisplayed(), "Edit page not opened");

	    // Change Status
	    WebElement statusDropdown = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//label[text()='Status']/following::div[contains(@class,'oxd-select-text')]")));
	    statusDropdown.click();

	    WebElement statusOption = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//div[@role='listbox']//span[text()='" + updatedStatus + "']")));
	    statusOption.click();

	    // Validate selection
	    Assert.assertTrue(statusDropdown.getText().contains(updatedStatus),
	            "Status not updated");

	    // Save
	    WebElement saveBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[@type='submit']")));
	    Assert.assertTrue(saveBtn.isEnabled(), "Save button not enabled");
	    saveBtn.click();

	    //  Toast validation
	    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	            "Edit failed");

	    // Verify updated value
	    searchUser.clear();
	    searchUser.sendKeys(username);
	    driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

	    WebElement statusCell = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[@role='row'][1]//div[5]")));

	    Assert.assertTrue(statusCell.getText().contains(updatedStatus),
	            "Updated status not reflected");

	    System.out.println(" User Edited Successfully");
	}
	
	@Test
	public void delete()
	{
		
	   String username = "thomas_test";

	    // Search user
	    WebElement searchUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//label[text()='Username']/following::input[1]")));
	    searchUser.clear();
	    searchUser.sendKeys(username);

	    driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

	    // Validate result
	    WebElement row = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[@role='row'][1]")));
	    Assert.assertTrue(row.isDisplayed(), "User not found");

	    //  Select checkbox
	    WebElement checkbox = row.findElement(By.xpath(".//input[@type='checkbox']"));
	    checkbox.click();

	    Assert.assertTrue(checkbox.isSelected(), "Checkbox not selected");

	    // Delete button
	    WebElement deleteBtn = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Delete Selected']")));
	    Assert.assertTrue(deleteBtn.isEnabled(), "Delete button not enabled");
	    deleteBtn.click();

	    // Confirm popup
	    WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'oxd-dialog-container')]")));
	    Assert.assertTrue(popup.isDisplayed(), "Delete popup not shown");

	    WebElement confirm = wait.until(ExpectedConditions.elementToBeClickable(
	            By.xpath("//button[normalize-space()='Yes, Delete']")));
	    confirm.click();

	    //  Toast validation
	    WebElement toast = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//div[contains(@class,'oxd-toast-content')]")));
	    Assert.assertTrue(toast.getText().toLowerCase().contains("success"),
	            "Delete failed");

	    //  Verify user deleted
	    searchUser = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//label[text()='Username']/following::input[1]")));
	    searchUser.clear();
	    searchUser.sendKeys(username);

	    driver.findElement(By.xpath("//button[normalize-space()='Search']")).click();

	    WebElement noRecord = wait.until(ExpectedConditions.visibilityOfElementLocated(
	            By.xpath("//span[text()='No Records Found']")));

	    Assert.assertTrue(noRecord.isDisplayed(), "User still exists after delete");

	    System.out.println(" User Deleted Successfully");
	}
        @AfterClass
        public void close()
        {
        	tearDown();
    }

}
    
	


        
               