/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.zoho;
import com.integrations.constants.FrameworkConstants;
import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.FrameHelper;
import com.integrations.helper.SeleniumActions;
import com.integrations.helper.WindowHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.utils.DateAndTimeUtils;
import com.integrations.utils.PropertyUtils;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import static com.integrations.constants.FrameworkConstants.CALL_LOG_SUCCESS_TOAST;
import static com.integrations.helper.ExplicitWaitHelper.presenceOfElement;

public class ZohoRightBar extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(ZohoRightBar.class.getName());
    public final By linkConnectToZoho = By.xpath("//a[contains(text(),'Connect Zoho')]");
    public final By hdrOpenActivities = By.xpath("//div[@class='open-activites__header']");
    public final By containerContactCard = By.xpath("//div[@class='zoho-contact-data']");
    public final By txtZoho = By.xpath("//h1[contains(text(),'Zoho')]");
    public final By sectionZoho = By.xpath(".//h1[normalize-space(text())='Zoho']//following-sibling::div[@class='d-pr8 d-py8']//*[name()='svg']");
    public final By txtSubject = By.xpath("//input[@id='zoho-note-logging__note-subject']");
    public final By txtDescription = By.xpath("//textarea[@id='zoho-note-logging__note-description']");
    public final By btnLogCall = By.xpath("//button[contains(text(),'Log Call')]");
    public final By btnViewAccount = By.xpath("//h1[contains(text(),'Zoho')]//following-sibling::button//span//*[name()='svg']");
    public final By toastLoggingSuccess = By.cssSelector(".d-notice__content>span");
    public final By btnZohoAccount = By.xpath("//span[@class='select_text' and contains(text(),'Dialpad Inc')]");
    public final By btnSubmitZohoAccount = By.xpath("//div[@class='but_action']//button[contains(text(),'Submit')]");
    public final By btnAcceptZohoAccount = By.xpath("//button[contains(text(),'Accept')]");

    public ZohoRightBar(WebDriver driver) {
        super(driver);
    }

    public ZohoRightBar connectToZoho() {
        if(!isZohoTabExpanded()){
            click(sectionZoho);
            LOGGER.info("Expanded Zoho Section");
            WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(10);
        boolean isFindAFrameAndSwitchToIt = FrameHelper.findAFrameAndSwitchToIt(driver,linkConnectToZoho);
        if(isFindAFrameAndSwitchToIt){
            click(linkConnectToZoho);
            WaitForSecondsUtils.waitForTaskToCompleteInSeconds(10);
            if(WindowHelper.totalWindowsPresent(driver)==2){
                String parentWindow = WindowHelper.switchBackToMainWindowFromChildWindow(driver, FrameworkConstants.ZOHO_ACCOUNTS_TITLE);
                click(btnZohoAccount);
                LOGGER.info("Clicked on the CRM account as Dialpad Inc");
                click(btnSubmitZohoAccount);
                LOGGER.info("Clicked on Submit button and chosen the services for Dialpad");
                click(btnAcceptZohoAccount);
                WaitForSecondsUtils.waitForTaskToCompleteInSeconds(2);
                LOGGER.info("Clicked on Accept button");
                driver.switchTo().window(parentWindow);
                FrameHelper.findAFrameAndSwitchToIt(driver,containerContactCard);
            }
            ExplicitWaitHelper.visibilityOfElement(wait,containerContactCard);
        }
        FrameHelper.switchToDefaultContent(driver);
        System.out.println("switched back");
        LOGGER.info("Connected to Zoho by clicking the 'Connect to Zoho' link");
        return this;
    }
    public boolean isZohoTabExpanded(){
        boolean flag = false;
        WebElement element= getElement(sectionZoho);
        String expanded = element.getAttribute("data-qa");
        if(expanded.equalsIgnoreCase("chevron-down-icon"))
            flag = true;
        LOGGER.info("isZohoTabExpanded"+flag);
        System.out.println("flag"+flag);
        return flag;
    }
    public ZohoRightBar enterSubject(String textToEnter){
        click(txtSubject);
        sendKeys(txtSubject,textToEnter);
        LOGGER.info("Entered Zoho-Subject");
        return this;
    }
    public ZohoRightBar enterDescription(String textToEnter){
        click(txtDescription);
        sendKeys(txtDescription,textToEnter);
        LOGGER.info("Entered Zoho-Description");
        WaitForSecondsUtils.waitForTaskToComplete(7);
        return this;
    }
    public ZohoRightBar clickLogCallButton(){
        click(btnLogCall);
        LOGGER.info("Clicked on Log Call Button");
        return this;
    }
    public String getNotificationText(){
        String text = getText(presenceOfElement(wait, toastLoggingSuccess));
        LOGGER.info("Returning Notification Text : " + text);
        return text;
    }
    public ZohoRightBar logCallInZoho(){
        boolean isFindAFrameAndSwitchToIt = FrameHelper.findAFrameAndSwitchToIt(driver,txtSubject);
        if(isFindAFrameAndSwitchToIt){
            enterSubject(PropertyUtils.get("zoho.subject")+ DateAndTimeUtils.getDayMonthTodaysDate())
                    .enterDescription(PropertyUtils.get("zoho.desc")+DateAndTimeUtils.getDayMonthTodaysDate())
                    .clickLogCallButton();
        }
        FrameHelper.switchToDefaultContent(driver);
        String successToast = getNotificationText();
        Assert.assertEquals(successToast, CALL_LOG_SUCCESS_TOAST);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
        return this;
    }
    public void clickOnViewAccount(){
        click(txtZoho);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(5);
        SeleniumActions.moveToHiddenElementAndClick(driver,txtZoho,btnViewAccount);
        if(WindowHelper.totalWindowsPresent(driver)==1){
            SeleniumActions.moveToHiddenElementAndClick(driver,txtZoho,btnViewAccount);
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(5);
    }
}
