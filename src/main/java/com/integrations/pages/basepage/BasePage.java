/**
 * @author Neetha Jose
 * Date : 21/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.basepage;
import com.google.common.util.concurrent.Uninterruptibles;
import com.integrations.exceptions.FrameworkWebDriverException;
import com.integrations.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;
import static com.integrations.constants.FrameworkConstants.*;
import static com.integrations.helper.ExplicitWaitHelper.*;
import static com.integrations.utils.PropertyUtils.get;
import static com.integrations.utils.WaitForSecondsUtils.waitForTaskToComplete;

public class BasePage {

    private static final Logger LOGGER = LogManager.getLogger(BasePage.class.getName());

    protected WebDriver driver;
    protected WebDriverWait wait;
    private WebDriverWait waitForSecond;

    protected BasePage(WebDriver driver) {
        this.driver = driver;
        wait = new WebDriverWait(this.driver, Duration.ofSeconds(Long.parseLong(get("explicitwaittimeout"))));
        waitForSecond = new WebDriverWait(driver, Duration.ofSeconds(1));
    }

    protected boolean waitForElement(WebElement element) {
        waitForTaskToComplete();
        return isDisplayed(element);
    }
    /**
     * This method is to check whether the element is displayed or not
     *
     * @param element is web element that needs to check whether its displayed
     * @return the boolean result of element displayed
     * @author Neetha Jose
     * June 26, 2023
     */
    protected boolean isDisplayed(WebElement element) {
        waitForTaskToComplete();
        boolean status = false;
        try {
            status = visibilityOfElement(wait, element).isDisplayed();
        } catch (TimeoutException e) {
            return false;
        } catch (ElementNotInteractableException | StaleElementReferenceException exception) {
            LOGGER.info("Warning :: element was not clicked \n" + exception.getMessage());
            for (long i = 0; i < Integer.parseInt(get("custom_timeout")); i++) {
                try {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                    status = element.isDisplayed();
                    break;
                } catch (ElementNotInteractableException | StaleElementReferenceException exception1) {
                    LOGGER.info("Warning :: " + exception1.getMessage() + " Try " + i);
                }
            }
        } catch (WebDriverException e) {
            LOGGER.error(ELEMENT_IS_NOT_VISIBLE + e.getMessage());
            throw new FrameworkWebDriverException(ELEMENT_IS_NOT_VISIBLE + e.getMessage());
        }
        waitForTaskToComplete();
        return status;
    }

    /**
     * Waits for element by given wait strategy, performs the clicking operation on element
     *
     * @param by Name of the locator that will be clicked
     * @author Neetha Jose
     * June 26, 2023
     */
    protected void click(By by) {
        try {
            elementToBeClickable(wait, by).click();
        } catch (ElementNotInteractableException | StaleElementReferenceException exception) {
            LOGGER.info("Warning :: element was not clicked "); //+ exception.getMessage());
            for (long i = 0; i < Integer.parseInt(get("custom_timeout")); i++) {
                try {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                    elementToBeClickable(waitForSecond, by).click();
                    break;
                } catch (ElementNotInteractableException | StaleElementReferenceException |
                         TimeoutException exception1) {
                    LOGGER.info("Warning :: " + exception1.getMessage() + " Try " + i);
                }
            }
        } catch (WebDriverException e) {
            LOGGER.error(NOT_ABLE_TO_PERFORM_CLICK_BECAUSE + e.getMessage());

        }
        waitForTaskToComplete(3);
    }

    /**
     * Use this method to simulate typing into an element, which may set its value.
     * Waits for the element by given wait strategy, performs the operation on element
     * clear the text filed and perform the send key operation
     *
     * @param locator Locator of the element that is for send key
     * @param value   value to be sent in the text box
     * @author Neetha Jose
     * June 30, 2023
     */
    protected void sendKeys(By locator, String value) {
        try {
            presenceOfElement(wait, locator).clear();
            waitForTaskToComplete();
            presenceOfElement(wait, locator).sendKeys(value);
        } catch (ElementNotInteractableException | StaleElementReferenceException exception) {
            LOGGER.info("Warning :: element was not clicked \n" + exception.getMessage());
            for (long i = 0; i < Integer.parseInt(PropertyUtils.get("custom_timeout")); i++) {
                try {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                    presenceOfElement(waitForSecond, locator).sendKeys(value);
                    break;
                } catch (ElementNotInteractableException | StaleElementReferenceException |
                         TimeoutException exception1) {
                    LOGGER.info("Warning :: " + exception1.getMessage() + " Try " + i);
                }
            }
        } catch (WebDriverException e) {
            LOGGER.error(NOT_ABLE_TO_PERFORM_SEND_KEY_BECAUSE + e.getMessage());
        }
        waitForTaskToComplete(3);
    }

    /**
     * The Text of the element.
     *
     * @param element is name of web element to get the text
     * @return Return the web element text where the selenium is currently interacting.
     * @author Neetha Jose
     * July 03, 2023
     */
    protected String getText(WebElement element) {
        waitForTaskToComplete();
        String text = "";
        try {
            text = visibilityOfElement(wait, element).getText();
            LOGGER.info("text of element : " + text);
        } catch (ElementNotInteractableException | StaleElementReferenceException e1) {
            LOGGER.info("Warning :: element was not clicked \n" + e1.getMessage());
            for (long i = 0; i < Integer.parseInt(PropertyUtils.get("custom_timeout")); i++) {
                try {
                    Uninterruptibles.sleepUninterruptibly(1, TimeUnit.SECONDS);
                    text = element.getText();
                    LOGGER.info("text of element : " + text);
                    break;
                } catch (ElementNotInteractableException | StaleElementReferenceException e2) {
                    LOGGER.error("Not able to get text because ====>>" + e2.getMessage());
                }
            }
        } catch (WebDriverException e) {
            LOGGER.error("Not able to get text because ====>>" + e.getMessage());
        }
        waitForTaskToComplete();
        return text;
    }

    public WebElement getElement(By by) {
        try {
            return (new WebDriverWait(this.driver, Duration.ofSeconds(Long.parseLong(get("explicitwaittimeout"))))).until(ExpectedConditions.visibilityOfElementLocated(by));
        } catch (org.openqa.selenium.TimeoutException te) {
            return driver.findElement(by);
        }
    }

    public WebElement getElement(WebElement element, By by) {
        try {
            return new WebDriverWait(this.driver, Duration.ofSeconds(Long.parseLong(get("medium_timeout")))).until(ExpectedConditions.visibilityOf(element)).findElement(by);
        } catch (Exception e) {
        }
        return element.findElement(by);
    }

    /**
     * Get all elements with identifier.
     * @param by Name of the locator that will be clicked
     * @return Return all the web elements that is matching with the locator.
     * @author Neetha Jose
     * July 18, 2023
     */
    public List<WebElement> getAllElements(By by) {
        try {
            (new WebDriverWait(this.driver, Duration.ofSeconds(Long.parseLong(get("medium_timeout"))))).until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
        } catch (org.openqa.selenium.TimeoutException te) {
            //do nothing
        }
        List<WebElement> elements = this.driver.findElements(by);
        return elements;
    }

}
