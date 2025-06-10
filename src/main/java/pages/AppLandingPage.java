package pages;


import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import utils.driver.DriverManager;


public class AppLandingPage extends BasePage{



    @AndroidFindBy(xpath="//android.widget.Button[@resource-id='android:id/button1']")
    private WebElement okButton_LeaforgPopup;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text=\"TESTLEAF\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[1]/android.widget.EditText")
    private WebElement txtfield_Username;

    @AndroidFindBy(xpath="//android.webkit.WebView[@text=\"TESTLEAF\"]/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View/android.view.View[2]/android.widget.EditText")
    private WebElement txtfield_Password;

    @AndroidFindBy(xpath = "//android.widget.Button[@text='LOGIN']")
    private WebElement button_Login;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Create an Account']")
    private WebElement link_CreateAccount;

    public AppLandingPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverManager.getDriver()), this);
    }

    public void clickPopupOkButton() throws InterruptedException {
        click(okButton_LeaforgPopup,"okButton_LeaforgPopup button");
    }

    public PersonalInfoPage clickCreateAcountLink() throws InterruptedException {
        click(link_CreateAccount,"link_CreateAccount link");
        //Thread.sleep(5000);
        return new PersonalInfoPage();
    }



}
