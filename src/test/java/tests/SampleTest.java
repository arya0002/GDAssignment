package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.*;
import pojo.AccountData;
import utils.generic.YamlDataProvider;

public class SampleTest extends BaseTest {

    @BeforeMethod
    public void beforeMethod() {
        YamlDataProvider.setYamlFileName("src/test/resources/testdata/create_account_data.yaml");
    }

    @Test(dataProvider = "yamlDataProvider", dataProviderClass = YamlDataProvider.class,description = "User will open the app and create a new account")
    public void createNewAccountTest(AccountData data) throws InterruptedException {
        AppLandingPage appLandingPage = new AppLandingPage();
        PersonalInfoPage personalInfoPage;
        AddLicensesPage addLicensesPage;
        AddParticipantIDPage addParticipantIDPage;

        appLandingPage.clickPopupOkButton();
        personalInfoPage = appLandingPage.clickCreateAcountLink();
        personalInfoPage.isPersonalInfoHeaderVisible();
        personalInfoPage.fillFirstName(data.getFirstName());
        personalInfoPage.fillLastName(data.getLastName());
        personalInfoPage.fillEmail(data.getEmail());
        personalInfoPage.fillPhone(data.getPhone());
        personalInfoPage.selectState(data.getState());
        personalInfoPage.selectDOB(data.getDob());
        addLicensesPage=personalInfoPage.clickContinue();
        addLicensesPage.isAddLicensesHeaderVisible();
        addLicensesPage.fillLicenseNo(data.getLicenseNumber());
        addLicensesPage.selectLicenseExpDate(data.getLicenseExp());
        addParticipantIDPage = addLicensesPage.clickContinue();
        addParticipantIDPage.isAddLicensesHeaderVisible();
        addParticipantIDPage.fillParticipantIDNo(data.getParticipantId());
        addParticipantIDPage.selectJoinDate(data.getJoinDate());
        addParticipantIDPage.clickSubmitRegistrationButton();
        addParticipantIDPage.validateSuccessPopUp();
    }
//
//    @Test(description = "Sample description")
//    public void sampleTest1() {
//
//    }


}