package base;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;
import java.util.Arrays;

/**
 * Класс PageBase используется для хранения стандартных действий (свайпы, клики, получение аттрибута элемента и т.д.).
 * Методы класса PageBase используются в шагах для каждой платформы каждого теста (toDoAndroid, toDoIOS).
 * Описание каждого стандартного действия завернуто в аннотации <b>@Step</b>. Стандартное ожидание - 10 секунд,
 * которое содержится в переменной <b>WAIT</b>.
 */

public class PageBase extends TestBase {
    /**
     * Клик по указанному элементу.
     *
     * @param element элемент WebElement
     */

    private static final Logger logger = LogManager.getLogger(TestBase.class);

    Duration WAIT = Duration.ofSeconds(10);

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

    @Step
    public void waitForClickable(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            logger.error("Not found element: " + element);
        }
    }

    @Step
    public void waitForVisability(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, WAIT);
            wait.until(ExpectedConditions.visibilityOf(element));
        } catch (TimeoutException ex) {
            logger.error("Not found element: " + element);
        }
    }

    @Step
    public void explicitWaitToClickable(WebElement element, long seconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
            wait.until(ExpectedConditions.elementToBeClickable(element));
        } catch (TimeoutException ex) {
            logger.error("Not found element: " + element);
        }
    }

    @Step
    public String getText(WebElement element) {
        waitForVisability(element);

        return element.getText();
    }

    @Step
    public void sendText(WebElement element, String text) {
        waitForVisability(element);
        element.sendKeys(text);
    }

    /**
     * Вертикальный скролл экрана.
     * @param area область типа WebElement, которую необходимо скроллить.
     *             Область должна иметь признак scrollable = true.
     */
    @Step
    public void verticalScroll(WebElement area) {
        int centerX = area.getRect().x + (area.getSize().width/2);

        double startY = area.getRect().y + (area.getSize().height * 0.9);

        double endY = area.getRect().y + (area.getSize().height * 0.1);

        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");

        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),centerX,(int)startY));
        //Finger comes down into contact with screen
        swipe.addAction(finger.createPointerDown(0));
        //Finger moves to end position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),centerX, (int)endY));
        //Get up Finger from Srceen
        swipe.addAction(finger.createPointerUp(0));

        //Perform the actions
        driver.perform(Arrays.asList(swipe));

    }

    public void horizontalScroll(WebElement area){
        //Creating Horizontal Scroll Event
        //Scrollable Element

        int centerY = area.getRect().y + (area.getSize().height/2);

        double startX = area.getRect().x + (area.getSize().width * 0.9);

        double endX = area.getRect().x + (area.getSize().width * 0.1);
        //Type of Pointer Input
        PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH,"finger");
        //Creating Sequence object to add actions
        Sequence swipe = new Sequence(finger,1);
        //Move finger into starting position
        swipe.addAction(finger.createPointerMove(Duration.ofSeconds(0),PointerInput.Origin.viewport(),(int)startX,centerY));
        //Finger comes down into contact with screen
        swipe.addAction(finger.createPointerDown(0));
        //Finger moves to end position
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(700),
                PointerInput.Origin.viewport(),(int)endX,centerY));
        //Get up Finger from Srceen
        swipe.addAction(finger.createPointerUp(0));

        //Perform the actions
        driver.perform(Arrays.asList(swipe));

        try{
            Thread.sleep(3000);
        }catch (Exception ex){
            ex.printStackTrace();
        }

    }



}