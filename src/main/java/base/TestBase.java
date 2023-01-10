package base;


import config.devices.Device;
import config.devices.DeviceConfig;
import config.environment.ThreadEnvironment;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.hu.De;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AuthListPage;
import utils.ConfigReader;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;


/**
 * Класс TestBase содержит набор фикстур для начала тест сьюта, метода и их окончания. В методе с аннотацией
 * BeforeMethod активируется собранное окружение под указанное устройство. Желаемое устройство указывается в переменную
 * Device, ее название должно совпадать с id из файла androidDevices.xml. В teardown методе делается скриншот, если
 * тест был закончен с ошибкой.
 */

public class TestBase {

    AuthListPage authListPage;

    public static AppiumDriver<MobileElement> driver;
    public static AppiumDriver<MobileElement> driver2;

    private String device = null;

    int counter = 0;

    private static final Logger logger = LogManager.getLogger(TestBase.class);
    Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/androidDevices.xml");

    public void getDesiredCaps() {
        try {
            this.device = (String) devices.keySet().toArray()[counter];
            counter++;
        } catch (NullPointerException ex) {
            logger.error("NullPointerException");
            ex.fillInStackTrace();
        }
    }

    public String getServerPort(String deviceName) {
        for (Map.Entry<String, Device> device: devices.entrySet()) {
            if (Objects.equals(device.getKey(), deviceName)) {
                return device.getValue().serverPort;
            }
        }
        return null;
    }
    @BeforeSuite
    public void beforeSuite() throws MalformedURLException {


    }
    @Parameters({"deviceName_", "URL_"})
    @BeforeMethod(alwaysRun = true)
    public void setUpAndroid(Method method, String deviceName_, String URL_) throws MalformedURLException {

        logger.info("Start method: " + method.getName());
        try {
            logger.info("Device: " + deviceName_);
            DesiredCapabilities desiredCapabilities = DeviceConfig.getCaps("android", deviceName_);
            driver = new AndroidDriver<>(new URL(URL_), desiredCapabilities);
            Thread.sleep(10000);
        } catch (NullPointerException ex) {
            logger.error("NullPointerException");
            ex.fillInStackTrace();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
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
            logger.info("Teardown " + tr.getMethod() + "\n");
            driver.quit();
        }

    }

    @AfterSuite
    public void tearDownSuite() throws MalformedURLException {

    }

    public AppiumDriver<MobileElement> getAppiumDriver() {
        return ThreadEnvironment.getAppiumDriverInstance();
    }


    public void auth_complete() throws MalformedURLException, InterruptedException {
        authListPage = new AuthListPage(driver);
        authListPage.signMainButtonClick();
        authListPage.setInputLogin("7756655544");
        authListPage.setinputPass("orapas123");
        authListPage.sign_complete_button_click();
        authListPage.try_buttonPermission_click();
        authListPage.setButtonsCode();
        authListPage.waitforloadaccountList();
    }

}
