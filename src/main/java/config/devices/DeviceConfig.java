package config.devices;

import org.openqa.selenium.remote.DesiredCapabilities;
import utils.ConfigReader;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.fail;

public class DeviceConfig {

    public static Map<String, DesiredCapabilities> getCaps(String platform) {


        Map<String, DesiredCapabilities> capabilitiesList = new HashMap<>();

        switch (platform) {
            case "android":
                Map<String, Device> devices = ConfigReader.xmlReader("src/main/resources/androidDevices.xml");
                if (devices != null && devices.size() != 0) {
                    for (Map.Entry<String, Device> entry: devices.entrySet()){
                        DesiredCapabilities capabilities = new DesiredCapabilities();
                        capabilities.setCapability("deviceName", entry.getValue().deviceName);
                        capabilities.setCapability("platformName", entry.getValue().platformName);
                        capabilities.setCapability("platformVersion", entry.getValue().platformVersion);
                        capabilities.setCapability("appActivity", entry.getValue().appActivity);
                        capabilities.setCapability("appPackage", entry.getValue().appPackage);
                        capabilities.setCapability("automationName", entry.getValue().automationName);
                        capabilities.setCapability("clearSystemFiles", "true");
                        capabilities.setCapability("autoGrantPermissions","true");
                        capabilitiesList.put(entry.getKey(), capabilities);
                    }
                } else {
                    throw new RuntimeException("Список устройств пуст");
                }
                break;
            case "ios":
                return null; //для доработки
        }
        return capabilitiesList;
    }
}

