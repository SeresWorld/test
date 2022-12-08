
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Flaky;
import io.qameta.allure.Story;
import org.json.simple.parser.ParseException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.Assert;
import org.testng.annotations.*;
import utils.JsonReader;

import java.io.IOException;
import java.net.MalformedURLException;



@Epic("Android tests")
public class todoAndroid extends TestBase {

    AuthListPage authListPage;


    @Story("Авторизация с валидными данными")
    @Test (groups = {"Auth screen"})
    public void auth_valid() throws MalformedURLException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);

        authListPage.sign_main_button_click();
        authListPage.setInput_login("7756655544");
        authListPage.setInput_pass("orapas123");

        authListPage.sign_complete_button_click();
        authListPage.try_button_permission_click();

    }
    @Story("Авторизация с невалидными данными")
    @Test (groups = {"Auth screen"})
    public void auth_invalid() throws MalformedURLException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);
        authListPage.sign_main_button_click();
        authListPage.setInput_login("0123456789");
        authListPage.setInput_pass("123");
        authListPage.sign_complete_button_click();
        authListPage.try_button_permission_click();
        authListPage.check_text_android_message("Не верный логин или пароль");
    }

    @Story("Проверка некликабельности кнопки авторизации при незаполненном логине")
    @Test (groups = {"Auth screen"})
    public void auth_not_filled_login() throws MalformedURLException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);
        authListPage.sign_main_button_click();
        authListPage.setInput_pass("123");
        authListPage.check_active_sign_button_is_not_clickable();
    }

    @Story("Проверка некликабельности кнопки авторизации при незаполненном пароле")
    @Test (groups = {"Auth screen"})
    public void auth_not_filled_pass() throws MalformedURLException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);
        authListPage.sign_main_button_click();
        authListPage.setInput_login("0123456789");
        authListPage.check_active_sign_button_is_not_clickable();
    }

    @Story("Проверка горизонтального свайпа")
    @Test (groups = {"Main page"})
    public void horizontal_swipe() throws MalformedURLException, InterruptedException {
        auth_complete();
        authListPage = new AuthListPage(driver);
        authListPage.main_screen_swipe_horizontal_list();
        Thread.sleep(10000);
    }

    @Story("Проверка работы перевода со личного счета на инвестиционный")
    @Flaky
    @Test (groups = {"Main page"})
    public void transitionFromPersonalToInvest() throws MalformedURLException, InterruptedException {
        auth_complete();
        authListPage = new AuthListPage(driver);
        authListPage.investbannerClick();
        authListPage.waitforloadInvest();
        authListPage.skipInstruction();
        authListPage.swipeScreenDown();
        int current_1 = authListPage.getTextFromCurrentBill();
        authListPage.topUpButtonClick();
        authListPage.setFromBill();
        authListPage.checkCurrentBillIsNotEmpty();
        authListPage.setSumInsert();
        authListPage.transferButtonClick();
        authListPage.investConfirmTrnasaction();
        authListPage.checkResultStatusTransition();
        authListPage.closeResultTransition();
        authListPage.swipeToRefreshBill();
        int current_2 = authListPage.getTextFromCurrentBill();
        authListPage.comparsionBills(current_2, current_1 + 1000);
    }

    @Story("Проверка работы перевода с инвестиционного счета на личный")
    @Flaky
    @Test (groups = {"Main page"})
    public void transitionFromInsvestToPersonal() throws MalformedURLException, InterruptedException {
        auth_complete();
        authListPage = new AuthListPage(driver);
        authListPage.investbannerClick();
        authListPage.waitforloadInvest();
        authListPage.skipInstruction();
        authListPage.swipeScreenDown();
        int current_1 = authListPage.getTextFromCurrentBill();
        authListPage.transitBetweenButtonClick();
        authListPage.setToBill();
        authListPage.checkCurrentBillIsNotEmpty();
        authListPage.setSumInsert();
        authListPage.transferButtonClick();
        authListPage.investConfirmTrnasaction();
        authListPage.checkResultStatusTransition();
        authListPage.closeResultTransition();
        authListPage.swipeToRefreshBill();
        int current_2 = authListPage.getTextFromCurrentBill();
        authListPage.comparsionBills(current_2, current_1 - 1000);
    }

    @Test
    public void closeModalAbout() throws MalformedURLException, InterruptedException {
        auth_complete();
        authListPage = new AuthListPage(driver);
        authListPage.investbannerClick();
        authListPage.waitforloadInvest();
        authListPage.skipInstruction();
        authListPage.questionButtonClick();
        authListPage.questionCloseButtonClick();
        authListPage.transitBetweenButtonClick();

    }

}
