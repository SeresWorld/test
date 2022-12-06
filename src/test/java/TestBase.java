import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITest;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static AppiumDriver driver;
    AuthListPage authListPage;

    public static void setUpAndroid() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Redmi Note 10S");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("appActivity", "kz.bcc.starbanking.ui.screens.splash.SplashActivity");
        caps.setCapability("appPackage", "kz.bcc.starbanking.stage");
        // caps.setCapability("app", System.getProperty("user.dir") + "/apps/release-3.4.28-test.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    public void auth_complete() throws MalformedURLException, InterruptedException {
        setUpAndroid();
        authListPage = new AuthListPage(driver);
        authListPage.sign_main_button_click();
        authListPage.setInput_login("7756655544");
        authListPage.setInput_pass("orapas123");
        authListPage.sign_complete_button_click();
        authListPage.try_button_permission_click();
        authListPage.setButtonsCode();
        authListPage.waitforloadaccountList();
    }


    public static void setUpIOS() throws MalformedURLException, InterruptedException {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "iPhone 11");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "15.5");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("app", System.getProperty("user.dir") + "/apps/DailyCheck.app");
        /*caps.setCapability("bundleId", "kz.bcc.starbanking");*/
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }


    @AfterMethod
    public static void tearDown(ITestResult tr) {
        if (null != driver) {
            driver.quit();
        }
    }
}
