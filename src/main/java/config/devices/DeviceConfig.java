package config.devices;

import base.TestBase;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.cucumber.java.hu.De;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import java.util.Map;

import static io.appium.java_client.remote.MobilePlatform.ANDROID;
import static io.appium.java_client.remote.MobilePlatform.IOS;

/**
 * Класс DeviceConfig собирает capabilities из файла конфигурации с помощью XMLReader (пакет utils)
 * и возвращает список готовых наборов capabilities для каждого устройства из файла.
 * @see ConfigReader#xmlReader(String)
 */

public class DeviceConfig {
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    public static UiAutomator2Options getCaps(String platform, String deviceName) {
        UiAutomator2Options options = new UiAutomator2Options();
        switch (platform) {
            case ANDROID:
                Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/androidDevices.xml");
                if (devices != null && devices.size() != 0) {
                    Device device = devices.get(deviceName);
                    options.setDeviceName(device.deviceName)
                            .setPlatformName(device.platformName)
                            .setPlatformVersion(device.platformVersion)
                            .setAutomationName(device.automationName)
                            .setClearSystemFiles(true)
                            .setUdid(device.udid);
                    if (device.isEmulator) {
                        options.setApp(System.getProperty("user.dir") + device.app)
                                .setAppWaitPackage(device.waitAppPackage)
                                .setAppWaitActivity(device.appWaitActivity);
                    }
                    else {
                        options.setAppActivity(device.appActivity)
                                .setAppPackage(device.appPackage);
                    }
                    return options;
                } else {
                    logger.error("Devices list is empty");
                    throw new RuntimeException("Список устройств пуст");
                }
            case IOS:
                return null; //для доработки
        }
        return options;
    }
}

