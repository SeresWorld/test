package com.test;


import base.PageBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Epic;
import io.qameta.allure.Flaky;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthListPage;
import base.TestBase;

import java.net.MalformedURLException;



@Epic("Android tests")
public class toDoAndroid extends TestBase {
    AuthListPage authListPage;

    @Story("Авторизация с валидными данными")
    @Test(groups = {"Auth screen"})
    public void authValid() {

        authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick();
        authListPage.setInputLogin("7756655544");
        authListPage.setinputPass("orapas123");
        authListPage.sign_complete_button_click();
        authListPage.try_buttonPermission_click();
    }

    @Story("Авторизация с невалидными данными")
    @Test(groups = {"Auth screen"})
    public void authInvalid() {

        authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick();
        authListPage.setInputLogin("0123456789");
        authListPage.setinputPass("123");
        authListPage.sign_complete_button_click();
        authListPage.try_buttonPermission_click();
        authListPage.check_text_androidMessage("Не верный логин или пароль");
    }
}
