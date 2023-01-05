package base;


import config.environment.ThreadEnvironment;
import config.environment.ThreadEnvironmentConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.qameta.allure.Allure;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.*;
import pages.AuthListPage;

import java.io.ByteArrayInputStream;
import java.lang.reflect.Method;
import java.net.MalformedURLException;


/**
 * Класс TestBase содержит набор фикстур для начала тест сьюта, метода и их окончания. В методе с аннотацией
 * BeforeMethod активируется собранное окружение под указанное устройство. Желаемое устройство указывается в переменную
 * Device, ее название должно совпадать с id из файла androidDevices.xml. В teardown методе делается скриншот, если
 * тест был закончен с ошибкой.
 */

public class TestBase {

    AuthListPage authListPage;

    private final String device = "Nexus 6"; // ЗДЕСЬ УКАЗЫВАЕТСЯ ID УСТРОЙСТВА ИЗ androidDevices.xml ДЛЯ ТЕСТА

    private static final Logger logger = LogManager.getLogger(TestBase.class);

    private final ThreadLocal<ThreadEnvironment> environment = new ThreadLocal<>();

    @BeforeSuite
    public void beforeSuite() {

        logger.info("Device: " + device);

    }

    @BeforeMethod(alwaysRun = true)
    public void setUpAndroid(Method method) throws MalformedURLException {

        logger.info("Start method: " + method.getName());

        ThreadEnvironment envThread = ThreadEnvironmentConfig.getAndroidEnvironment(device);
        if (envThread != null) {
            this.environment.set(envThread);
        } else {
            logger.info("Device is not found");
            throw new RuntimeException("Device is not found");
        }

    }

    @AfterMethod
    public void tearDown(ITestResult tr) {

        if (null != getAppiumDriver()) {
            if (tr.getStatus() == ITestResult.FAILURE) {
                Allure.addAttachment("Error", new ByteArrayInputStream(
                        ((TakesScreenshot) getAppiumDriver()).getScreenshotAs(OutputType.BYTES)));
                logger.error("Test " + tr.getMethod().getMethodName() + " has been failed...");
            }
            logger.info("Teardown " + tr.getMethod() + "\n");
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
