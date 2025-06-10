package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import reports.ExtentLogger;
import utils.driver.DriverManager;


public class AppLandingPage extends BasePage{



    @AndroidFindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
    private WebElement okButton_LeaforgPopup;

    @AndroidFindBy(xpath="//android.widget.TextView[@text='LeafOrg']")
    private WebElement header_LeaforgLoginPage;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text='TESTLEAF']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.EditText")
    private WebElement txtfield_Username;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text='TESTLEAF']/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")
    private WebElement txtfield_Password;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='LOGIN']")
    private WebElement button_Login;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Create an Account']")
    private WebElement link_CreateAccount;

    public AppLandingPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    public AppLandingPage clickPopupOkButton() throws InterruptedException {
        try {
            if (isElementPresent(okButton_LeaforgPopup, "Leaforg Popup OK button")) {
                click(okButton_LeaforgPopup, "Leaforg Popup OK button");
            }
        } catch (Exception e) {
            // Log that popup was not present, but continue
            System.out.println("Popup not present, continuing without clicking OK.");
            ExtentLogger.info("Popup not present, continuing without clicking OK.");
        }
        return this;
    }

    public AppLandingPage isLandingPageHeaderVisible() throws InterruptedException {
        isElementVisible(header_LeaforgLoginPage,"LeafOrg header");
        return this;
    }

    public AppLandingPage fillUserName(String userName) throws InterruptedException {
        setText(txtfield_Username,userName,"Username");
        return this;
    }

    public AppLandingPage fillPassword(String password) throws InterruptedException {
        setText(txtfield_Password,password,"Password");
        return this;
    }

    public UserHomePage clickLogin() throws InterruptedException {
        click(button_Login,"Login button");
        return new UserHomePage();
    }

    public PersonalInfoPage clickCreateAcountLink() throws InterruptedException {
        click(link_CreateAccount,"link_CreateAccount link");
        //Thread.sleep(5000);
        return new PersonalInfoPage();
    }



}
