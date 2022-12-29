package utils;

import config.devices.Device;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.XMLConstants;
import java.io.File;
import java.io.IOException;
import java.util.*;

public class ConfigReader {



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
                device.platformName = element.getElementsByTagName("platformName").item(0).getTextContent();
                device.deviceName = element.getElementsByTagName("deviceName").item(0).getTextContent();
                device.appActivity = element.getElementsByTagName("appActivity").item(0).getTextContent();
                device.automationName = element.getElementsByTagName("automationName").item(0).getTextContent();
                device.platformVersion = element.getElementsByTagName("platformVersion").item(0).getTextContent();
                device.appPackage = element.getElementsByTagName("appPackage").item(0).getTextContent();
                devices.put(device.deviceId, device);
            }
            return devices;
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
