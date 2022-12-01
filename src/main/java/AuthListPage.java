import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
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

/*    @AndroidFindBy(id = "kz.bcc.starbanking.stage:id/view_anketa_edit_input")
    MobileElement input_login;
    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.widget.FrameLayout/androidx.appcompat.widget.LinearLayoutCompat/android.widget.ScrollView/androidx.appcompat.widget.LinearLayoutCompat/androidx.cardview.widget.CardView[2]/android.view.ViewGroup/androidx.appcompat.widget.LinearLayoutCompat/android.widget.EditText")
    MobileElement input_pass;
    @AndroidFindBy(id = "kz.bcc.starbanking.stage:id/fragment_login_enter")
    MobileElement button_login;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_foreground_only_button")
    MobileElement button_permission;*/

    public void sign_main_button_click() {
        click((MobileElement) driver.findElement(sign_button));
    }

    public void setInput_login(String login) {
        MobileElement input = (MobileElement) driver.findElement(input_login);
        sendText(input, login);
    }

    public void sign_complete_button_click() {click((MobileElement) driver.findElement(button_login));}

    public void setInput_pass(String pass) {
        MobileElement input = (MobileElement) driver.findElement(input_pass);
        sendText(input, pass);
    }
/*
    public void try_button_permission_click() {
        try {
            click(button_permission);
        } catch (NoSuchElementException noSuchElementException) {
            System.out.println("Permission has not found");
        }
    }*/


}
