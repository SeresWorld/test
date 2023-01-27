package tests;


import base.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.AuthSteps;
import utils.ConfigReader;
import utils.JsonReader;

import java.io.IOException;
import java.util.Date;


@Epic("Android tests")
public class AuthTest extends TestBase {
    AuthSteps authSteps;

    @DataProvider(name = "data")
    public Object[][] passData() throws IOException, ParseException {
        return JsonReader.getJSONData(ConfigReader.testDataPath, "Logins", 1);
    }

    @Story("Авторизация с валидными данными")
    @Test(groups = {"Auth screen"})
    public void checkInputLogin() {
        authSteps = new AuthSteps(driver);
        authSteps.login();
    }

    @Test(groups = {"Auth screen"}, dataProvider = "data")
    public void checkInvalidLogin(String login) {
        authSteps = new AuthSteps(driver);
        authSteps.inputInvalidLogin(login);
    }


}
