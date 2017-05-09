package com.mykolabs.apple.selenium;

import com.mykolabs.apple.util.FolderManager;
import com.mykolabs.apple.util.WebDriverUtil;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Reporter;

/**
 * Creates Webdriver instance based on requested browser (value pulled from
 * properties file).
 *
 * @author nikprix
 */
public class SeleniumManager implements BrowserDriver {

    public static WebDriver driver;

    /**
     * Default constructor. Does not allow to initialize this class from
     * outside.
     */
    private SeleniumManager() {
    }

    /**
     * Factory method to return new SeleniumManager instance.
     *
     * @return SeleniumManager
     */
    public static SeleniumManager valueOf() {
        return new SeleniumManager();
    }

    /**
     * Gets the driver object. Used for BasePageObject initialization.
     *
     * @return WebDriver
     */
    @Override
    public WebDriver getDriver() {
        return driver;
    }

    /**
     * Returns new Browser instance by provided browser name.
     *
     * @param browser
     * @return WebDriver - Chrome or FF instances configured for now.
     */
    @Override
    public WebDriver getAppropriateDriver(String browser) {

        if (browser.equalsIgnoreCase("firefox")) {
            // no need to download Firefox binary since we are using 
            // Maven dependency: https://github.com/bonigarcia/webdrivermanager
            // fixes current issue with launching Firefox
            FirefoxDriverManager.getInstance().setup("0.14.0");
            //FirefoxDriverManager.getInstance().setup();

            driver = new FirefoxDriver();

        } else if (browser.equalsIgnoreCase("Chrome")) {
            // no need to download Chrome binary since we are using 
            // Maven dependency: https://github.com/bonigarcia/webdrivermanager
            ChromeDriverManager.getInstance().setup();

            // ! Chrome sometimes does not start in full screen on Mac
            // this fix might make it start by default
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            DesiredCapabilities cap = DesiredCapabilities.chrome();
            cap.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new ChromeDriver(cap);

        }
        // trying to maximize window using standard methods
        driver.manage().window().maximize();
        // maxinmizing screen using custom method:
        WebDriverUtil.maximizeScreen(driver);
        // launchin browser instance with no cookies
        driver.manage().deleteAllCookies();
        
        return driver;
    }

    /**
     * Loads base URL in the browser instance.
     *
     * @param baseUrl
     */
    public void loadBaseUrl(String baseUrl) {
        // simple validation
        if (driver != null) {
            try {
                driver.get(baseUrl);
            } catch (Exception e) {
            }

        }
    }

    /**
     * Quits the driver and closes the browser(s).
     */
    public void closeBrowsers() {
        try {
            // simple validation
            if (driver != null) {
                driver.quit();
            }
        } catch (Exception e) {
        }
    }

    /**
     * Takes screenshot of the page.
     */
    public void takeScreenShot() {
        
        Reporter.log("Taking Screenshot of the page", true);

        File file = ((org.openqa.selenium.TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        
        try {
            // Creating filename for the screenshot
            java.util.Date date = new java.util.Date();
            String timestamp = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss").format(date.getTime());
            timestamp = timestamp.replace(":", "_");

            // providing folder for screenshots saving
            String fileName = StringUtils.join(new String[]{FolderManager.createScreensDir(FolderManager.getUserDesktopDirPath(), "Test_Screenshots"), timestamp + "_"
                + getClass().getSimpleName() + ".png"}, File.separator);

            FileUtils.copyFile(file, new File(fileName));
        } catch (IOException exception) {
        }
    }

}
