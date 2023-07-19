/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.helper;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.awt.*;
import java.util.List;

public final class ExplicitWaitHelper {

    public static WebElement presenceOfElement(WebDriverWait wait, By element) {
        return wait.until(ExpectedConditions.presenceOfElementLocated(element));
    }
//    public static WebElement frameToBeAvailable(WebDriverWait wait, By locator) {
//        return wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(locator));
//    }

    public static List<WebElement> visibilityOfAllElements(WebDriverWait wait, List<WebElement> elements){
        return wait.until(ExpectedConditions.visibilityOfAllElements(elements));
    }

    public static WebElement elementToBeClickable(WebDriverWait wait, WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public static WebElement visibilityOfElement(WebDriverWait wait, WebElement element) {
        try {
            return wait.until(ExpectedConditions.visibilityOf(element));
        }catch (StaleElementReferenceException e){
            return wait.until(ExpectedConditions.visibilityOf(element));
        }
    }
    public static WebElement visibilityOfElement(WebDriverWait wait, By element) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }catch (StaleElementReferenceException e){
            return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        }
    }
    public static Alert isAlertIsPresent(WebDriverWait wait){
        return wait.until(ExpectedConditions.alertIsPresent());
    }

    public static boolean waitForInvisibilityOfElement(WebDriverWait wait,WebElement element){
        return wait.until(ExpectedConditions.invisibilityOf(element));
    }

}
