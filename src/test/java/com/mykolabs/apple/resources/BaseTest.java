package com.mykolabs.apple.resources;

import com.mykolabs.apple.selenium.SeleniumManager;
import com.mykolabs.apple.util.PropertiesReader;
import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Prepares data and actions before and after tests.
 *
 * @author nikprix
 */
public class BaseTest extends AllPageObjectNavigation {

    // Logger
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);

    SeleniumManager driverManager;

    @BeforeMethod(alwaysRun = true)
    public void setUpTestPreconditions() throws Exception {
        Reporter.log("Loading specified in the properties browser", true);
        driverManager = SeleniumManager.valueOf();

        log.info("===== Selected Browser ===== " + PropertiesReader.getProperties().getProperty("browserType"));

        driverManager.getAppropriateDriver(PropertiesReader.getProperties().getProperty("browserType"));
        driverManager.loadBaseUrl(PropertiesReader.getProperty("baseUrl"));

        // navigate to SignUp page since all automated cases require this precondition
        navigateToSignUp();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() throws IOException {
        if (PropertiesReader.getPropertyAsBoolean("closeBrowserInstance")) {
            Reporter.log("Closing browser", true);
            driverManager.closeBrowsers();
        }
    }

}
