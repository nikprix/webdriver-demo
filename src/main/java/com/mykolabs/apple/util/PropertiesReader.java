package com.mykolabs.apple.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Manages project's properties.
 *
 * @author mykola.prykhodko
 */
public class PropertiesReader {

    private static Properties properties = new Properties();

    /**
     * Returns properties.
     *
     * @return
     */
    public static Properties getProperties() {
        InputStream in = PropertiesReader.class.getClassLoader().getResourceAsStream("project.properties");

        try {
            properties.load(in);
        } catch (IOException ex) {
            Logger.getLogger(PropertiesReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        return properties;
    }

    /**
     * Returns boolean representation of the property.
     *
     * @param key
     * @return boolean
     */
    public static boolean getPropertyAsBoolean(String key) {
        return properties.get(key).equals("true");
    }

    /**
     * Returns property value by specified key.
     *
     * @param key
     * @return String
     */
    public static String getProperty(String key) {
        return properties.getProperty(key);
    }
}
