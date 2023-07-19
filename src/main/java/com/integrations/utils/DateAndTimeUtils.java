/**
 * @author Neetha Jose
 * Date : 11/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAndTimeUtils {

    private DateAndTimeUtils() {
    }
    public static String getDayMonthTodaysDate() {
        DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
        String date = formatter.format(new Date());
        String[] d = date.toString().split(" ");
        return d[0] + " " + d[1] + " " + d[2];

    }
}
