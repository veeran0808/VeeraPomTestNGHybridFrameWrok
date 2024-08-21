package com.qa.opencart.errors;

/**
 * The AppError class serves as a centralized repository for error messages used across the application.
 * This class contains only static final fields that represent different error messages, ensuring consistency
 * and ease of maintenance. The class is designed to be a utility class with no instances.
 */
public class AppError {
    
    // Error messages related to browser operations
    public static final String BROWSER_NOT_FOUND = "====BROWSER NOT FOUND=====";
    public static final String TITLE_NOT_FOUND = "====TITLE NOT FOUND=====";
    public static final String URL_NOT_FOUND = "====URL NOT FOUND=====";
    public static final String ELEMENT_NOT_FOUND = "===ELEMENT NOT FOUND=====";
    public static final String LIST_IS_NOT_MATCHED = "====LIST IS NOT MATCHED OR EMPTY";
    
    // Error messages related to search and results
    public static final String RESULTS_COUNT_MISMATCHED = "===PRODUCT SEARCH RESULTS MISMATCHED===";
    public static final String HEADER_NOT_FOUND = "===HEADER NOT FOUND===";
    public static final String IMAGES_COUNT_MISMATCHED = "===IMAGES COUNT NOT MATCHED===";
    public static final String USER_REG_NOT_DONE = "====USER REGISTRATION IS NOT DONE====";
    
    // Error messages related to product details
    public static final String BRAND_NOT_FOUND = "Brand not found";
    public static final String CODE_NOT_FOUND = "Product code not found";
    public static final String REWARD_POINTS_NOT_FOUND = "Reward points not found";
    public static final String AVAILABILITY_NOT_FOUND = "Availability status not found";
    public static final String PRICE_NOT_FOUND = "Price not found";
    public static final String EX_TAX_PRICE_NOT_FOUND = "Ex-tax price not found";

    /**
     * OOP Concepts Used:
     * - Encapsulation: All error messages are encapsulated within this class, ensuring they are centralized and accessible throughout the application.
     * - Singleton Pattern: The class is not designed to be instantiated, following the Singleton pattern for utility classes.
     * 
     * Access Modifiers:
     * - `public`: Error messages are `public` to ensure they can be accessed from anywhere in the application.
     * - `static final`: The messages are declared `static` so they belong to the class itself, and `final` to ensure they cannot be modified after initialization.
     * 
     * Purpose:
     * 
     * 1. **Centralized Error Handling**:
     *    - Provides a single location for defining all error messages, making it easier to manage and maintain error handling across the application.
     * 
     * 2. **Consistency**:
     *    - Ensures consistent error messages are used throughout the codebase, reducing redundancy and potential discrepancies.
     * 
     * 3. **Ease of Maintenance**:
     *    - Allows easy updates to error messages, as changes only need to be made in one place rather than scattered across multiple files.
     * 
     * 4. **Prevents Instantiation**:
     *    - By using only static fields and no constructors, the class is used purely as a holder for constants, following best practices for utility classes.
     */
}
