/**
 * @author Neetha Jose
 * Date : 06/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.tests.zoho;
import com.integrations.base.TestBase;
import com.integrations.helper.WindowHelper;
import com.integrations.pages.dialpad.*;
import com.integrations.pages.zoho.ZohoFunctionalities;
import com.integrations.pages.zoho.ZohoLoginPage;
import com.integrations.pages.zoho.ZohoRightBar;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.integrations.utils.PropertyUtils.get;

public final class ZohoTests extends TestBase {
    private String zohoUrl;
    private String dialpadAppUrl;
    private String partName;
    private String partNumber;
    private String zohoOrgUser;
    private String zohoOrgPassword;
    private String zohoEmail;
    private String zohoPassword;
    ZohoFunctionalities zohoFunctionalitiesOrg;
    DPHarnessPage dpHarnessPageOrg;
    DPHarnessPage dpHarnessPagePart;
    ZohoRightBar zohoRightBarOrg;
    DPMakeACallPage dpMakeACallPageOrg;
    DPInCallPage dpInCallPageOrg;
    DPIncomingCallToastPage dpIncomingCallToastPage;
    DPBetaLoginPage dpBetaLoginPageOrg;
    DPBetaLoginPage dpBetaLoginPagePart;
    ZohoLoginPage zohoLoginPageOrg;
    DPIncomingCallToastPage dpIncomingCallToastPagePart;

    @BeforeClass
    @Parameters({"role"})
    public void setUp(String role){
        zohoUrl = get("zoho.url");
        zohoEmail = get("zoho.email");
        zohoPassword = get("zoho.password");
        dialpadAppUrl = get("dp.appurl");
        zohoOrgUser = get("zoho.org.email");
        zohoOrgPassword = get("zoho.org.password");
        partName = get("zohopart.name");
        partNumber = get("zohopart.number");

        if(role.equalsIgnoreCase("org")){
            zohoLoginPageOrg = new ZohoLoginPage(orgDriver);
            zohoRightBarOrg = new ZohoRightBar(orgDriver);
            dpMakeACallPageOrg = new DPMakeACallPage(orgDriver);
            dpInCallPageOrg = new DPInCallPage(orgDriver);
            zohoFunctionalitiesOrg = new ZohoFunctionalities(orgDriver);
            dpBetaLoginPageOrg = new DPBetaLoginPage(orgDriver);

            zohoFunctionalitiesOrg = zohoLoginPageOrg.loginToZoho(zohoEmail,zohoPassword,zohoUrl);
//            WindowHelper.switchToNewTab(orgDriver);
            dpHarnessPageOrg = dpBetaLoginPageOrg.loginWithO365(zohoOrgUser,zohoOrgPassword,dialpadAppUrl);
        }
//        else if (role.equalsIgnoreCase("part")) {
//            dpBetaLoginPagePart = new DPBetaLoginPage(partDriver);
//            dpIncomingCallToastPagePart = new DPIncomingCallToastPage(partDriver);
//
//            dpHarnessPagePart = dpBetaLoginPagePart.loginWithO365(user,password,dialpadAppUrl);
//        }
    }

    @Test(description = "Zoho - Log Call for an Outgoing call from Org to Part and Part answers the call")
    @Parameters({"role"})
    public void ZohoLogCall(String role){
        if(role.equalsIgnoreCase("org")){
            dpHarnessPageOrg.clickOnPartUser(partName);
            zohoRightBarOrg = zohoRightBarOrg.connectToZoho();
            dpMakeACallPageOrg = dpMakeACallPageOrg.makeACall(partNumber);
            dpInCallPageOrg = dpInCallPageOrg.waitForTimer().endACall();
            zohoRightBarOrg.logCallInZoho().clickOnViewAccount();
            zohoFunctionalitiesOrg = zohoFunctionalitiesOrg.clickOnClosedActivities();
        }
//        else if (role.equalsIgnoreCase("part")) {
//            new DPIncomingCallToastPage(partDriver).waitForCallNotification().clickOnAnswerBtn().waitForTimer();
//
//        }
        }
    }

