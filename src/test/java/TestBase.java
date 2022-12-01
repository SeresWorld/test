import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.*;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static AppiumDriver driver;

    public static void setUpAndroid() throws MalformedURLException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "Redmi Note 10S");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("appActivity", "kz.bcc.starbanking.ui.screens.splash.SplashActivity");
        caps.setCapability("appPackage", "kz.bcc.starbanking.stage");
        // caps.setCapability("app", System.getProperty("user.dir") + "/apps/release-3.4.28-test.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        System.out.println("я родился");
    }


    public static void setUpIOS() throws MalformedURLException, InterruptedException {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "iPhone 11");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "15.5");
        caps.setCapability("automationName", "XCUITest");
        /*caps.setCapability("app", System.getProperty("user.dir") + "/apps/AboutMe.app");*/
        caps.setCapability("bundleId", "kz.bcc.starbankingtest");
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps);
    }



    public static void tearDown() {
        if (null != driver) {
            driver.quit();
        }
    }
}
