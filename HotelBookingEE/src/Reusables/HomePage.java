package Reusables;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;

public class HomePage {

	WebDriver driver;

	// common elements
	@FindBy(id = "bookings")
    WebElement bookingsTable;
	
	@FindBy(id = "form")
    WebElement fieldsRow;


	// constructor
	public HomePage(WebDriver hmDriver) {
		this.driver = hmDriver;
		PageFactory.initElements(new AjaxElementLocatorFactory(this.driver, 20), this);
	}

	// function to return the total number of Booking entries
	public int getNumberOfBookings() {
		try {
			return bookingsTable.findElements(By.xpath("./div[@id and @class = 'row']"))
					.size();
		} catch (Exception e) {
			System.out.println("Issue in getNumberOfBookings function. - " + e.getMessage());
		}
		return 0;
	}

	// Function to check the existence of a row matching the provided details
	public boolean checkifRowExists(String firstname, String surname, String price, String deposit, String checkIn,
			String checkOut) {
		try {

			List<WebElement> rows = bookingsTable.findElements(By.xpath("./*[@id and @class = 'row']"));

			for (int count = 0; count < rows.size(); count++) {
				if (rows.get(count).findElements(By.xpath("./div/p")).get(0).getText().equalsIgnoreCase(firstname)
						&& rows.get(count).findElements(By.xpath("./div/p")).get(1).getText().equalsIgnoreCase(surname)
						&& rows.get(count).findElements(By.xpath("./div/p")).get(2).getText().equalsIgnoreCase(price)
						&& rows.get(count).findElements(By.xpath("./div/p")).get(3).getText().equalsIgnoreCase(deposit)
						&& rows.get(count).findElements(By.xpath("./div/p")).get(4).getText().equalsIgnoreCase(checkIn)
						&& rows.get(count).findElements(By.xpath("./div/p")).get(5).getText()
								.equalsIgnoreCase(checkOut))
					return true;
			}

		} catch (Exception e) {
			System.out.println("Issue in getNumberOfBookings function. - " + e.getMessage());
		}
		return false;
	}

	// Function to Enter the give data in the fields
	public boolean enterData(String fName, String sName, String price, String deposit, String checkIn,
			String checkOut) {
		try {

			fieldsRow.findElement(By.id("firstname")).clear();
			fieldsRow.findElement(By.id("firstname")).sendKeys(fName);

			fieldsRow.findElement(By.id("lastname")).clear();
			fieldsRow.findElement(By.id("lastname")).sendKeys(sName);

			fieldsRow.findElement(By.id("totalprice")).clear();
			fieldsRow.findElement(By.id("totalprice")).sendKeys(price);

			fieldsRow.findElement(By.id("depositpaid")).click();
			fieldsRow.findElement(By.id("depositpaid"))
					.findElement(By.xpath("./option[text() = '" + deposit + "']")).click();

			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('checkin').value='" + checkIn + "'");
			js.executeScript("document.getElementById('checkout').value='" + checkOut + "'");

			return true;
		} catch (Exception e) {
			System.out.println("Issue in enterData function. - " + e.getMessage());
		}
		return false;
	}

	// Function to Click the Save button
	public void clickSaveButton() {
		try {

			int preCount = this.getNumberOfBookings();

			this.driver.findElement(By.xpath("//input[@value = ' Save ']")).click();

			for (int count = 0; count < 10; count++) {
				if (this.getNumberOfBookings() == preCount + 1)
					break;
				else
					Thread.sleep(1000);
			}

		}

		catch (Exception e) {
			System.out.println("Issue in clickSaveButton function. - " + e.getMessage());
		}

	}

	// Function to click the delete button against a particular row
	public void clickDeleteButton(String fName, String sName) {
		try {

			int preCount = this.getNumberOfBookings();

			List<WebElement> rows = bookingsTable.findElements(By.xpath("./*[@id and @class = 'row']"));

			for (int count = 0; count < rows.size(); count++) {
				if (rows.get(count).findElements(By.xpath("./div/p")).get(0).getText().equalsIgnoreCase(fName)
						&& rows.get(count).findElements(By.xpath("./div/p")).get(1).getText().equalsIgnoreCase(sName))
					rows.get(count).findElement(By.xpath("./div/input[@value='Delete']")).click();
			}

			for (int count = 0; count < 10; count++) {
				if (this.getNumberOfBookings() == preCount - 1)
					break;
				else
					Thread.sleep(1000);
			}

		} catch (Exception e) {
			System.out.println("Issue in clickDeleteButton function. - " + e.getMessage());
		}

	}

}
