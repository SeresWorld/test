package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import pages.AuthListPage;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class DriverSetup extends DriverPool {

    AuthListPage authListPage;

    @BeforeSuite(alwaysRun = true)
    public static void beforeSuite() {

    }

    @BeforeMethod
    public static void androidSetUp() throws MalformedURLException {
        Capabilities caps = new Capabilities();
        AndroidDriver<MobileElement> driver = new AndroidDriver<>(
                new URL("http://localhost:4723/wd/hub"), caps.localAndroid());
        setAndroidDriverInstance(driver);
        System.out.println("Driver: " + driver);
    }
    @BeforeMethod
    public static void iosSetUp() throws MalformedURLException {
        Capabilities caps = new Capabilities();
        IOSDriver<MobileElement> driver = new IOSDriver<>(
                new URL("http://localhost:4723/wd/hub"), caps.localIOS());
        setIosDriverInstance(driver);
    }

    @AfterMethod
    public static void tearDown(ITestResult tr) {

        if (null != getAndroidDriverInstance()) {
            if (tr.getStatus() == ITestResult.FAILURE) {
                Allure.addAttachment("Error", new ByteArrayInputStream(((TakesScreenshot) getAndroidDriverInstance()).getScreenshotAs(OutputType.BYTES)));
                System.out.println("Test " + tr.getMethod().getMethodName() + " has been failed...");
            }
            getAndroidDriverInstance().quit();
        }
    }

    public void auth_complete(AppiumDriver driver) throws MalformedURLException, InterruptedException {
        authListPage = new AuthListPage(driver);
        authListPage.sign_main_button_click();
        authListPage.setInput_login("7756655544");
        authListPage.setInput_pass("orapas123");
        authListPage.sign_complete_button_click();
        authListPage.try_button_permission_click();
        authListPage.setButtonsCode();
        authListPage.waitforloadaccountList();
    }
}
