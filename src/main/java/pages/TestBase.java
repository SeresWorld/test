package pages;

import base.Capabilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AuthListPage;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class TestBase {

    public static AppiumDriver driver;
    AuthListPage authListPage;

    public static void setUpAndroid() throws MalformedURLException {

        Capabilities caps = new Capabilities();
        // caps.setCapability("app", System.getProperty("user.dir") + "/apps/release-3.4.28-test.apk");
        driver = new AndroidDriver(new URL("http://localhost:4723/wd/hub"), caps.localAndroid());
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
        Capabilities caps = new Capabilities();
        /*caps.setCapability("bundleId", "kz.bcc.starbanking");*/
        driver = new IOSDriver(new URL("http://localhost:4723/wd/hub"), caps.localIOS());
    }


    @AfterMethod
    public static void tearDown(ITestResult tr) {
        if (null != driver) {
            if (tr.getStatus() == ITestResult.FAILURE) {
                Allure.addAttachment("Error", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                System.out.println("Test " + tr.getMethod().getMethodName() + "has been failed...");
            }
            driver.quit();
        }
    }
}
