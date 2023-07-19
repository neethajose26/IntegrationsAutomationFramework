/**
 * @author Neetha Jose
 * Date : 06/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.utils;

import com.integrations.constants.FrameworkConstants;
import com.integrations.exceptions.PropertyFileUsageException;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Properties;

public final class PropertyUtils {

    private PropertyUtils(){

    }
    private static final String PROPERTY_NAME = "Property name ";
    private static final String IS_NOT_FOUND_PLEASE_CHECK_PROPERTIES_FILES = " is not found. Please check properties files";
    private static final Map<String, String> CONFIGMAP = new HashMap<>();
    private static final Properties property = new Properties();

    static {
        //reading application properties
        try (FileInputStream file1 = new FileInputStream(FrameworkConstants.getApplicationPropertiesFilePath())) {
            property.load(file1);
            for (Map.Entry<Object, Object> entry : property.entrySet()) {
                CONFIGMAP.put(String.valueOf(entry.getKey()).trim(), String.valueOf(entry.getValue()).trim()); //remove the trailing and leading spaces
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(0);
        }
    }
    public static String get(String key){
        if (Objects.isNull(key) || Objects.isNull(CONFIGMAP.get(key))) {
            throw new PropertyFileUsageException(PROPERTY_NAME + key + IS_NOT_FOUND_PLEASE_CHECK_PROPERTIES_FILES);
        }
        return CONFIGMAP.get(key);
    }
}
