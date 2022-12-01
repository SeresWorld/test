import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class todoIOS extends TestBase {
    AuthListPage authListPage;

    @Test
    public void auth () throws MalformedURLException, InterruptedException {
        setUpIOS();
        authListPage = new AuthListPage(driver);
        authListPage.try_button_permission_click_ios();
        authListPage.sign_main_button_click_ios();
        authListPage.setInput_login_ios("7756655544");
        authListPage.setInput_pass_ios("orapas123");
        authListPage.sign_complete_button_click_ios();
        tearDown();
    }
}
