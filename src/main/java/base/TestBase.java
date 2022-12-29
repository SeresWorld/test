package base;


import config.devices.DeviceConfig;
import config.environment.ThreadEnvironment;
import config.environment.ThreadEnvironmentConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.qameta.allure.Allure;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.support.PageFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AuthListPage;

import java.io.ByteArrayInputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.Map;

public class TestBase extends DriverPool {

    AuthListPage authListPage;

    private final ThreadLocal<ThreadEnvironment> environment = new ThreadLocal<>();
    @BeforeMethod (alwaysRun = true)
    public void setUpAndroid() throws MalformedURLException {

        Map<String, ThreadEnvironment> environments = ThreadEnvironmentConfig.getAndroidEnvironments();
        for (Map.Entry<String, ThreadEnvironment> environment: environments.entrySet()) {
            System.out.println("Device: " + environment.getKey());
            ThreadEnvironment envThread = environment.getValue();
            this.environment.set(envThread);
        }
    }

    @AfterMethod
    public void tearDown(ITestResult tr) {

        if (null != getAppiumDriver()) {
            if (tr.getStatus() == ITestResult.FAILURE) {
                Allure.addAttachment("Error", new ByteArrayInputStream(((TakesScreenshot) getAppiumDriver()).getScreenshotAs(OutputType.BYTES)));
                System.out.println("Test " + tr.getMethod().getMethodName() + " has been failed...");
            }
            getAppiumDriver().quit();
        }
    }

    private ThreadEnvironment getEnvironment() {
        return environment.get();
    }

    public AppiumDriver<MobileElement> getAppiumDriver() {
        return getEnvironment().driver;
    }


    public void auth_complete() throws MalformedURLException, InterruptedException {
        setUpAndroid();
        authListPage = new AuthListPage(getAppiumDriver());
        authListPage.sign_main_button_click();
        authListPage.setInput_login("7756655544");
        authListPage.setInput_pass("orapas123");
        authListPage.sign_complete_button_click();
        authListPage.try_button_permission_click();
        authListPage.setButtonsCode();
        authListPage.waitforloadaccountList();
    }

}
