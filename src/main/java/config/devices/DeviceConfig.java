package config.devices;

import base.TestBase;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;
import io.appium.java_client.ios.options.simulator.Permissions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.ConfigReader;

import java.time.Duration;
import java.util.Map;


/**
 * Класс DeviceConfig собирает capabilities из файла конфигурации с помощью XMLReader (пакет utils)
 * и возвращает список готовых наборов capabilities для передаваемого названия устройства.
 *
 * @see ConfigReader#xmlReader(String)
 */

public class DeviceConfig {
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    public static UiAutomator2Options getAndroidCaps(String deviceName) {
        var options = new UiAutomator2Options();

        Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/Devices.xml");
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
            } else {
                options.setAppActivity(device.appActivity)
                        .setAppPackage(device.appPackage);
            }
            return options;
        }
        return options;
    }

    public static XCUITestOptions getIOSCaps(String deviceName) {
        var xcuiTestOptions = new XCUITestOptions();
        Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/Devices.xml");

        if (devices != null && devices.size() != 0) {
            Device device = devices.get(deviceName);

            xcuiTestOptions.setDeviceName(device.deviceName)
                    .setPlatformName(device.platformName)
                    .setPlatformVersion(device.platformVersion)
                    .setAutomationName(device.automationName);
            if (device.isEmulator) {
                xcuiTestOptions.setBundleId(device.bundleId)
                        // после каждого теста приложение не переустанавливается
                        .setNoReset(true)
                        // после каждого теста приложение закрывается
                        .setShouldTerminateApp(true)
                        // автоматическое принятие разрешения на геолокацию
                        .setPermissions(new Permissions("{'kz.bcc.starbankingtest':{'location':'always'}}"));


            } else {
                // TODO
            }
            return xcuiTestOptions;
        }
        return xcuiTestOptions;
    }

    /**
     * Получение порта устройства из файла xml
     *
     * @param deviceName название устройства
     * @return порт устройства в виде строки
     */
    public static String getSystemPort(String deviceName) {

        Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/Devices.xml");
        String devicePort;
        try {
            Device device = devices.get(deviceName);
            devicePort = device.serverPort;
        } catch (NullPointerException ex) {
            logger.error("Device" + deviceName + "is not found");
            throw ex;
        }

        return devicePort;
    }

    public static String getPlatformName(String deviceName) {
        Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/Devices.xml");
        String platformName;
        try {
            Device device = devices.get(deviceName);
            platformName = device.platformName;
        } catch (NullPointerException ex) {
            logger.error("Device" + deviceName + "is not found");
            throw ex;
        }

        return platformName;

    }
}

