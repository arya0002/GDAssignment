package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
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

    public void isAddLicensesHeaderVisible() throws InterruptedException {
        isElementVisible(header_AddLicensesPage,"Add Licenses header");
    }

    public void fillLicenseNo(String licenseNo){
        setText(txtBox_LicenseNo,licenseNo,"License No");
    }


    public void selectLicenseExpDate(String doe) {
        click(selector_LicenseExpDate,"License Expiration Date");
        String[] parts = doe.split("-");
        if (parts.length != 3) {
            throw new IllegalArgumentException("DOB format must be dd-MMMM-yyyy, e.g. 04-July-2017");
        }
        String day = parts[0];
        String month = parts[1];
        String year = parts[2];
        DatePickerUtils.selectDate(day,month,year,selector_YearHeader,selector_YearList);
       }

    public AddParticipantIDPage clickContinue() throws InterruptedException {
        click(button_Continue,"Continue button");
        return new AddParticipantIDPage();

    }



}
