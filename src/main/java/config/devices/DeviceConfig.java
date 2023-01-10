package config.devices;

import base.TestBase;
import io.cucumber.java.hu.De;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import java.util.Map;

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
            case "android":
                Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/androidDevices.xml");
                if (devices != null && devices.size() != 0) {
                    Device device = devices.get(deviceName);
                    capabilities.setCapability("deviceName", device.deviceName);
                    capabilities.setCapability("platformName", device.platformName);
                    capabilities.setCapability("platformVersion", device.platformVersion);
                    capabilities.setCapability("automationName", device.automationName);
                    capabilities.setCapability("clearSystemFiles", "true");
                    capabilities.setCapability("autoGrantPermissions", "true");
                    capabilities.setCapability("udid", device.udid);
                    if (device.isEmulator.contains("yes")) {
                        capabilities.setCapability("app", System.getProperty("user.dir") + device.app);
                        capabilities.setCapability("waitAppPackage", device.waitAppPackage);
                        capabilities.setCapability("appWaitActivity", device.appWaitActivity);
                    }
                    else {
                        capabilities.setCapability("appActivity", device.appActivity);
                        capabilities.setCapability("appPackage", device.appPackage);
                    }
                    return capabilities;
                } else {
                    logger.error("Devices list is empty");
                    throw new RuntimeException("Список устройств пуст");
                }
            case "ios":
                return null; //для доработки
        }
        return capabilities;
    }
}

