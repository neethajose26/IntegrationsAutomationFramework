/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.zoho;

import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.SeleniumHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.utils.PropertyUtils;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ZohoLoginPage extends BasePage {

    private static final Logger LOGGER = LogManager.getLogger(ZohoLoginPage.class.getName());

    public final By btnSignIn = By.xpath("(//a[contains(text(),'SIGN IN')])[1]");
    public final By txtEmail = By.id("login_id");
    public final By btnNext = By.id("nextbtn");
    public final By txtPassword = By.id("password");
    public final By btnRemindLater = By.xpath("//span[contains(text(),'Remind me later')]");
    public final By tabAccounts = By.xpath("//div[@data-value='Accounts']");
    public final By btnSubmitSignIn = By.xpath("//button/span[contains(text(),'Sign in')]");

    public ZohoLoginPage(WebDriver driver) {
        super(driver);
    }

    public ZohoLoginPage enterEmailAddress(String email){
        ExplicitWaitHelper.visibilityOfElement(wait,txtEmail);
        click(txtEmail);
        sendKeys(txtEmail,email);
        LOGGER.info("Zoho Login Page - Entered Email");
        return this;
    }
    public ZohoLoginPage clickOnNextButton(){
        click(btnNext);
        LOGGER.info("Clicked on Next Button");
        return this;
    }
    public ZohoLoginPage enterPassword(String password){
        ExplicitWaitHelper.visibilityOfElement(wait,txtPassword);
        click(txtPassword);
        sendKeys(txtPassword,password);
        LOGGER.info("Zoho Login Page - Entered Password");
        return this;
    }
    public void clickOnRemindMeLater(){
        click(btnRemindLater);
        LOGGER.info("Clicked on Remind Me Later");
    }
    public ZohoLoginPage clickOnSignInButton(){
        ExplicitWaitHelper.visibilityOfElement(wait,btnSignIn);
        click(btnSignIn);
        LOGGER.info("Clicked on SignIn button from Zoho landing page");
        return this;
    }
    public ZohoFunctionalities submitLoginForm(){
        click(btnSubmitSignIn);
        LOGGER.info("Clicked on SignIn button after entering Zoho creds");
        List<WebElement>remindBtn = getAllElements(btnRemindLater);
        System.out.println("Size :"+remindBtn.size());
        if(remindBtn.size()>0){
            clickOnRemindMeLater();
            LOGGER.info("Clicked on Remind Me Later");
        }
        ExplicitWaitHelper.visibilityOfElement(wait,tabAccounts);
        return new ZohoFunctionalities(driver);
    }


    public ZohoFunctionalities loginToZoho(String email, String password, String url){
        SeleniumHelper.navigateToURL(driver,url);
           return clickOnSignInButton()
                    .enterEmailAddress(email)
                   .clickOnNextButton()
                   .enterPassword(password)
                   .submitLoginForm();
    }

}
