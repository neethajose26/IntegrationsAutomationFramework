/**
 * @author Neetha Jose
 * Date : 19/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.driver;

import com.integrations.enums.FrameworkDriverType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.EnumMap;
import java.util.Map;
import java.util.Objects;

public final class Driver {

    private Driver(){

    }
    public static WebDriver driver;


    public static void initDriver(FrameworkDriverType driverType) throws MalformedURLException {
        if(Objects.isNull(DriverManager.getDriver(driverType))){
            Map<FrameworkDriverType, WebDriver> map = new EnumMap<>(FrameworkDriverType.class);
            map.put(driverType, GetChromeDriver.getInstance(driverType));
            DriverManager.setDriver(map);
            }
        }

    public static void initOrgDriver() throws MalformedURLException {
        initDriver(FrameworkDriverType.ORG);
    }
    public static void initPartDriver() throws MalformedURLException {
        initDriver(FrameworkDriverType.PART);
    }
    public static void closeAllDrivers() {
        for (FrameworkDriverType driverType : DriverManager.getAllDriverTypes()) {
            if (Objects.nonNull(DriverManager.getDriver(driverType))) {
                Objects.requireNonNull(DriverManager.getDriver(driverType)).close();
                Objects.requireNonNull(DriverManager.getDriver(driverType)).quit();
            }
        }
        DriverManager.unload();
    }
}
