package pages;

import base.PageBase;
import base.TestBase;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumElementLocatorFactory;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Step;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.*;

/**
 * Класс AuthListPage используется для хранения всех путей для элементов на странице авторизации и действий над ними.
 * Описание каждого действия завернуто в аннотации @Step.
 */

public class AuthListPage extends PageBase {
    private static final Logger logger = LogManager.getLogger(TestBase.class);
    @FindAll(
            {
                    @FindBy(xpath = "//android.widget.Button[@text=\"блаблабла\"]"),
                    @FindBy(id = "kz.bcc.starbanking.stage:id/fragment_welcome_login")
            }
    ) WebElement signButton;

    @FindAll(
            {
                    @FindBy(id = "kz.bcc.starbanking.stage:id/view_anketa_edit_input")
            }
    ) WebElement inputLogin;

    @FindAll(
            {
                    @FindBy(xpath = "//android.widget.EditText[@text='Введите пароль']")
            }
    ) WebElement inputPass;

    @FindAll(
            {
                    @FindBy(id = "kz.bcc.starbanking.stage:id/fragment_login_enter")
            }
    ) WebElement buttonLogin;

    @FindAll(
    {
        @FindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    }
    ) WebElement buttonPermission;

    private final By codeText = By.id("kz.bcc.starbanking.stage:id/passcode_fragment_subtitle");
    private final By codeFirst = By.xpath("//android.widget.LinearLayout[@bounds='[94,1035][324,1332]']");
    private final By codeFirstRepeat = By.xpath("//android.widget.LinearLayout[@bounds='[94,1035][324,1332]']");
    private final By androidMessage = By.id("android:id/message");
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
    private final By firstPopularShare = By.xpath("//android.view.View[7]/android.view.View[@index='0']");
    private final By shareDescribeText = By.xpath("//android.widget.ScrollView/android.view.View[8]");
    private final By maximizeShareDescribeText = By.xpath("//android.widget.ScrollView/android.view.View[9]");
    private final By buyShareButton = By.xpath("//android.view.View[10]/android.widget.Button");
    private final By pricePerShare = By.xpath("//android.widget.RelativeLayout/android.widget.EditText[@text!=\"0\"]");
    private final By sharesCount = By.xpath("//android.widget.RelativeLayout/android.widget.EditText[@text=\"0\"]");
    private final By sumShares = By.xpath("//android.widget.ScrollView/android.view.View[13]");

