/**
 * @author Neetha Jose
 * Date : 30/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.helper;

import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;

import java.util.Iterator;
import java.util.Set;

public final class WindowHelper {

    private static final Logger LOGGER = LogManager.getLogger(WindowHelper.class.getName());
    private static final String SWITCHED_TO_CHILD_WINDOW = "Switched to child window";
    private static final String NO_CHILD_WINDOW = "There are no Child Windows";

    private WindowHelper(){

    }
    /**
     * This method will switch to the child window
     * Useful when there is only 1 child window.
     * @author Neetha Jose
     * July 06, 2023
     */

    public static void switchToWindow(WebDriver driver){
        String currentWindow = driver.getWindowHandle();
        Set<String> allWindows = driver.getWindowHandles();
        Iterator<String> i = allWindows.iterator();
        while(i.hasNext()) {
            String childWindow = i.next();
            if (!childWindow.equalsIgnoreCase(currentWindow)) {
                driver.switchTo().window(childWindow);
                LOGGER.info(SWITCHED_TO_CHILD_WINDOW);
            } else {
                LOGGER.info(NO_CHILD_WINDOW);
            }
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
    }

    /**
     * This method is to open a new TAB, switch to that TAB and then navigate to given url
     * @author Neetha Jose
     * July 03, 2023
     */
    public static void switchToNewTab(WebDriver driver){
        driver.switchTo().newWindow(WindowType.TAB);
        Set<String> allWindows = driver.getWindowHandles();
        if(allWindows.size()>1){
            LOGGER.info("Switched to newly opened TAB");
        }
        else{
            LOGGER.info("No new tabs are opened");
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
    }

    public static int totalWindowsPresent(WebDriver driver){
        Set<String> allWindows = driver.getWindowHandles();
        System.out.println("no of windows :"+allWindows.size());
        return allWindows.size();
    }

    /**
     * This method will switch to the specific child window that matches the title.
     * Useful when there are multiple child windows.
     * @author Neetha Jose
     * July 06, 2023
     */
    public static boolean switchToASpecificChildWindow(WebDriver driver, String title){
        // To handle parent window
        String MainWindow = driver.getWindowHandle();
        System.out.println("Main window handle is " + MainWindow);
        // To handle child window
        Set<String> s1 = driver.getWindowHandles();
        System.out.println("Child window handle is" + s1);
        Iterator<String> i1 = s1.iterator();
        while (i1.hasNext()) {
            String ChildWindow = i1.next();
            if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
                driver.switchTo().window(ChildWindow);
                String pageTitle = driver.getTitle();
                System.out.println("The web page title of child window is:" + pageTitle);
                if (pageTitle.contains(title)) {
                    LOGGER.info("Switched to the Specific child window with title: "+pageTitle);
                    break;
                }
            }
        }
        return true;
    }
}
