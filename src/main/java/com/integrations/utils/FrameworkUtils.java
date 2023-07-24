/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.utils;

import com.integrations.helper.ExplicitWaitHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;


public class FrameworkUtils {
    private WebDriver driver;
    ExplicitWaitHelper explicitWaitHelper;
    private WebDriverWait wait;

    public void sendKeys(WebElement element, String value) {
//        waitForTaskToComplete();
        try {
            explicitWaitHelper.visibilityOfElement(wait, element).clear();
//            System.out.println("value:"+value);
//            waitForTaskToComplete();
            explicitWaitHelper.visibilityOfElement(wait, element).sendKeys(value);
        }
//        catch (ElementNotInteractableException | StaleElementReferenceException exception) {
//            System.out.println("inside catch");
//            LOGGER.info("Warning :: element was not clicked \n" + exception.getMessage());
//            for (long i = 0; i < Integer.parseInt(PropertyUtils.get("custom_timeout")); i++) {
//                try {
//                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
//                    element.clear();
//                    element.sendKeys(value);
//                    break;
//                } catch (ElementNotInteractableException | StaleElementReferenceException exception1) {
//                    LOGGER.info("Warning :: " + exception1.getMessage() + " Try " + i);
//                }
//            }
//        }
        catch (WebDriverException e) {
//            LOGGER.error(NOT_ABLE_TO_PERFORM_SEND_KEY_BECAUSE + e.getMessage());
//            ExtentLogger.fail(e.fillInStackTrace());
        }
//        waitForTaskToComplete();
    }

}
