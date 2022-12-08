import io.qameta.allure.testng.AllureTestNg;
import org.json.simple.parser.ParseException;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import utils.JsonReader;

import java.io.IOException;
import java.net.MalformedURLException;


public class todoIOS extends TestBase {
    DailyCheck dailyCheck;

    @DataProvider(name = "test data")
    public Object[][] passData() throws IOException, ParseException {
        return JsonReader.getJSONData(System.getProperty("user.dir") + "/data/TestData.json",
                "Users", 3);

    }

    @Test
    public void swipeCalendar() throws MalformedURLException, InterruptedException {
        setUpIOS();
        dailyCheck = new DailyCheck(driver);
        dailyCheck.open_calendar();
        dailyCheck.swipe_calendar();
        Thread.sleep(10000);
    }

    @Test
    public void longPress() throws MalformedURLException, InterruptedException {
        setUpIOS();
        dailyCheck = new DailyCheck(driver);
        dailyCheck.open_edit_mode();
        dailyCheck.longPress();
    }

    @Test (dataProvider = "test data")
    public void createTask(String hours, String minutes, String setting) throws MalformedURLException, InterruptedException {
        setUpIOS();
        dailyCheck = new DailyCheck(driver);
        dailyCheck.openCreateTaskModal();
        dailyCheck.setTitleInsert();
        dailyCheck.setDescriptionInsert();
        dailyCheck.dueTimeClick();
        dailyCheck.setTime(hours, minutes, setting);
        dailyCheck.saveButtonClick();
    }
}
