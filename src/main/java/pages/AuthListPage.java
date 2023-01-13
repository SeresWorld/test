package pages;

import base.PageBase;
import base.TestBase;
import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindAll;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * Класс AuthListPage используется для хранения всех путей для элементов на странице авторизации и действий над ними.
 * Описание каждого действия завернуто в аннотации @Step.
 */

public class AuthListPage extends PageBase {
    private static final Logger logger = LogManager.getLogger(TestBase.class);
    @FindAll(
            {
                    @FindBy(xpath = "//android.widget.Button[@text=\"блаблабла\"]"),
                    @FindBy(id = "kz.bcc.starbanking.stage:id/fragment_welcome_login")
            }
    ) WebElement signButton;

    @FindAll(
            {
                    @FindBy(id = "kz.bcc.starbanking.stage:id/view_anketa_edit_input")
            }
    ) WebElement inputLogin;

    @FindAll(
            {
                    @FindBy(xpath = "//android.widget.EditText[@text='Введите пароль']")
            }
    ) WebElement inputPass;

    @FindAll(
            {
                    @FindBy(id = "kz.bcc.starbanking.stage:id/fragment_login_enter")
            }
    ) WebElement buttonLogin;

    @FindAll(
    {
        @FindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    }
    ) WebElement buttonPermission;

    public AuthListPage(AppiumDriver appiumDriver) {

        PageFactory.initElements(driver, this);
        driver = appiumDriver;
    }


    @Step ("Клик по кнопке Войти на экране сплеша")
    public void  signMainButtonClick() {
        click(signButton);
    }

    @Step ("Ввод пароля '{pass}' в поле пароля")
    public void setInputPass(String pass) {
        sendText(inputPass, pass);
    }

    @Step ("Клик по кнопке 'Войти' на экране авторизации")
    public void signCompleteButtonClick() {click(buttonLogin);}

    @Step ("Ввод телефона {login} в поле телефона")
    public void setInputLogin(String login) {
        waitForClickable(inputLogin, 10);
        sendText(inputLogin, login);
    }

    @Step ("Попытка клика по кнопке разрешения на получение геолокации")
    public void tryButtonPermissionClick() {
        try {
            click(buttonPermission);
        } catch (Exception exception) {
            logger.info("Permission has not found");
        }
    }

}
