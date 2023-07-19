/**
 * @author Neetha Jose
 * Date : 11/07/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.msDynamics;

import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.SeleniumHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.pages.dialpad.DPBetaLoginPage;
import com.integrations.pages.dialpad.DPHarnessPage;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MSDLoginPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(MSDLoginPage.class.getName());

    public MSDLoginPage(WebDriver driver){
        super(driver);
    }

    public final By txtMSEmail = By.xpath(".//input[@type='email']");
    public final By btnMSNext = By.xpath(".//input[@type='submit']");
    public final By txtMSPasswd = By.xpath(".//input[@type='password']");
    public final By btnMSNoOption = By.xpath(".//input[@type='button' and @value='No']");

    public MSDLoginPage enterO365emailAddress(String email) {
        ExplicitWaitHelper.visibilityOfElement(wait,txtMSEmail);
        click(txtMSEmail);
        sendKeys(txtMSEmail,email);
        LOGGER.info("Entered email address");
        return this;
    }

    public MSDLoginPage clickOnNextBtnO365() {
        click(btnMSNext);
        LOGGER.info("clicked on Next Btn");
        return this;
    }

    public MSDLoginPage enterO365Password(String password) {
        ExplicitWaitHelper.visibilityOfElement(wait,txtMSPasswd);
        click(txtMSPasswd);
        sendKeys(txtMSPasswd,password);
        LOGGER.info("Entered MSDynamics password");
        return this;
    }

    public MSDLandingPage clickOnNoOnO365save() {
        ExplicitWaitHelper.visibilityOfElement(wait,btnMSNoOption);
        click(btnMSNoOption);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(10);
        LOGGER.info("clicked on No option from MSD Login Page");
        return new MSDLandingPage(driver);
    }
    public MSDLandingPage loginToMSDynamics(String email, String password, String msdUrl) {
        SeleniumHelper.navigateToURL(driver,msdUrl);
        return enterO365emailAddress(email)
                .clickOnNextBtnO365()
                .enterO365Password(password)
                .clickOnNextBtnO365()
                .clickOnNoOnO365save();
    }

}
