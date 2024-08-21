package com.qa.opencart.listeners;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Calendar;
import java.util.Date;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.qa.opencart.factory.DriverFactory;

/**
 * The ExtentReportListener class implements the ITestListener interface from TestNG.
 * It integrates with ExtentReports to generate detailed test execution reports.
 * This listener manages the creation, updating, and flushing of ExtentReports based on test execution results.
 */
public class ExtentReportListener implements ITestListener {

    // Directory for storing the report
    private static final String OUTPUT_FOLDER = "./reports/";
    // File name for the report
    private static final String FILE_NAME = "TestExecutionReport.html";

    // Static instance of ExtentReports to manage report generation
    private static ExtentReports extent = init();
    // ThreadLocal to store ExtentTest instances for each test method
    public static ThreadLocal<ExtentTest> test = new ThreadLocal<ExtentTest>();
    private static ExtentReports extentReports;

    /**
     * Initializes the ExtentReports instance and sets up the report configuration.
     * Creates the output directory if it does not exist.
     * 
     * @return The initialized ExtentReports instance.
     */
    private static ExtentReports init() {
        Path path = Paths.get(OUTPUT_FOLDER);
        // Check if the directory exists, if not, create it
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                // Log and handle directory creation failure
                e.printStackTrace();
            }
        }
        
        extentReports = new ExtentReports();
        ExtentSparkReporter reporter = new ExtentSparkReporter(OUTPUT_FOLDER + FILE_NAME);
        reporter.config().setReportName("Open Cart Automation Test Results");
        extentReports.attachReporter(reporter);
        extentReports.setSystemInfo("System", "MAC");
        extentReports.setSystemInfo("Author", "Naveen AutomationLabs");
        extentReports.setSystemInfo("Build#", "1.1");
        extentReports.setSystemInfo("Team", "OpenCart QA Team");
        extentReports.setSystemInfo("ENV NAME", System.getProperty("env"));

        return extentReports;
    }

    /**
     * Invoked before the start of the test suite.
     * 
     * @param context The test context for the suite.
     */
    @Override
    public synchronized void onStart(ITestContext context) {
        System.out.println("Test Suite started!");
    }

    /**
     * Invoked after the end of the test suite.
     * 
     * @param context The test context for the suite.
     */
    @Override
    public synchronized void onFinish(ITestContext context) {
        System.out.println("Test Suite is ending!");
        // Flush the extent reports to save the report
        extent.flush();
        test.remove();
    }

    /**
     * Invoked when a test method is started.
     * 
     * @param result The result of the test method.
     */
    @Override
    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        String qualifiedName = result.getMethod().getQualifiedName();
        int last = qualifiedName.lastIndexOf(".");
        int mid = qualifiedName.substring(0, last).lastIndexOf(".");
        String className = qualifiedName.substring(mid + 1, last);

        System.out.println(methodName + " started!");
        // Create a new test entry in the report
        ExtentTest extentTest = extent.createTest(result.getMethod().getMethodName(),
                result.getMethod().getDescription());
        extentTest.assignCategory(result.getTestContext().getSuite().getName());
        extentTest.assignCategory(className);
        test.set(extentTest);
        test.get().getModel().setStartTime(getTime(result.getStartMillis()));
    }

    /**
     * Invoked when a test method succeeds.
     * 
     * @param result The result of the test method.
     */
    @Override
    public synchronized void onTestSuccess(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        System.out.println(methodName + " passed!");
        // Mark the test as passed in the report
        test.get().pass("Test passed");
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    /**
     * Invoked when a test method fails.
     * 
     * @param result The result of the test method.
     */
    @Override
    public synchronized void onTestFailure(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " failed!");
        String methodName = result.getMethod().getMethodName();
        // Mark the test as failed in the report and add a screenshot
        test.get().fail("Test failed");
        test.get().fail(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    /**
     * Invoked when a test method is skipped.
     * 
     * @param result The result of the test method.
     */
    @Override
    public synchronized void onTestSkipped(ITestResult result) {
        System.out.println(result.getMethod().getMethodName() + " skipped!");
        String methodName = result.getMethod().getMethodName();
        // Mark the test as skipped in the report and add a screenshot
        test.get().skip("Test skipped");
        test.get().skip(result.getThrowable(), MediaEntityBuilder.createScreenCaptureFromPath(DriverFactory.getScreenshot(methodName), methodName).build());
        test.get().getModel().setEndTime(getTime(result.getEndMillis()));
    }

    /**
     * Invoked when a test method fails but is within the success percentage.
     * 
     * @param result The result of the test method.
     */
    @Override
    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        System.out.println("onTestFailedButWithinSuccessPercentage for " + result.getMethod().getMethodName());
    }

    /**
     * Converts milliseconds to Date object for setting start and end times in the report.
     * 
     * @param millis The time in milliseconds.
     * @return The Date object representing the time.
     */
    private Date getTime(long millis) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);
        return calendar.getTime();
    }
}
