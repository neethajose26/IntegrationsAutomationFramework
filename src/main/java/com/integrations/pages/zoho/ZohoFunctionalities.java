/**
 * @author Neetha Jose
 * Date : 10/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.pages.zoho;
import com.integrations.constants.FrameworkConstants;
import com.integrations.helper.ExplicitWaitHelper;
import com.integrations.helper.WindowHelper;
import com.integrations.pages.basepage.BasePage;
import com.integrations.utils.WaitForSecondsUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ZohoFunctionalities extends BasePage {
    private static final Logger LOGGER = LogManager.getLogger(ZohoFunctionalities.class.getName());
    public final By tabClosedActivities = By.linkText("Closed Activities");
    public final By listCallLog = By.xpath("//link-to[contains(@data-zcqa,'Zoho_Subject')]");
    public final By fieldSubject = By.xpath("//div[contains(@class,'actSubject')]//a");
    public final By fieldOwner = By.xpath("//span[contains(@id,'Activities_owner')]");
    public final By fieldDesc = By.xpath("//span[contains(@data-zcqa,'Activities_Description')]");

    public ZohoFunctionalities(WebDriver driver) {
        super(driver);
    }

    public ZohoFunctionalities clickOnClosedActivities(){
        boolean isSwitchedToChildWindow = WindowHelper.switchToASpecificChildWindow(driver, FrameworkConstants.getZohoTitle());
        LOGGER.info("isSwitchedToChildWindow "+isSwitchedToChildWindow);
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(3);
        if(isSwitchedToChildWindow){
            ExplicitWaitHelper.visibilityOfElement(wait,tabClosedActivities);
            click(tabClosedActivities);
            LOGGER.info("Clicked on Closed Activities - Zoho");
        }
        WaitForSecondsUtils.waitForTaskToCompleteInSeconds(7);
        return this;
    }

    public List<String> verifyLatestCallLogDetails(){
        List <String> recentCallLogDetails = new ArrayList<>();
        WebElement recentCall = getAllElements(listCallLog).get(0);
        String loggedSubject = getElement(recentCall,fieldSubject).getText();
        recentCallLogDetails.add(loggedSubject);
        String loggedUser = getElement(recentCall,fieldOwner).getText();
        recentCallLogDetails.add(loggedUser);
        String[] loggedDetailsArray = getElement(recentCall,fieldDesc).getAttribute("value").split("\\n");
        List<String> newList = Arrays.asList(loggedDetailsArray);
        recentCallLogDetails.addAll(newList);
        System.out.println("recentCallLogDetails : "+recentCallLogDetails);
        System.out.println("recentCallLogDetails(0) : "+recentCallLogDetails.get(0));
        System.out.println("recentCallLogDetails(1) : "+recentCallLogDetails.get(1));
        System.out.println("recentCallLogDetails(2) : "+recentCallLogDetails.get(2));
//        String recordingLinkText = loggedDetailsArray[0];
//        String recordingLink = loggedDetailsArray[1];
//        recentCallLogDetails.add(recordingLinkText);
//        recentCallLogDetails.add(recordingLink);
//        String transcriptURL = loggedDetailsArray[2];
//        recentCallLogDetails.add(transcriptURL);
//        String loggedDesc = loggedDetailsArray[3];
//        recentCallLogDetails.add(loggedDesc);
        return recentCallLogDetails;



    }
}
