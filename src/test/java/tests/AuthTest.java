package tests;


import base.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import steps.AuthSteps;


@Epic("Android tests")
public class AuthTest extends TestBase {
    AuthSteps authSteps;

    @Story("Авторизация с валидными данными")
    @Test(groups = {"Auth screen"})
    public void checkInputLogin() {
        authSteps = new AuthSteps(driver);
        authSteps.login();
    }
}
