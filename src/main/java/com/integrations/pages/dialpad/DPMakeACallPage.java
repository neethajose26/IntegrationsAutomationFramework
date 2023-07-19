/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.dialpad;

import com.integrations.driver.DriverManager;
import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.SeleniumHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.utils.FrameworkUtils;
import com.integrations.utils.PropertyUtils;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import static com.integrations.helper.ExplicitWaitHelper.visibilityOfElement;
public class DPMakeACallPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(DPMakeACallPage.class.getName());
    public DPMakeACallPage(WebDriver driver) {
        super(driver);
    }

    public final By btnMakeACall = By.cssSelector("[data-name='Phone']");
    public final By txtEnterNumber = By.id("search-input");



    public DPMakeACallPage clickOnMakeAcallBtn() {
        click(btnMakeACall);
        visibilityOfElement(wait,txtEnterNumber);
        LOGGER.info("Clicked on Make A call Btn");
        return this;
    }
    public DPMakeACallPage enterNumber(String number){
        click(txtEnterNumber);
        sendKeys(txtEnterNumber,number);
        LOGGER.info("Entered Phone num.");
        return this;
    }
    public DPMakeACallPage makeACall(String number){
                clickOnMakeAcallBtn()
                .enterNumber(number);
        SeleniumHelper.pressAnyKey(driver,txtEnterNumber,Keys.ENTER);
        return this;

    }
}
