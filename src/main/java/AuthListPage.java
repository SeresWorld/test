import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.qameta.allure.Step;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

public class AuthListPage extends PageBase {
    public AuthListPage(AppiumDriver appiumDriver) {
        super(appiumDriver);
    }
    private By sign_button = By.id("kz.bcc.starbanking.stage:id/fragment_welcome_login");
    private By input_login = By.id("kz.bcc.starbanking.stage:id/view_anketa_edit_input");
    private By input_pass = By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.cardview.widget.CardView[2]/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.EditText");
    private By button_login = By.id("kz.bcc.starbanking.stage:id/fragment_login_enter");
    private By button_permission = By.id("com.android.permissioncontroller:id/permission_allow_foreground_only_button");


    private By android_message = By.id("android:id/message");

    private By sign_button_ios = By.name("Войти");
    private By input_login_ios = By.xpath
            ("//XCUIElementTypeApplication[@name=\"BCC.KZ\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther[1]/XCUIElementTypeOther[1]/XCUIElementTypeTextField");
    private By input_pass_ios = By.cssSelector("XCUIElementTypeSecureTextField[value='Введите пароль']");
    private By button_permission_ios = By.name("Однократно");
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
    @Step ("Клик по кнопке Войти на экране авторизации")
    public void sign_complete_button_click() {click((MobileElement) driver.findElement(button_login));}
    @Step
    public void sign_complete_button_click_ios() {click((MobileElement) driver.findElement(sign_button_ios));}

    @Step ("Ввод пароля {pass} в поле пароля")
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
    @Step ("Проверка наличия текста {text} на модалке")
    public void check_text_android_message(String text) {
        containsmessageAssert((MobileElement) driver.findElement(android_message), text);
    }
    @Step ("Проверка неактивности кнопки Войти на экране авторизации")
    public void check_active_sign_button_is_not_clickable() {
        String button_login_atr = getAttribute((MobileElement) driver.findElement(button_login), "enabled");
        Assert.assertEquals("Object is clickable", "false", button_login_atr);

    }
}

/*    @AndroidFindBy(id = "kz.bcc.starbanking.stage:id/view_anketa_edit_input")
    MobileElement input_login;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.cardview.widget.CardView[2]/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.EditText")
    MobileElement input_pass;
    @AndroidFindBy(id = "kz.bcc.starbanking.stage:id/fragment_login_enter")
    MobileElement button_login;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    MobileElement button_permission;*/