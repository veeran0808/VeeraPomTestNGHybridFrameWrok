package com.qa.opencart.listeners;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

/**
 * The Retry class implements the IRetryAnalyzer interface from TestNG.
 * It provides functionality to retry failed test methods a specified number of times before ultimately marking them as failed.
 */
public class Retry implements IRetryAnalyzer {
	
    // The count of current retry attempts
	private int count = 0;
    // Maximum number of retry attempts allowed
	private static int maxTry = 3;

    /**
     * Determines if the test method should be retried.
     * 
     * @param iTestResult The result of the test method.
     * @return true if the test should be retried, false otherwise.
     */
	@Override
	public boolean retry(ITestResult iTestResult) {
		if (!iTestResult.isSuccess()) { // Check if test failed
			if (count < maxTry) { // Check if retry count is within the limit
				count++; // Increment the retry count
				iTestResult.setStatus(ITestResult.FAILURE); // Explicitly mark test as failed
				return true; // Indicate that the test should be retried
			} else {
				iTestResult.setStatus(ITestResult.FAILURE); // If retry count exceeded, mark test as failed
			}
		} else {
			iTestResult.setStatus(ITestResult.SUCCESS); // If test passes, mark it as successful
		}
		return false; // Indicate that no further retries are needed
	}
}
