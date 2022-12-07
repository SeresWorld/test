import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Step;
import org.openqa.selenium.By;

public class DailyCheck extends PageBase {
    public DailyCheck(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private String calendarButton = "calendar";
    private By calendar = By.xpath("//XCUIElementTypeApplication[@name=\"DailyCheck\"]/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
    @Step

    public void open_calendar() {
        MobileElement calendar = (MobileElement) driver.findElementByAccessibilityId(calendarButton);
        waitForVisability(calendar);
        click(calendar);
    }
    @Step
    public void swipe_calendar() {
        MobileElement obj = (MobileElement) driver.findElement(calendar);
        waitForVisability(obj);
        swipeElementIOS(obj, Direction.LEFT);
    }

}
