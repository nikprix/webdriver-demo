package com.mykolabs.apple.pageobjects;

import com.mykolabs.apple.util.BasePageObject;
import com.mykolabs.apple.util.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 *
 * @author nikprix
 */
public class SignupPageImpl extends BasePageObject implements SignupPage {

    @FindBy(id = "submitData")
    WebElement btnSubscribe;

    @FindBy(id = "emailAddress")
    WebElement emailField;

    @FindBy(id = "confirmEmailAddress")
    WebElement confirmEmailField;

    @FindBy(id = "dmPermission")
    WebElement ckboxAnnouncements;

    final By btnSubscribeLocator = By.id("submitData");
    final By signupConfirmationEmail = By.id("address");
    final By emailFieldLocator = By.id("emailAddress");
    final By confirmationEmailFieldLocator = By.id("confirmEmailAddress");

    @Override
    protected void load() {
        System.out.println("load() " + this.getClass().getName());
    }

    @Override
    protected void isLoaded() throws Error {
        System.out.println("isLoaded()  " + this.getClass().toString());
        waitForPageLoadAndTitleContains(TimeUnit.SEC_30.getSeconds(), SIGNUP_PAGE_TITLE);
        waitForElementPresence(emailFieldLocator, TimeUnit.SEC_30.getSeconds());
        jsWaitForPageToLoad(TimeUnit.SEC_30.getSeconds());
    }

    @Override
    public void subsribeButtonClick() {
        click(btnSubscribe);
    }

    @Override
    public void preferenceChckBxClick() {
        click(ckboxAnnouncements);
    }

    @Override
    public void enterEmail(String email) {
        type(email, emailField);
    }

    @Override
    public void confirmEmail(String email) {
        type(email, confirmEmailField);
    }

    @Override
    public void submitEmail(String email) {
        enterEmail(email);
        confirmEmail(email);
        subsribeButtonClick();
    }
    
        @Override
    public void submitDifferentEmails(String email1, String email2) {
        enterEmail(email1);
        confirmEmail(email2);
        subsribeButtonClick();
    }

    @Override
    public boolean isEmailAccepted(String email) {
        // since it was selected to check valid submission on success message appearance,
        // if success message is not appearing within specified time - exception is thrown, 
        // which needs to be handled (returning false in our case)
        try {
            waitForElementPresence(signupConfirmationEmail, TimeUnit.SEC_3.getSeconds());
        } catch (TimeoutException | NoSuchElementException ex) {
            return false;
        }
        // validates displaying of 'Thank you...' message and presence of submitted email value in it.
        return isDisplayed(signupConfirmationEmail) && getElemText(signupConfirmationEmail).equals(email);
    }

    @Override
    public int extractedEmailValueLength() {
        return getInputText(emailFieldLocator, "value").length();
    }

    @Override
    public String sibscribeBtnClassValue() {
        return getAttributeValue(btnSubscribeLocator, "class");
    }

}
