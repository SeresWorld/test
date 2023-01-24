package com.test;


import base.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthListPage;



@Epic("Android tests")
public class authPage extends TestBase {
    AuthListPage authListPage;

    @Story("Авторизация с валидными данными")
    @Test(groups = {"Auth screen"})
    public void checkInputLogin() {
        authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick();
        authListPage.setInputLogin("7756655544");
    }

    @Story("Авторизация с валидными данными")
    @Test(groups = {"Auth screen"})
    public void checkInputPass() {
        authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick();
        authListPage.setInputPass("orapas123");
    }
}
