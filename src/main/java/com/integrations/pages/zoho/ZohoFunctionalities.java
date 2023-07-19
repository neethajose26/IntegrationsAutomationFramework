/**
 * @author Neetha Jose
 * Date : 10/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.zoho;
import com.integrations.constants.FrameworkConstants;
import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.WindowHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ZohoFunctionalities extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(ZohoFunctionalities.class.getName());
    public final By tabClosedActivities = By.linkText("Closed Activities");

    public ZohoFunctionalities(WebDriver driver) {
        super(driver);
    }

    public ZohoFunctionalities clickOnClosedActivities(){
        boolean isSwitchedToChildWindow = WindowHelper.switchToASpecificChildWindow(driver, FrameworkConstants.getZohoTitle());
        LOGGER.info("isSwitchedToChildWindow "+isSwitchedToChildWindow);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
        if(isSwitchedToChildWindow){
            ExplicitWaitHelper.visibilityOfElement(wait,tabClosedActivities);
            click(tabClosedActivities);
            LOGGER.info("Clicked on Closed Activities - Zoho");
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(7);
        return this;
    }
    public void verifyLatestCallLogDetails(){

    }
}
