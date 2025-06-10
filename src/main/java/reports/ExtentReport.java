package reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

public class ExtentReport {

    public static ExtentReports extent;


    public static void initReport(){
        if(isNull(extent)) {
            extent = new ExtentReports();
            ExtentSparkReporter sparkReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/index.html");

            sparkReporter.config().setTheme(Theme.DARK);
            sparkReporter.config().setReportName("TrustWallet Automation Report");
            sparkReporter.config().setDocumentTitle("checking");
            extent.attachReporter(sparkReporter);
        }

    }

    public static void tearDownReport(){
        if(nonNull(extent)) {
            extent.flush();
            ExtentManager.unload();
        }
    }

    public static void createTest(String testCaseName){
        ExtentManager.setExtentTest(extent.createTest(testCaseName));
    }
}
