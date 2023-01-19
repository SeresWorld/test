package utils;

import base.TestBase;
import config.devices.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.Assert;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.XMLConstants;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;


/**
 * Класс ConfigReader содержит в себе методы для считывания конфигураций для глобальных свойств и свойств девайсов.
 */
public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(TestBase.class);

    public static Properties properties;
    public static String appiumJSPath;
    public static String serverIp;
    public static String logLevel;
    public static int systemPort;

    // Считывание свойств из config.properties

    static {
        File globalConfigFile = new File("src/test/java/config.properties");

        Properties globalProperties = new Properties();

        try {
            globalProperties.load(new FileInputStream(globalConfigFile));

            properties = new Properties();
            properties.putAll(globalProperties);

        } catch (IOException e) {
            logger.error("Error during open config file");
            Assert.fail("Error open config file.\n" + e.getMessage());
        }

        serverIp = properties.getProperty("SERVER_IP");
        appiumJSPath = properties.getProperty("APPIUM_JS_PATH");
        logLevel = properties.getProperty("LOG_LEVEL");
        systemPort = Integer.parseInt(properties.getProperty("SYSTEM_PORT"));

    }

    /**
     * В зависимости от переданного пути до файла конфигурации, метод считывает в нем все доступные capabilities
     * для каждого устройства. Если для capabilities устройства существует признак isEmulator = yes, считывается
     * альтернативный набор capabilities.
     * @param xmlPath путь до файла конфигурации формата .xml
     * @return возвращает маппер вида "НазваниеУстройства: ПараметрыУстройства" с capabilities для всех найденных
     * устройств.
     */
    public static Map<String, Device> xmlReader(String xmlPath) {
        Map<String, Device> devices = new HashMap<>();
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            dbf.setFeature(XMLConstants.FEATURE_SECURE_PROCESSING, true);

            DocumentBuilder db = dbf.newDocumentBuilder();

            Document doc = db.parse(new File(xmlPath));

            NodeList deviceList = doc.getElementsByTagName("device");

            for (int i = 0; i < deviceList.getLength(); i++) {
                Node node = deviceList.item(i);
                Element element = (Element) node;

                Device device = new Device();
                device.deviceId = element.getAttribute("id");
                device.isEmulator = Boolean.parseBoolean(element.getElementsByTagName("isEmulator").item(0).getTextContent());
                device.platformName = element.getElementsByTagName("platformName").item(0).getTextContent();
                device.deviceName = element.getElementsByTagName("deviceName").item(0).getTextContent();
                device.automationName = element.getElementsByTagName("automationName").item(0).getTextContent();
                device.platformVersion = element.getElementsByTagName("platformVersion").item(0).getTextContent();
                device.udid = element.getElementsByTagName("udid").item(0).getTextContent();
                device.serverPort = element.getElementsByTagName("serverPort").item(0).getTextContent();

                if (device.isEmulator) {
                    device.app = element.getElementsByTagName("app").item(0).getTextContent();
                    device.waitAppPackage = element.getElementsByTagName("waitAppPackage").item(0).getTextContent();
                    device.appWaitActivity = element.getElementsByTagName("appWaitActivity").item(0).getTextContent();
                } else {
                    device.appActivity = element.getElementsByTagName("appActivity").item(0).getTextContent();
                    device.appPackage = element.getElementsByTagName("appPackage").item(0).getTextContent();
                }

                devices.put(device.deviceId, device);
            }
            return devices;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            logger.error("Error during collecting capabilities");
            e.printStackTrace();
        }
        return null;
    }




}
