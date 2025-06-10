package utils.generic;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import utils.driver.DriverManager;

public class ScreenshotUtils {

//    private ScreenshotUtils(){}
//
//    public static String getBase64Image(){
//        return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
//    }

    private ScreenshotUtils(){}

    public static String getBase64Image(){
        if (DriverManager.getDriver() == null) {
            System.out.println("Driver is null, cannot take screenshot.");
            return null;
        }
        try {
            return ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BASE64);
        } catch (Exception e) {
            System.out.println("Failed to capture screenshot: " + e.getMessage());
            return null;
        }
    }
}
