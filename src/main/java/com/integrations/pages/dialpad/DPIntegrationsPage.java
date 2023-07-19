/**
 * @author Neetha Jose
 * Date : 12/06/23
 * Project Name : IntegrationsAutomationFramework
 * This Page covers the elements and methods from Settings-Integrations tab
 */

package com.integrations.pages.dialpad;

import com.integrations.driver.DriverManager;
import com.integrations.pages.basepage.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static com.integrations.driver.DriverManager.getDriver;

public class DPIntegrationsPage extends BasePage {

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(600));

    public static String tabIntegrations = "//li[@title='Integrations']";
    public static String imgZoho = "//img[@class='integration-logo-OAuthApp:sTrXeVZvDK3AfPSrmnHMVEPKg']";

    protected DPIntegrationsPage(WebDriver driver) {
        super(driver);
    }

    public void clickOnIntegrationsTab(){
        driver.findElement(By.xpath(tabIntegrations)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(imgZoho)));
    }
}