    private final By signButton_ios = By.name("Войти");
    private final By inputLogin_ios = By.xpath
            ("//XCUIElementTypeApplication[@name='BCC.KZ']/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    private final By inputPass_ios = By.cssSelector("XCUIElementTypeSecureTextField[value='Введите пароль']");
    private final By buttonPermission_ios = By.name("Однократно");

    public AuthListPage(AppiumDriver appiumDriver) {

        PageFactory.initElements(driver, this);
        driver = appiumDriver;
    }


    @Step ("Клик по кнопке Войти на экране сплеша")
    public void  signMainButtonClick() {
        click(signButton);
    }

    @Step ("Ввод пароля '{pass}' в поле пароля")
    public void setInputPass(String pass) {
        sendText(inputPass, pass);
    }

    @Step ("Клик по кнопке 'Войти' на экране авторизации")
    public void signCompleteButtonClick() {click(buttonLogin);}

    @Step ("Ввод телефона {login} в поле телефона")
    public void setInputLogin(String login) {
        waitForClickable(inputLogin, 10);
        sendText(inputLogin, login);
    }

    @Step ("Попытка клика по кнопке разрешения на получение геолокации")
    public void tryButtonPermissionClick() {
        try {
            click(buttonPermission);
        } catch (Exception exception) {
            logger.info("Permission has not found");
        }
    }

    @Step ("....")
    public void longPressSign() {
        try {
            longPressElement(signButton, 2000);
        } catch (Exception exception) {
            logger.info("Permission has not found");
        }
    }
/*

    public void signMainButtonClick_ios() {click((MobileElement) driver.findElement(signButton_ios));}

    @Step
    public void setInputLogin_ios(String login) {
        MobileElement input = (MobileElement) driver.findElement(inputLogin_ios);
        sendText(input, login);
    }
    @Step ("Клик по кнопке 'Войти' на экране авторизации")
    public void sign_complete_button_click() {click(buttonLogin);}
    @Step
    public void sign_complete_button_click_ios() {click((MobileElement) driver.findElement(signButton_ios));}

    @Step ("Ввод пароля '{pass}' в поле пароля")
    public void setInputPass(String pass) {
        sendText(inputPass, pass);
    }
    @Step
    public void setinputPass_ios(String pass) {
        MobileElement input = (MobileElement) driver.findElement(inputPass_ios);
        sendText(input, pass);
    }
    @Step ("Попытка клика по кнопке разрешения на получение геолокации")
    public void tryButtonPermissionClick() {
        WebDriverWait wait = new WebDriverWait(driver, 5);
        try {
            wait.until(ExpectedConditions.elementToBeClickable(buttonPermission)).click();
        } catch (Exception exception) {
            logger.info("Permission has not found");
        }

    }
    @Step
    public void try_buttonPermission_click_ios() {
        click(buttonPermission_ios);
    }

    @Step ("Проверка наличия текста '{text}' на модалке")
    public void check_text_androidMessage(String text) {
        containsMessageAssert(androidMessage, text);
    }
    @Step ("Проверка неактивности кнопки Войти на экране авторизации")
    public void check_active_signButton_is_not_clickable() {
        String buttonLogin_atr = getAttribute(buttonLogin, "enabled");
        Assert.assertEquals(buttonLogin_atr, "false", "Object is clickable");
    }

    @Step ("Ввод четырехзначного кода")
    public void setButtonsCode () {
        explicitWaitToClickable(codeFirst, 30);
        for (int i = 0; i < 4; i++) {click(codeFirst);}
        for (int i = 0; i < 4; i++) {click(codeFirstRepeat);}
    }

    @Step ("Ожидание загрузки счетов")
    public void waitforloadaccountList() {
        waitForVisability(bannerInvest, 30);
    }

    @Step ("Свайп горизонтального списка на главной странице")
    public void main_screen_swipe_horizontal_list() {
        swipeElementAndroid(paidButton, Direction.LEFT);
    }

    @Step ("Клик на баннер инвестиций")
    public void investbannerClick() {
        click(bannerInvest);
    }
    @Step ("Ожидание прогрузки страницы инвестиций")
    public void waitforloadInvest() {
        explicitWaitToClickable(investInstructionSkipButton, 30);
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
        click(transitBetweenButton);
    }

    @Step ("Нажатие кнопки 'Пополнить'")
    public void topUpButtonClick() {
        click(topUpButton);
    }

    @Step ("Установка первого счета в поле 'Откуда'")
    public void setFromBill() {
        click(fromBill);

        click(firstBill);
    }

    @Step ("Установка вторго счета в поле 'Куда' в разделе переводов")
    public void setToBill() {
        click(transitBetweenToBill);

        click(firstBill);
    }

    @Step ("Установка суммы перевода '1000'")
    public void setSumInsert(double sum) {
        click(sumInsert);

        sendText(sumInsert, String.valueOf(sum));
    }

    @Step ("Нажатие кнопки 'Перевести'")
    public void transferButtonClick() {
        click(transferButton);
    }

    @Step ("Свайп экрана вниз")
    public void swipeScreenDown() {
        swipeElementAndroid(billBlock, Direction.DOWN);
    }

    @Step ("Свайп экрана вверх")
    public void swipeScreenUp() {
        swipeElementAndroid(billBlock, Direction.UP);
    }

    @Step ("Обновление счета")
    public void swipeToRefreshBill() throws InterruptedException {
        swipeToRefreshAndroid(billBlock);
        Thread.sleep(5000);
    }

    @Step ("Закрытие модалки перевода")
    public void closeResultTransition() {click(closeResultButton);}

    @Step ("Пропуск гайда")
    public void skipInstruction() {click(investInstructionSkipButton);}

    @Step ("Подтверждение перевода")
    public void investConfirmTrnasaction() throws InterruptedException {
        click(investConfirmationTransaction);
        Thread.sleep(5000);
    }

    @Step ("Проверка на минимальную сумму текущего счета")
    public void checkCurrentInvestBillIsNotEmpty () {
        List<MobileElement> info = driver.findElements(currentBillFrom);
        for (MobileElement el : info) {
            String elText = el.>getText();
            if (elText.contains("Текущий счет для инвестиционной деятельности")) {
                String currentMoney = elText.replaceAll("[^-0-9.]+", "");
                Assert.assertTrue(
                        Integer.parseInt(currentMoney) >= 1001,
                        "Сумма текущего счета меньше 1001: " + Integer.parseInt(currentMoney));
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
                Assert.assertTrue(
                        Integer.parseInt(currentMoney) >= 1001,
                        "Сумма текущего счета меньше 1001: " + Integer.parseInt(currentMoney));
            }
        }
    }

    @Step ("Получение текущего счета")
    public Double getCurrentPersonalBill() {
        List<MobileElement> info = driver.findElements(currentBillFrom);
        for (MobileElement el : info) {
            String elText = el.getText();
            if (elText.contains("Visa") || elText.contains("MasterCard")) {
                String currentMoney = elText.replaceAll("[^-0-9,]+", "");
                String currentMoney2 = currentMoney.replace(",", ".");
                double currentMoneyInt = Double.parseDouble(currentMoney2);
                return currentMoneyInt;
            }
        }
        logger.error("Personal Bill is not found.");
        throw new RuntimeException("Personal Bill is not found.");
    }

    @Step ("Сравнение текущей суммы счета с ожидаемой")
    public void comparsionBills (int actual, int expected) {
        numbersComparsion(actual, expected, "Число " + actual + " != " + expected);
    }

    @Step ("Проверка на успешность завершения транзакции")
    public void checkResultStatusTransition() {
        MobileElement text = (MobileElement) driver.findElement(statusResultTransaction);
        String status = text.getText();
        textsComparsion(
                status,
                "Перевод успешно принят",
                "Actual: " + status + "; Expected: " + "Перевод успешно принят");
    }

    @Step ("Нажатие на значок вопроса на странице инвестиций")
    public void questionButtonClick() {
        click(questionOpenButton);
    }

    @Step ("Ожидание ошибки превышения доступной суммы")
    public void checkErrorSumBiggerThanBill() {
        waitForVisability(errorSumBiggerThanBill);
    }

    @Step ("Клик на первую популярную акцию")
    public void firstPopularShareClick() {
        click(firstPopularShare);
    }

    @Step ("Клик на кнопку покупки акции")
    public void buyShareButtonClick() {
        click(buyShareButton);
    }


    @Step ("Свайп и закрытие подсказки в разделе инвестиций")
    public void questionCloseButtonClick() {swipeElementAndroid(questionBanner, Direction.DOWN);}

    @Step ("Смена валюты на доллар")
    public void investSwitchToggleDollar() throws InterruptedException {
        click(investToggleCurrencyDollar);
    }

    @Step ("Смена валюты на тенге")
    public void investSwitchToggleTenge() {
        click(investToggleCurrencyTenge);
    }

    @Step ("Раскрытие описания акции")
    public void maximizeShareDescribeTextClick() {
        click(maximizeShareDescribeText);
    }
    @Step ("Получение количества символов в описании")
    public int getCountCharsDescribeText() {
        int textCount = driver.findElement(shareDescribeText).getText().length();
        return textCount;
    }
    @Step ("Получение цены за одну акцию")
    public String getPricePerShare() {
        String textPrice = driver.findElement(pricePerShare).getText();
        return textPrice;
    }

    @Step ("Установление количества покупаемых акций")
    public void setSharesCount (String count) {
        sendText(sharesCount, count);
    }

    @Step ("Получение общей стоимости акций за указанное количество")
    public String getSumShares () {
        String sum = driver.findElement(sumShares).getText();
        return sum.replaceAll("[^-0-9,]+", "");
    }

    @Step ("Проверка расчета суммы за указанное количество покупаемых акций")
    public void checkSumShares(String pricePer, String shareCount, String sum) {
        double expectedPrice = Double.parseDouble(pricePer) * Double.parseDouble(shareCount);
        Assert.assertEquals(
                Integer.parseInt(sum),
                expectedPrice, "Actual: " + Integer.parseInt(sum) + ", Expected: " + expectedPrice);
    }

    @Step ("Сравнение количества символов до раскрытия описания ({beforeMax}) и после ({afterMax})")
    public void comparsionCharsShareDescribeText(int beforeMax, int afterMax) {
        numbersComparsion(afterMax, beforeMax, "Число символов до расширения описания и после одинаковое: ");
    }

    @Step ("Проверка текущей установленной валюты")
    public void checkInvestCurrency(String currency) {
        switch (currency) {
            case "dollar":
                String objText = getText(myBriefcaseDollar);
                Assert.assertTrue(objText.contains("$"), "Currency is not in " + currency);
                break;
            case "tenge":
                String objText1 = getText(myBriefcaseTenge);
                Assert.assertTrue(objText1.contains("₸"), "Currency is not in " + currency);;
                break;
            default:
                logger.error("Currency is unknown!");
                throw new RuntimeException("Currency is unknown!");
        }
    }
*/

}
