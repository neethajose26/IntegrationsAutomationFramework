/**
 * @author Neetha Jose
 * Date : 21/07/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.helper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

import static org.testng.Assert.assertTrue;

public final class AssertionHelper {

    private AssertionHelper(){

    }

    private static final Logger LOGGER = LogManager.getLogger(AssertionHelper.class.getName());

    public static void verifyTrue(boolean status) {
        LOGGER.info("Verify TRUE with : " +status);
        assertTrue(status);
    }

    public static void verifyTrue(boolean status, String message) {
        LOGGER.info("Verify TRUE with : " +status);
        assertTrue(status);
        LOGGER.info(message);
    }

    public static void verifyContains(List<String> actual, String expected, String message) {
        LOGGER.info("Verifying List" + actual + "contains"+ expected);
        verifyTrue(actual.contains(expected));
        LOGGER.info(message);
    }
}
