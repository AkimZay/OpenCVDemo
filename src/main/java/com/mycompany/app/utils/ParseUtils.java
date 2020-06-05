package com.mycompany.app.utils;

import org.apache.log4j.Logger;

/**
 * The type Parse utils.
 */
public final class ParseUtils {

    private final static Logger logger = Logger.getLogger(ParseUtils.class);

    /**
     * Hides default constructor
     */
    private ParseUtils() {
    }

    /**
     * Get integer value int, when the parameter is not an int value, return 0
     *
     * @param stringValue the string value
     * @return the int value
     */
    public static int getIntegerValue(String stringValue){
        try {
            return Integer.parseInt(stringValue);

        }catch (NumberFormatException e){
            logger.error("The argument is not a number" + e);
            return 0;
        }
    }
}
