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
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class MSDFunctionalities extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(MSDFunctionalities.class.getName());
    public final By listCallLog = By.xpath("//li[@aria-label='Phone Call from ']");
    public final By fieldSubject = By.xpath("//div[contains(@class,'actSubject')]//a");
    public final By fieldOwner = By.xpath("//span[contains(@id,'Activities_owner')]");
    public final By logDetails = By.xpath("//div[contains(@id,'timeline_record_content')]");

    public MSDFunctionalities(WebDriver driver) {
        super(driver);
    }

    public MSDFunctionalities waitForCallLogList() {
        boolean isSwitchedToChildWindow = WindowHelper.switchToASpecificChildWindow(driver, FrameworkConstants.getMSDTitle());
        LOGGER.info("isSwitchedToChildWindow " + isSwitchedToChildWindow);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
        if (isSwitchedToChildWindow) {
            ExplicitWaitHelper.visibilityOfElement(wait, listCallLog);
            click(listCallLog);
            LOGGER.info("Listed all the logged calls - MSDynamics");
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(7);
        return this;
    }

    public List<String> verifyLatestCallLogDetails() {
        List<String> recentCallLogDetails = new ArrayList<>();
        WebElement recentCall = getAllElements(listCallLog).get(0);
        String[] loggedDetailsArray = getElement(recentCall, logDetails).getText().split("\\n");
        List<String> newList = Arrays.asList(loggedDetailsArray);
        recentCallLogDetails.addAll(newList);
        System.out.println("recentCallLogDetails : " + recentCallLogDetails);
        System.out.println("recentCallLogDetails(0) : " + recentCallLogDetails.get(0));
        System.out.println("recentCallLogDetails(1) : " + recentCallLogDetails.get(1));
        System.out.println("recentCallLogDetails(2) : " + recentCallLogDetails.get(2));

        return recentCallLogDetails;

    }
}
