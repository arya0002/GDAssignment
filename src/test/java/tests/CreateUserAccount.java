package tests;

import org.testng.annotations.Test;
import pages.*;
import pojo.AccountData;
import utils.generic.YamlDataProvider;
import utils.generic.YamlDataSource;

public class CreateUserAccount extends BaseTest {

    @Test(dataProvider = "yamlDataProvider", dataProviderClass = YamlDataProvider.class, description = "User will open the app and create a new account")
    @YamlDataSource("src/test/resources/testdata/create_account_data.yaml")
    public void createNewAccountTest(AccountData data) throws InterruptedException {
        AppLandingPage appLandingPage = new AppLandingPage();
        PersonalInfoPage personalInfoPage;
        AddLicensesPage addLicensesPage;
        AddParticipantIDPage addParticipantIDPage;

        personalInfoPage = appLandingPage.clickPopupOkButton().isLandingPageHeaderVisible().clickCreateAcountLink();
        addLicensesPage = personalInfoPage.isPersonalInfoHeaderVisible().fillFirstName(data.getFirstName()).
                          fillLastName(data.getLastName()).fillEmail(data.getEmail()).fillPhone(data.getPhone()).
                          selectState(data.getState()).selectDOB(data.getDob()).clickContinue();
        addParticipantIDPage = addLicensesPage.isAddLicensesHeaderVisible().fillLicenseNo(data.getLicenseNumber()).
                               selectLicenseExpDate(data.getLicenseExp()).clickContinue();
        addParticipantIDPage.isAddLicensesHeaderVisible().fillParticipantIDNo(data.getParticipantId()).
                             selectJoinDate(data.getJoinDate()).clickSubmitRegistrationButton().validateSuccessPopUp();
        appLandingPage.isLandingPageHeaderVisible();
    }
}