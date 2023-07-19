/**
 * @author Neetha Jose
 * Date : 17/07/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public final class SeleniumActions {

    private static final Logger LOGGER = LogManager.getLogger(SeleniumHelper.class.getName());

    private SeleniumActions(){

    }
    private static Actions getActions(WebDriver driver) {
        return new Actions(driver);
    }

    public static void moveToHiddenElementAndClick(WebDriver driver, By by1, By by2) {
        LOGGER.info("performing move to element and then click");
        getActions(driver).moveToElement(driver.findElement(by1)).moveToElement(driver.findElement(by2)).click().build().perform();
    }
}
