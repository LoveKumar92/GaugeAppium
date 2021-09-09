package com.gauge.appium.utility;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	
	public static Properties prop;
	
	public static Properties intializeConfigFile() {
		prop = new Properties();

		try {
			FileInputStream reader = new FileInputStream("src\\test\\java\\com\\gauge\\appium\\config\\config.properties");
			try {
				prop.load(reader);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return prop;
	}
	
	public static String getConfigValue(String Value) {
		Value = prop.getProperty(Value).toString();
		return Value;	
	}
	
}
