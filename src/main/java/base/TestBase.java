package base;


import config.devices.DeviceConfig;
import config.environment.ThreadEnvironment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AuthListPage;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;


/**
 * Класс TestBase содержит набор фикстур для начала тест сьюта, метода и их окончания. В методе с аннотацией
 * BeforeMethod активируется собранное окружение под указанное устройство. Желаемое устройство указывается в переменную
 * Device, ее название должно совпадать с id из файла androidDevices.xml. В teardown методе делается скриншот, если
 * тест был закончен с ошибкой.
 */

public class TestBase {

    AuthListPage authListPage;

    public static AppiumDriver driver;

    private static final Logger logger = LogManager.getLogger(TestBase.class);



    @BeforeSuite
    public void beforeSuite(ITestContext ctx) {
        String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
        logger.info("Start suite: " + suiteName);

    }
    @Parameters({"deviceName_", "URL_"})
    @BeforeMethod(alwaysRun = true)
    public void setUpAndroid(Method method, String deviceName_, String URL_) throws MalformedURLException {

        logger.info("Start method: " + method.getName());

        try {
            logger.info("Device: " + deviceName_);
            UiAutomator2Options options = DeviceConfig.getCaps(ANDROID, deviceName_);
            driver = new AppiumDriver(new URL(URL_), options);
        } catch (NullPointerException ex) {
            logger.error("NullPointerException");
            ex.fillInStackTrace();
        } catch (SessionNotCreatedException sessionNotCreatedException) {
            logger.error("Session has not been created");
            throw sessionNotCreatedException;
        }
    }

    @AfterMethod
    public void tearDown(ITestResult tr) {
        if (null != driver) {
            if (tr.getStatus() == ITestResult.FAILURE) {
                Allure.addAttachment("Error", new ByteArrayInputStream(
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                logger.error("Test " + tr.getMethod().getMethodName() + " has been failed...");
            }
            logger.info("Teardown method:" + tr.getMethod() + "\n");
            driver.quit();
        }

    }

    @AfterSuite
    public void tearDownSuite(ITestContext ctx) {
        String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
        logger.info("Teardown suite: " + suiteName);
    }



    public void auth_complete() throws MalformedURLException, InterruptedException {
        authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick();
    }

}
