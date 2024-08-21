package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

import io.qameta.allure.Step;

/**
 * The LoginPage class represents the login page of the OpenCart application.
 * It provides methods to interact with and retrieve information from the login page.
 */
public class LoginPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    // Page Object Model (POM) locators for the login page
    private By emailId = By.id("input-email");
    private By password = By.id("input-password");
    private By loginBtn = By.xpath("//input[@value='Login']");
    private By forgotPwdLink = By.linkText("Forgotten Password");
    private By registerLink = By.linkText("Register");

    /**
     * Constructor to initialize the LoginPage class with a WebDriver instance.
     * 
     * @param driver The WebDriver instance to interact with the browser.
     */
    public LoginPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    /**
     * Gets the title of the login page.
     * 
     * @return The title of the login page.
     */
    @Step("getting login page title value...")
    public String getLoginPageTitle() {
        String title = eleUtil.waitForTitleToBe(AppConstants.LOGIN_PAGE_TITLE, TimeUtil.DEFAULT_TIME);
        System.out.println("login page title : " + title);
        return title;
    }

    /**
     * Gets the URL of the login page.
     * 
     * @return The URL of the login page.
     */
    @Step("getting login page URL...")
    public String getLoginPageURL() {
        String url = eleUtil.waitForURLContains(AppConstants.LOGIN_PAGE_FRACTION_URL, TimeUtil.DEFAULT_TIME);
        System.out.println("login page url : " + url);
        return url;
    }

    /**
     * Checks if the "Forgot Password" link is present on the login page.
     * 
     * @return true if the "Forgot Password" link is displayed, otherwise false.
     */
    @Step("getting the state of forgot pwd link exist...")
    public boolean checkForgotPwdLinkExist() {
        return eleUtil.doIsDisplayed(forgotPwdLink);
    }

    /**
     * Logs in to the application with the provided username and password.
     * 
     * @param username The username for login.
     * @param pwd The password for login.
     * @return An instance of AccountsPage after a successful login.
     */
    @Step("login to application with username: {0} and password: {1}")
    public AccountsPage doLogin(String username, String pwd) {
        System.out.println("user creds : " + username + ":" + pwd);
        eleUtil.doSendKeys(emailId, username, TimeUtil.DEFAULT_MEDIUM_TIME);
        eleUtil.doSendKeys(password, pwd);
        eleUtil.doClick(loginBtn);
        return new AccountsPage(driver);
    }

    /**
     * Navigates to the registration page.
     * 
     * @return An instance of RegisterPage.
     */
    @Step("navigating to register page...")
    public RegisterPage navigateToRegisterPage() {
        eleUtil.doClick(registerLink, TimeUtil.DEFAULT_TIME);
        return new RegisterPage(driver);
    }

}
