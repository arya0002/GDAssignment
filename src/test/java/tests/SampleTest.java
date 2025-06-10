package tests;

import org.testng.ITest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import pojo.AccountData;
import utils.generic.YamlDataProvider;
import utils.generic.YamlDataSource;

public class SampleTest extends BaseTest {

    //private static ThreadLocal<String> testName = new ThreadLocal<>();

    @Test(dataProvider = "yamlDataProvider", dataProviderClass = YamlDataProvider.class,description = "User will open the app and create a new account")
    @YamlDataSource("src/test/resources/testdata/create_account_data.yaml")
    public void createNewAccountTest(AccountData data) throws InterruptedException {
        //testName.set(data.getCaseName());
        AppLandingPage appLandingPage = new AppLandingPage();
        PersonalInfoPage personalInfoPage;
        AddLicensesPage addLicensesPage;
        AddParticipantIDPage addParticipantIDPage;

        personalInfoPage = appLandingPage.clickPopupOkButton().
                           isLandingPageHeaderVisible().
                           clickCreateAcountLink();

        addLicensesPage = personalInfoPage.isPersonalInfoHeaderVisible().
                          fillFirstName(data.getFirstName()).
                          fillLastName(data.getLastName()).
                          fillEmail(data.getEmail()).
                          fillPhone(data.getPhone()).
                          selectState(data.getState()).
                          selectDOB(data.getDob()).
                          clickContinue();

        addParticipantIDPage = addLicensesPage.isAddLicensesHeaderVisible().
                               fillLicenseNo(data.getLicenseNumber()).
                               selectLicenseExpDate(data.getLicenseExp()).
                               clickContinue();
        addParticipantIDPage.isAddLicensesHeaderVisible().
                             fillParticipantIDNo(data.getParticipantId()).
                             selectJoinDate(data.getJoinDate()).
                             clickSubmitRegistrationButton().
                             validateSuccessPopUp();
        appLandingPage.isLandingPageHeaderVisible();
    }

//    @Override
//    public String getTestName() {
//        return testName.get();
//    }
//
//    @Test(description = "Sample description")
//    public void sampleTest1() {
//
//    }


}