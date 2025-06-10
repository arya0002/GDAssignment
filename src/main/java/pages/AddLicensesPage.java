package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import reports.ExtentLogger;
import utils.driver.DriverManager;
import utils.generic.DatePickerUtils;
import utils.generic.GenericMethods;


public class AddLicensesPage extends BasePage {

    String passcode;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='Add Licenses']")
    private WebElement header_AddLicensesPage;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text='Add Licenses']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[1]/android.view.View/android.widget.EditText")
    private WebElement txtBox_LicenseNo;

    @AndroidFindBy(xpath ="(//android.widget.Button[@text='CONTINUE'])[2]")
    private WebElement button_Continue;


    @AndroidFindBy(xpath ="//android.webkit.WebView[@text='Add Licenses']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.view.View[2]/android.view.View/android.view.View/android.view.View[2]/android.view.View/android.widget.Spinner")
    private WebElement selector_LicenseExpDate;

    @AndroidFindBy(xpath ="//android.widget.TextView[@resource-id='android:id/date_picker_header_year']")
    private WebElement selector_YearHeader;


    @AndroidFindBy(xpath ="//android.widget.ListView[@resource-id='android:id/date_picker_year_picker']")
    private WebElement selector_YearList;


    public AddLicensesPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }



    public AddLicensesPage isAddLicensesHeaderVisible() throws InterruptedException {
        isElementVisible(header_AddLicensesPage,"Add Licenses header");
        return this;
    }

    public AddLicensesPage fillLicenseNo(String licenseNo){
        setText(txtBox_LicenseNo,licenseNo,"License No");
        return this;
    }


    public AddLicensesPage selectLicenseExpDate(String doe) {
        click(selector_LicenseExpDate,"License Expiration Date");
        DatePickerUtils.selectDate(doe,selector_YearHeader,selector_YearList);
        ExtentLogger.info("License Expiration Date is set to"+doe);
        return this;
       }

    public AddParticipantIDPage clickContinue() throws InterruptedException {
        click(button_Continue,"Continue button");
        return new AddParticipantIDPage();

    }



}
