import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.management.MemoryPoolMXBean;
import java.security.PublicKey;
import java.util.List;

public class AuthListPage extends PageBase {
    public AuthListPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    private By sign_button = By.id("kz.bcc.starbanking.stage:id/fragment_welcome_login");
    private By input_login = By.id("kz.bcc.starbanking.stage:id/view_anketa_edit_input");
    private By input_pass = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.cardview.widget.CardView[2]/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.EditText");
    private By button_login = By.id("kz.bcc.starbanking.stage:id/fragment_login_enter");
    private By button_permission = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private By codeText = By.id("kz.bcc.starbanking.stage:id/passcode_fragment_subtitle");
    private By codeFirst = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout");
    private By codeFirstRepeat = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.LinearLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.widget.FrameLayout/android.widget.LinearLayout");
    private By android_message = By.id("android:id/message");
    private By accountObj = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.LinearLayout[1]/android.widget.LinearLayout");
    private By paidButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout[1]/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/androidx.recyclerview.widget.RecyclerView/android.widget.HorizontalScrollView/android.view.ViewGroup/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.view.ViewGroup");
    private By transitBetweenButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View[4]");
    private By transitBetweenToBill = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout");
    private By bannerInvest = By.id("kz.bcc.starbanking.stage:id/banner_invest");
    private By mybriefcaseButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[4]/android.view.View[3]");
    private By currentengeBill = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View[1]/android.view.View[1]/android.view.View[2]");
    private By topUpButton = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]/android.view.View[3]");
    private By fromBill = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout");
    private By firstBill = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.RelativeLayout[1]/android.widget.TextView[1]");
    private By sumInsert = By.id("kz.bcc.starbanking.stage:id/exchange_view_amount");
    private By transferButton = By.id("kz.bcc.starbanking.stage:id/transfer");
    private By billBlock = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.FrameLayout/androidx.compose.ui.platform.ComposeView/android.view.View/android.view.View/android.view.View[4]/android.view.View[1]");
    private By investInstructionSkipButton = By.xpath("/hierarchy/android.view.ViewGroup/android.view.View/android.view.View/android.view.View/android.view.View[3]/android.widget.Button");
    private By investConfirmationTransaction = By.id("kz.bcc.starbanking.stage:id/bottom_sheet_next_button");
    private By closeResultButton = By.id("kz.bcc.starbanking.stage:id/result_view_close");
    private By currentBillFrom = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.widget.ScrollView/android.widget.LinearLayout/android.widget.FrameLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView[2]");
    private By statusResultTransaction = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup[2]/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.ScrollView/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.LinearLayout[1]/android.widget.LinearLayout[1]/android.widget.LinearLayout/android.widget.RelativeLayout/android.widget.TextView");

    private By sign_button_ios = By.name("Войти");
    private By input_login_ios = By.xpath
            ("//XCUIElementTypeApplication[@name='BCC.KZ']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    private By input_pass_ios = By.cssSelector("XCUIElementTypeSecureTextField[value='Введите пароль']");
    private By button_permission_ios = By.name("Однократно");
    private String calendarButton = "calendar";
    private By calendar = By.xpath("//XCUIElementTypeApplication[@name=\"DailyCheck\"]/XCUIElementTypeWindow/XCUIElementTypeOther[2]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther");
    @Step ("Клик по кнопке Войти на экране сплеша")
    public void sign_main_button_click() {
        click((MobileElement) driver.findElement(sign_button));
    }

    public void sign_main_button_click_ios() {click((MobileElement) driver.findElement(sign_button_ios));}
    @Step ("Ввод телефона {login} в поле телефона")
    public void setInput_login(String login) {
        MobileElement input = (MobileElement) driver.findElement(input_login);
        sendText(input, login);
    }
    @Step
    public void setInput_login_ios(String login) {
        MobileElement input = (MobileElement) driver.findElement(input_login_ios);
        sendText(input, login);
    }
    @Step ("Клик по кнопке 'Войти' на экране авторизации")
    public void sign_complete_button_click() {click((MobileElement) driver.findElement(button_login));}
    @Step
    public void sign_complete_button_click_ios() {click((MobileElement) driver.findElement(sign_button_ios));}

    @Step ("Ввод пароля '{pass}' в поле пароля")
    public void setInput_pass(String pass) {
        MobileElement input = (MobileElement) driver.findElement(input_pass);
        sendText(input, pass);
    }
    @Step
    public void setInput_pass_ios(String pass) {
        MobileElement input = (MobileElement) driver.findElement(input_pass_ios);
        sendText(input, pass);
    }
    @Step ("Попытка клика по кнопке разрешения на получение геолокации")
    public void try_button_permission_click() {
        try {
            click((MobileElement) driver.findElement(button_permission));
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Permission has not found");
        }

    }
    @Step
    public void try_button_permission_click_ios() {
        MobileElement btn = (MobileElement) driver.findElement(button_permission_ios);
        waitForVisability(btn);
        click(btn);
    }

    @Step
    public void open_calendar() {
        MobileElement calendar = (MobileElement) driver.findElementByAccessibilityId(calendarButton);
        waitForVisability(calendar);
        click(calendar);
    }
    @Step
    public void swipe_calendar() {
        MobileElement obj = (MobileElement) driver.findElement(calendar);
        waitForVisability(obj);
        swipeElementIOS(obj, Direction.LEFT);
    }

    @Step ("Проверка наличия текста '{text}' на модалке")
    public void check_text_android_message(String text) {
        containsmessageAssert((MobileElement) driver.findElement(android_message), text);
    }
    @Step ("Проверка неактивности кнопки Войти на экране авторизации")
    public void check_active_sign_button_is_not_clickable() {
        String button_login_atr = getAttribute((MobileElement) driver.findElement(button_login), "enabled");
        Assert.assertEquals("Object is clickable", "false", button_login_atr);

    }

    @Step ("Ввод четырехзначного кода")
    public void setButtonsCode () {
        MobileElement codeSubText = (MobileElement) driver.findElement(codeText);
        boolean is_visability = false;
        while (!is_visability) {
            try {
                waitForVisability(codeSubText);
                is_visability = true;
            } catch (Exception e) {
                System.out.println("Не прогрузилось");
            }
        }
        for (int i = 0; i < 4; i++) {
            MobileElement el = (MobileElement) driver.findElement(codeFirst);
            click(el);
        }
        for (int i = 0; i < 4; i++) {
            MobileElement el = (MobileElement) driver.findElement(codeFirstRepeat);
            click(el);
        }
    }

    @Step ("Ожидание загрузки счетов")
    public void waitforloadaccountList() {
        MobileElement obj = (MobileElement) driver.findElement(accountObj);
        waitForVisability(obj);
    }

    @Step
    public void main_screen_swipe_horizontal_list() {
        MobileElement paidBtn = (MobileElement) driver.findElement(paidButton);
        swipeElementAndroid(paidBtn, Direction.LEFT);
    }

    @Step ("Клик на баннер инвестиций")
    public void investbannerClick() {
        MobileElement investBtn = (MobileElement) driver.findElement(bannerInvest);
        click(investBtn);
    }
    @Step ("Ожидание прогрузки страницы инвестиций")
    public void waitforloadInvest() {
        boolean is_clicked = false;
        while (!is_clicked) {
            try {
                WebDriverWait wait = new WebDriverWait(driver, 5);
                wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(investInstructionSkipButton)));
                is_clicked = true;
            } catch (Exception e) {
                System.out.println("Не получилось нажать");
            }
        }
    }

    @Step ("Получение текущего количества средств на счету в тенге")
    public int getTextFromCurrentBill() {
        MobileElement currentBill = (MobileElement) driver.findElement(currentengeBill);
        String text = getText(currentBill);
        int bill = Integer.parseInt(text.replaceAll("[^0-9.]", ""));
        return bill;
    }

    @Step ("Нажатие на кнопку 'Перевести' в разделе инвестиций")
    public void transitBetweenButtonClick() {
        MobileElement transitBetweenBtn = (MobileElement) driver.findElement(transitBetweenButton);
        click(transitBetweenBtn);
    }

    @Step ("Нажатие кнопки 'Пополнить'")
    public void topUpButtonClick() {
        MobileElement topUpBtn = (MobileElement) driver.findElement(topUpButton);
        click(topUpBtn);
    }

    @Step ("Установка первого счета в поле 'Откуда'")
    public void setFromBill() {
        MobileElement fromBillButton = (MobileElement) driver.findElement(fromBill);
        click(fromBillButton);

        MobileElement firstBillButton = (MobileElement) driver.findElement(firstBill);
        click(firstBillButton);
    }

    @Step ("Установка вторго счета в поле 'Куда'в разделе переводов")
    public void setToBill() {
        MobileElement toBillButton = (MobileElement) driver.findElement(transitBetweenToBill);
        click(toBillButton);
        MobileElement firstBillButton = (MobileElement) driver.findElement(firstBill);
        click(firstBillButton);
    }

    @Step ("Установка суммы перевода '1000'")
    public void setSumInsert() {
        MobileElement sumInsertField = (MobileElement) driver.findElement(sumInsert);
        click(sumInsertField);
        sendText(sumInsertField, "1000");
    }

    @Step ("Нажатие кнопки 'Перевести'")
    public void transferButtonClick() {
        MobileElement transferBtn = (MobileElement) driver.findElement(transferButton);
        click(transferBtn);
    }

    @Step ("Свайп экрана вниз")
    public void swipeScreenDown() {
        MobileElement billsBlock = (MobileElement) driver.findElement(billBlock);
        swipeElementAndroid(billsBlock, Direction.DOWN);
    }

    @Step ("Обновление счета")
    public void swipeToRefreshBill() throws InterruptedException {
        MobileElement billsBlock = (MobileElement) driver.findElement(billBlock);
        swipeToRefreshAndroid(billsBlock);
        Thread.sleep(5000);
    }

    @Step ("Закрытие модалки перевода")
    public void closeResultTransition() {
        MobileElement closeBtn = (MobileElement) driver.findElement(closeResultButton);
        click(closeBtn);
    }

    @Step ("Пропуск гайда")
    public void skipInstruction() {
        MobileElement skipButton = (MobileElement) driver.findElement(investInstructionSkipButton);
        click(skipButton);
    }

    @Step ("Подтверждение перевода")
    public void investConfirmTrnasaction() throws InterruptedException {
        MobileElement confBtn = (MobileElement) driver.findElement(investConfirmationTransaction);
        click(confBtn);
        Thread.sleep(5000);
    }

    @Step ("Проверка на минимальную сумму текушего счета")
    public void checkCurrentBillIsNotEmpty () {
        MobileElement info = (MobileElement) driver.findElement(currentBillFrom);
        String text = info.getText();
        String currentMoney = text.replaceAll("[^0-9.]", "");
        System.out.println(currentMoney);
        Assert.assertTrue("Сумма текущего счета меньше 1000: " + Integer.parseInt(currentMoney), Integer.parseInt(currentMoney) >= 1000);
    }

    @Step ("Сравнение текущей суммы счета с ожидаемой")
    public void comparsionBills (int actual, int expected) {
        numbersComparsion(actual, expected, "Число " + actual + " != " + expected);
    }

    @Step ("Проверка на успешность завершения транзакции")
    public void checkResultStatusTransition() {
        MobileElement text = (MobileElement) driver.findElement(statusResultTransaction);
        String status = text.getText();
        textsComparsion(status, "Перевод успешно принят", "Actual: " + status + "; Expected: " + "Перевод успешно принят");
    }
}
