/**
 * @author Neetha Jose
 * Date : 11/07/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.msDynamics;

import com.integrations.constants.FrameworkConstants;
import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.WindowHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.pages.zoho.ZohoFunctionalities;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MSDFunctionalities extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(MSDFunctionalities.class.getName());
    public final By listCallLog = By.xpath("//li[@aria-label='Phone Call from ']");

    public MSDFunctionalities(WebDriver driver){
        super(driver);
    }

    public MSDFunctionalities waitForCallLogList(){
        boolean isSwitchedToChildWindow = WindowHelper.switchToASpecificChildWindow(driver, FrameworkConstants.getMSDTitle());
        LOGGER.info("isSwitchedToChildWindow "+isSwitchedToChildWindow);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
        if(isSwitchedToChildWindow){
            ExplicitWaitHelper.visibilityOfElement(wait,listCallLog);
            click(listCallLog);
            LOGGER.info("Listed all the logged calls - MSDynamics");
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(7);
        return this;
    }

}
