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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Класс AppiumServerConfig содержит методы для работы с Appium сервером.
 */

public class AppiumServerConfig {

    private static final Logger logger = LogManager.getLogger(TestBase.class);
    private static AppiumDriverLocalService service;

    private static final String SERVER_IP = ConfigReader.serverIp;
    private static final int SYSTEM_PORT = ConfigReader.systemPort;
    private static final String APPIUM_JS_PATH = ConfigReader.appiumJSPath;
    private static final String LOG_LEVEL = ConfigReader.logLevel;
    private static final int START_SERVER_WAIT = ConfigReader.startServerWait;
    private static final int POLLING_EVERY_IN_SECONDS = ConfigReader.explicitWait;

    public void startAppiumServer() {

        AtomicInteger attempt = new AtomicInteger(1);

        logger.info("Start Appium Server...");
        HashMap<String, String> environment = new HashMap<>();
        environment.put("PATH", "/usr/local/bin:" + System.getenv("PATH"));
        // Создаем объект билдера сервера с аргументами
        AppiumServiceBuilder builder = new AppiumServiceBuilder()
                .withArgument(() -> "--base-path", "/wd/hub")
                // Запуск с логом только ошибок в терминале
                .withArgument(GeneralServerFlag.LOG_LEVEL, LOG_LEVEL)
                // Запуск с отключенными доп. проверками безопасности
                .withArgument(GeneralServerFlag.RELAXED_SECURITY);

        // Запускаем билдер с заданными параметрами
        builder.withIPAddress(SERVER_IP)
                .usingPort(SYSTEM_PORT)
                .withAppiumJS(new File(APPIUM_JS_PATH))
                .withEnvironment(environment);
        // Обновляем переменную service, помещая в нее объект настроенного билдера
        service = AppiumDriverLocalService.buildService(builder);


        /*
        Попытка старта сервера в течении заданного времени с шагом в указанное количество секунд, иначе останавливаем
        сервер.
        */
        try {

            getWaiter().until(func -> {
                logger.info(
                         "Starting Appium Server at "
                                + SERVER_IP + ":" + SYSTEM_PORT + "..." + "Attempt: " + attempt + ".");
                attempt.getAndIncrement();
                service.start();
                return service.isRunning();
            });
            logger.info("Appium Server started successfully!");

        } catch (TimeoutException ex) {

            logger.info("Appium Server NOT started! Cause: \n" + ex.getMessage());
            stopAppiumServer();

        }
    }

    public void stopAppiumServer() {
        logger.info("Terminating Appium Server...\n");
        service.stop();
    }

    /**
     * Создание и возвращение объекта ожидания сервера с заданными параметрами
     * (START_SERVER_WAIT - общее время ожидания сервера, POLLING_EVERY_IN_SECONDS - шаг, с которым происходит попытка
     * подключения).
     * @return объект ожидания.
     */
    private FluentWait<AppiumDriverLocalService> getWaiter() {
        return new FluentWait<>(service)
                .withTimeout(Duration.ofSeconds(START_SERVER_WAIT))
                .pollingEvery(Duration.ofSeconds(POLLING_EVERY_IN_SECONDS));
    }

}
