/**
 * @author Neetha Jose
 * Date : 09/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.dialpad;

import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.pages.basepage.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class DPHarnessPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(DPHarnessPage.class.getName());
    public final By btnCall = By.xpath("//div[@id='start-call-controls']//div[@class='d-btn__icon d-m0 cl-call-icon cl-call']");

    public DPHarnessPage(WebDriver driver) {
        super(driver);
    }


    public void clickOnPartUser(String name){
        String partName = "//a[contains(@data-qa,'dt-leftbar-row-link') and contains(@title,'"+name+"')]";
        ExplicitWaitHelper.visibilityOfElement(wait,By.xpath(partName));
        click(By.xpath(partName));
        LOGGER.info("Clicked on Part User from Recent list");
    }

    public void clickOnCallButton(){
        ExplicitWaitHelper.visibilityOfElement(wait,btnCall);
        click(btnCall);
        LOGGER.info("Clicked on Call Btn");
    }
}
