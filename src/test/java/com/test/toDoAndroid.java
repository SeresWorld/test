package com.test;


import base.TestBase;
import io.qameta.allure.Epic;
import io.qameta.allure.Story;
import org.testng.annotations.Test;
import pages.AuthListPage;



@Epic("Android tests")
public class toDoAndroid extends TestBase {
    AuthListPage authListPage;


    @Story("Авторизация с валидными данными")
    @Test(groups = {"Auth screen"})
    public void authValid() {
        authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick();
        authListPage.setInputLogin("");
        authListPage.setInputPass("");
    }
}
