package com.qa.opencart.factory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;

public class OptionsManager {

    private Properties prop;
    private ChromeOptions co;
    private FirefoxOptions fo;
    private EdgeOptions eo;

    /**
     * Constructor for OptionsManager. Initializes the Properties object.
     *
     * @param prop The Properties object containing configuration data.
     */
    public OptionsManager(Properties prop) {
        this.prop = prop;
    }

    /**
     * Creates and configures ChromeOptions based on the provided properties.
     * 
     * @return ChromeOptions configured with the necessary settings.
     */
    public ChromeOptions getChromeOptions() {
        co = new ChromeOptions();
        // Add the argument to allow all origins when running remotely
        co.addArguments("--remote-allow-origins=*");

        // Set Chrome to run in headless mode if specified in properties
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("====Running tests in headless======");
            co.addArguments("--headless");
        }

        // Set Chrome to run in incognito mode if specified in properties
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            co.addArguments("--incognito");
        }

        // Set additional capabilities if running tests on a remote machine
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            co.setCapability("browserName", "chrome");
            co.setBrowserVersion(prop.getProperty("browserversion").trim());

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("screenResolution", "1280x1024x24");
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("name", prop.getProperty("testname"));
            co.setCapability("selenoid:options", selenoidOptions);
        }
        return co;
    }

    /**
     * Creates and configures FirefoxOptions based on the provided properties.
     * 
     * @return FirefoxOptions configured with the necessary settings.
     */
    public FirefoxOptions getFirefoxOptions() {
        fo = new FirefoxOptions();

        // Set Firefox to run in headless mode if specified in properties
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("====Running tests in headless======");
            fo.addArguments("--headless");
        }

        // Set Firefox to run in incognito mode if specified in properties
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            fo.addArguments("--incognito");
        }

        // Set additional capabilities if running tests on a remote machine
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            fo.setCapability("browserName", "firefox");
            fo.setBrowserVersion(prop.getProperty("browserversion").trim());

            Map<String, Object> selenoidOptions = new HashMap<>();
            selenoidOptions.put("screenResolution", "1280x1024x24");
            selenoidOptions.put("enableVNC", true);
            selenoidOptions.put("name", prop.getProperty("testname"));
            fo.setCapability("selenoid:options", selenoidOptions);
        }

        return fo;
    }

    /**
     * Creates and configures EdgeOptions based on the provided properties.
     * 
     * @return EdgeOptions configured with the necessary settings.
     */
    public EdgeOptions getEdgeOptions() {
        eo = new EdgeOptions();

        // Set Edge to run in headless mode if specified in properties
        if (Boolean.parseBoolean(prop.getProperty("headless"))) {
            System.out.println("====Running tests in headless======");
            eo.addArguments("--headless");
        }

        // Set Edge to run in incognito (InPrivate) mode if specified in properties
        if (Boolean.parseBoolean(prop.getProperty("incognito"))) {
            eo.addArguments("--inPrivate");
        }

        // Set additional capabilities if running tests on a remote machine
        if (Boolean.parseBoolean(prop.getProperty("remote"))) {
            eo.setCapability("browserName", "edge");
            // eo.setCapability("enableVNC", true);
        }

        return eo;
    }

    /**
     * OOP Concepts Used:
     * - Encapsulation: Encapsulates the logic for configuring browser options in dedicated methods.
     * - Abstraction: Provides a simplified interface for creating browser options without exposing the underlying complexity.
     * 
     * Methods:
     * - `getChromeOptions()`: Returns a configured `ChromeOptions` object.
     * - `getFirefoxOptions()`: Returns a configured `FirefoxOptions` object.
     * - `getEdgeOptions()`: Returns a configured `EdgeOptions` object.
     * 
     * Access Modifiers:
     * - `public`: Methods that need to be accessible outside the class (e.g., `getChromeOptions`, `getFirefoxOptions`, `getEdgeOptions`).
     * - `private`: Variables that are internal to the class and should not be exposed (e.g., `prop`, `co`, `fo`, `eo`).
     * 
     * Purpose:
     * 
     * 1. **Browser-Specific Options Management**:
     *    - The `OptionsManager` class is designed to create and configure browser-specific options (`ChromeOptions`, `FirefoxOptions`, `EdgeOptions`) based on the provided properties.
     * 
     * 2. **Centralized Configuration Handling**:
     *    - This class centralizes the logic for setting various browser options, ensuring consistency across different parts of the test framework.
     * 
     * 3. **Support for Remote Execution**:
     *    - It includes support for remote execution by setting necessary capabilities like browser version and screen resolution when running tests on a Selenium Grid or a remote machine.
     * 
     * 4. **Dynamic Option Setting**:
     *    - The class dynamically configures browser options such as headless mode, incognito mode, and remote execution based on the properties provided, making it flexible and adaptable to different test scenarios.
     * 
     * 5. **Ease of Maintenance**:
     *    - By encapsulating the browser configuration logic, this class simplifies maintenance and updates, allowing changes to be made in a single location rather than scattered across the test code.
     */
}
