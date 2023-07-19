/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.base;

import com.integrations.driver.Driver;
import com.integrations.driver.DriverManager;
import com.integrations.pages.dialpad.*;
import com.integrations.pages.zoho.ZohoFunctionalities;
import com.integrations.pages.zoho.ZohoLoginPage;
import com.integrations.pages.zoho.ZohoRightBar;
import com.integrations.utils.FrameworkUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

public abstract class TestBase {

    private static final Logger LOGGER = LogManager.getLogger(TestBase.class.getName());
    protected WebDriver orgDriver;
    protected WebDriver partDriver;

    @BeforeTest
    @Parameters({"role"})
    public void setup (String role) throws Exception {
        if(role.equalsIgnoreCase("org")){
            Driver.initOrgDriver();
            LOGGER.info("Initialized Org Driver");
            orgDriver = DriverManager.getOrgDriver();
        } else if (role.equalsIgnoreCase("part")) {
            Driver.initPartDriver();
            LOGGER.info("Initialized Part Driver");
            partDriver = DriverManager.getPartDriver();
        }
       }

    @AfterTest
    public void tearDown(){
        Driver.closeAllDrivers();
    }


}
