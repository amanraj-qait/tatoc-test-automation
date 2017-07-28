package com.tatoc.util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.openqa.selenium.By;

public class Pathreader {

	Properties properties;

	public Pathreader() {

		try {
			InputStream locatorInput = getClass().getClassLoader()
					.getResourceAsStream("./PageElementAttribute.properties");
			properties = new Properties();
			properties.load(locatorInput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 
	 * @param ElementName
	 * @return
	 * @throws Exception
	 */
	public String getData(String ElementName) throws Exception {
		// Read value using the logical name as Key
		String data = properties.getProperty(ElementName);
		return data;
	}

	/**
	 * 
	 * @param ElementName
	 * @return
	 * @throws Exception
	 */
	public By getLocator(String ElementName) throws Exception {
		String locator = properties.getProperty(ElementName);
		String locatorType = locator.split(";")[0];
		String locatorValue = locator.split(";")[1];
		if (locatorType.toLowerCase().equals("id"))
			return By.id(locatorValue);
		else if (locatorType.toLowerCase().equals("name"))
			return By.name(locatorValue);
		else if ((locatorType.toLowerCase().equals("classname")) || (locatorType.toLowerCase().equals("class")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("tagname")) || (locatorType.toLowerCase().equals("tag")))
			return By.className(locatorValue);
		else if ((locatorType.toLowerCase().equals("linktext")) || (locatorType.toLowerCase().equals("link")))
			return By.linkText(locatorValue);
		else if (locatorType.toLowerCase().equals("partiallinktext"))
			return By.partialLinkText(locatorValue);
		else if ((locatorType.toLowerCase().equals("cssselector")) || (locatorType.toLowerCase().equals("css")))
			return By.cssSelector(locatorValue);
		else if (locatorType.toLowerCase().equals("xpath"))
			return By.xpath(locatorValue);
		else
			throw new Exception("Locator type '" + locatorType + "' not defined!!");
	}

}