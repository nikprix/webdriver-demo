package com.mykolabs.apple.pageobjects;

import com.mykolabs.apple.util.BasePageObject;
import com.mykolabs.apple.util.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Page object class which represents 'Instructions' page.
 * @author nikprix
 */
public class InstructionsPageImpl extends BasePageObject implements InstructionsPage{

    @FindBy(linkText = "Email Subscriptions")
    WebElement subscribeLink;

    final By mainHeaderTitle = By.id("main-title");

    @Override
    protected void load() {
        System.out.println("load() " + this.getClass().getName());
    }

    @Override
    protected void isLoaded() throws Error {
        System.out.println("isLoaded()  " + this.getClass().toString());
        // verification of the page loading status
        waitForPageLoadAndTitleContains(TimeUnit.SEC_30.getSeconds(), INSTRUCTIONS_PAGE_TITLE);
        waitForElementPresence(mainHeaderTitle, TimeUnit.SEC_30.getSeconds());
        jsWaitForPageToLoad(TimeUnit.SEC_30.getSeconds());
    }

    @Override
    public void loadEmailSubscriptions() {
        click(subscribeLink);
    }

}
