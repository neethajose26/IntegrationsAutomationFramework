/**
 * @author Neetha Jose
 * Date : 21/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.exceptions;

import org.openqa.selenium.WebDriverException;

public class FrameworkWebDriverException extends WebDriverException {
    public FrameworkWebDriverException(String message) {
        super(message);
    }
}
