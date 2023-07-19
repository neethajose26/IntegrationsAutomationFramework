/**
 * @author Neetha Jose
 * Date : 11/07/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.msDynamics;

import com.integrations.constants.FrameworkConstants;
import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.FrameHelper;
import com.integrations.helper.SeleniumActions;
import com.integrations.helper.WindowHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.pages.zoho.ZohoRightBar;
import com.integrations.utils.DateAndTimeUtils;
import com.integrations.utils.PropertyUtils;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import static com.integrations.helper.ExplicitWaitHelper.presenceOfElement;

public class MSDRightBar extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(MSDRightBar.class.getName());
    public MSDRightBar(WebDriver driver){
        super(driver);
    }

    public final By linkConnectMSD = By.xpath("//a[@class='connect-app__link' and contains(text(),'Connect Dynamics')]");
    public final By sectionDynamics = By.xpath(".//h1[normalize-space(text())='Dynamics']//following-sibling::div[@class='d-pr8 d-py8']//*[name()='svg']");
    public final By containerContactCard = By.xpath("//div[@class='contact-data__name-container']");
    public final By txtSubject = By.id("note-logging__note-subject");
    public final By txtDescription = By.id("note-logging__note-description");
    public final By btnLogCall = By.xpath("//button[contains(text(),'Log Call')]");
    public final By toastLoggingSuccess = By.cssSelector(".d-notice__content>span");
    public final By txtDynamics = By.xpath("//h1[contains(text(),'Dynamics')]");
    public final By btnViewProfile = By.xpath("//h1[contains(text(),'Dynamics')]//following-sibling::button//span//*[name()='svg']");

    public MSDRightBar connectToMSDynamics(String msAccount) {
        if(!isMSDTabExpanded()){
            click(sectionDynamics);
            LOGGER.info("Expanded MSD Section");
            WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(10);
        boolean isMSDFrameAvailable = FrameHelper.findAFrameAndSwitchToIt(driver,linkConnectMSD);
        if(isMSDFrameAvailable){
            click(linkConnectMSD);
            WaitForSecondsUtils.waitForTaskToCompleteInSeconds(10);
            ExplicitWaitHelper.visibilityOfElement(wait,containerContactCard);
        }
        FrameHelper.switchToDefaultContent(driver);
        System.out.println("switched back");
        LOGGER.info("Connected to MSDynamics by clicking the 'Connect Dynamics' link");
        return this;
    }

    public void selectMSAccount(String account){
        String msAccount = "//div[@data-test-id='"+account+"']";
        ExplicitWaitHelper.visibilityOfElement(wait,By.xpath(msAccount));
        click(By.xpath(msAccount));
        LOGGER.info("Selected the MS Account from login page");
    }

    public boolean isMSDTabExpanded(){
        boolean flag = false;
        WebElement element= getElement(sectionDynamics);
        String expanded = element.getAttribute("data-qa");
        if(expanded.equalsIgnoreCase("chevron-down-icon"))
            flag = true;
        LOGGER.info("isMSDTabExpanded"+flag);
        System.out.println("flag"+flag);
        return flag;
    }
    public MSDRightBar enterSubject(String textToEnter){
        click(txtSubject);
        sendKeys(txtSubject,textToEnter);
        LOGGER.info("Entered MSD-Subject");
        return this;
    }
    public MSDRightBar enterDescription(String textToEnter){
        click(txtDescription);
        sendKeys(txtDescription,textToEnter);
        LOGGER.info("Entered MSD-Description");
        WaitForSecondsUtils.waitForTaskToComplete(7);
        return this;
    }
    public MSDRightBar clickLogCallButton(){
        click(btnLogCall);
        LOGGER.info("Clicked on Log Call Button");
        return this;
    }
    public String getNotificationText(){
        String text = getText(presenceOfElement(wait, toastLoggingSuccess));
        LOGGER.info("Returning Notification Text : " + text);
        return text;
    }

    public MSDRightBar logCallInDynamics(){
        boolean isFindAFrameAndSwitchToIt = FrameHelper.findAFrameAndSwitchToIt(driver,txtSubject);
        if(isFindAFrameAndSwitchToIt){
            enterSubject(PropertyUtils.get("msd.subject")+ DateAndTimeUtils.getDayMonthTodaysDate())
                    .enterDescription(PropertyUtils.get("msd.desc")+DateAndTimeUtils.getDayMonthTodaysDate())
                    .clickLogCallButton();
        }
        FrameHelper.switchToDefaultContent(driver);
        String successToast = getNotificationText();
        Assert.assertEquals(successToast,FrameworkConstants.CALL_LOG_SUCCESS_TOAST);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
        return this;
    }

    public void clickOnViewProfile(){
        click(txtDynamics);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(5);
        SeleniumActions.moveToHiddenElementAndClick(driver,txtDynamics,btnViewProfile);
        if(WindowHelper.totalWindowsPresent(driver)==1){
            SeleniumActions.moveToHiddenElementAndClick(driver,txtDynamics,btnViewProfile);
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(5);
    }
}
