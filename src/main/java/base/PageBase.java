package base;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.sql.Time;
import java.time.Duration;

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
    public String getText(WebElement element) {
        waitForVisability(element);

        return element.getText();
    }

    @Step
    public void sendText(WebElement element, String text) {
        waitForVisability(element);
        element.sendKeys(text);
    }

    
}