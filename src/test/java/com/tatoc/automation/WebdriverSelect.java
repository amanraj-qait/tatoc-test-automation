package com.tatoc.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebdriverSelect {
	String value = "";
	//PropertyFileReader reader;
	WebDriver main_driver;

	public WebdriverSelect() {
		//reader = new PropertyFileReader();
	}

	/**
	 * Select the driver to be used
	 * 
	 * @param id
	 * @return
	 */
	public WebDriver get_driver_for_running_automation() {
		value =/* reader.read_property("driver");*/"chrome";
		if (value.equalsIgnoreCase("chrome")) {
			main_driver = get_chromedriver();
		} else if (value.equalsIgnoreCase("firefox")) {
			main_driver = get_firefoxdriver();
		} else if (value.equalsIgnoreCase("edge")) {
			main_driver = get_edgedriver();
		}
		return main_driver;
	}

	/**
	 * initialize Chrome driver
	 * 
	 * @return
	 */
	public WebDriver get_chromedriver() {
		System.setProperty("webdriver.chrome.driver", "D:\\AmanBackup\\Drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		return driver;
	}

	/**
	 * initialize Firefox driver
	 * 
	 * @return
	 */
	public WebDriver get_firefoxdriver() {
		System.setProperty("webdriver.gecko.driver", "D:\\AmanBackup\\Drivers\\geckodriver.exe");
		WebDriver driver = new FirefoxDriver();
		return driver;
	}

	/**
	 * initialize Microsoft edge driver
	 * 
	 * @return
	 */
	public WebDriver get_edgedriver() {
		System.setProperty("webdriver.edge.driver", "D:\\AmanBackup\\Drivers\\MicrosoftWebDriver.exe");
		WebDriver driver = new EdgeDriver();
		return driver;
	}
}