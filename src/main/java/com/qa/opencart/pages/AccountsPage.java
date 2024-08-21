package com.qa.opencart.pages;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

/**
 * The AccountsPage class represents the account page of the OpenCart application.
 * It provides methods to interact with and retrieve information from the accounts page.
 */
public class AccountsPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    /**
     * Constructor to initialize the AccountsPage class with a WebDriver instance.
     * 
     * @param driver The WebDriver instance to interact with the browser.
     */
    public AccountsPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    // Locator for logout link
    private By logoutLink = By.linkText("Logout");

    // Locator for page headers
    private By headers = By.cssSelector("div#content h2");

    // Locator for search field
    private By search = By.name("search");

    // Locator for search button
    private By searchIcon = By.cssSelector("div#search button");

    /**
     * Gets the title of the accounts page.
     * 
     * @return The title of the accounts page.
     */
    public String getAccPageTitle() {
        String title = eleUtil.waitForTitleToBe(AppConstants.ACCOUNTS_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
        System.out.println("Acc page title : " + title);
        return title;
    }

    /**
     * Gets the URL of the accounts page.
     * 
     * @return The URL of the accounts page.
     */
    public String getAccPageURL() {
        String url = eleUtil.waitForURLContains(AppConstants.ACC_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
        System.out.println("Acc page url : " + url);
        return url;
    }

    /**
     * Checks if the logout link is present on the accounts page.
     * 
     * @return true if the logout link is displayed, otherwise false.
     */
    public boolean isLogoutLinkExist() {
        return eleUtil.doIsDisplayed(logoutLink);
    }

    /**
     * Retrieves the headers displayed on the accounts page.
     * 
     * @return A list of header texts from the accounts page.
     */
    public List<String> getAccPageHeaders() {
        List<WebElement> headersList = eleUtil.waitForVisibilityOfElemenetsLocated(headers, TimeUtil.DEFAULT_MEDIUM_TIME);
        List<String> headersValList = new ArrayList<String>();
        for (WebElement e : headersList) {
            String text = e.getText();
            headersValList.add(text);
        }
        return headersValList;
    }

    /**
     * Checks if the search field is present on the accounts page.
     * 
     * @return true if the search field is displayed, otherwise false.
     */
    public boolean isSearchExist() {
        return eleUtil.doIsDisplayed(search);
    }

    /**
     * Performs a search operation on the accounts page.
     * 
     * @param searchKey The search keyword to be entered in the search field.
     * @return An instance of SearchResultsPage if search field is present, otherwise null.
     */
    public SearchResultsPage doSearch(String searchKey) {
        System.out.println("Searching for product: " + searchKey);

        if (isSearchExist()) {
            eleUtil.doSendKeys(search, searchKey);
            eleUtil.doClick(searchIcon);
            return new SearchResultsPage(driver);
        } else {
            System.out.println("Search field is not present on the page");
            return null;
        }
    }
}
