package tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import pages.*;
import pojo.AccountData;
import reports.ExtentLogger;
import utils.generic.YamlDataProvider;
import utils.generic.YamlDataSource;

public class UpdateUserAccount extends BaseTest {


    @Test(dataProvider = "yamlDataProvider", dataProviderClass = YamlDataProvider.class,description = "User will open the app , login and update first name and last name")
    @YamlDataSource("src/test/resources/testdata/update_user_data.yaml")
    public void updateUserDetailTest(AccountData data) throws InterruptedException {
        AppLandingPage appLandingPage = new AppLandingPage();
        UserHomePage userHomePage;
        SettingsPage settingsPage;
        EditProfilePage editProfilePage;
        userHomePage = appLandingPage.clickPopupOkButton().isLandingPageHeaderVisible().fillUserName(data.getUserName()).
                fillPassword(data.getPassword()).clickLogin();
        settingsPage = userHomePage.isHomePageLabelVisible().clickSettings();
        editProfilePage = settingsPage.isSettingsPageHeaderVisible().clickMyProfile();
        String messageText = editProfilePage.isEditProfilePageHeaderVisible().fillFirstName(data.getFirstName()).fillLastName(data.getLastName()).clickSaveChanges().validateSuccessPopUp();
        Assert.assertTrue(
                messageText.contains("saved successfully") || messageText.contains("review"),
                "Expected text to contain 'saved successfully' or 'review', but found: " + messageText
        );
        String name = userHomePage.clickMyInfo().isHomePageLabelVisible().getCurrentName();
        Assert.assertEquals(name,String.format("%s %s",data.getFirstName(),data.getLastName()),"Name did not got updated");
        ExtentLogger.pass("Data updated successfully");

    }
}
