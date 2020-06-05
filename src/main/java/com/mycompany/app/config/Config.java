package com.mycompany.app.config;

import com.mycompany.app.constant.Constant;
import org.apache.log4j.Logger;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Configuration utility. Allows to get configuration properties from the
 * configuration file
 */
public final class Config {
    private final static Logger logger = Logger.getLogger(Config.class);
    private final static Properties props = new Properties();

    /**
     * Hides default constructor
     */
    private Config() {
    }

    private static void loadConfiguration() {
        try {
            logger.debug("loadConfiguration: loading properties");
            FileInputStream fileStream = new FileInputStream(System.getProperty(Constant.CONFIG_PROPERTIES));
            props.load(fileStream);
            logger.info("loadConfiguration: Properties are loaded");
        } catch (IOException e) {
            logger.error("loadConfiguration: Error: File config.properties not found, " + e);
            throw new RuntimeException(e);
        } catch (NullPointerException e) {
            logger.error("loadConfiguration: Error: Argument config.properties not found, " + e);
            throw new RuntimeException(e);
        }
    }


    /**
     * Gets configuration entry value
     *
     * @param key Entry key
     * @return Entry value by key
     */
    public static String getProp(String key) {
        if (props.isEmpty()) {
            loadConfiguration();
        }
        return props.getProperty(key);
    }

}
