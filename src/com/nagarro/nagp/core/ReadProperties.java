package com.nagarro.nagp.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class ReadProperties {

	static String cwd = System.getProperty("user.dir");
	static String propFile = "\\src\\com\\nagarro\\nagp\\resources\\config.properties";

	static FileInputStream fileInputStream;
	static Properties properties;

	/*
	 * Reads the property file and returns the value of the given value. Return type
	 * is String
	 */
	public static String getProperty(String propertyName) throws IOException {
		fileInputStream = new FileInputStream(cwd + propFile);
		properties = new Properties();
		properties.load(fileInputStream);

		return properties.get(propertyName).toString();
	}

}
