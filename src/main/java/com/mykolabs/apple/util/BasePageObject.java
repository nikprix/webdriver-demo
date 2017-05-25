package com.mykolabs.apple.util;

import com.mykolabs.apple.selenium.SeleniumManager;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.Annotations;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.LoadableComponent;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Base class for inheriting by page object classes.
 *
 * @author nikprix
 */
public abstract class BasePageObject extends LoadableComponent<BasePageObject> {

    final static Logger logger = LoggerFactory.getLogger(BasePageObject.class);

    public WebDriver driver;

    public BasePageObject() {
        this.driver = SeleniumManager.valueOf().getDriver();
        PageFactory.initElements(driver, this);
    }

    ////////////////////////////////////////////
    ///////////////   Waits   //////////////////
    ////////////////////////////////////////////
    public void waitForPageLoadAndTitleContains(int timeout, String pageTitle) {
        WebDriverWait wait = new WebDriverWait(driver, timeout, 1000);
        wait.until(ExpectedConditions.titleContains(pageTitle));
    }

    public void waitForElementPresence(By locator, int seconds) {
        WebDriverWait wait = new WebDriverWait(driver, seconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public void jsWaitForPageToLoad(int timeOutInSeconds) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsCommand = "return document.readyState";

        // Validate readyState before doing any waits
        if (js.executeScript(jsCommand).toString().equals("complete")) {
            return;
        }

        for (int i = 0; i < timeOutInSeconds; i++) {
            TimeManager.waitInSeconds(3);
            if (js.executeScript(jsCommand).toString().equals("complete")) {
                break;
            }
        }
    }

    /**
     * Gets locator.
     *
     * @param fieldName
     * @return
     */
    public By getBy(String fieldName) {
        try {
            return new Annotations(this.getClass().getDeclaredField(fieldName)).buildBy();
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    /**
     * Clicks on the provided WebElement. If the WebElement is null, test
     * continues and error is logged.
     *
     * @param element
     */
    public void click(WebElement element) {
        if (element == null) {
            errorForNotFoundElement();
            return;
        }
        element.click();
    }

    /**
     * Types provided String into the provided WebElement.
     *
     * @param inputText
     * @param element
     */
    public void type(String inputText, WebElement element) {
        if (element == null) {
            BasePageObject.this.errorForNotFoundElement(element);
            return;
        }
        element.sendKeys(inputText);
    }

    /**
     * Returns the input text value of an element via its byLocator. Error is
     * logged in case if element is not found and test continues.
     *
     * @param byLocator
     * @param attribute
     * @return
     */
    public String getInputText(By byLocator, String attribute) {
        WebElement element = findElementThatIsPresent(byLocator, 0);
        if (element == null) {
            BasePageObject.this.errorForNotFoundElement(byLocator);
            return "";
        }
        return element.getAttribute(attribute);
    }

    /**
     * Returns the text value of an element via its byLocator. Error is logged
     * in case if element is not found and test continues.
     *
     * @param byLocator
     * @return
     */
    public String getElemText(By byLocator) {
        WebElement element = findElementThatIsPresent(byLocator, 0);
        if (element == null) {
            BasePageObject.this.errorForNotFoundElement(byLocator);
            return "";
        }
        return element.getText();
    }

    /**
     * Returns the value of an element's attribute via the byLocator.
     *
     * @param byLocator
     * @param attributeName
     * @return
     */
    public String getAttributeValue(By byLocator, String attributeName) {
        WebElement element = findElementThatIsPresent(byLocator, 0);
        if (element == null) {
            BasePageObject.this.errorForNotFoundElement(byLocator);
            return "";
        }
        return element.getAttribute(attributeName);
    }

    /**
     * Returns boolean state of the visibility of an element by provided
     * locator.
     *
     * @param byLocator
     * @return
     */
    public boolean isDisplayed(By byLocator) {
        return isElementCurrentlyDisplayed(byLocator);
    }

    /**
     * Identifies visibility of the element by provided locator.
     *
     * @param byLocator
     * @return
     */
    private boolean isElementCurrentlyDisplayed(By byLocator) {
        WebElement element = findElementThatIsPresent(byLocator, 0);
        if ((element != null) && element.isDisplayed()) {
            return true;
        }
        return false;
    }

    /**
     * Looks for a visible OR invisible element via the provided locator for up
     * to maxWaitTime. Returns as soon as the element is found.
     *
     * @param byLocator
     * @param maxWaitTime - In seconds
     * @return
     *
     */
    public WebElement findElementThatIsPresent(final By byLocator, int maxWaitTime) {
        if (driver == null) {
            nullDriverNullPointerExeption();
        }
        FluentWait<WebDriver> wait = new FluentWait<>(driver).withTimeout(maxWaitTime, java.util.concurrent.TimeUnit.SECONDS)
                .pollingEvery(200, java.util.concurrent.TimeUnit.MILLISECONDS);

        try {
            return wait.until((WebDriver webDriver) -> {
                List<WebElement> elems = driver.findElements(byLocator);
                if (elems.size() > 0) {
                    return elems.get(0);
                } else {
                    return null;
                }
            });
        } catch (Exception e) {
            return null;
        }
    }

    protected void errorForNotFoundElement(By byLocator) {
        logger.error("Element is not found by provided locator: " + byLocator.toString());
    }

    protected void errorForNotFoundElement(WebElement element) {
        logger.error("Provided WebElement is not found: " + getBy("element").toString());
    }

    protected void errorForNotFoundElement() {
        logger.error("Element is null");
    }

    protected void nullDriverNullPointerExeption() {
        throw new NullPointerException(
                "The Driver object is null. Please check settings and SeleniumManager");
    }
}
