package com.qa.opencart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import com.qa.opencart.constants.AppConstants;
import com.qa.opencart.utils.ElementUtil;
import com.qa.opencart.utils.TimeUtil;

/**
 * The RegisterPage class represents the registration page of the OpenCart application.
 * It provides methods to interact with the registration form and submit user registration details.
 */
public class RegisterPage {

    private WebDriver driver;
    private ElementUtil eleUtil;

    // Locator definitions for the registration form fields and buttons
    private By firstName = By.id("input-firstname");
    private By lastName = By.id("input-lastname");
    private By email = By.id("input-email");
    private By telephone = By.id("input-telephone");
    private By password = By.id("input-password");
    private By confirmpassword = By.id("input-confirm");

    private By subscribeYes = By.xpath("(//label[@class='radio-inline'])[position()=1]/input[@type='radio']");
    private By subscribeNo = By.xpath("(//label[@class='radio-inline'])[position()=2]/input[@type='radio']");

    private By agreeCheckBox = By.name("agree");
    private By continueButton = By.xpath("//input[@type='submit' and @value='Continue']");

    private By successMessg = By.cssSelector("div#content h1");
    private By logoutLink = By.linkText("Logout");
    private By registerLink = By.linkText("Register");

    /**
     * Constructor to initialize the RegisterPage class with a WebDriver instance.
     * 
     * @param driver The WebDriver instance to interact with the browser.
     */
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        eleUtil = new ElementUtil(driver);
    }

    /**
     * Registers a new user by filling out the registration form and submitting it.
     * 
     * @param firstName   The user's first name.
     * @param lastName    The user's last name.
     * @param email       The user's email address.
     * @param telephone   The user's telephone number.
     * @param password    The user's password.
     * @param subscribe   The subscription preference ("yes" or "no").
     * @return            True if registration is successful, otherwise false.
     */
    public boolean userRegister(String firstName, String lastName, String email, String telephone, String password,
            String subscribe) {

        // Fill out the registration form
        eleUtil.doSendKeys(this.firstName, firstName, TimeUtil.DEFAULT_MEDIUM_TIME);
        eleUtil.doSendKeys(this.lastName, lastName);
        eleUtil.doSendKeys(this.email, email);
        eleUtil.doSendKeys(this.telephone, telephone);
        eleUtil.doSendKeys(this.password, password);
        eleUtil.doSendKeys(this.confirmpassword, password);

        // Handle subscription preference
        if (subscribe.equalsIgnoreCase("yes")) {
            eleUtil.doClick(subscribeYes);
        } else {
            eleUtil.doClick(subscribeNo);
        }

        // Agree to terms and submit the form
        eleUtil.doClick(agreeCheckBox);
        eleUtil.doClick(continueButton);

        // Verify registration success
        String successMesg = eleUtil.waitForElementVisible(successMessg, TimeUtil.DEFAULT_MEDIUM_TIME).getText();
        System.out.println(successMesg);

        // Check if the success message matches the expected message
        if (successMesg.contains(AppConstants.USER_REGISTER_SUCCESS_MESSG)) {
            eleUtil.doClick(logoutLink);
            eleUtil.doClick(registerLink);
            return true;
        } else {
            return false;
        }
    }
}
