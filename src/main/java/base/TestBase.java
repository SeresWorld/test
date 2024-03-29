package base;


import config.AppiumServerConfig;
import config.devices.DeviceConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.SessionNotCreatedException;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;


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
     * @param ctx параметр, содержащий данные о тестовой сессии
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
    public void setup(Method method, String deviceName_) throws MalformedURLException {

        logger.info("Start method: " + method.getName());

        try {
            logger.info("Device: " + deviceName_);
            String platformName = DeviceConfig.getPlatformName(deviceName_);
            String systemPort = DeviceConfig.getSystemPort(deviceName_);
            switch (platformName) {
                case ANDROID:
                    UiAutomator2Options options = DeviceConfig.getAndroidCaps(deviceName_);
                    driver = new AppiumDriver(new URL("http://127.0.0.1:" + systemPort +"/wd/hub"), options);
                    break;
                case IOS:
                    XCUITestOptions optionsIOS = DeviceConfig.getIOSCaps(deviceName_);
                    driver = new AppiumDriver(new URL("http://127.0.0.1:" + systemPort +"/wd/hub"), optionsIOS);
                    break;
                default:
                    logger.error("Get caps: Invalid platform name.");
                    throw new RuntimeException("Get caps: Invalid platform name.");
            }
        } catch (NullPointerException ex) {
            logger.error("SetUp: NullPointerException.");
            ex.fillInStackTrace();
        } catch (SessionNotCreatedException sessionNotCreatedException) {
            logger.error("Session has not been created\n");
            throw sessionNotCreatedException;
        }
    }

    /**
     * Окончание метода сопровождается проверкой на статус теста. Если он провален - делается скриншот экрана.
     * @param tr параметр, содержащий результат теста.
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
        if (driver != null) {
            driver.quit();
        }
        appiumServer.stopAppiumServer();
    }

}
