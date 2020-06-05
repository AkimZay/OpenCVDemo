package com.mycompany.app.utils;

import com.mycompany.app.constant.Constant;
import com.mycompany.app.config.Config;
import org.apache.log4j.Logger;
import sun.awt.OSInfo;
import sun.awt.OSInfo.OSType;

import java.util.Locale;

/**
 * The type Operating system utils.
 */
public final class OperatingSystemUtils {
    private final static Logger logger = Logger.getLogger(OperatingSystemUtils.class);

    /**
     * Hides default constructor
     */
    private OperatingSystemUtils() {
    }

    private static OSInfo.OSType getOperatingSystemType() {
        String OS = System.getProperty("os.name", "generic").toLowerCase(Locale.ENGLISH);
        if ((OS.contains("mac")) || (OS.contains("darwin"))) {
            return OSType.SOLARIS;
        } else if (OS.contains("win")) {
            return OSType.WINDOWS;
        } else if (OS.contains("nux")) {
            return OSType.LINUX;
        } else {
            return OSType.UNKNOWN;
        }
    }

    /**
     * Load config.
     *
     * @throws Exception the exception
     */
    public static void loadConfig() throws Exception {
        logger.debug("Checking OS.....");


        switch (getOperatingSystemType()) {
            case LINUX:
                System.load(Config.getProp(Constant.PATH_TO_NATIVE_LIB_LINUX));
                break;
            case WINDOWS:
                System.load(Config.getProp(Constant.PATH_TO_NATIVE_LIB_WIN));
                break;
            case SOLARIS:
                throw new Exception("Mac OS does not support!!!!!!!!");
            case UNKNOWN:
                throw new Exception("Current OS does not support!!!!!");
            default:
                throw new Exception("Your OS does not support!!!");
        }
    }
}
