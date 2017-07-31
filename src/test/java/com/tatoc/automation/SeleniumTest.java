package com.tatoc.automation;
import java.io.File;
import java.net.URL;
import org.openqa.selenium.OutputType;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class SeleniumTest {
public static void main(String s[]) throws Exception {
URL url = new URL( "http", "10.0.16.15", 5555, "/wd/hub" );
DesiredCapabilities capabilities =DesiredCapabilities.internetExplorer();
System.out.println("1");
capabilities.setJavascriptEnabled(true);
System.out.println("2");
WebDriver driver = new RemoteWebDriver(url,capabilities);
System.out.println("4");
driver.get("http://www.google.com");
File scrFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE); 
FileUtils.copyFile(scrFile, new File("c:\\screenshot\\googlesearch-webdriverapi.png"));
driver.quit();
}
}
