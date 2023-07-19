/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 * This page covers the elements and methods from Incoming call Pop Up
 */

package com.integrations.pages.dialpad;

import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.utils.FrameworkUtils;
import com.integrations.utils.WaitForSecondsUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class DPIncomingCallToastPage extends BasePage {
//    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(6000));
    FrameworkUtils frameworkUtils;
    ExplicitWaitHelper explicitWaitHelper;


    public final By btnAnswerCall = By.xpath("//td[@id='call-incoming-answer']");
    public final By toastCallNotification = By.xpath("//div[@id='inc-call-view']");
    public final By timer = By.xpath("(//span[@class='screen-reader-only'])[2]");
    public final By frameName = By.name("notification");

    public DPIncomingCallToastPage(WebDriver driver) {
        super(driver);
    }

    public void switchToIframe() {
        driver.switchTo().defaultContent();
        //System.out.println("switched to default content");
        driver.switchTo().frame(0);
        //System.out.println("switched to frame");
    }

    public void switchToMain() {
        driver.switchTo().defaultContent();
    }

    public DPIncomingCallToastPage clickOnAnswerBtn() {
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(10);
        switchToIframe();
        int check = 0;
        while (check <= 1) {
            try {
                click(btnAnswerCall);
                check = 5;
            } catch (Error e) {
                click(btnAnswerCall);
                System.out.println("====++ Error while answering the call ++==== " + e.getMessage());
                switchToMain();
                switchToIframe();
                check++;
            }
        }
        switchToMain();
        return this;
            }

    public DPIncomingCallToastPage waitForCallNotification(){
//        ExplicitWaitHelper.frameToBeAvailable(wait,frameName);
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("notification"));
        ExplicitWaitHelper.visibilityOfElement(wait,toastCallNotification);
        return this;
    }
    public DPIncomingCallToastPage waitForTimer(){
        ExplicitWaitHelper.visibilityOfElement(wait,timer);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(6);
        return this;
    }
}
