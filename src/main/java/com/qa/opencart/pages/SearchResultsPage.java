package com.qa.opencart.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

/**
 * The SearchResultsPage class represents the search results page of the OpenCart application.
 * It provides methods to interact with and retrieve information from the search results.
 */
public class SearchResultsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    // Locator for search result items
    private By searchResult = By.cssSelector("div.product-thumb");

    /**
     * Constructor to initialize the SearchResultsPage class with a WebDriver instance.
     * 
     * @param driver The WebDriver instance to interact with the browser.
     */
    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    /**
     * Gets the count of search results displayed on the search results page.
     * 
     * @return The count of search result items.
     */
    public int getSearchResultsCount() {
        // Wait for the visibility of search result elements and get their count
        List<WebElement> resultsList = 
                eleUtil.waitForVisibilityOfElemenetsLocated(searchResult, TimeUtil.DEFAULT_MEDIUM_TIME);
        int resultCount = resultsList.size(); // Number of search results
        System.out.println("Product search results count ====" + resultCount);
        return resultCount;
    }

    /**
     * Selects a product from the search results based on the product name and navigates to the ProductInfoPage.
     * 
     * @param productName The name of the product to select.
     * @return A ProductInfoPage instance representing the product's details page.
     */
    public ProductInfoPage selectProduct(String productName) {
        // Click on the link with the product name and navigate to the product info page
        eleUtil.doClick(By.linkText(productName), TimeUtil.DEFAULT_TIME);
        return new ProductInfoPage(driver);
    }
}
