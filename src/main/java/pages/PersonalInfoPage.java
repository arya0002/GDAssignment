package pages;





import reports.ExtentLogger;
import utils.generic.GenericMethods;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.generic.DatePickerUtils;
import utils.driver.DriverManager;

import static utils.generic.DatePickerUtils.selectDate;


public class PersonalInfoPage extends BasePage {

    String passcode;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Personal Info']")
    private WebElement header_CreateAccountPage;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text='Personal Info']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.EditText")
    private WebElement txtBox_Firstname;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text='Personal Info']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.EditText")
    private WebElement txtBox_Lastname;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text='Personal Info']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[3]/android.view.View/android.widget.EditText")
    private WebElement txtBox_Email;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text='Personal Info']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[4]/android.view.View/android.widget.EditText")
    private WebElement txtBox_Phone;

    @AndroidFindBy(xpath ="//android.widget.Button[@text='CONTINUE']")
    private WebElement button_Continue;

    @AndroidFindBy(xpath ="//android.widget.Image")
    private WebElement selector_State;

    @AndroidFindBy(xpath = "(//android.widget.TextView[@text='State'])[2]")
    private WebElement header_StateList;

    private String stateName = "//android.widget.TextView[@text='%s']";

    @AndroidFindBy(xpath ="//android.widget.Spinner")
    private WebElement selector_DOB;

    @AndroidFindBy(xpath ="//android.widget.TextView[@resource-id='android:id/date_picker_header_year']")
    private WebElement selector_YearHeader;

    @AndroidFindBy(xpath ="//android.widget.ListView[@resource-id='android:id/date_picker_year_picker']")
    private WebElement selector_YearList;


    public PersonalInfoPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    public PersonalInfoPage isPersonalInfoHeaderVisible() throws InterruptedException {
        isElementVisible(header_CreateAccountPage,"PersonalInfo header");
        return this;
    }


    public PersonalInfoPage fillFirstName(String firstName) throws InterruptedException {
        setText(txtBox_Firstname,firstName,"First Name");
        return this;
    }

    public PersonalInfoPage fillLastName(String lastName) throws InterruptedException {
        setText(txtBox_Lastname,lastName,"Last Name");
        return this;
    }

    public PersonalInfoPage fillEmail(String email) throws InterruptedException {
        setText(txtBox_Email,email,"Email");
        return this;
    }

    public PersonalInfoPage fillPhone(String phone) throws InterruptedException {
        String formattedPhoneno = GenericMethods.formatPhoneNumber(phone);
        setText(txtBox_Phone,formattedPhoneno,"Phone");
        return this;
    }

    public PersonalInfoPage selectState(String state) throws InterruptedException {
        click(selector_State,"State");
        isElementVisible(header_StateList,"State header");
        chooseItemAndclick(stateName,state);
        return this;
    }

    public PersonalInfoPage selectDOB(String dob) {
        click(selector_DOB,"dob");
        String[] parts = dob.split("-");
        selectDate(dob,selector_YearHeader,selector_YearList);
        ExtentLogger.info("Date of birth is set to"+dob);
        return this;
       }

    public AddLicensesPage clickContinue() throws InterruptedException {
        click(button_Continue,"Continue button");
        return new AddLicensesPage();
    }



}
