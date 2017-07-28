package com.tatoc.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyFileReader {

	String path = "src/test/resources/Property-files/config.properties";
	static String value = "";
	static Properties properties = new Properties();
	static Properties properties2 = new Properties();
	InputStream locatorInput;
	InputStream locatorInput2;

	public PropertyFileReader() throws IOException {
		/*
		 * locatorInput =
		 * getClass().getClassLoader().getResourceAsStream("./config.properties"
		 * ); properties.load(locatorInput);
		 */
	}

	/**
	 * Read value from config.properties file
	 * 
	 * @param property
	 * @return
	 */
	public String read_property(String property) {
		value = properties.getProperty(property);
		return value;

	}

	/**
	 * Read value from downloaded file
	 * 
	 * @param property
	 * @return
	 * @throws IOException
	 */
	public String read_file(String property) throws IOException {
		locatorInput2 = new FileInputStream(System.getProperty("user.home") + File.separator
				+ "Downloads" + File.separator + "file_handle_test.dat");
		properties2.load(locatorInput2);
		value = properties2.getProperty(property);
		return value;

	}

	
	 /* public static void main(String[] k) throws IOException {
	  PropertyFileReader n = new PropertyFileReader();
	  n.read_file();
	 
	  }*/
	 
}
