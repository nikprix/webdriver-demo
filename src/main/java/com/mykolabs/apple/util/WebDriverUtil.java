package com.mykolabs.apple.util;

import java.awt.Toolkit;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Contains helper methods for Webdriver.
 *
 * @author mykola.prykhodko
 */
public class WebDriverUtil {

    private static final Logger log = LoggerFactory.getLogger(WebDriverUtil.class);

    /**
     * Maximizes Chrome. Used when verifying on MacOSX
     *
     * @param driver
     */
    public static void maximizeScreen(WebDriver driver) {
        java.awt.Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Point position = new Point(0, 0);
        driver.manage().window().setPosition(position);
        Dimension maximizedScreenSize
                = new Dimension((int) screenSize.getWidth(), (int) screenSize.getHeight());
        driver.manage().window().setSize(maximizedScreenSize);
    }

    /**
     * Detects OS of the user.
     *
     * @return
     */
    public static String OSDetector() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return "Windows";
        } else if (os.contains("nux") || os.contains("nix")) {
            return "Linux";
        } else if (os.contains("mac")) {
            return "Mac";
        } else if (os.contains("sunos")) {
            return "Solaris";
        } else {
            return "Other";
        }
    }

}
