/**
 * @author Neetha Jose
 * Date : 06/06/23
 * Project Name : IntegrationsAutomationFramework
 */

package com.integrations.tests.msDynamics;
import com.integrations.base.TestBase;
import com.integrations.helper.WindowHelper;
import com.integrations.pages.dialpad.*;
import com.integrations.pages.msDynamics.MSDFunctionalities;
import com.integrations.pages.msDynamics.MSDLandingPage;
import com.integrations.pages.msDynamics.MSDLoginPage;
import com.integrations.pages.msDynamics.MSDRightBar;
import com.integrations.pages.zoho.ZohoFunctionalities;
import com.integrations.pages.zoho.ZohoLoginPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import static com.integrations.utils.PropertyUtils.get;

public final class MSDynamicsTests extends TestBase {
    private String msdUrl;
    private String msdEmail;
    private String msdPassword;
    private String dialpadAppUrl;
    private String partName;
    private String partNumber;
    private String msdOrgUser;
    private String msdOrgPassword;
    ZohoFunctionalities zohoFunctionalitiesOrg;

    MSDLoginPage msdLoginPageOrg;
    MSDLandingPage msdLandingPageOrg;
    DPHarnessPage dpHarnessPageOrg;
    DPHarnessPage dpHarnessPagePart;
    MSDRightBar msdRightBarOrg;
    MSDFunctionalities msdFunctionalitiesOrg;
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
        msdUrl = get("msd.url");
        msdEmail = get("msd.user.email");
        msdPassword = get("msd.user.password");
        dialpadAppUrl = get("dp.appurl");
        msdOrgUser = get("msd.org.email");
        msdOrgPassword = get("msd.org.password");
        partName = get("msd.part.name");
        partNumber = get("msd.part.number");

        if(role.equalsIgnoreCase("org")){
            dpBetaLoginPageOrg = new DPBetaLoginPage(orgDriver);
            msdLoginPageOrg = new MSDLoginPage(orgDriver);
            msdRightBarOrg = new MSDRightBar(orgDriver);
            dpMakeACallPageOrg = new DPMakeACallPage(orgDriver);
            dpInCallPageOrg = new DPInCallPage(orgDriver);

            msdLandingPageOrg = msdLoginPageOrg.loginToMSDynamics(msdEmail,msdPassword,msdUrl);
            msdFunctionalitiesOrg = msdLandingPageOrg.clickOnSalesHub();
            dpHarnessPageOrg = dpBetaLoginPageOrg.loginWithO365ForMSD(msdOrgUser,msdOrgPassword,dialpadAppUrl);
        }
//        else if (role.equalsIgnoreCase("part")) {
//            dpBetaLoginPagePart = new DPBetaLoginPage(partDriver);
//            dpIncomingCallToastPagePart = new DPIncomingCallToastPage(partDriver);
//
//            dpHarnessPagePart = dpBetaLoginPagePart.loginWithO365(user,password,dialpadAppUrl);
//        }
    }

    @Test(description = "MSDynamics - Log Call for an Outgoing call from Org to Part")
    @Parameters({"role"})
    public void MSDynamicsLogCall(String role){
        if(role.equalsIgnoreCase("org")){
            dpHarnessPageOrg.clickOnPartUser(partName);
            msdRightBarOrg = msdRightBarOrg.connectToMSDynamics(msdEmail);
            dpMakeACallPageOrg = dpMakeACallPageOrg.makeACall(partNumber);
            dpInCallPageOrg = dpInCallPageOrg.waitForTimer().recordACall().endACall();
            msdRightBarOrg.logCallInDynamics().clickOnViewProfile();
            msdFunctionalitiesOrg.waitForCallLogList();
        }
//        else if (role.equalsIgnoreCase("part")) {
//            new DPIncomingCallToastPage(partDriver).waitForCallNotification().clickOnAnswerBtn().waitForTimer();
//
//        }
        }
    }

