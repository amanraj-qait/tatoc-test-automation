package com.tatoc.automation;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.Test;

import com.jayway.restassured.RestAssured;
import com.tatoc.util.Pathreader;
import com.tatoc.util.PropertyFileReader;

public class TatocAdvancedAutomation {
	private WebDriver driver;
	private WebdriverSelect loadDriver;
	private Pathreader data;
	private PropertyFileReader reader;
	private String url;
	private String dbUrl;
	private String value;
	private String name;
	private String passkey;

	public TatocAdvancedAutomation() throws IOException {
		loadDriver = new WebdriverSelect();
		data = new Pathreader();
		reader = new PropertyFileReader();
		driver = loadDriver.get_driver_for_running_automation();
		url = "http://10.0.1.86/tatoc/advanced/hover/menu";
		dbUrl = "jdbc:mysql://10.0.1.86/tatoc";
		driver.get(url);
		driver.manage().window().maximize();
	}

	@Test(priority = 1)
	public void automateHoverMenu() throws Exception {
		Actions action = new Actions(driver);
		WebElement menu = driver.findElement(data.getLocator("menu"));
		action.moveToElement(menu).moveToElement(driver.findElement(data.getLocator("go_next"))).click().build()
				.perform();
	}

	@Test(priority=2)
	public void automateQueryGate() throws Exception {
		String symbol = driver.findElement(data.getLocator("symbol")).getText();
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = DriverManager.getConnection(dbUrl, "tatocuser", "tatoc01");
		Statement stmt = con.createStatement();
		ResultSet set1 = stmt.executeQuery("select *  from identity;");
		while (set1.next()) {
			if (symbol.toLowerCase().equals(set1.getString(2))) {
				value = set1.getString(1);
			}
		}
		ResultSet set2 = stmt.executeQuery("select *  from credentials;");
		while (set2.next()) {
			if (value.equals(set2.getString(1))) {
				name = set2.getString(2);
				passkey = set2.getString(3);
			}
		}
		driver.findElement(data.getLocator("name")).sendKeys(name);
		driver.findElement(data.getLocator("passkey")).sendKeys(passkey);
		driver.findElement(data.getLocator("submit")).click();
	}

	// Video not working
	/*
	 * public void automatePlayVideo() {
	 * driver.findElement(By.cssSelector("object#OoFlashhwp40h_internal")).click
	 * (); }
	 */
	@Test(priority=1)
	public void automateRestful() throws Exception {
		driver.get("http://10.0.1.86/tatoc/advanced/rest/#");
		String session = driver.findElement(data.getLocator("session_id")).getText();
		String Id = session.substring(session.indexOf(':') + 1);
		Id = Id.trim();
		String newSession = RestAssured.given().when().get("http://10.0.1.86/tatoc/advanced/rest/service/token/" + Id)
				.getBody().asString();
		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(newSession);
		String token = (String) obj.get("token");
		RestAssured.given().parameters("id", Id, "signature", token, "allow_access", "1").when()
				.post("http://10.0.1.86/tatoc/advanced/rest/service/register").then().statusCode(200);
		driver.findElement(data.getLocator("proceed")).click();
	}

	@Test(priority=3)
	public void automateReadFile() throws Exception {
		File file = new File(System.getProperty("user.home") + File.separator + "Downloads" + File.separator
				+ "file_handle_test.dat");
		if (file.delete()) {
			System.out.println(file.getName() + " is deleted!");
		} else {
			System.out.println("Delete operation is failed.");
		}
		Thread.sleep(2000);
		driver.findElement(data.getLocator("download")).click();
		Thread.sleep(2000);
		String signature = reader.read_file("Signature");
		driver.findElement(data.getLocator("input")).sendKeys(signature);
		driver.findElement(data.getLocator("submit_sig")).click();

	}
}
