package utils.driver;

import org.openqa.selenium.WebDriver;

import java.net.MalformedURLException;

public interface IDriver {

    //Interface gives you a skeleton, we can build our code on top of it

    public WebDriver getDriver() throws MalformedURLException;
}
