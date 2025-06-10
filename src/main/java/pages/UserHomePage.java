package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.driver.DriverManager;


public class UserHomePage extends BasePage{



    @AndroidFindBy(xpath="//android.view.View[@text='PARTICIPANT NAME']")
    private WebElement label_ParticipantName;

    @AndroidFindBy(xpath="//android.view.View[@resource-id='tab-t0-2']")
    private WebElement button_settings;

    @AndroidFindBy(xpath="//android.view.View[@resource-id='tab-t0-0']")
    private WebElement button_MyInfo;

    @AndroidFindBy(xpath = "//android.view.View[@resource-id='tabpanel-t0-0']/android.view.View/android.view.View/android.view.View/android.view.View[2]//android.view.View[1]")
    private WebElement label_Name;

    public UserHomePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }


    public UserHomePage isHomePageLabelVisible() throws InterruptedException {
        isElementVisible(label_ParticipantName,"Participant name label");
        return this;
    }


    public SettingsPage clickSettings() throws InterruptedException {
        click(button_settings,"Settings button");
        return new SettingsPage();
    }

    public UserHomePage clickMyInfo() throws InterruptedException {
        click(button_MyInfo,"My Info button");
        return this;
    }

    public String getCurrentName(){
        return getText(label_Name);
    }



}
