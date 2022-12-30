package base;


import config.AppiumServer;
import config.devices.DeviceConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AuthListPage;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class TestBase extends DriverPool {

    AuthListPage authListPage;

    private static final Logger logger = LogManager.getLogger(TestBase.class);

    private final ThreadLocal<AppiumDriver> environment = new ThreadLocal<>();
    private static String[] list = new String[]{"5555", "6666"};
    @BeforeMethod (alwaysRun = true)
    public void setUpAndroid(Method method, String localPort) throws MalformedURLException {

        logger.info("Start method: " + method.getName());
        startAppiumService(localPort);
        Map<String, DesiredCapabilities> caps =
        setDriver(new AndroidDriver(new URL("127.0.0.1")));
    }

    @AfterMethod
    public void tearDown(ITestResult tr) {

        if (null != getAppiumDriver()) {
            if (tr.getStatus() == ITestResult.FAILURE) {
                Allure.addAttachment("Error", new ByteArrayInputStream(((TakesScreenshot) getAppiumDriver()).getScreenshotAs(OutputType.BYTES)));
                logger.error("Test " + tr.getMethod().getMethodName() + " has been failed...");
            }
            logger.info("Teardown " + tr.getMethod() + "\n");
            getAppiumDriver().quit();
        }
    }

    public void startAppiumService(String localPort) {
        AppiumDriverLocalService service;
        AppiumServiceBuilder builder = new AppiumServiceBuilder();
        builder.withIPAddress("127.0.0.1");
        builder.usingPort(Integer.parseInt(localPort));
        service = AppiumDriverLocalService.buildService(builder);
        service.start();
        logger.info("Service has been started with port: " + localPort);
    }


    public AppiumDriver getAppiumDriver() {
        return this.environment.get();
    }

    public void setDriver(AppiumDriver driver) {
        this.environment.set(driver);
    }

    public void auth_complete() throws MalformedURLException, InterruptedException {

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
