// DatePickerUtils.java
package utils.generic;

import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.BasePage;
import reports.ExtentLogger;
import utils.driver.DriverManager;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Collections;
import java.util.Date;

public class DatePickerUtils extends BasePage {

    /**
     * Select a date in a calendar-style Android DatePicker.
     */
    public static void selectDate(String day, String month, String year,WebElement yearHeader,WebElement yearList) {
        WebDriverWait wait = new WebDriverWait(((AppiumDriver) DriverManager.getDriver()), Duration.ofSeconds(10));
        // Tap the year header
        wait.until(ExpectedConditions.elementToBeClickable(yearHeader)).click();

        //scrollToYear(year,yearList);
        scrollToYearSmart(year,yearList);
        // Select year
        String yearXpath = String.format("//android.widget.ListView[@resource-id='android:id/date_picker_year_picker']//android.widget.TextView[@text='%s']", year);
        WebElement yearElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(yearXpath)));
        yearElement.click();

        String targetMonthYear = month.substring(0, 1).toUpperCase() + month.substring(1).toLowerCase() + " " + year;
        scrollToMonthSmart(targetMonthYear);

        // Build content-desc for date (e.g., "08 June 2025")
        String formattedDay = String.format("%02d", Integer.parseInt(day));
        String contentDesc = String.format("%s %s %s", formattedDay, month, year);
        String dayXpath = String.format("//android.view.View[@content-desc='%s']", contentDesc);

        WebElement dayElement = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(dayXpath)));
        dayElement.click();

        WebElement setButton = wait.until(ExpectedConditions.elementToBeClickable(By.id("android:id/button1")));
        setButton.click();

        ExtentLogger.pass("DOB is set successfully");
    }

    /**
     * Scrolls vertically to find the given year manually using W3C actions.
     */
    public static void scrollToYear(String year, WebElement element) {
        int maxScrolls = 12;
        boolean found = false;

        for (int i = 0; i < maxScrolls; i++) {
            try {
                String yearXpath = String.format("//android.widget.TextView[@text='%s']", year);
                if (DriverManager.getDriver().findElements(By.xpath(yearXpath)).size() > 0) {
                    found = true;
                    break;
                }
            } catch (Exception ignored) {}

            // Perform manual scroll up
            //Get location and size of the picker element
            int startX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
            int startY = element.getLocation().getY() + (int) (element.getSize().getHeight() * 0.2);
            int endY = element.getLocation().getY() + (int) (element.getSize().getHeight() * 0.8);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence swipe = new Sequence(finger, 1);

            swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), startX, startY));
            swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            swipe.addAction(finger.createPointerMove(Duration.ofMillis(300), PointerInput.Origin.viewport(), startX, endY));
            swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            ((AppiumDriver) DriverManager.getDriver()).perform(Collections.singletonList(swipe));
        }

        if (!found) {
            throw new RuntimeException("Year not found after scrolling: " + year);
        }
    }

    public static void scrollToMonthSmart(String targetMonthYear) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(10));
        SimpleDateFormat format = new SimpleDateFormat("MMMM yyyy");

        By dateCell = By.xpath("//android.view.View[contains(@content-desc, ' ')]");
        By nextArrow = By.xpath("//android.widget.ImageButton[@content-desc='Next month']");
        By prevArrow = By.xpath("//android.widget.ImageButton[@content-desc='Previous month']");

        for (int i = 0; i < 12; i++) {
            try {
                WebElement firstDateCell = wait.until(ExpectedConditions.presenceOfElementLocated(dateCell));
                String currentMonthYear = extractMonthYear(firstDateCell.getAttribute("content-desc"));

                if (currentMonthYear.equalsIgnoreCase(targetMonthYear)) {
                    return;
                }

                Date current = format.parse(currentMonthYear);
                Date target = format.parse(targetMonthYear);

                if (current.before(target)) {
                    DriverManager.getDriver().findElement(nextArrow).click();
                } else {
                    DriverManager.getDriver().findElement(prevArrow).click();
                }

                Thread.sleep(500);
            } catch (Exception e) {
                throw new RuntimeException("Could not scroll to target month: " + targetMonthYear, e);
            }
        }
        throw new RuntimeException("Month not found after scrolling: " + targetMonthYear);
    }

    private static String extractMonthYear(String contentDesc) {
        String[] parts = contentDesc.split(" ");
        if (parts.length == 3) {
            return parts[1] + " " + parts[2];
        }
        return "";
    }

    public static void scrollToYearSmart(String targetYear, WebElement element) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
        int maxScrolls = 20;
        int currentYear = Integer.parseInt(wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.id("android:id/date_picker_header_year"))).getText());

        int direction = Integer.parseInt(targetYear) > currentYear ? -1 : 1;

        for (int i = 0; i < maxScrolls; i++) {
            String yearXpath = String.format("//android.widget.TextView[@text='%s']", targetYear);
            if (DriverManager.getDriver().findElements(By.xpath(yearXpath)).size() > 0) {
                return;
            }
            if (direction > 0) {
                swipeDown(element);
            } else {
                swipeUp(element);
            }
        }
        throw new RuntimeException("Year not found after scrolling: " + targetYear);
    }

    public static void swipeUp(WebElement element) {
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int startY = element.getLocation().getY() + (int) (element.getSize().getHeight() * 0.8);
        int endY = element.getLocation().getY() + (int) (element.getSize().getHeight() * 0.2);


        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), centerX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        ((AppiumDriver)DriverManager.getDriver()).perform(Collections.singletonList(swipe));
    }

    public static void swipeDown(WebElement element) {

        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int startY = element.getLocation().getY() + (int) (element.getSize().getHeight() * 0.2);
        int endY = element.getLocation().getY() + (int) (element.getSize().getHeight() * 0.8);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, startY));
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700), PointerInput.Origin.viewport(), centerX, endY));
        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        ((AppiumDriver)DriverManager.getDriver()).perform(Collections.singletonList(swipe));
    }
}
