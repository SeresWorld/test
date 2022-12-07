import org.testng.annotations.Test;

import java.net.MalformedURLException;

public class todoIOS extends TestBase {
    DailyCheck dailyCheck;

    @Test
    public void swipeCalendar() throws MalformedURLException, InterruptedException {
        setUpIOS();
        dailyCheck = new DailyCheck(driver);
        dailyCheck.open_calendar();
        dailyCheck.swipe_calendar();
        Thread.sleep(10000);
    }

    @Test
    public void createTestTask() throws MalformedURLException, InterruptedException {
        setUpIOS();
    }
}
