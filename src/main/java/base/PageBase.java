package base;

import config.environment.ThreadEnvironment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidTouchAction;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class PageBase extends ThreadEnvironment {

    public PageBase(AppiumDriver<MobileElement> appiumDriver) {
        driver = appiumDriver;
        PageFactory.initElements(new AppiumFieldDecorator(appiumDriver), this);
    }

    public AndroidTouchAction actions;
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    public static final long WAIT = 10;

    @Step("Ожидание видимости элемента {element} в течении {WAIT} секунд")
    public void waitForVisability(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException ex) {
            logger.error("Can't see element: " + element);
            throw ex;
        }
    }

    @Step("Ожидание видимости элемента {element} в течении {WAIT} секунд")
    public void waitForVisability(By element, long WAIT) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.visibilityOfElementLocated(element));
        } catch (TimeoutException ex) {
            logger.error("Can't see element: " + element);
            throw ex;
        }
    }

    @Step("Ожидание видимости элемента {element}")
    public void waitForVisability(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            logger.error("Can't see element:" + element);
            throw ex;
        }
    }

    @Step("Ожидание {timer} секунд элемента {element}")
    public void explicitWaitToClickable(By element, int timer) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timer);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (Exception ex) {
            logger.error("Element: " + element + " is not clickable");
            throw ex;
        }

    }

    @Step("Проверка совпадения текста элемента '{text_element}' и текста '{text}'")
    public void containsMessageAssert(By element, String text) {
        try {
            waitForVisability(element);
            String text_element = driver.findElement(element).getText();
            text_element.contains(text);
        } catch (Exception ex) {
            logger.error("Can't see text: " + text + " in the element: " + text);
            throw ex;
        }
    }

    public void clear(MobileElement element) {
        waitForVisability(element);
        element.clear();
    }

    public void click(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (Exception ex) {
            logger.error("Can't click element: " + element);
            throw ex;
        }

    }

    public void click(MobileElement element) {
        WebDriverWait wait = new WebDriverWait(driver, WAIT);
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }

    public String getText(MobileElement element) {
        waitForVisability(element);
        String element_text = element.getText();
        return element_text;
    }

    public void sendText(MobileElement element, String text) {
        waitForVisability(element);
        element.sendKeys(text);
    }

    public void sendText(By element, String text) {
        try {
            explicitWaitToClickable(element, 10);
            driver.findElement(element).sendKeys(text);
        } catch (Exception ex) {
            logger.error("Can't send text to the element: " + element);
            throw ex;
        }
    }

    public String getAttribute(By element, String attribute) {
        try {
            waitForVisability(element);
            String elem_atr = driver.findElement(element).getAttribute(attribute);
            return elem_atr;
        } catch (Exception ex) {
            logger.error("Can't get attribute: " + attribute + " of the element: " + element);
            throw ex;
        }
    }

    public void scrollDown(int endPoint, int durationSec) {
        Dimension dimension = driver.manage().window().getSize();
        int startPoint = (int) (dimension.getHeight() * 0.8);

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(durationSec)))
                .moveTo(PointOption.point(0, endPoint))
                .release()
                .perform();
    }

    public void scrollDown_with_start_point(int startPoint, int endPoint, int durationSec) {

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(durationSec)))
                .moveTo(PointOption.point(0, endPoint))
                .release()
                .perform();
    }

    public void swipeElementIOS(MobileElement el, Direction dir) {
        System.out.println("swipeElementIOS(): dir: '" + dir + "'"); // always log your actions

        // Длительность анимации и нажатия
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 500; // ms

        // Инициирование переменных экрана
        Dimension dims = driver.manage().window().getSize();
        Rectangle rect = el.getRect();

        // Проверка на нахождение элемента вне экрана
        if (rect.x >= dims.width || rect.x + rect.width <= 0
                || rect.y >= dims.height || rect.y + rect.height <= 0) {
            throw new IllegalArgumentException("swipeElementIOS(): элемент вне экрана");
        }

        // Инициирование краев приложения как переменных
        int leftBorder, rightBorder, upBorder, downBorder;
        leftBorder = 0;
        rightBorder = 0;
        upBorder = 0;
        downBorder = 0;

        if (rect.x < 0) {
            rect.width = rect.width + rect.x;
            rect.x = 0;
        }
        if (rect.y < 0) {
            rect.height = rect.height + rect.y;
            rect.y = 0;
        }
        if (rect.width > dims.width)
            rect.width = dims.width;
        if (rect.height > dims.height)
            rect.height = dims.height;

        PointOption pointOptionStart, pointOptionEnd;
        switch (dir) {
            case DOWN: // от верха к низу
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + upBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - downBorder);
                break;
            case UP: // от низа к верху
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - downBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + upBorder);
                break;
            case LEFT: // справа налево
                pointOptionStart = PointOption.point(rect.x + rect.width - rightBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + leftBorder,
                        rect.y + rect.height / 2);
                break;
            case RIGHT: // слева направо
                pointOptionStart = PointOption.point(rect.x + leftBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + rect.width - rightBorder,
                        rect.y + rect.height / 2);
                break;
            default:
                throw new IllegalArgumentException("swipeElementIOS(): dir: '" + dir + "' NOT supported");
        }

        // Выполнение свайпа с помощью TouchAction
        try {
            new TouchAction<>(driver)
                    .press(pointOptionStart)
                    // Ожидание выполнения свайпа
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementIOS(): TouchAction FAILED\n" + e.getMessage());
            return;
        }

        // Ожидание завершения анимации
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // ignore
        }
    }

    public void swipeToRefreshAndroid(By elem) {
        MobileElement el = (MobileElement) driver.findElement(elem);
        final int ANIMATION_TIME = 200;
        int edgeBorder;
        PointOption<?> pointOptionStart, pointOptionEnd;
        final int PRESS_TIME = 800;
        // init screen variables
        Rectangle rect = el.getRect();
        // sometimes it is needed to configure edgeBorders
        // you can also improve borders to have vertical/horizontal
        // or left/right/up/down border variables
        edgeBorder = 0;
        pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                rect.y + edgeBorder);
        pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                (rect.y + rect.height - edgeBorder) + 500);

        try {
            new TouchAction<>(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // игнорирование
        }
    }

    public void swipeElementAndroid(By elem, Direction dir) {
        MobileElement el = driver.findElement(elem);
        logger.info("swipeElementAndroid(): dir: '" + dir + "'");

        // Animation default time:
        //  - Android: 300 ms
        //  - iOS: 200 ms
        // final value depends on your app and could be greater
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder;
        PointOption<?> pointOptionStart, pointOptionEnd;

        // init screen variables
        Rectangle rect = el.getRect();
        // sometimes it is needed to configure edgeBorders
        edgeBorder = 0;

        switch (dir) {
            case DOWN: // from up to down
                int x = rect.x + rect.width / 2;
                int y = rect.y + edgeBorder;
                int x2 = rect.x + rect.width / 2;
                int y2 = (rect.y + rect.height - edgeBorder) + 300;

                logger.info("x_start = " + x + ", y_start = " + y + ", x_end = " + x2 + ", y_end = " + y2);

                pointOptionStart = PointOption.point(x, y);
                pointOptionEnd = PointOption.point(x2, y2);
                break;
            case UP: // from down to up
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - edgeBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + edgeBorder);
                break;
            case LEFT: // from right to left
                pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                break;
            case RIGHT: // from left to right
                pointOptionStart = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                break;
            default:
                logger.error("Error during swiping");
                throw new IllegalArgumentException("swipeElementAndroid(): dir: '" + dir + "' NOT supported");
        }

        // execute swipe using TouchAction
        try {
            new TouchAction<>(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .release().perform();
        } catch (Exception e) {
            logger.error("swipeElementAndroid(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {

        }
    }


    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    public void numbersComparsion(int a, int b, String message) {
        Assert.assertEquals(a, b, message);
    }

    public void textsComparsion(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
}
