package com.qa.opencart.logger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * The Log class provides a centralized logging mechanism using Log4j2.
 * It offers various logging levels to record messages and exceptions.
 * This class is used to log information, warnings, errors, and debug messages.
 */
public class Log {

    private static final Logger logger = LogManager.getLogger(Log.class);

    /**
     * Logs an informational message.
     *
     * @param message The message to be logged.
     */
    public static void info(String message) {
        logger.info(message);
    }

    /**
     * Logs an error message.
     *
     * @param message The message to be logged.
     */
    public static void error(String message) {
        logger.error(message);
    }

    /**
     * Logs an error message along with an exception.
     *
     * @param message The message to be logged.
     * @param e       The exception to be logged.
     */
    public static void error(String message, Exception e) {
        logger.error(message, e);
    }

    /**
     * Logs a debug message.
     *
     * @param message The message to be logged.
     */
    public static void debug(String message) {
        logger.debug(message);
    }

    /**
     * Logs a warning message.
     *
     * @param message The message to be logged.
     */
    public static void warn(String message) {
        logger.warn(message);
    }
}
