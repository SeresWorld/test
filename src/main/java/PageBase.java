import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageBase {
    AppiumDriver driver;

    public PageBase(AppiumDriver appiumDriver) {
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
        driver = appiumDriver;
    }
    public AndroidTouchAction actions;
    public static final long WAIT = 10;

    public void waitForVisability (MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void containsmessageAssert (MobileElement element, String text) {
        waitForVisability(element);
        String text_element = element.getText();
        text_element.contains(text);
    }

    public void clear (MobileElement element) {
        waitForVisability(element);
        element.clear();
    }

    public void click (MobileElement element) {
        waitForVisability(element);
        element.click();
    }

    public void sendText (MobileElement element, String text) {
        waitForVisability(element);
        element.sendKeys(text);
    }

    public String getAttribute (MobileElement element, String attribute) {
        waitForVisability(element);
        String elem_atr = element.getAttribute(attribute);
        return elem_atr;
    }

    public void scrollDown (int endPoint, int durationSec) {
        Dimension dimension = driver.manage().window().getSize();
        int startPoint = (int) (dimension.getHeight() * 0.8);

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(durationSec)))
                .moveTo(PointOption.point(0, endPoint))
                .release()
                .perform();
    }

    public void scrollDown_with_start_point (int startPoint, int endPoint, int durationSec) {

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(durationSec)))
                .moveTo(PointOption.point(0, endPoint))
                .release()
                .perform();
    }
}
