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

import java.sql.Time;
import java.time.Duration;

/**
 * Класс PageBase используется для хранения стандартных действий (свайпы, клики, получение аттрибута элемента и т.д.).
 * Методы класса PageBase используются в шагах для каждой платформы каждого теста (toDoAndroid, toDoIOS).
 * Описание каждого стандартного действия завернуто в аннотации <b>@Step</b>. Стандартное ожидание - 10 секунд,
 * которое содержится в переменной <b>WAIT</b>.
 */

public class PageBase extends TestBase {

    public AndroidTouchAction actions;
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    public static final long WAIT = 10; // Стандартное ожидание
    public PageBase(AppiumDriver<MobileElement> driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
    }

    /**
     * Ожидание видимости элемента в течение 10 секунд.
     * @param element локатор элемента
     */
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
    /**
     * Ожидание видимости элемента в течение указанного времени.
     * @param element локатор элемента
     * @param WAIT время ожидания элемента
     */
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
    /**
     * Ожидание видимости элемента в течение 10 секунд.
     * @param element элемент MobileElement
     */
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

    /**
     * Ожидание кликабельности элемента в течение указанного времени.
     * @param element локатор элемента
     * @param timer время ожидания кликабельности
     */
    @Step("Ожидание {timer} секунд элемента {element}")
    public void explicitWaitToClickable(By element, int timer) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, timer);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            logger.error("Element: " + element + " is not clickable");
            throw ex;
        }

    }

    /**
     * Поиск текста в указанном элементе
     * @param element локатор элемента
     * @param text текст, который необходимо найти в элементе
     */
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

    /**
     * Очистка поля указываемого элемента.
     * @param element локатор элемента
     */
    @Step("Очистка поля '{element}'")
    public void clear(MobileElement element) {
        try {
            waitForVisability(element);
            element.clear();
        } catch(Exception ex) {
            logger.error("Can't clear element: " + element);
            throw ex;
        }
    }

    /**
     * Клик по указанному элементу.
     * @param element локатор элемента
     */
    @Step("Клик по элементу '{element}'")
    public void click(By element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException ex) {
            logger.error("Can't click element: " + element);
            throw ex;
        }

    }

    /**
     * Клик по указанному элементу.
     * @param element элемент MobileElement
     */
    @Step("Клик по элементу '{element}'")
    public void click(MobileElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException ex) {
            logger.error("Can't click element: " + element);
            throw ex;
        }
    }

    /**
     * Получение текста указанного элемента.
     * @param element элемент MobileElement
     * @return возвращает текст элемента в виде строки
     */
    @Step("Получение текста элемента '{element}'")
    public String getText(MobileElement element) {
        try {
            waitForVisability(element);
            String element_text = element.getText();
            return element_text;
        } catch (Exception ex) {
            logger.error("Can't get text from element: " + element);
            throw ex;
        }
    }
    /**
     * Возвращает текст указанного элемента.
     * @param element локатор элемента
     * @return возвращает текст элемента в виде строки
     */
    @Step("Получение текста элемента '{element}'")
    public String getText(By element) {
        try {
            waitForVisability(element);

            String element_text = driver.findElement(element).getText();
            return element_text;
        } catch (Exception ex) {
            logger.error("Can't get text from element: " + element);
            throw ex;
        }
    }

    /**
     * Ввод указанного текста в поле элемента.
     * @param element элемент MobileElement
     * @param text текст для ввода
     */
    @Step("Ввод текста '{text}' в элемент '{element}'")
    public void sendText(MobileElement element, String text) {
        try {
            waitForVisability(element);
            element.sendKeys(text);
        } catch (Exception ex) {
            logger.error("Can't send text to element: " + element);
            throw ex;
        }

    }

    /**
     * Ввод указанного текста в поле элемента.
     * @param element локатор элемента
     * @param text текст для ввода
     */
    @Step("Ввод текста '{text}' в элемент '{element}'")
    public void sendText(By element, String text) {
        try {
            explicitWaitToClickable(element, 10);
            driver.findElement(element).sendKeys(text);
        } catch (Exception ex) {
            logger.error("Can't send text to the element: " + element);
            throw ex;
        }
    }
    /**
     * Возвращает указанный аттрибут элемента.
     * @param element локатор элемента
     * @param attribute аттрибут, который нужно получить
     */
    @Step("Получение аттрибута у элемента '{element}'")
    public String getAttribute(By element, String attribute) {
        try {
            waitForVisability(element);
            return driver.findElement(element).getAttribute(attribute);
        } catch (Exception ex) {
            logger.error("Can't get attribute: " + attribute + " of the element: " + element);
            throw ex;
        }
    }
    /**
     * Скролл экрана вниз в течении указанного времени. Точка окончания указывается выше стартовой.
     * @param endPoint конечная точка для перемещения по экрану
     * @param durationSec длительность перемещения (в секундах)
     */
    @Step("Скролл вниз")
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
    /**
     * Скролл экрана вниз от стартовой точки до конечной в течении указанного времени.
     * Точка окончания указывается выше стартовой.
     * @param startPoint стартовая точка
     * @param endPoint конечная точка для перемещения по экрану
     * @param durationSec длительность перемещения (в секундах)
     */
    @Step("Скролл вниз с точки {startPoint} до точки {endPoint}")
    public void scrollDown_with_start_point(int startPoint, int endPoint, int durationSec) {

        actions = new AndroidTouchAction(driver)
                .press(PointOption.point(0, startPoint))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(durationSec)))
                .moveTo(PointOption.point(0, endPoint))
                .release()
                .perform();
    }
    /**
     * Скролл экрана вниз от стартовой точки в виде элемента до конечной в указанном направлении.
     * Точка окончания указывается выше стартовой.
     * @param el элемент MobileElement
     * @param dir направление для свайпа
     * Доступные варианты: UP (от низа к верху), DOWN (от верха к низу), LEFT (справа налево), RIGHT (слева направо).
     * @see PageBase#swipeElementAndroid(By, Direction)
     * @see PageBase#swipeToRefreshAndroid(By)
     */
    @Step("Свайп элемента '{el}' в направлении {dir}")
    public void swipeElementIOS(MobileElement el, Direction dir) {
        logger.info("swipeElementIOS(): dir: '" + dir + "'");
        // Стандартное ожидание анимации для IOS: 200 мс
        // Инициирование длительности анимации и нажатия
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
            // игнорирование
        }
    }
    /**
     * Скролл экрана вниз от стартовой точки в виде элемента до конечной.
     * @param elem локатор элемента
     * @see PageBase#swipeElementAndroid(By, Direction)
     * @see PageBase#swipeElementIOS(MobileElement, Direction)
     */
    @Step("Длинный свайп для обновления экрана")
    public void swipeToRefreshAndroid(By elem) {
        MobileElement el = driver.findElement(elem);
        final int ANIMATION_TIME = 200;
        int edgeBorder;
        PointOption<?> pointOptionStart, pointOptionEnd;
        final int PRESS_TIME = 800;
        // Инициирование переменных экрана
        Rectangle rect = el.getRect();
        // Иногда необходимо указать edgeBorders
        edgeBorder = 0;
        pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                rect.y + edgeBorder);
        pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                (rect.y + rect.height - edgeBorder) + 500);

        try {
            new TouchAction<>(driver)
                    .press(pointOptionStart)
                    // добавляем ожидание окончания нажатия, перемещения указателя и снова ожидание
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

    /**
     * Скролл экрана вниз от стартовой точки в виде элемента до конечной в указанном направлении.
     * Точка окончания указывается выше стартовой.
     * @param elem локатор элемента
     * @param dir направление для свайпа
     * Доступные варианты: UP (от низа к верху), DOWN (от верха к низу), LEFT (справа налево), RIGHT (слева направо).
     * @see PageBase#swipeElementIOS(MobileElement, Direction)
     * @see PageBase#swipeToRefreshAndroid(By)
     */
    @Step("Свайп элемента '{elem}' в направлении {dir}")
    public void swipeElementAndroid(By elem, Direction dir) {
        MobileElement el = driver.findElement(elem);
        logger.info("swipeElementAndroid(): dir: '" + dir + "'");

        // Стандартное ожидание анимации для Android: 300 мс
        // Инициирование длительности анимации и нажатия
        final int ANIMATION_TIME = 200; // ms

        final int PRESS_TIME = 200; // ms

        int edgeBorder;
        PointOption<?> pointOptionStart, pointOptionEnd;

        // Инициирование переменных экрана
        Rectangle rect = el.getRect();

        edgeBorder = 0;

        switch (dir) {
            case DOWN: // от верха к низу
                int x = rect.x + rect.width / 2;
                int y = rect.y + edgeBorder;
                int x2 = rect.x + rect.width / 2;
                int y2 = (rect.y + rect.height - edgeBorder) + 300;

                logger.info("x_start = " + x + ", y_start = " + y + ", x_end = " + x2 + ", y_end = " + y2);

                pointOptionStart = PointOption.point(x, y);
                pointOptionEnd = PointOption.point(x2, y2);
                break;
            case UP: // от низа к верху
                pointOptionStart = PointOption.point(rect.x + rect.width / 2,
                        rect.y + rect.height - edgeBorder);
                pointOptionEnd = PointOption.point(rect.x + rect.width / 2,
                        rect.y + edgeBorder);
                break;
            case LEFT: // справа налево
                pointOptionStart = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                break;
            case RIGHT: // слева направо
                pointOptionStart = PointOption.point(rect.x + edgeBorder,
                        rect.y + rect.height / 2);
                pointOptionEnd = PointOption.point(rect.x + rect.width - edgeBorder,
                        rect.y + rect.height / 2);
                break;
            default:
                logger.error("Configure invalid direction. Stop executing...");
                throw new IllegalArgumentException("swipeElementAndroid(): direction: '" + dir + "' NOT supported");
        }

        // Выполнение свайпа с помощью TouchAction
        try {
            new TouchAction<>(driver)
                    .press(pointOptionStart)
                    // ожидание выполнение свайпа
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
            //игнорирование
        }
    }
    /**
     * Скролл экрана вниз от стартовой точки до конечной в течении указанного времени.
     * Точка окончания указывается выше стартовой.
     * @param element локатор элемента
     * @param duration время зажатия элемента
     * @see PageBase#click(By)
     */
    @Step ("Долгое нажатие на элемент {element} в течении {duration} миллисекунд")
    public void longPress(By element, int duration) throws InterruptedException {

        final int ANIMATION_TIME = 200;
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder;

        Thread.sleep(2000);
        MobileElement el = driver.findElement(element);
        el.click();
        Rectangle rect = el.getRect();
        edgeBorder = 0;
        pointOptionStart = PointOption.point(rect.x,
                rect.y);
        pointOptionEnd = PointOption.point(rect.x,
                rect.y+ 100); // Опциональная точка для перемещения зажатого объекта

        try {
            new TouchAction<>(driver)
                    .press(pointOptionStart)
                    // Ожидание нажатия
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
                    .moveTo(pointOptionEnd)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(duration)))
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

    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT;
    }

    /**
     * Сравнение чисел, отправка сообщения в случае ошибки.
     * @param actual действительное число
     * @param expected ожидаемое число
     * @param message текст в случае ошибки
     * @see PageBase#textsComparsion(String, String, String)
     */
    @Step("Ожидание равенства числа '{a}' и числа '{b}'")
    public void numbersComparsion(int actual, int expected, String message) {
        Assert.assertEquals (actual, expected, message);
    }

    /**
     * Сравнение двух строк, отправка сообщения в случае ошибки.
     * @param actual действительный текст
     * @param expected ожидаемый текст
     * @param message текст в случае ошибки
     * @see PageBase#numbersComparsion(int, int, String)
     */
    @Step("Ожидание равенства действительного текста '{actual}' и ожидаемого текста '{expected}'")
    public void textsComparsion(String actual, String expected, String message) {
        Assert.assertEquals(actual, expected, message);
    }
}
