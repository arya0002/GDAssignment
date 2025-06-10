package tests;

import com.aventstack.extentreports.ExtentTest;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import reports.ExtentReport;
import utils.driver.DriverUtils;

import java.lang.reflect.Method;
import java.net.MalformedURLException;

public class BaseTest {



    @BeforeMethod
    public void setUp() throws MalformedURLException {

        DriverUtils.initializeDriver();

    }

    @AfterMethod
    public void tearDown(ITestResult result) {
        DriverUtils.quitDriver();
    }

}
