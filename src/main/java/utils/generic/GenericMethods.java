package utils.generic;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.ExtentLogger;
import utils.driver.DriverManager;

import java.time.Duration;
import java.util.List;
import java.util.function.Consumer;

public class GenericMethods {



    public static String formatPhoneNumber(String input) {
        if (input != null && input.length() == 10) {
            return input.substring(0, 3) + "-" + input.substring(3, 6) + "-" + input.substring(6);
        }
        throw new IllegalArgumentException("Phone number must be 10 digits");
    }



    public static void validateSuccessPopup(String titleText, String messageText, WebElement info, WebElement body, WebElement button) {

        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        try{
            // Validate popup title
            WebElement title = wait.until(ExpectedConditions.visibilityOf(info));
            Assert.assertEquals(title.getText().trim(), titleText, "Popup title mismatch");

            // Validate popup message (generic text match — update if specific ID exists)
            WebElement msg = wait.until(ExpectedConditions.visibilityOf(body));
            Assert.assertEquals(msg.getText().trim(), messageText, "Popup message mismatch");

            ExtentLogger.info("Registration Successful Popup found: " + messageText);

            // Optional: click OK or Close button (adjust text as needed)

            WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(button));
            okButton.click();
        }catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Popup not found or failed to validate.", e);
        }
    }

}
