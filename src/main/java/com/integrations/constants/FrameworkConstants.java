/**
 * @author Neetha Jose
 * Date : 06/06/23
 * Project Name : IntegrationsAutomationFramework
 * This class contains all the strings which are common in the application
 */

package com.integrations.constants;

//Make the class as final so that people cannot extend it
public final class FrameworkConstants {

//Private constructor helps to restrict from creating an object of the class
    private FrameworkConstants(){
    }
    private static final String USER_DIR = System.getProperty("user.dir");
    private static final String RESOURCES_PATH = USER_DIR + "/src/test/resources";
    private static final String APPLICATION_PROPERTIES_FILE_PATH = RESOURCES_PATH + "/configs/application.properties";
    public static final String ELEMENT_IS_NOT_VISIBLE = "Element is not visible";
    public static final String NOT_ABLE_TO_PERFORM_CLICK_BECAUSE = "Not able to perform click because ====>>";
    public static final String NOT_ABLE_TO_PERFORM_SEND_KEY_BECAUSE = "Not able to perform send key because====>>";
    public static final String ZOHO_TITLE = "Auto Account (Account) - Zoho CRM";
    public static final String MSD_TITLE = "Contact: Contact:";
    public static final String MSLOGIN_TITLE = "Sign in to your account";
    public static final String DIALPAD_TITLE = "Dialpad";
    public static final String CALL_LOG_SUCCESS_TOAST = "Note logging successful";
    public static String getApplicationPropertiesFilePath() {
        return APPLICATION_PROPERTIES_FILE_PATH;
    }
    public static String getZohoTitle(){
        return ZOHO_TITLE;
    }
    public static String getMSDTitle(){
        return MSD_TITLE;
    }
    public static String getMSloginTitle(){
        return MSLOGIN_TITLE;
    }

    public static String getDialpadTitle(){
        return DIALPAD_TITLE;
    }
}
