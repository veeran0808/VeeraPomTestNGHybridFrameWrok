package com.qa.opencart.exceptions;

/**
 * FrameworkException is a custom runtime exception used for handling errors specific to the framework.
 * It extends the RuntimeException class, allowing unchecked exceptions to be thrown and caught.
 */
public class FrameworkException extends RuntimeException {

    /**
     * Constructs a new FrameworkException with the specified detail message.
     * 
     * @param mesg The detail message, which is saved for later retrieval by the getMessage() method.
     */
    public FrameworkException(String mesg) {
        super(mesg);
    }

    /**
     * OOP Concepts Used:
     * - Inheritance: FrameworkException inherits from RuntimeException, which is part of Java's exception hierarchy.
     * - Polymorphism: By extending RuntimeException, FrameworkException can be used polymorphically wherever exceptions are handled.
     * 
     * Access Modifiers:
     * - `public`: The class and its constructor are public, making them accessible from anywhere in the application.
     * 
     * Purpose:
     * 
     * 1. **Custom Exception Handling**:
     *    - Provides a specific exception type for errors related to the framework itself, enhancing the granularity of error handling.
     * 
     * 2. **Inheritance**:
     *    - Inherits from RuntimeException, allowing it to be used as an unchecked exception. This means it doesn't need to be declared or handled explicitly.
     * 
     * 3. **Improved Debugging**:
     *    - Custom error messages provide more context-specific information, which aids in debugging and resolving framework-related issues.
     * 
     * 4. **Exception Segregation**:
     *    - Enables more precise exception handling by segregating framework errors from other types of exceptions, leading to cleaner and more maintainable code.
     */
}
