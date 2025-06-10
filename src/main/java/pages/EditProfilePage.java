package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import reports.ExtentLogger;
import utils.driver.DriverManager;
import utils.generic.GenericMethods;


public class EditProfilePage extends BasePage{



    @AndroidFindBy(xpath="//android.widget.TextView[@text='Edit Profile']")
    private WebElement header_EditProfilePage;

    @AndroidFindBy(xpath="//android.view.View[@resource-id='tabpanel-t0-2']/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View//android.widget.EditText")
    private WebElement txtbox_FirstName;

    @AndroidFindBy(xpath="//android.view.View[@resource-id='tabpanel-t0-2']/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[3]/android.view.View//android.widget.EditText")
    private WebElement txtbox_LastName;

    @AndroidFindBy(xpath="//android.widget.Button[@text=\"SAVE CHANGES\"]")
    private WebElement button_SaveChanges;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Info']")
    private WebElement popup_header;

    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@text, 'saved successfully') or contains(@text, 'review')]")
    private WebElement popup_message;

    @AndroidFindBy(xpath ="//android.widget.Button[@text='OK']")
    private WebElement popup_OKButton;


    public EditProfilePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }



    public EditProfilePage isEditProfilePageHeaderVisible() throws InterruptedException {
        isElementVisible(header_EditProfilePage,"Edit Profile page header");
        return this;
    }

    public EditProfilePage fillFirstName(String firstName) throws InterruptedException {
        setText(txtbox_FirstName,firstName,"First name");
        return this;
    }

    public EditProfilePage fillLastName(String lastName) throws InterruptedException {
        setText(txtbox_LastName,lastName,"Last name");
        return this;
    }

    public EditProfilePage clickSaveChanges() throws InterruptedException {
        click(button_SaveChanges,"Save Changes button");
        return this;
    }

    public String validateSuccessPopUp(){
        isElementVisible(popup_header,"Popup header");
        String message = getText(popup_message);
        click(popup_OKButton,"Popup ok button");
        return message;
    }





}
