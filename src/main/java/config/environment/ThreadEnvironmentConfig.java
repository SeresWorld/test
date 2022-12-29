package config.environment;

import config.devices.Device;
import config.devices.DeviceConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.testng.Assert.fail;

public class ThreadEnvironmentConfig {

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
