package tests;


import base.TestBase;
import data.User;
import data.UserList;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import steps.AuthSteps;
import utils.ConfigReader;
import utils.JsonReader;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;


@Epic("Android tests")
public class AuthTest extends TestBase {
    AuthSteps authSteps;

    @DataProvider(name = "users")
    public Object[][] userDetails() throws InvalidAlgorithmParameterException, NoSuchPaddingException, IllegalBlockSizeException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException {
        UserList userList = new UserList();
        return userList.getUserList();

    }

    @Story("Авторизация с валидными данными")
    @Test(groups = {"Auth screen"}, dataProvider = "users")
    public void checkInputLogin(User user) {
        authSteps = new AuthSteps(driver);
        authSteps.login(user.getLogin(), user.getPassword());
    }

    /*@Test(groups = {"Auth screen"}, dataProvider = "users")
    public void checkInvalidLogin(String login, String password) {
        authSteps = new AuthSteps(driver);
        authSteps.inputInvalidLogin(login);
    }*/


}
