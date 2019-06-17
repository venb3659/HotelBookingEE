package Reusables;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Utilities {

	public static WebDriver loadURL(String URL) {

		try {
			System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "\\Jars\\geckodriver.exe");
			WebDriver driverInstance = new FirefoxDriver();
			driverInstance.get(URL);
			return driverInstance;
		}

		catch (Exception e) {
			System.out.println("Issue in Load URL function. - " + e.getMessage());
		}
		return null;
	}

	public static void quitDriver(WebDriver driverInst) {

		try {
			driverInst.quit();
		}

		catch (Exception e) {
			System.out.println("Issue in quitDriver function. - " + e.getMessage());
		}
	}

	public static void waitForPageLoad(WebDriver driverInst) {

		try {
			Thread.sleep(2000);
			driverInst.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
			JavascriptExecutor js=(JavascriptExecutor) driverInst;
			while (true)
			{
			 if (js.executeScript("return document.readyState").toString().equalsIgnoreCase("COMPLETE"))
				 break;
			}//While(true)
			Thread.sleep(2000);
		}

		catch (Exception e) {
			System.out.println("Issue in quitDriver function. - " + e.getMessage());
		}
	}

}
