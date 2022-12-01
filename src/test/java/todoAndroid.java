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
    public void auth_valid() {

        authListPage = new AuthListPage(driver);

        authListPage.sign_main_button_click();
        authListPage.setInput_login("7756655544");
        authListPage.setInput_pass("orapas123");
        authListPage.sign_complete_button_click();

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
    }

    @Test
    public void 



}
