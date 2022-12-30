package config.environment;

import base.TestBase;
import config.devices.DeviceConfig;
import io.appium.java_client.android.AndroidDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class ThreadEnvironmentConfig {
    private static final Logger logger = LogManager.getLogger(TestBase.class);
    public static Map<String, ThreadEnvironment> getAndroidEnvironments() throws MalformedURLException {
        Map<String, ThreadEnvironment> environments = new HashMap<>();
        try {
            for (Map.Entry<String, DesiredCapabilities> entry: DeviceConfig.getCaps("android").entrySet()) {
                ThreadEnvironment environment = new ThreadEnvironment();
                environment.driver = new AndroidDriver<>(
                        new URL("http://localhost:4723/wd/hub"), entry.getValue());
                environments.put(entry.getKey(), environment);
            }
        } catch (NullPointerException ex) {
            logger.error("NullPointerException");
            ex.fillInStackTrace();
        }
        return environments;
    }

    public static Map<String, ThreadEnvironment> getIOSEnvironments() throws MalformedURLException {
        Map<String, ThreadEnvironment> environments = new HashMap<>();
        try {
            for (Map.Entry<String, DesiredCapabilities> entry: DeviceConfig.getCaps("ios").entrySet()) {
                ThreadEnvironment environment = new ThreadEnvironment();
                environment.driver = new AndroidDriver<>(
                        new URL("http://localhost:4723/wd/hub"), entry.getValue());
                environments.put(entry.getKey(), environment);
            }
        } catch (NullPointerException ex) {
            ex.fillInStackTrace();
        }
        return environments;
    }
}
