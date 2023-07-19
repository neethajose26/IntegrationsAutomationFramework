/**
 * @author Neetha Jose
 * Date : 11/07/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.msDynamics;

import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.FrameHelper;
import com.integrations.pages.basepage.BasePage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public final class MSDLandingPage extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(MSDLandingPage.class.getName());
    public final By tabAccounts = By.id("sitemap-entity-Account");
    public final By frameSalesHub = By.id("AppLandingPage");
    public final By sectionSalesHub = By.id("AppTileContainerSec_1_LI_1");

    public MSDLandingPage(WebDriver driver){
        super(driver);
    }

    public MSDFunctionalities clickOnSalesHub() {
        FrameHelper.switchToFrame(driver,getElement(frameSalesHub));
        ExplicitWaitHelper.visibilityOfElement(wait,sectionSalesHub);
        click(sectionSalesHub);
        LOGGER.info("clicked on the section Sales Hub");
        ExplicitWaitHelper.visibilityOfElement(wait,tabAccounts);
        return new MSDFunctionalities(driver);
    }
}
