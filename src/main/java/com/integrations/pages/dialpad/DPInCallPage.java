/**
 * @author Neetha Jose
 * Date : 09/06/23
 * Project Name : IntegrationsAutomationFramework
 * This page covers the elements and methods of the Active call page once the call is connected
 */

package com.integrations.pages.dialpad;

import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

public class DPInCallPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(DPInCallPage.class.getName());
    public final By btnHold = By.cssSelector(".cc-button-wrapper.cc-button-control_hold-container.cc-default");
    public final By btnRecord = By.cssSelector(".cc-button-wrapper.cc-button-call_recording-container.cc-default");
    public final By btnRecordActive = By.cssSelector(".cc-button-wrapper.cc-button-call_recording-container.cc-active");
    public final By btnHangUp = By.cssSelector(".button.cc-button.cc-button-control_hangup.snappy .mblock.icon.cc-button-icon.snappy");
    public final By timer = By.xpath("(//span[@class='screen-reader-only'])[2]");

    public DPInCallPage(WebDriver driver) {
        super(driver);
    }
    public DPInCallPage waitForTimer(){
        ExplicitWaitHelper.visibilityOfElement(wait,timer);
        LOGGER.info("Call is connected and Timer is shown");
        WaitForSecondsUtils.waitForTaskToComplete(3);
        return this;
    }

    public DPInCallPage endACall(){
        ExplicitWaitHelper.visibilityOfElement(wait,btnHangUp);
        click(btnHangUp);
        LOGGER.info("Clicked on Call HangUp button");
        WaitForSecondsUtils.waitForTaskToComplete(7);
        return this;
    }

    public DPInCallPage recordACall(){
        ExplicitWaitHelper.visibilityOfElement(wait,btnRecord);
        click(btnRecord);
        LOGGER.info("Clicked on Call Record button");
        WaitForSecondsUtils.waitForTaskToComplete(15);
        boolean isCallGetsRecorded = isDisplayed(getElement(btnRecordActive));
        Assert.assertTrue(isCallGetsRecorded,"Record btn is Active and Call is Getting Recorded");
        WaitForSecondsUtils.waitForTaskToComplete(3);
//        click(btnRecordActive);
//        WaitForSecondsUtils.waitForTaskToComplete(3);
//        LOGGER.info("Call Recording is stopped  ");
        return this;
    }

}
