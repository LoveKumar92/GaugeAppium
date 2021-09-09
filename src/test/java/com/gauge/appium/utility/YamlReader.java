
package com.gauge.appium.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.Reader;
import java.util.Map;

import org.yaml.snakeyaml.Yaml;

@SuppressWarnings("unchecked")
public class YamlReader {

    public static String yamlFilePath = System.getProperty("user.dir") + File.separator + "resources" + File.separator + "testdata" + File.separator + ConfigReader.getConfigValue("environment").toUpperCase() + "_TestData.yml";
  
    public static String setYamlFilePath() {
        if (yamlFilePath.isEmpty()) {
            yamlFilePath = yamlFilePath.replaceAll("XXX", ConfigReader.getConfigValue("environment").toUpperCase().replace("/", ""));
        }
        System.out.println("Yaml init: " + yamlFilePath);
        return yamlFilePath;
    }

    public static String getYamlValue(String token) {
        try {
        	
            return getValue(token, yamlFilePath);
        } catch (FileNotFoundException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    public static String getData(String token) {
        return getYamlValue(token);
    }

    public static Map<String, Object> getYamlValues(String token) {
        Reader doc;
        System.out.println("Yaml: " + yamlFilePath);
        try {
            doc = new FileReader(yamlFilePath);
        } catch (FileNotFoundException ex) {
            System.out.println("File not valid or missing!!!");
            ex.printStackTrace();
            return null;
        }
        Yaml yaml = new Yaml();
        // TODO: check the type casting of object into the Map and create
        // instance in one place
        Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
        	return parseMap(object, token + ".");
    }
    
    private static String getValue(String token, String yamlFile) throws FileNotFoundException {
        Reader doc = new FileReader(yamlFile);
        Yaml yaml = new Yaml();
        Map<String, Object> object = (Map<String, Object>) yaml.load(doc);
        return getMapValue(object, token);

    }

    public static String getMapValue(Map<String, Object> object, String token) {
        // TODO: check for proper yaml token string based on presence of '.'
        String[] st = token.split("\\.");
        return parseMap(object, token).get(st[st.length - 1]).toString();
    }

    private static Map<String, Object> parseMap(Map<String, Object> object,
                                                String token) {
        if (token.contains(".")) {
            String[] st = token.split("\\.");
            object = parseMap((Map<String, Object>) object.get(st[0]),
                    token.replace(st[0] + ".", ""));
        }
        return object;
    }

    public static int generateRandomNumber(int MinRange, int MaxRange) {
        int randomNumber = MinRange
                + (int) (Math.random() * ((MaxRange - MinRange) + 1));
        return randomNumber;
    }
}
