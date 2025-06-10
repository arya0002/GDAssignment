package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.driver.DriverManager;


public class SettingsPage extends BasePage{



    @AndroidFindBy(xpath="//android.widget.TextView[@text='Settings']")
    private WebElement header_SettingsPage;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='My Profile']")
    private WebElement selector_MyProfile;


    public SettingsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }



    public SettingsPage isSettingsPageHeaderVisible() throws InterruptedException {
        isElementVisible(header_SettingsPage,"Setting page header");
        return this;
    }


    public EditProfilePage clickMyProfile() throws InterruptedException {
        click(selector_MyProfile,"My Profile link");
        return new EditProfilePage();
    }



}
