package base;

import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.Point;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Collections;

/**
 * Класс PageBase используется для хранения стандартных действий (свайпы, клики, получение аттрибута элемента и т.д.).
 * Методы класса PageBase используются в шагах для каждой платформы каждого теста (toDoAndroid, toDoIOS).
 * Описание каждого стандартного действия завернуто в аннотации <b>@Step</b>. Стандартное ожидание - 10 секунд,
 * которое содержится в переменной <b>WAIT</b>.
 */

public class PageBase extends TestBase {

    private static final Logger logger = LogManager.getLogger(TestBase.class);
    // статическая переменная ожидания
    private final Duration WAIT = Duration.ofSeconds(10);

    /**
     * Простой тап по указанному элементу.
     * @param element элемент типа WebElement
     * @see PageBase#waitForClickable(WebElement)
     * @see PageBase#waitForClickable(WebElement, long) 
     */
    @Step("Клик по элементу '{element}'")
    public void click(WebElement element) {
        try {
            waitForClickable(element);
            element.click();
        } catch (TimeoutException ex) {
            logger.error("Can't click element: " + element);
            throw ex;
        }
    }

    /**
     * Ожидание кликабельности элемента в течение 10 секунд.
     * @param element элемент типа WebElement.
     */
    @Step ("Ожидание кликабельности элемента {element}")
    public void waitForClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            logger.error("Not found element: " + element);
        }
    }

    /**
     * Ожидание кликабельности элемента в течении указанного времени.
     * @param element элемент типа WebElement.
     * @param seconds время ожидания кликабельности в секундах.
     */

    @Step ("Ожидание кликабельности элемента {element} в течении {seconds} секунд")
    public void waitForClickable(WebElement element, long seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            logger.error("Not found element: " + element);
        }
    }

    /**
     * Ожидание видимости элемента в течение 10 секунд.
     * @param element элемент типа WebElement.
     */
    @Step ("Ожидание видимости элемента {element}")
    public void waitForVisability(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            logger.error("Not found element: " + element);
        }
    }

    @Step
    public void waitForNotVisability(WebElement element, long seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.invisibilityOf(element));
        } catch (TimeoutException ex) {
            logger.error("Not found element: " + element);
        }
    }

    /**
     * Ожидание видимости элемента течение указанного времени.
     * @param element элемент типа WebElement.
     * @param seconds время ожидания видимости в секундах.
     */
    @Step ("Ожидание видимости элемента {element}")
    public void waitForVisability(WebElement element, long seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            logger.error("Not found element: " + element);
        }
    }

    /**
     * Получение текста из передаваемого элемента.
     * @param element элемент типа WebElement. В передаваемом элементе должен быть аттрибут 'text'.
     * @return строку String.
     * @see PageBase#sendText(WebElement, String) 
     */
    @Step ("Получение текста элемента {element}")
    public String getText(WebElement element) {
        waitForVisability(element);

        return element.getText();
    }
    /**
     * Ввод текста в передаваемый элемент.
     * @param element элемент типа WebElement.
     * @param text строка для ввода в элемент.
     * @see PageBase#getText(WebElement) 
     */
    @Step ("Отправка текста '{text}' в элемент {element}")
    public void sendText(WebElement element, String text) {
        waitForVisability(element);
        element.sendKeys(text);
    }

    /**
     * Получение значения аттрибута элемента.
     * @param element элемент типа WebElement.
     * @param attribute название аттрибута, значение которого нужно получить.
     * @return строку String со значением аттрибута.
     */
    @Step ("Получение аттрибута '{attribute}' элемента {element}")
    public String getAttribute(WebElement element, String attribute) {
        waitForVisability(element);
        return element.getAttribute(attribute);
    }

    /**
     * Вертикальный скролл экрана.
     * @param area область типа WebElement, которую необходимо скролить.
     *             Область должна иметь признак scrollable = true.
     * @see PageBase#horizontalScroll(WebElement) 
     */
    @Step ("Вертикальный скролл элемента {element}")
    public void verticalScroll(WebElement area) {
        // создаем точки центра экрана по оси Y, центра экрана по оси X начала, центра экрана по оси X конца

        int centerX = area.getRect().x + (area.getSize().width/2);

        double startY = area.getRect().y + (area.getSize().height * 0.9);

        double endY = area.getRect().y + (area.getSize().height * 0.1);

        // создаем объект PointerInput в виде нажатия на экран

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        // создаем объект последовательности действий
        Sequence swipe = new Sequence(finger, 1);
        // наводим палец на объект
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
        // опускаем палец на экран
        swipe.addAction(finger.createPointerDown(0));
        // двигаем палец в течении 700 мс в конечную точку
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),centerX, (int)endY));
        // поднимаем палец с экрана
        swipe.addAction(finger.createPointerUp(0));

        // выполняем последовательность действий
        driver.perform(Collections.singletonList(swipe));

        try{
            Thread.sleep(3000);
        }catch (Exception ex){
            logger.error("Can't scroll element: " + area);
            ex.printStackTrace();
        }

    }
    /**
     * Горизонтальный скролл экрана.
     * @param area область типа WebElement, которую необходимо скролить.
     *             Область должна иметь признак scrollable = true.
     * @see PageBase#verticalScroll(WebElement) 
     */
    @Step ("Горизонтальный скролл элемента {element}")
    public void horizontalScroll(WebElement area){

        // создаем точки центра экрана по оси Y, центра экрана по оси X начала, центра экрана по оси X конца

        int centerY = area.getRect().y + (area.getSize().height/2);

        double startX = area.getRect().x + (area.getSize().width * 0.9);

        double endX = area.getRect().x + (area.getSize().width * 0.1);

        // создаем объект PointerInput в виде нажатия на экран

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        // создаем объект последовательности действий
        Sequence swipe = new Sequence(finger,1);
        // наводим палец на объект
        swipe.addAction(
                finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),(int)startX,centerY));
        // опускаем палец на экран
        swipe.addAction(finger.createPointerDown(0));
        // двигаем палец в течении 700 мс в конечную точку
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),(int)endX,centerY));
        // поднимаем палец с экрана
        swipe.addAction(finger.createPointerUp(0));

        // выполняем последовательность действий
        driver.perform(Collections.singletonList(swipe));

        try{
            Thread.sleep(3000);
        }catch (Exception ex){
            logger.error("Can't scroll element: " + area);
            ex.printStackTrace();
        }

    }

    /**
     * Долгое нажатие на элемент в течении указанного времени.
     * @param element элемент типа WebElement.
     * @param milliseconds время удержания пальца на элементе (в миллисекундах).
     */
    @Step ("Нажатие элемента {element} в течении {milliseconds} миллисекунд")
    public void longPressElement(WebElement element, int milliseconds) {
        // создаем объект локации элемента
        Point location = element.getLocation();

        // создаем объект PointerInput в виде нажатия на экран
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        // создаем объект последовательности действий
        Sequence longPress = new Sequence(finger,1);
        // наводим палец на объект
        longPress.addAction(
                finger.createPointerMove(
                        Duration.ofSeconds(0),
                        PointerInput.Origin.viewport(),
                        location.x, location.y));
        // нажимаем на наведенную точку
        longPress.addAction(
                finger.createPointerDown(
                        PointerInput.MouseButton.LEFT.asArg()));
        // держим палец на указанной точке в течении указанного времени
        longPress.addAction(
                finger.createPointerMove(
                        Duration.ofMillis(milliseconds),
                        PointerInput.Origin.viewport(),
                        location.x, location.y));
        // поднимаем палец с экрана
        longPress.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        // выполняем последовательность действий
        driver.perform(Collections.singletonList(longPress));

        try{
            Thread.sleep(3000);
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }
}