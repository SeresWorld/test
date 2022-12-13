import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class AuthListPage extends PageBase {
    public AuthListPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }

    private final By sign_button = By.id("kz.bcc.starbanking.stage:id/fragment_welcome_login");
    private final By input_login = By.id("kz.bcc.starbanking.stage:id/view_anketa_edit_input");
    private final By input_pass = By.xpath("//android.widget.EditText[@text='Введите пароль']");
    private final By button_login = By.id("kz.bcc.starbanking.stage:id/fragment_login_enter");
    private final By button_permission = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");
    private final By codeText = By.id("kz.bcc.starbanking.stage:id/passcode_fragment_subtitle");
    private final By codeFirst = By.xpath("//android.widget.LinearLayout[@bounds='[94,1035][324,1332]']");
    private final By codeFirstRepeat = By.xpath("//android.widget.LinearLayout[@bounds='[94,1035][324,1332]']");
    private final By android_message = By.id("android:id/message");
    private final By paidButton = By.xpath("//android.view.ViewGroup[@bounds='[521,1833][982,1965]']");
    private final By transitBetweenButton = By.xpath("//android.view.View[@text='Перевести']");
    private final By transitBetweenToBill = By.xpath("//android.widget.TextView[@text='Выберите']");
    private final By bannerInvest = By.id("kz.bcc.starbanking.stage:id/banner_invest");
    private final By currenttengeBill = By.xpath("//android.view.View[1]/android.view.View[2]");
    private final By topUpButton = By.xpath("//android.view.View[@text='Пополнить']");
    private final By fromBill = By.xpath("//android.widget.TextView[@text='Выберите']");
    private final By firstBill = By.xpath("//android.widget.RelativeLayout[1][@clickable='true']");
    private final By sumInsert = By.id("kz.bcc.starbanking.stage:id/exchange_view_amount");
    private final By transferButton = By.id("kz.bcc.starbanking.stage:id/transfer");
    private final By billBlock = By.xpath("//android.view.View[@text='Мой портфель']");
    private final By investInstructionSkipButton = By.xpath("//android.view.View[@text='Пропустить']");
    private final By investConfirmationTransaction = By.id("kz.bcc.starbanking.stage:id/bottom_sheet_next_button");
    private final By closeResultButton = By.id("kz.bcc.starbanking.stage:id/result_view_close");
    private final By currentBillFrom = By.xpath("//android.widget.TextView[@resource-id='kz.bcc.starbanking.stage:id/include_account_picker_name']");
    private final By statusResultTransaction = By.xpath("//android.widget.TextView[@bounds='[473,757][996,816]']");
    private final By questionOpenButton = By.xpath("//android.view.View[3]/android.widget.Button");
    private final By questionBanner = By.xpath("//android.widget.ImageView[@bounds='[0,247][1080,1258]']");
    private final By errorSumBiggerThanBill = By.xpath("//android.widget.TextView[@text='Сумма перевода превышает остаток на счете']");
    private final By investToggleCurrencyDollar = By.xpath("//android.view.View[@text='$']");
    private final By investToggleCurrencyTenge = By.xpath("//android.view.View[@text='₸']");
    private final By myBriefcaseTenge = By.xpath("//android.view.View[@bounds='[790,1037][881,1169]']");
    private final By myBriefcaseDollar = By.xpath("//android.view.View[@bounds='[881,1037][1013,1169]']");


    private By sign_button_ios = By.name("Войти");
    private By input_login_ios = By.xpath
            ("//XCUIElementTypeApplication[@name='BCC.KZ']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    private By input_pass_ios = By.cssSelector("XCUIElementTypeSecureTextField[value='Введите пароль']");
    private By button_permission_ios = By.name("Однократно");

    @Step ("Клик по кнопке Войти на экране сплеша")
    public void sign_main_button_click() {
        MobileElement button = (MobileElement) driver.findElement(sign_button);
        click(button);
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
        MobileElement obj = (MobileElement) driver.findElement(bannerInvest);
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
        List<MobileElement> currentBill = driver.findElements(currenttengeBill);
        for (MobileElement el:currentBill) {
            String textEl = el.getText();
            if (textEl.contains("₸")) {
                int bill = Integer.parseInt(textEl.replaceAll("[^-0-9,.]+", ""));
                return bill;
            }
        }
        System.out.println("Не удалось прочитать сумму");
        return 0;
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

    @Step ("Установка вторго счета в поле 'Куда' в разделе переводов")
    public void setToBill() {
        MobileElement toBillButton = (MobileElement) driver.findElement(transitBetweenToBill);
        click(toBillButton);
        MobileElement firstBillButton = (MobileElement) driver.findElement(firstBill);
        click(firstBillButton);
    }

    @Step ("Установка суммы перевода '1000'")
    public void setSumInsert(double sum) {
        MobileElement sumInsertField = (MobileElement) driver.findElement(sumInsert);
        click(sumInsertField);
        sendText(sumInsertField, String.valueOf(sum));
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

    @Step ("Проверка на минимальную сумму текущего счета")
    public void checkCurrentInvestBillIsNotEmpty () {
        List<MobileElement> info = driver.findElements(currentBillFrom);
        for (MobileElement el : info) {
            String elText = el.getText();
            if (elText.contains("Текущий счет для инвестиционной деятельности")) {
                String currentMoney = elText.replaceAll("[^-0-9.]+", "");
                Assert.assertTrue("Сумма текущего счета меньше 1001: " + Integer.parseInt(currentMoney), Integer.parseInt(currentMoney) >= 1001);
            }
        }
    }

    @Step ("Проверка на минимальную сумму текущего счета")
    public void checkCurrentPersonalBillIsNotEmpty () {
        List<MobileElement> info = driver.findElements(currentBillFrom);
        for (MobileElement el : info) {
            String elText = el.getText();
            if (elText.contains("Visa")) {
                String currentMoney = elText.replaceAll("[^-0-9.]+", "");
                Assert.assertTrue("Сумма текущего счета меньше 1001: " + Integer.parseInt(currentMoney), Integer.parseInt(currentMoney) >= 1001);
            }
        }
    }

    @Step
    public Double getCurrentPersonalBill() {
        List<MobileElement> info = driver.findElements(currentBillFrom);
        for (MobileElement el : info) {
            String elText = el.getText();
            if (elText.contains("Visa")) {
                String currentMoney = elText.replaceAll("[^-0-9,]+", "");
                String currentMoney2 = currentMoney.replace(",", ".");
                double currentMoneyInt = Double.parseDouble(currentMoney2);
                return currentMoneyInt;
            }
        }
        return null;
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

    @Step
    public void questionButtonClick() {
        MobileElement obj = (MobileElement) driver.findElement(questionOpenButton);
        click(obj);
    }

    @Step
    public void checkErrorSumBiggerThanBill() {
        MobileElement obj = (MobileElement) driver.findElement(errorSumBiggerThanBill);
        waitForVisability(obj);
    }
    @Step
    public void longPress() throws InterruptedException {

        final int ANIMATION_TIME = 200;
        PointOption pointOptionStart, pointOptionEnd;
        int edgeBorder;
        final int PRESS_TIME = 2000;
        Thread.sleep(2000);
        MobileElement el = (MobileElement) driver.findElement(By.xpath(""));
        el.click();
        Rectangle rect = el.getRect();
        edgeBorder = 0;
        pointOptionStart = PointOption.point(rect.x,
                rect.y);
        pointOptionEnd = PointOption.point(rect.x,
                rect.y+ 100);

        try {
            new TouchAction(driver)
                    .press(pointOptionStart)
                    // a bit more reliable when we add small wait
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .moveTo(pointOptionEnd)
                    .waitAction(WaitOptions.waitOptions(Duration.ofMillis(PRESS_TIME)))
                    .release().perform();
        } catch (Exception e) {
            System.err.println("swipeElement(): TouchAction FAILED\n" + e.getMessage());
            return;
        }
        try {
            Thread.sleep(ANIMATION_TIME);
        } catch (InterruptedException e) {
            // игнорирование
        }
    }
    @Step
    public void questionCloseButtonClick() {
        MobileElement el = (MobileElement) driver.findElement(questionBanner);
        swipeElementAndroid(el, Direction.DOWN);
    }

    @Step
    public void investSwitchToggleDollar() throws InterruptedException {
        MobileElement obj = (MobileElement) driver.findElement(investToggleCurrencyDollar);
        click(obj);
        Thread.sleep(1000);
    }

    @Step
    public void investSwitchToggleTenge() {
        MobileElement obj = (MobileElement) driver.findElement(investToggleCurrencyTenge);
        click(obj);
    }

    @Step
    public void checkInvestCurrency(String currency) {
        switch (currency) {
            case "dollar":
                MobileElement obj = (MobileElement) driver.findElement(myBriefcaseDollar);
                String objText = obj.getText();
                Assert.assertEquals("Currency is not in " + currency, objText.contains("$"), true);
                break;
            case "tenge":
                MobileElement obj1 = (MobileElement) driver.findElement(myBriefcaseTenge);
                String objText1 = obj1.getText();
                Assert.assertEquals("Currency is not in " + currency, objText1.contains("₸"), true);
                break;
            default:
                Assert.assertEquals("Currency is unknown!", 0 , 1);
        }
    }

}
