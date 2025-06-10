package pages;


import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Pause;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import reports.ExtentLogger;
import utils.driver.DriverManager;

import java.time.Duration;
import java.util.Collections;

import static utils.generic.DynamicXpathUtils.getXpath;


public class BasePage {


    private void explicitWait(WebElement element){
        new WebDriverWait(DriverManager.getDriver(),Duration.ofSeconds(10)).withMessage(()->"some problem in finding element "+element)
                .pollingEvery(Duration.ofSeconds(1))
                .until(ExpectedConditions.visibilityOf(element));
    }

    protected void isElementVisible(WebElement element, String elementname){
       explicitWait(element);
       Assert.assertTrue(element.isDisplayed(),elementname+" is not displayed");
       ExtentLogger.pass(elementname+" is displayed");
    }

    protected boolean isElementPresent(WebElement element, String elementname){
        explicitWait(element);
        if(element.isDisplayed())
            return true;
        else {
            return false;
        }

    }

    protected String getText(WebElement element){
        explicitWait(element);
        String textValue = element.getText();
        ExtentLogger.info("Text extracted from element "+element+" is "+textValue);
        return textValue;
    }

    protected void setText(WebElement element, String text, String elementname){
        explicitWait(element);
        element.sendKeys(text);
        ExtentLogger.info("Text entered to element "+elementname+" is "+text);
    }

    protected void click(WebElement element, String elementname){
        explicitWait(element);
        element.click();
        ExtentLogger.pass(elementname+" is clicked successfully");
    }

    protected void click(By by, String elementname){
        click((WebElement) DriverManager.getDriver().findElement(by),elementname);
        ExtentLogger.pass(elementname+" is clicked successfully");
    }

    protected void click(String locatortype,String value,String elementname){
        if (locatortype.equalsIgnoreCase("xpath")){
            click(By.xpath(value),elementname);
        } else if (locatortype.equalsIgnoreCase("id")) {
            click(By.id(value),elementname);

        }
    }

    protected void chooseItemAndclick(String xpath,String value) throws InterruptedException {
            String newXpath = getXpath(xpath, value);
            Thread.sleep(500);
            click(DriverManager.getDriver().findElement(By.xpath(newXpath)),value);
    }

    /**
     * Performs a tap action on the given element using W3C Actions.
     * @param element WebElement to tap
     */
    protected void tapOnElement(WebElement element, String elementName) {
        try {
            // Calculate center coordinates
            int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
            int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

            PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
            Sequence tapSequence = new Sequence(finger, 1);

            tapSequence.addAction(finger.createPointerMove(Duration.ofMillis(0),
                    PointerInput.Origin.viewport(), centerX, centerY));
            tapSequence.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
            tapSequence.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

            ((AppiumDriver) DriverManager.getDriver()).perform(Collections.singletonList(tapSequence));
            ExtentLogger.pass(elementName+" is clicked successfully");
        } catch (Exception e) {
            System.err.println("W3C Tap failed. Falling back to click(). Error: " + e.getMessage());
            element.click();  // Fallback if W3C tap fails
        }
    }



    /**
     * Long press on an element.
     */
    protected void longPressOnElement(WebElement element, int durationMs, String elementName) {
        int centerX = element.getLocation().getX() + (element.getSize().getWidth() / 2);
        int centerY = element.getLocation().getY() + (element.getSize().getHeight() / 2);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Sequence longPress = new Sequence(finger, 1);

        longPress.addAction(finger.createPointerMove(Duration.ZERO, PointerInput.Origin.viewport(), centerX, centerY));
        longPress.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        longPress.addAction(new Pause(finger, Duration.ofMillis(durationMs)));
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));

        ((AppiumDriver) DriverManager.getDriver()).perform(Collections.singletonList(longPress));
        ExtentLogger.pass(elementName+" is long pressed successfully");
    }

    /**
     * Swipe from one point to another.
     */
    protected void scrollInsideElement(WebElement element, String elementName,String year) {
        int maxScrolls = 12;
        boolean found = false;

        for (int i = 0; i < maxScrolls; i++) {
            try {
                String yearXpath = String.format("//android.widget.TextView[@text='%s']", year);
                if (DriverManager.getDriver().findElements(By.xpath(yearXpath)).size() > 0) {
                    found = true;
                    break;
                }
            } catch (Exception ignored) {
            }

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
            ExtentLogger.pass(elementName + " is swipped successfully");
        }
    }

}
