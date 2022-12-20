import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Rectangle;
import pages.PageBase;

import java.time.Duration;

public class DailyCheck extends PageBase {
    public DailyCheck(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    public TouchAction touchAction;

    private String calendarButton = "calendar";
    private By calendar = By.xpath("//XCUIElementTypeApplication[@name=\"DailyCheck\"]/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
    private By editButton = By.name("Edit");
    private By addNewTaskButton = By.name("plus.circle");
    private By dueTimeInsert = By.xpath("//XCUIElementTypeTextField[@value='Due Time']");
    private By wheelHours = By.xpath("//XCUIElementTypePickerWheel[1]");
    private By wheelMinutes = By.xpath("//XCUIElementTypePickerWheel[1]");
    private By wheelSettings = By.xpath("//XCUIElementTypePickerWheel[1]");
    private By doneButton = By.name("Done");
    private By titleInsert = By.xpath("//XCUIElementTypeTextField[@value='Title']");
    private By descriptionInsert = By.xpath("//XCUIElementTypeTextField[@value='Description']");
    private By saveButton = By.name("Save");



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

    @Step
    public void open_edit_mode() {
        MobileElement obj = (MobileElement) driver.findElement(editButton);
        waitForVisability(obj);
        click(obj);
    }

    @Step
    public void openCreateTaskModal() {
        MobileElement obj = (MobileElement) driver.findElement(addNewTaskButton);
        waitForVisability(obj);
        click(obj);
    }

    @Step
    public void dueTimeClick() {
        MobileElement obj = (MobileElement) driver.findElement(dueTimeInsert);
        waitForVisability(obj);
        click(obj);
    }

    @Step
    public void setTitleInsert() {
        MobileElement title = (MobileElement) driver.findElement(titleInsert);
        waitForVisability(title);
        sendText(title, "Test Task");
    }

    @Step
    public void setDescriptionInsert() {
        MobileElement description = (MobileElement) driver.findElement(descriptionInsert);
        waitForVisability(description);
        sendText(description, "Test description");
    }

    @Step
    public void saveButtonClick() throws InterruptedException {
        MobileElement saveBtn = (MobileElement) driver.findElement(saveButton);
        waitForVisability(saveBtn);
        click(saveBtn);
        Thread.sleep(5000);
    }

    @Step
    public void setTime(String hours, String minutes, String setting) {
        MobileElement hoursWheel = (MobileElement) driver.findElement(wheelHours);
        MobileElement minutesWheel = (MobileElement) driver.findElement(wheelMinutes);
        MobileElement settingWheel = (MobileElement) driver.findElement(wheelSettings);
        MobileElement doneBtn = (MobileElement) driver.findElement(doneButton);
        hoursWheel.setValue(hours);
        minutesWheel.setValue(minutes);
        settingWheel.setValue(setting);
        click(doneBtn);
    }

    @Step
    public void longPress() throws InterruptedException {

        final int ANIMATION_TIME = 200;
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder;
        final int PRESS_TIME = 2000;
        Thread.sleep(2000);
        MobileElement el = (MobileElement) driver.findElement(By.xpath(""));
        el.click();
        Rectangle rect = el.getRect();
        edgeBorder = 0;
        pointOptionStart = PointOption.point(rect.x,
                rect.y);
        pointOptionEnd = PointOption.point(rect.x,
                rect.y+ 100);

        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElement(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // игнорирование
        }
    }
}
