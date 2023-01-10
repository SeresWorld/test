package config.environment;

import base.TestBase;
import config.devices.DeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс ThreadEnvironmentConfig содержит методы для сборки необходимого окружения с установленными capabilities.
 */

public class ThreadEnvironmentConfig {
    private static final Logger logger = LogManager.getLogger(TestBase.class);


    public static Map<String, ThreadEnvironment> getIOSEnvironments() throws MalformedURLException {
        return null;
    }
}
