package com.qa.opencart.exceptions;

/**
 * ElementException is a custom runtime exception that is thrown when an error related to web elements occurs.
 * It extends the RuntimeException class, allowing unchecked exceptions to be thrown and caught.
 */
public class ElementException extends RuntimeException {

    /**
     * Constructs a new ElementException with the specified detail message.
     * 
     * @param mesg The detail message, which is saved for later retrieval by the getMessage() method.
     */
    public ElementException(String mesg) {
        super(mesg);
    }

    /**
     * OOP Concepts Used:
     * - Inheritance: ElementException inherits from RuntimeException, which is part of Java's exception hierarchy.
     * - Polymorphism: By extending RuntimeException, ElementException can be used polymorphically wherever exceptions are handled.
     * 
     * Access Modifiers:
     * - `public`: The class and its constructor are public, making it accessible from anywhere in the application.
     * 
     * Purpose:
     * 
     * 1. **Custom Exception Handling**:
     *    - Provides a specific exception type for element-related errors, improving the granularity of error handling in the application.
     * 
     * 2. **Inheritance**:
     *    - Inherits from RuntimeException, allowing it to be used as an unchecked exception, which does not require explicit handling or declaration.
     * 
     * 3. **Improved Debugging**:
     *    - By using custom messages, it provides more context-specific information when exceptions are thrown, aiding in debugging and error resolution.
     * 
     * 4. **Exception Segregation**:
     *    - Allows for segregation of different types of exceptions, enabling more precise catch blocks and error processing in the code.
     */
}
