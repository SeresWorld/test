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

public class ThreadEnvironmentConfig {
    private static final Logger logger = LogManager.getLogger(TestBase.class);
    public static ThreadEnvironment getAndroidEnvironment(String device) throws MalformedURLException {
        try {
            DesiredCapabilities caps = DeviceConfig.getCaps("android", device);
            ThreadEnvironment environment = new ThreadEnvironment();
            environment.driver = new AndroidDriver<>(
                    new URL("http://localhost:4723/wd/hub"), caps);
            return environment;
        } catch (NullPointerException ex) {
            logger.error("NullPointerException");
            ex.fillInStackTrace();
        }
        return null;
    }

    public static Map<String, ThreadEnvironment> getIOSEnvironments() throws MalformedURLException {
        return null;
    }
}
