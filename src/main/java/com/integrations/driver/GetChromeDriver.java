/**
 * @author Neetha Jose
 * Date : 26/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.driver;

import com.integrations.enums.FrameworkDriverType;
import com.integrations.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class GetChromeDriver {

    private GetChromeDriver(){}

    public static WebDriver getInstance(FrameworkDriverType driverType) throws MalformedURLException {
        WebDriver driver = new RemoteWebDriver(new URL(PropertyUtils.get("seleniumgridurl")), ChromeBrowserOptions.getChromeOptions(driverType)) ;
        return driver;
    }
}
