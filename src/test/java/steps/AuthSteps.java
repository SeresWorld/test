package steps;

import io.appium.java_client.AppiumDriver;
import io.qameta.allure.Step;
import pages.login.AuthListPage;

public class AuthSteps {

    static AppiumDriver driver;

    public AuthSteps(AppiumDriver appiumDriver) {
        driver = appiumDriver;
    }
    @Step("Валидная авторизация")
    public void login() {
        AuthListPage authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick()
                .setInputLogin("7756655544")
                .setInputPass("orapas123")
                .signCompleteButtonClick()
                .tryButtonPermissionClick()
                .setButtonsCode();
    }
}
