/**
 * @author Neetha Jose
 * Date : 15/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.driver;

import com.integrations.enums.FrameworkDriverType;
import com.integrations.utils.PropertyUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ChromeBrowserOptions {

    WebDriver driver;

    public ChromeBrowserOptions(WebDriver driver){
        this.driver=driver;
    }

    public static ChromeOptions getChromeOptions(FrameworkDriverType driverType) {
        ChromeOptions options = new ChromeOptions();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability(CapabilityType.BROWSER_NAME, PropertyUtils.get("browser"));
        Map<String, Object> prefs = new HashMap<String, Object>();
        prefs.put("profile.default_content_setting_values.notifications", 2);
        //options.addArguments("--incognito");
        options.addArguments("--allow-running-insecure-content", "--ignore-certificate-errors", "--disable-popup-blocking", "--always-authorize-plugins", "--use-fake-ui-for-media-stream");
        options.addArguments("use-fake-device-for-media-stream");
        options.addArguments("use-fake-ui-for-media-stream");
        options.addArguments("--start-maximized");
        options.setExperimentalOption("prefs", prefs);
        options.addArguments("--ignore-certificate-errors");
        options.addArguments("--disable-popup-blocking");
        return options;
    }
}
