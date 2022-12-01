import io.cucumber.java.bs.A;
import org.json.simple.parser.ParseException;
import org.testng.annotations.*;
import utils.JsonReader;

import java.io.IOException;
import java.net.MalformedURLException;



public class todoAndroid extends TestBase {

    AuthListPage authListPage;


    @DataProvider (name = "test data")
    public Object[][] passData() throws IOException, ParseException {
        return JsonReader.getJSONData(System.getProperty("user.dir") + "/data/TestData.json",
                "Users", 2);

    }
    @Test
    public void auth_valid() throws MalformedURLException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);

        authListPage.sign_main_button_click();
        authListPage.setInput_login("7756655544");
        authListPage.setInput_pass("orapas123");
        authListPage.sign_complete_button_click();
        authListPage.try_button_permission_click();
        tearDown();

    }

    @Test
    public void auth_invalid() throws MalformedURLException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);
        authListPage.sign_main_button_click();
        authListPage.setInput_login("0123456789");
        authListPage.setInput_pass("123");
        authListPage.sign_complete_button_click();
        authListPage.try_button_permission_click();
        authListPage.check_text_android_message("Не верный логин или пароль");
        tearDown();
    }

    @Test
    public void auth_not_filled_login() throws MalformedURLException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);
        authListPage.sign_main_button_click();
        authListPage.setInput_pass("123");
        authListPage.check_active_sign_button_is_not_clickable();
        tearDown();
    }
    @Test
    public void auth_not_filled_pass() throws MalformedURLException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);
        authListPage.sign_main_button_click();
        authListPage.setInput_login("0123456789");
        authListPage.check_active_sign_button_is_not_clickable();
        tearDown();
    }




}

// swipe horizontal list
        /*
        AndroidElement pic1 = (AndroidElement) driver.findElement(By.id("kz.bcc.starbanking.stage:id/item_banner_title"));
        Rectangle rect = pic1.getRect();
        System.out.println(rect);
        System.out.println(pic1.getLocation());
        PointOption pointOptionStart = PointOption.point(rect.x + rect.width + 100,
                rect.y + rect.height / 2);
        PointOption pointOptionEnd = PointOption.point(rect.x,
        rect.y + rect.height / 2);
        actions = new AndroidTouchAction(driver)
                .press(pointOptionStart)
                .waitAction()
                .moveTo(pointOptionEnd)
                .release()
                .perform();
        try {
            Thread.sleep(10000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }*/
