package steps;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import pages.login.AuthListPage;
import utils.ConfigReader;
import utils.JsonReader;

import java.io.IOException;

public class AuthSteps {

    static AppiumDriver driver;

    public AuthSteps(AppiumDriver appiumDriver) {
        driver = appiumDriver;
    }
    @Step("Валидная авторизация")
    public void login(String login, String password) {
        AuthListPage authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick()
                .setInputLogin(login)
                .setInputPass(password)
                .signCompleteButtonClick()
                .tryButtonPermissionClick()
                .setButtonsCode();
    }
    @Step("Невалидный логин")
    public void inputInvalidLogin(String login) {
        AuthListPage authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick()
                .setInputLogin(login)
                .assertLoginIsEmpty();
    }


}
