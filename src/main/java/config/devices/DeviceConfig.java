package config.devices;

import base.TestBase;
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

    public static DesiredCapabilities getCaps(String platform, String deviceName) {
        DesiredCapabilities capabilities = new DesiredCapabilities();
        switch (platform) {
            case ANDROID:
                Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/androidDevices.xml");
                if (devices != null && devices.size() != 0) {
                    Device device = devices.get(deviceName);
                    capabilities.setCapability("appium:deviceName", device.deviceName);
                    capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, device.platformName);
                    capabilities.setCapability("appium:platformVersion", device.platformVersion); //"appium:platformVersion"
                    capabilities.setCapability("appium:automationName", device.automationName);
                    capabilities.setCapability("appium:clearSystemFiles", true);
                    capabilities.setCapability("appium:autoGrantPermissions", true);
                    capabilities.setCapability("appium:udid", device.udid);
                    if (device.isEmulator.contains("true")) {
                        capabilities.setCapability("appium:app", System.getProperty("user.dir") + device.app);
                        capabilities.setCapability("appium:waitAppPackage", device.waitAppPackage);
                        capabilities.setCapability("appium:waitAppActivity", device.appWaitActivity);
                    }
                    else {
                        capabilities.setCapability("appium:appActivity", device.appActivity);
                        capabilities.setCapability("appium:appPackage", device.appPackage);
                    }
                    return capabilities;
                } else {
                    logger.error("Devices list is empty");
                    throw new RuntimeException("Список устройств пуст");
                }
            case IOS:
                return null; //для доработки
        }
        return capabilities;
    }
}

