package com.mykolabs.apple.selenium;

import org.openqa.selenium.WebDriver;

/**
 * Interface for creating Webdriver instance with required browser driver.
 *
 * @author nikprix
 */
public interface BrowserDriver {

    WebDriver getDriver();
    WebDriver getAppropriateDriver(String browser);

}
