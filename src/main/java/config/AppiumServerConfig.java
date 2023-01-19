package config;

import base.TestBase;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.support.ui.FluentWait;
import utils.ConfigReader;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;


public class AppiumServerConfig {

    private static final Logger logger = LogManager.getLogger(TestBase.class);
    private static AppiumDriverLocalService service;

    private static final String SERVER_IP = ConfigReader.serverIp;
    private static final int SYSTEM_PORT = ConfigReader.systemPort;
    private static final String APPIUM_JS_PATH = ConfigReader.appiumJSPath;
    private static final String LOG_LEVEL = ConfigReader.logLevel;

    public void startAppiumServer() {
        logger.info("Start Appium Server...");
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));

        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withArgument(() -> "--base-path", "/wd/hub")
                .withArgument(GeneralServerFlag.LOG_LEVEL, LOG_LEVEL) // Запуск с логом только ошибок в терминале
                .withArgument(GeneralServerFlag.RELAXED_SECURITY); // Запуск с отключенными доп. проверками безопасности


        builder.withIPAddress(SERVER_IP)
                .usingPort(SYSTEM_PORT)
                .withAppiumJS(new File(APPIUM_JS_PATH))
                .withEnvironment(environment);
        service = AppiumDriverLocalService.buildService(builder);
        logger.info("Starting Appium Server at " + SERVER_IP + ":" + SYSTEM_PORT + "...");

        try {
            getWaiter().until(func -> {
                service.start();
                return !service.isRunning();
            });
            logger.info("Appium Server started successfully!");

        } catch (TimeoutException ex) {
            logger.info("Appium Server NOT started! Cause: \n" + ex.getMessage());
            service.stop();
            throw ex;
        }
    }

    public void stopAppiumServer() {
        logger.info("Terminating Appium Server...\n");
        service.stop();
    }

    private FluentWait<AppiumDriverLocalService> getWaiter() {
        return new FluentWait<>(service)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofSeconds(2));
    }

}
