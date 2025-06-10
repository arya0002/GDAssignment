package utils.driver;


import org.openqa.selenium.WebDriver;
import utils.property.PropertyUtils;
import java.net.MalformedURLException;
import java.util.Objects;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;
import static utils.driver.DriverManager.*;


public final class DriverUtils {

    //class should be closed for modification and open for extension
    //Factory pattern --> generating child classes for a super class or an interface
    //Thread safe
    //local variable are thread safe



    private DriverUtils(){}


    public static void initializeDriver() throws MalformedURLException {
        if(isNull(getDriver())){
            String modeValue = PropertyUtils.getValue("mode");
            WebDriver driver = DriverFactory.get(Modes.valueOf(modeValue.toUpperCase()));
            setDriver(driver);
        }
    }


//    public static WebDriver getDriver () {
//        if (driver == null) {
//            throw new IllegalStateException("Driver not initialized. Please call initializeDriver first.");
//        }
//        return driver;
//    }



    public static void quitDriver () {
        if(nonNull(getDriver())){
            getDriver().quit();
        }
        unload();
    }
}
