/**
 * @author Neetha Jose
 * Date : 15/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.exceptions;

public class BrowserInvocationFailedException extends FrameworkException{
    /**
     * Pass the message that needs to be appended to the stacktrace
     * @param message Details about the exception or custom message
     */
    public BrowserInvocationFailedException(String message) {
        super(message);
    }
    /**
     *
     * @param message Details about the exception or custom message
     * @param cause Pass the enriched stacktrace or customised stacktrace
     */
    public BrowserInvocationFailedException(String message,Throwable cause) {
        super(message,cause);
    }
}
