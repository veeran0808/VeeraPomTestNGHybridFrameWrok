package com.qa.opencart.factory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.errors.AppError;
import com.qa.opencart.exceptions.BrowserException;
import com.qa.opencart.exceptions.FrameworkException;
import com.qa.opencart.logger.Log;

public class DriverFactory {

    WebDriver driver;
    Properties prop;
    OptionsManager optionsManager;

    public static String highlight;

    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<WebDriver>();

    /**
     * This is used to init the driver on the basis of the given browser name.
     * 
     * @param browserName
     */
    public WebDriver initDriver(Properties prop) {
        String browserName = prop.getProperty("browser");
        //System.out.println("browser name is : " + browserName);
        Log.info("browser name is : " + browserName);

        highlight = prop.getProperty("highlight");

        optionsManager = new OptionsManager(prop);

        switch (browserName.toLowerCase().trim()) {
            case "chrome":
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    // run tcs on remote machine/grid:
                    initRemoteDriver("chrome");
                } else {
                    // run it in local
                    Log.info("Running tests on Local");
                    tlDriver.set(new ChromeDriver(optionsManager.getChromeOptions()));
                }
                break;

            case "firefox":
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    initRemoteDriver("firefox");
                } else {
                    tlDriver.set(new FirefoxDriver(optionsManager.getFirefoxOptions()));
                }
                break;

            case "edge":
                if (Boolean.parseBoolean(prop.getProperty("remote"))) {
                    initRemoteDriver("edge");
                } else {
                    tlDriver.set(new EdgeDriver(optionsManager.getEdgeOptions()));
                }
                break;
            case "safari":
                tlDriver.set(new SafariDriver());
                break;

