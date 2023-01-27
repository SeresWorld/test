package pages.login;

import base.PageBase;
import base.TestBase;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс AuthListPage используется для хранения всех путей для элементов на странице авторизации и действий над ними.
 * Описание каждого действия завернуто в аннотации @Step.
 */

public class AuthListPage extends PageBase {

    public AuthListPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private static final Logger logger = LogManager.getLogger(TestBase.class);
    @FindAll(
            {
                    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Войти\"]"),
                    @FindBy(id = "kz.bcc.starbanking.stage:id/fragment_welcome_login")
            }
    ) WebElement signButton;

    @FindAll(
            {
                    @FindBy(xpath = "//XCUIElementTypeTextField"),
                    @FindBy(id = "kz.bcc.starbanking.stage:id/view_anketa_edit_input")
            }
    ) protected WebElement inputLogin;

    @FindAll(
            {
                    @FindBy(xpath = "//XCUIElementTypeSecureTextField"),
                    @FindBy(xpath = "//android.widget.EditText[@text='Введите пароль']")
            }
    ) protected WebElement inputPass;

    @FindAll(
            {
                    @FindBy(xpath = "//XCUIElementTypeButton[@name=\"Войти\"]"),
                    @FindBy(id = "kz.bcc.starbanking.stage:id/fragment_login_enter")
            }
    ) protected WebElement buttonLogin;

    @FindAll(
    {
        @FindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    }
    ) protected WebElement buttonPermission;

    @FindBy(id = "kz.bcc.starbanking.stage:id/avi")
    protected WebElement loaderAccountList;

    @FindBy(xpath = "//android.widget.TextView[@text=\"1\"]")
    protected WebElement codeFirst;

    @FindBy(className = "android.widget.HorizontalScrollView")
    protected WebElement horizontalArea;

    @FindBy(id = "kz.bcc.starbanking.stage:id/negative")
    protected WebElement signInByFingerTip;

    public AuthListPage setButtonsCode () {
        waitForClickable(codeFirst, 30);
        try {
            while (codeFirst.isDisplayed() && codeFirst.isEnabled()) {codeFirst.click();}
        } catch (NoSuchElementException ignored) {}
        return this;
    }

    public AuthListPage signMainButtonClick() {
        click(signButton);

        return this;
    }

    public AuthListPage setInputPass(String pass) {
        sendText(inputPass, pass);

        return this;
    }

    public AuthListPage signCompleteButtonClick() {
        click(buttonLogin);

        return this;
    }

    public AuthListPage setInputLogin(String login) {
        waitForClickable(inputLogin, 10);
        sendText(inputLogin, login);

        return this;
    }

    public AuthListPage tryButtonPermissionClick() {
        try {
            click(buttonPermission);
        } catch (Exception exception) {
            logger.info("Permission has not found");
        }

        return this;
    }

    public AuthListPage waitForLoadAccountList() {
        waitForNotVisability(loaderAccountList, 30);

        return this;
    }

    public AuthListPage waitForLoader() {
        waitForVisability(loaderAccountList);

        return this;
    }

    public AuthListPage authByFingerTip() {
        click(signInByFingerTip);

        return this;
    }

    public AuthListPage horizontalScroll() {
        horizontalScroll(horizontalArea);

        return this;
    }


}
