package reports;

import com.aventstack.extentreports.MediaEntityBuilder;
import utils.generic.ScreenshotUtils;

public class ExtentLogger {

    private ExtentLogger(){}

    public static void pass(String message){
        ExtentManager.getExtentTest().pass(message);
    }

    public static void fail(String message){
//        ExtentManager.getExtentTest().fail(message,
//                MediaEntityBuilder.createScreenCaptureFromBase64String(ScreenshotUtils.getBase64Image()).build());
        String base64Image = null;
        try {
            base64Image = ScreenshotUtils.getBase64Image();
        } catch (Exception e) {
            System.out.println("Warning: Could not capture screenshot: " + e.getMessage());
        }

        if (base64Image != null) {
            ExtentManager.getExtentTest().fail(message,
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64Image).build());
        } else {
            ExtentManager.getExtentTest().fail(message);
        }
    }

    public static void skip(String message){
        ExtentManager.getExtentTest().skip(message);
    }

    public static void info(String message){
        ExtentManager.getExtentTest().info(message);
    }
}