            default:
                //System.out.println("plz pass the right browser name..." + browserName);
                Log.error("plz pass the right browser name..." + browserName);
                throw new BrowserException(AppError.BROWSER_NOT_FOUND);
        }

        getDriver().manage().deleteAllCookies();
        getDriver().manage().window().maximize();
        Log.info("app url : " + prop.getProperty("url"));
        getDriver().get(prop.getProperty("url"));// loginPage

        return getDriver();

    }

    /**
     * Init remote driver to run test cases on remote (grid) machine
     * 
     * @param browserName
     */
    private void initRemoteDriver(String browserName) {
        System.out.println("Running it on GRID...with browser: " + browserName);

        try {
            switch (browserName) {
                case "chrome":
                    tlDriver.set(
                            new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getChromeOptions()));
                    break;
                case "firefox":
                    tlDriver.set(
                            new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getFirefoxOptions()));
                    break;
                case "edge":
                    tlDriver.set(new RemoteWebDriver(new URL(prop.getProperty("huburl")), optionsManager.getEdgeOptions()));
                    break;

                default:
                    System.out.println("plz pass the right browser on grid..");
                    throw new BrowserException(AppError.BROWSER_NOT_FOUND);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

    }

    /**
     * Get the local thread copy of the driver
     * 
     * @return WebDriver instance
     */
    public static WebDriver getDriver() {
        return tlDriver.get();
    }

    /**
     * This is used to init the properties from the .properties file
     * 
     * @return This returns properties (prop)
     */
    public Properties initProp() {
        prop = new Properties();
        FileInputStream ip = null;
        // mvn clean install -Denv="qa"
        // mvn clean install

        String envName = System.getProperty("env");
        //System.out.println("running test suite on env--->" + envName);
        Log.info("running test suite on env--->" + envName);

        if (envName == null) {
            System.out.println("env name is not given, hence running it on QA environment....");
            try {
                ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
            } catch (FileNotFoundException e) {
                Log.error("file not found", e);
                e.printStackTrace();
            }
        } else {
            try {
                switch (envName.trim().toLowerCase()) {
                    case "qa":
                        ip = new FileInputStream(AppConstants.CONFIG_QA_FILE_PATH);
                        break;
                    case "stage":
                        ip = new FileInputStream(AppConstants.CONFIG_STAGE_FILE_PATH);
                        break;
                    case "dev":
                        ip = new FileInputStream(AppConstants.CONFIG_DEV_FILE_PATH);
                        break;
                    case "uat":
                        ip = new FileInputStream(AppConstants.CONFIG_UAT_FILE_PATH);
                        break;
                    case "prod":
                        ip = new FileInputStream(AppConstants.CONFIG_FILE_PATH);
                        break;

                    default:
                        System.out.println("please pass the right env name.." + envName);
                        throw new FrameworkException("===WRONGENVPASSED===");
                }
            } catch (FileNotFoundException e) {
                Log.error("file not found", e);
                e.printStackTrace();
            }
        }

        try {
            prop.load(ip);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prop;

    }

    /**
     * Take screenshot
     * 
     * @param methodName
     * @return The absolute path of the screenshot file
     */
    public static String getScreenshot(String methodName) {

        // Get the driver instance
        TakesScreenshot screenshotTaker = (TakesScreenshot) getDriver();

        // Take the screenshot and save it to a temporary location
        File srcFile = screenshotTaker.getScreenshotAs(OutputType.FILE);

        // Define the path for the screenshots folder
        String screenshotsDirPath = System.getProperty("user.dir") + "/screenshots";

        // Create the screenshots folder if it doesn't exist
        File screenshotsDir = new File(screenshotsDirPath);
        if (!screenshotsDir.exists()) {
            if (screenshotsDir.mkdirs()) {
                System.out.println("Folder 'screenshots' created successfully at: " + screenshotsDirPath);
            } else {
                System.out.println("Failed to create the folder 'screenshots' at: " + screenshotsDirPath);
            }
        }

        // Define the destination path for the screenshot
        String screenshotPath = screenshotsDirPath + "/" + methodName + "_" + System.currentTimeMillis() + ".png";
        File destination = new File(screenshotPath);

        // Copy the screenshot to the destination path
        try {
            FileHandler.copy(srcFile, destination);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return destination.getAbsolutePath();
    }

    /**
     * OOP Concepts Used:
     * - Encapsulation: Manages the WebDriver instance and its lifecycle within the class.
     * - Abstraction: Hides the complex logic of driver initialization from the user.
     * - Polymorphism: Utilizes the WebDriver interface, allowing for different implementations
     *   (e.g., ChromeDriver, FirefoxDriver) to be used interchangeably.
     * - Inheritance: Indirectly uses inheritance through WebDriver interface implementations
     *   (e.g., ChromeDriver inherits from WebDriver).
     *
     * Methods:
     * - `initDriver(Properties prop)`: Initializes WebDriver based on provided properties.
     * - `initRemoteDriver(String browserName)`: Sets up RemoteWebDriver for grid execution.
     * - `getDriver()`: Retrieves the current thread's WebDriver instance.
     * - `initProp()`: Loads properties from a file based on the environment.
     * - `getScreenshot(String methodName)`: Captures a screenshot and saves it to a file.
     *
     * Access Modifiers:
     * - `public`: Methods that need to be accessible outside the class (e.g., `initDriver`, `initProp`, `getDriver`).
     * - `private`: Methods that are internal to the class and should not be exposed (e.g., `initRemoteDriver`).
     * - `protected`: Not used in this class, but would be used if the class had subclasses that needed access to certain methods.
     *
     * Purpose:
     * 
     * 1. **WebDriver Initialization**:
     *    - The `DriverFactory` class is responsible for setting up the WebDriver instance based on the given browser name and configuration properties. It simplifies the process of initializing browsers like Chrome, Firefox, Edge, and Safari.
     * 
     * 2. **Support for Remote Execution**:
     *    - The class provides functionality to run tests on remote machines or Selenium Grid by initializing `RemoteWebDriver` based on the specified browser and grid URL.
     * 
     * 3. **Environment-Specific Configuration**:
     *    - It handles loading configuration properties from files based on the environment (e.g., QA, Stage, Dev), ensuring that the correct settings are applied.
     * 
     * 4. **Thread-Safe WebDriver Management***:
     *    - Uses `ThreadLocal` to manage WebDriver instances, ensuring thread safety in parallel test execution.
     * 
     * 5. **Utility Functions**:
     *    - Provides utility functions like `getScreenshot` to capture and save screenshots, aiding in debugging and reporting.
     */
}
