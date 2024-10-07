package com.qa.opencart.constants;

import java.util.Arrays;
import java.util.List;

public class AppConstants {

    // Private constructor to prevent instantiation, hi
    private AppConstants() {}

    // File paths for various environment configurations
    public static final String CONFIG_FILE_PATH = "./src/test/resources/config/config.properties";
    public static final String CONFIG_QA_FILE_PATH = "./src/test/resources/config/qa.properties";
    public static final String CONFIG_DEV_FILE_PATH = "./src/test/resources/config/dev.properties";
    public static final String CONFIG_UAT_FILE_PATH = "./src/test/resources/config/uat.properties";
    public static final String CONFIG_STAGE_FILE_PATH = "./src/test/resources/config/stage.properties";

    // Page titles used for validation
    public static final String LOGIN_PAGE_TITLE = "Account Login";
    public static final String ACCOUNTS_PAGE_TITLE = "My Account";

    // URL fragments for specific pages used in validation
    public static final String LOGIN_PAGE_FRACTION_URL = "route=account/login";
    public static final String ACC_PAGE_FRACTION_URL = "route=account/account";

    // List of account page headers for validation
    public static final List<String> ACC_PAGE_HEADERS_LIST = 
            Arrays.asList("My Account", "My Orders", "My Affiliate Account", "Newsletter");

    // Success message for user registration
    public static final String USER_REGISTER_SUCCESS_MESSG = "Your Account Has Been Created!";

    // Sheet names used in Excel data-driven testing
    public static final String REGISTER_SHEET_NAME = "register";
    public static final String PRODUCT_IMAGES_SHEET_NAME = "productimages";

    /**
     * OOP Concepts Used:
     * - Encapsulation: The class encapsulates all the constants in one place, preventing modification by making them `public static final`.
     * - Singleton Pattern: The private constructor ensures that this class cannot be instantiated, following the Singleton pattern for utility classes.
     * 
     * Access Modifiers:
     * - `public`: Constants are made `public` to allow access throughout the application.
     * - `private`: The constructor is made `private` to prevent instantiation.
     * 
     * Purpose:
     * 
     * 1. **Centralized Configuration**:
     *    - This class provides a central location for defining constants that are used across the test framework, ensuring consistency and reducing hard-coded values.
     * 
     * 2. **Environment-Specific File Paths**:
     *    - The class holds file paths for configuration files corresponding to different environments like QA, Dev, UAT, and Stage, making it easy to switch between them.
     * 
     * 3. **Page Validation**:
     *    - It includes constants for page titles and URL fragments that are used for validation in tests, ensuring that the expected values are easily accessible.
     * 
     * 4. **Data-Driven Testing Support**:
     *    - The class defines sheet names for Excel files used in data-driven testing, simplifying the reference to these sheets in the test code.
     * 
     * 5. **Prevents Instantiation**:
     *    - By using a private constructor, the class follows the best practice for utility classes, preventing the creation of unnecessary objects.
     */
}
