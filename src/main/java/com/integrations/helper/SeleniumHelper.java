/**
 * @author Neetha Jose
 * Date : 30/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.integrations.utils.WaitForSecondsUtils.waitForTaskToComplete;

public final class SeleniumHelper {
    private static final Logger LOGGER = LogManager.getLogger(SeleniumActions.class.getName());

    private SeleniumHelper(){

    }

    public static void navigateToURL(WebDriver driver,String url) {
        waitForTaskToComplete();
        driver.get(url);
        LOGGER.info("Entered url :"+url);
        waitForTaskToComplete();
    }

    public static void pressAnyKey(WebDriver driver, By element, Keys key) {
        waitForTaskToComplete();
        driver.findElement(element).sendKeys(key);
        LOGGER.info("Entered the given KEY : "+key);
    }

}
