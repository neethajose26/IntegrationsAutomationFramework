/**
 * @author Neetha Jose
 * Date : 19/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.driver;

import com.integrations.enums.FrameworkDriverType;
import com.integrations.utils.PropertyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class DriverManager {
    private static final Logger LOGGER = LogManager.getLogger(DriverManager.class.getName());
    private static final ThreadLocal<Map<FrameworkDriverType, WebDriver>>DRIVER_THREAD_LOCAL = new ThreadLocal<>();

    private DriverManager(){

    }

    public static WebDriver getDriver(FrameworkDriverType driverType){
        if (Objects.isNull(DRIVER_THREAD_LOCAL.get()) || Objects.isNull(DRIVER_THREAD_LOCAL.get().get(driverType))) {
            System.out.println("driver is null for : " + driverType);
            return null;
        }
        System.out.println("returning driver for : " + driverType);
        return DRIVER_THREAD_LOCAL.get().get(driverType);
    }
    public static WebDriver getOrgDriver() {
        return getDriver(FrameworkDriverType.ORG);
    }
    public static WebDriver getPartDriver() {
        return getDriver(FrameworkDriverType.PART);
    }
    public static Set<FrameworkDriverType> getAllDriverTypes() {
        LOGGER.info("returning set of framework drivers : " + DRIVER_THREAD_LOCAL.get().keySet());
        return DRIVER_THREAD_LOCAL.get().keySet();
    }

//    public static void setDriver(WebDriver driverRef) throws MalformedURLException {
//        DRIVER_THREAD_LOCAL.set(driverRef);
//    }
static void setDriver(Map<FrameworkDriverType, WebDriver> map) {
    if (Objects.nonNull(map)) {
        Map<FrameworkDriverType, WebDriver> existingDriver = DRIVER_THREAD_LOCAL.get();

        if (Objects.isNull(existingDriver)) {
            LOGGER.info("driver created as : " + map);
            DRIVER_THREAD_LOCAL.set(map);
        } else {
            existingDriver.putAll(map);
            LOGGER.info("driver created as : " + map);
            DRIVER_THREAD_LOCAL.set(existingDriver);
        }
    }
}
    public static void unload(){
        DRIVER_THREAD_LOCAL.remove();
    }
}
