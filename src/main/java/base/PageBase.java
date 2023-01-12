package base;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

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

    @Step("Клик по элементу '{element}'")
    public void click(WebElement element) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.elementToBeClickable(element)).click();
        } catch (TimeoutException ex) {
            logger.error("Can't click element: " + element);
            throw ex;
        }
    }
}