/**
 * @author Neetha Jose
 * Date : 08/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.utils;

import java.util.concurrent.TimeUnit;

import static com.google.common.util.concurrent.Uninterruptibles.sleepUninterruptibly;

public final class WaitForSecondsUtils {
    private WaitForSecondsUtils(){}
    public static void waitForTaskToComplete(long value){
        sleepUninterruptibly(value*400, TimeUnit.MILLISECONDS);
    }
    public static void waitForTaskToComplete(){
        sleepUninterruptibly(400, TimeUnit.MILLISECONDS);
    }
    public static void waitForTaskToCompleteInSeconds(long seconds){
        sleepUninterruptibly(seconds, TimeUnit.SECONDS);
    }
    public static void waitForSecondToComplete(){
        sleepUninterruptibly(1, TimeUnit.SECONDS);
    }

}
