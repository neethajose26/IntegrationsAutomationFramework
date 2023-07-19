/**
 * @author Neetha Jose
 * Date : 03/07/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static com.integrations.utils.WaitForSecondsUtils.waitForTaskToComplete;

public class FrameHelper {
    private static final Logger LOGGER = LogManager.getLogger(FrameHelper.class.getName());

    private FrameHelper() {
    }

    /**
     * this method will switchToFrame based on frame index
     *
     * @param frameIndex is parameter to switch the frame by index
     * @param driver     is for driver to operate frame
     * @author Neetha Jose
     * July 03, 2023
     */
    public static void switchToFrame(WebDriver driver, int frameIndex) {
        driver.switchTo().frame(frameIndex);
        LOGGER.info("switched to :" + frameIndex + " frame");
    }

    /**
     * this method will switchToFrame based on frame name
     *
     * @param frameName is parameter to switch the frame by name
     * @param driver    is for driver to operate frame
     * @author Neetha Jose
     * July 03, 2023
     */
    public static void switchToFrame(WebDriver driver, String frameName) {
        driver.switchTo().frame(frameName);
        LOGGER.info("switched to :" + frameName + " frame");
    }

    /**
     * This method will switchToFrame based on frame WebElement when there is no Frame Id or Frame Name.
     *
     * @param element for switch frame using element
     * @param driver  is for driver to operate frame
     * @author Neetha Jose
     * July 03, 2023
     */
    public static void switchToFrame(WebDriver driver, WebElement element) {
        driver.switchTo().frame(element);
        LOGGER.info("switched to frame");
        waitForTaskToComplete(3);
    }

    /**
     * This method will switch driver from frame to default content
     *
     * @param driver local Driver for frame switch operation
     * @author Neetha Jose
     * July 03, 2023
     */
    public static void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
        LOGGER.info("Switched to Default Content");
        waitForTaskToComplete(3);
    }

    /**
     * This method will iterates all the iframes in the page, find the required frame and switch to it using its index
     * and then click on the element present in that frame.
     * This is useful if we CANNOT switch using frameId/Name/WebElement
     * @param by for switch frame using element
     * @param driver  is for driver to operate frame
     * @author Neetha Jose
     * July 03, 2023
     */
    public static boolean findAFrameAndSwitchToIt(WebDriver driver, By by) {
        boolean flag = false;
        int size = driver.findElements(By.tagName("iframe")).size();
        for(int i=0; i<=size; i++)
        {
            driver.switchTo().frame(i);
            int total=driver.findElements(by).size();
            System.out.println(total);
            if(total==1){
                flag = true;
                break;
            }
            else{
                driver.switchTo().defaultContent();
            }
        }
            return flag;
    }
}
