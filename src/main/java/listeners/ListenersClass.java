package listeners;

import org.testng.ISuite;
import org.testng.ISuiteListener;
import org.testng.ITestListener;
import org.testng.ITestResult;
import reports.ExtentLogger;
import reports.ExtentReport;

public class ListenersClass implements ITestListener, ISuiteListener {

    @Override //Before method
    public void onTestStart(ITestResult result) {
        ExtentReport.createTest(result.getName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        ExtentLogger.pass(result.getName()+" is passed");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        ExtentLogger.fail(result.getName()+" is failed");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        ExtentLogger.skip(result.getName()+" is skipped");
    }

    @Override //Before Suite
    public void onStart(ISuite suite) {
        ExtentReport.initReport();
    }

    @Override //After Suite
    public void onFinish(ISuite suite) {
        ExtentReport.tearDownReport();
    }
}
