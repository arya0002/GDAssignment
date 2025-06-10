package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import reports.ExtentLogger;
import utils.driver.DriverManager;
import utils.generic.DatePickerUtils;
import utils.generic.GenericMethods;


public class AddParticipantIDPage extends BasePage {

    String passcode;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Add Participant ID']")
    private WebElement header_AddParticipantIDPage;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text='Add Participant ID']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.EditText")
    private WebElement txtBox_ParticipantIDNo;

    @AndroidFindBy(xpath ="//android.widget.Button[@text='SUBMIT REGISTRATION']")
    private WebElement button_SubmitRegistration;


    @AndroidFindBy(xpath ="//android.webkit.WebView[@text='Add Participant ID']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.Spinner")
    private WebElement selector_JoinDate;

    @AndroidFindBy(xpath ="//android.widget.TextView[@resource-id='android:id/date_picker_header_year']")
    private WebElement selector_YearHeader;


    @AndroidFindBy(xpath ="//android.widget.ListView[@resource-id='android:id/date_picker_year_picker']")
    private WebElement selector_YearList;

    @AndroidFindBy(xpath ="//android.widget.TextView[@text='Info']")
    private WebElement popup_header;

    @AndroidFindBy(xpath ="//android.widget.TextView[contains(@text, 'High Five')]")
    private WebElement popup_message;

    @AndroidFindBy(xpath ="//android.widget.Button[@text='OK']")
    private WebElement popup_OKButton;





    public AddParticipantIDPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    public AddParticipantIDPage isAddLicensesHeaderVisible() throws InterruptedException {
        isElementVisible(header_AddParticipantIDPage,"Add Paricipant ID header");
        return this;
    }

    public AddParticipantIDPage fillParticipantIDNo(String licenseNo){
        setText(txtBox_ParticipantIDNo,licenseNo,"Paricipant ID No");
        return this;
    }


    public AddParticipantIDPage selectJoinDate(String doj) {
        click(selector_JoinDate,"Join Date");
        DatePickerUtils.selectDate(doj,selector_YearHeader,selector_YearList);
        ExtentLogger.info("Date of joining is set to"+doj);
        return this;
       }

    public AddParticipantIDPage clickSubmitRegistrationButton() throws InterruptedException {
        click(button_SubmitRegistration,"Submit Registration button");
        return this;
    }

    public AddParticipantIDPage validateSuccessPopUp(){
        GenericMethods.validateSuccessPopup("Info","High Five! Your account is being reviewed and we will contact you as soon as you are verified.",popup_header,popup_message,popup_OKButton);
    return this;
    }



}
