import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class todoIOS extends TestBase {
    AuthListPage authListPage;

    @Test
    public void auth () throws MalformedURLException, InterruptedException {
        setUpIOS();
        authListPage = new AuthListPage(driver);
        authListPage.open_calendar();
        authListPage.swipe_calendar();
        Thread.sleep(10000);
    }
}
