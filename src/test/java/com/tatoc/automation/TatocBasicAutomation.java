package com.tatoc.automation;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import com.tatoc.util.Pathreader;

//import com.tatoc.util.Pathreader;

public class TatocBasicAutomation {
	private WebDriver driver;
	private WebdriverSelect loadDriver;
	private String url;
	private Pathreader data;

	public TatocBasicAutomation() {
		data = new Pathreader();
		loadDriver = new WebdriverSelect();
		driver = loadDriver.get_driver_for_running_automation();
		url = "http://10.0.1.86/tatoc/basic/grid/gate";
		driver.get(url);
		driver.manage().window().maximize();
	}

	public void automateGrid() throws Exception {
		driver.findElement(data.getLocator("green_grid")).click();
	}

	public void automateFrameDungeon() throws Exception {
		Thread.sleep(2000);
		driver.switchTo().frame("main");
		String box1 = driver.findElement(data.getLocator("box_colour")).getAttribute("class");
		driver.switchTo().frame("child");
		String box2 = driver.findElement(data.getLocator("box_colour")).getAttribute("class");
		driver.switchTo().defaultContent();
		while (!box1.equals(box2)) {
			driver.switchTo().frame("main");
			driver.findElement(data.getLocator("reload_frame")).click();
			Thread.sleep(1000);
			driver.switchTo().frame("child");
			box2 = driver.findElement(data.getLocator("box_colour")).getAttribute("class");
			driver.switchTo().defaultContent();
		}
		driver.switchTo().frame("main");
		Thread.sleep(2000);
		driver.findElement(data.getLocator("proceed")).click();
	}

	public void automateDragAround() throws Exception {
		WebElement box = driver.findElement(data.getLocator("box_value"));
		WebElement field = driver.findElement(data.getLocator("field_value"));
		Thread.sleep(2000);
		(new Actions(driver)).dragAndDrop(box, field).perform();
		Thread.sleep(2000);
		driver.findElement(data.getLocator("proceed")).click();
	}

	public void automatePopupWindow() throws Exception {
		Thread.sleep(2000);
		String winHandleBefore = driver.getWindowHandle();
		driver.findElement(data.getLocator("Popup_value")).click();
		for (String winHandle : driver.getWindowHandles()) {
			driver.switchTo().window(winHandle);
		}
		driver.findElement(data.getLocator("name")).sendKeys("Aman");
		Thread.sleep(2000);
		driver.findElement(data.getLocator("submit")).click();
		driver.switchTo().window(winHandleBefore);
		Thread.sleep(2000);
		driver.findElement(data.getLocator("proceed")).click();
	}

	public void automateTokenGenerate() throws Exception {
		Thread.sleep(2000);
		driver.findElement(data.getLocator("token")).click();
		String token = driver.findElement(data.getLocator("token_value")).getText();
		String tokenValue = token.split(":")[1];
		Cookie newcookie = new Cookie("Token", tokenValue.trim());
		driver.manage().addCookie(newcookie);
		Thread.sleep(1000);
		driver.findElement(data.getLocator("proceed")).click();
	}

	public static void main(String as[]) throws Exception {
		TatocBasicAutomation td = new TatocBasicAutomation();
		td.automateGrid();
		td.automateFrameDungeon();
		td.automateDragAround();
		td.automatePopupWindow();
		td.automateTokenGenerate();
	}
}
