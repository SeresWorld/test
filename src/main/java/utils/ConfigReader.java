package utils;

import base.TestBase;
import config.devices.Device;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.XMLConstants;
import java.io.File;
import java.io.IOException;
import java.util.*;


/**
 * Класс ConfigReader содержит в себе метод для считывания данных конфигурации об устройстве из файла .xml.
 */
public class ConfigReader {
    private static final Logger logger = LogManager.getLogger(TestBase.class);

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
