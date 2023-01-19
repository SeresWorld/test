package base;


import config.AppiumServerConfig;
import config.devices.DeviceConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.SkipException;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;


/**
 * Класс <b>TestBase</b> содержит набор фикстур для начала тест сьюта, метода и их окончания. В методе с аннотацией
 * <b>BeforeMethod</b> последовательно собирается набор капов для каждого устройства, и стартует набор тестов для него.
 * В методе <b>tearDown</b> делается скриншот, если тест был закончен с ошибкой.
 */

public class TestBase {

    public static AppiumDriver driver;
    public static AppiumServerConfig appiumServer = new AppiumServerConfig();

    private static final Logger logger = LogManager.getLogger(TestBase.class);

    /**
     * Объявление начала выполнения сьюта. Старт Appium сервера.
     * @param ctx
     */
    @BeforeSuite
    public void beforeSuite(ITestContext ctx) {
        String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
        logger.info("Start suite: " + suiteName);
        appiumServer.startAppiumServer();
    }

    /**
     * Сбор капов для каждого устройства из сьюта и их последовательный запуск
     * @param method текущий исполняемый метод
     * @param deviceName_ название устройства как параметр
     * @throws MalformedURLException
     */
    @Parameters("deviceName_")
    @BeforeMethod(alwaysRun = true)
    public void setUpAndroid(Method method, String deviceName_) throws MalformedURLException {

        logger.info("Start method: " + method.getName());

        try {
            logger.info("Device: " + deviceName_);
            UiAutomator2Options options = DeviceConfig.getCaps(ANDROID, deviceName_);
            String systemPort = DeviceConfig.getSystemPort(deviceName_);
            driver = new AppiumDriver(new URL("http://127.0.0.1:" + systemPort +"/wd/hub"), options);

        } catch (NullPointerException ex) {
            logger.error("NullPointerException");
            ex.fillInStackTrace();
        } catch (SessionNotCreatedException sessionNotCreatedException) {
            logger.error("Session has not been created\n");
            throw sessionNotCreatedException;
        }
    }

    /**
     * Окончание метода сопровождается проверкой на статус теста. Если он провален - делается скриншот экрана.
     * @param tr результат теста.
     */
    @AfterMethod
    public void tearDown(ITestResult tr) {
        if (null != driver) {
            if (tr.getStatus() == ITestResult.FAILURE) {
                Allure.addAttachment("Error", new ByteArrayInputStream(
                        ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
                logger.error("Test " + tr.getMethod().getMethodName() + " has been failed...");
            }
            logger.info("Teardown method: " + tr.getMethod());
        }

    }

    /**
     * Окончание сьюта: остановка драйвера, Appium сервера.
     * @param ctx
     */
    @AfterSuite
    public void tearDownSuite(ITestContext ctx) {
        String suiteName = ctx.getCurrentXmlTest().getSuite().getName();
        logger.info("Teardown suite: " + suiteName);
        driver.quit();
        appiumServer.stopAppiumServer();
    }

}
