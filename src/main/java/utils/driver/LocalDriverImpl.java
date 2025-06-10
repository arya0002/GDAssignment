package utils.driver;

import constants.FrameworkConstants;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class LocalDriverImpl implements IDriver {
    @Override
    public WebDriver getDriver() throws MalformedURLException {
        // both option will work, with UiAutomator2Options we dont have to put so many capabilities

        UiAutomator2Options capabilities = new UiAutomator2Options();
        capabilities.setApp(FrameworkConstants.getInstance().getApkFilePath());
        capabilities.setCapability("automationName", "UiAutomator2");

        // server details
        // There is a design change in the Java Client version 2.0.0 as they mention on their site: AppiumDriver is now an abstract class, use IOSDriver and AndroidDriver which both extend it.
        //Webdriver is parent interface for all driver so we can use it
        return new AndroidDriver(new URL("http://0.0.0.0:4723"), capabilities);


    }
}
