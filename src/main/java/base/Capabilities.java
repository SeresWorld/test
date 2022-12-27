package base;

import org.openqa.selenium.remote.DesiredCapabilities;

public class Capabilities {
    public DesiredCapabilities localAndroid() {

        DesiredCapabilities caps = new DesiredCapabilities();

        caps.setCapability("deviceName", "Redmi Note 10S");
        caps.setCapability("platformName", "Android");
        caps.setCapability("platformVersion", "11.0");
        caps.setCapability("appActivity", "kz.bcc.starbanking.ui.screens.splash.SplashActivity");
        caps.setCapability("appPackage", "kz.bcc.starbanking.stage");
        caps.setCapability("automationName", "UiAutomator2");

        return caps;
    }

    public DesiredCapabilities localIOS() {

        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("deviceName", "iPhone 11");
        caps.setCapability("platformName", "iOS");
        caps.setCapability("platformVersion", "15.5");
        caps.setCapability("automationName", "XCUITest");
        caps.setCapability("app", System.getProperty("user.dir") + "/apps/DailyCheck.app");
        /*caps.setCapability("bundleId", "kz.bcc.starbanking");*/ // Бандл для эмулятора

        return caps;
    }
}
