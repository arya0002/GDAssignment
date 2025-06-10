package utils.driver;

import org.openqa.selenium.WebDriver;

public final class DriverManager {
    private DriverManager(){}


    private static ThreadLocal<WebDriver> threadLocalDriver = new ThreadLocal<>();


    public static void setDriver(WebDriver driver){
        threadLocalDriver.set(driver);
    }

    public static WebDriver getDriver(){
        return threadLocalDriver.get();
    }

    //safely remove values from threadLocalDriver
    public static void unload(){
        threadLocalDriver.remove();
    }

}
