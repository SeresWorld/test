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
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

import static org.testng.Assert.fail;

public class ThreadEnvironmentConfig {
    public static List<ThreadEnvironment> getAndroidEnvironments() throws MalformedURLException {
        List<ThreadEnvironment> environments = new ArrayList<>();
        try {
            for (DesiredCapabilities device: DeviceConfig.getCaps("android")) {

                ThreadEnvironment environment = new ThreadEnvironment();
                environment.driver = new AndroidDriver<>(new URL("http://localhost:4723/wd/hub"), device);
                environments.add(environment);
            }
        } catch (NullPointerException ex) {
            ex.fillInStackTrace();
        }
        return environments;
    }

    public static List<ThreadEnvironment> getIOSEnvironments() throws MalformedURLException {
        List<ThreadEnvironment> environments = new ArrayList<>();

        for (DesiredCapabilities device: Objects.requireNonNull(DeviceConfig.getCaps("ios"))) {
            ThreadEnvironment environment = new ThreadEnvironment();
            environment.driver = new IOSDriver<>(new URL("http://localhost:4723/wd/hub"), device);
            environments.add(environment);
        }
        return environments;
    }
}
