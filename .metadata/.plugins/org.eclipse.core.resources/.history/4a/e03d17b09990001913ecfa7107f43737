package Tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import org.openqa.selenium.*;
import Reusables.*;

public class CoreTests {

	SoftAssert softAssert = new SoftAssert();

	@DataProvider(name = "Data")
	public static Object[][] dataProvider() {

		return new Object[][] { { "testa", "testb", "150", "false", "2019-06-17", "2019-06-18" },
				{ "testc", "testd", "150", "false", "2020-06-17", "2021-06-18" } };

	}

	@Test(dataProvider = "Data")
	public void Testcase1_1(String fName, String sName, String price, String deposit, String checkIn, String checkOut) {

		// Test case to add a valid Booking Entry

		try {

			// Launch Browser and Load URL
			WebDriver driver = Utilities.loadURL("http://hotel-test.equalexperts.io");
			
			//initialize page object 
			HomePage homePage = new HomePage(driver);
			Utilities.waitForPageLoad(driver);

			//Enter data in the fields
			homePage.enterData(fName, sName, price, deposit, checkIn, checkOut);
			Utilities.waitForPageLoad(driver);
			
			//Click the Save button
			homePage.clickSaveButton();
			Utilities.waitForPageLoad(driver);

			//check if a row exists with the data entered
			softAssert.assertEquals(homePage.checkifRowExists(fName, sName, price, deposit, checkIn, checkOut), true, "Newly added entry is not shown in the site");

			//Kill the browser instance
			Utilities.quitDriver(driver);

			//Complete assertion
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println("Issue in Testcase1_1. - " + e.getMessage());
		}

	}

	@Test(dataProvider = "Data")
	public void Testcase1_2(String fName, String sName, String price, String deposit, String checkIn, String checkOut) {

		// Test case to Delete a Booking Entry

		try {

			// Launch Browser and Load URL
			WebDriver driver = Utilities.loadURL("http://hotel-test.equalexperts.io");

			//initialize page object 
			HomePage homePage = new HomePage(driver);
			Utilities.waitForPageLoad(driver);

			//Click delete button against a row
			homePage.clickDeleteButton(fName, sName);
			Utilities.waitForPageLoad(driver);

			//Check if the row is deleted
			softAssert.assertEquals(homePage.checkifRowExists(fName, sName, price, deposit, checkIn, checkOut), false, "Booking Entry is still shown in the site");

			//Kill the browser
			Utilities.quitDriver(driver);

			//complete Assertion
			softAssert.assertAll();
		}

		catch (Exception e) {
			System.out.println("Issue in Testcase1_2. - " + e.getMessage());
		}

	}

}
