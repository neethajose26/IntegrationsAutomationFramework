/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.dialpad;

import com.integrations.driver.DriverManager;
import com.integrations.enums.FrameworkDriverType;
import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.SeleniumHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;



public final class DPBetaLoginPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(DPBetaLoginPage.class.getName());
    public DPBetaLoginPage(WebDriver driver){
        super(driver);
    }

    public final By btnO365Login = By.xpath(".//span[contains(text(),'Log in with Microsoft')]");
    public final By txtO365Email = By.xpath(".//input[@type='email']");
    public final By btnO365Next = By.xpath(".//input[@type='submit']");
    public final By txtO365Passwd = By.xpath(".//input[@type='password']");
    public final By btnO365NoOption = By.xpath(".//input[@type='button' and @value='No']");

    public static DPBetaLoginPage getInstance(FrameworkDriverType driverType) {
        return new DPBetaLoginPage(DriverManager.getDriver(driverType));
    }
    public DPBetaLoginPage clickOnMicrosoftLoginOption() {
        click(btnO365Login);
        LOGGER.info("clicked on Microsoft Login button");
        return this;
    }
    public DPBetaLoginPage enterO365emailAddress(String email) {
        ExplicitWaitHelper.visibilityOfElement(wait,txtO365Email);
        click(txtO365Email);
        sendKeys(txtO365Email,email);
        LOGGER.info("Entered email address");
        return this;
    }
    public DPBetaLoginPage clickOnNextBtnO365() {
        ExplicitWaitHelper.visibilityOfElement(wait,btnO365Next);
        click(btnO365Next);
        LOGGER.info("clicked on Next Btn");
        return this;
    }
    public DPBetaLoginPage enterO365Password(String password) {
        ExplicitWaitHelper.visibilityOfElement(wait,txtO365Passwd);
        click(txtO365Passwd);
        sendKeys(txtO365Passwd,password);
        LOGGER.info("Entered o365 password");
        return this;
    }
    public DPHarnessPage clickOnNoOnO365save() {
        ExplicitWaitHelper.visibilityOfElement(wait,btnO365NoOption);
        click(btnO365NoOption);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(10);
        LOGGER.info("clicked on No option from Microsoft Login");
        return new DPHarnessPage(driver);
    }

    public DPHarnessPage loginWithO365(String email, String password, String dpUrl) {
                SeleniumHelper.navigateToURL(driver,dpUrl);
                return clickOnMicrosoftLoginOption()
                        .enterO365emailAddress(email)
                        .clickOnNextBtnO365()
                        .enterO365Password(password)
                        .clickOnNextBtnO365()
                        .clickOnNoOnO365save();
    }

    public DPHarnessPage loginWithO365ForMSD(String email, String password, String dpUrl) {
        SeleniumHelper.navigateToURL(driver,dpUrl);
        clickOnMicrosoftLoginOption();
        enterO365emailAddress(email);
        clickOnNextBtnO365();
        enterO365Password(password);
        clickOnNextBtnO365();
        return new DPHarnessPage(driver);
    }

}
