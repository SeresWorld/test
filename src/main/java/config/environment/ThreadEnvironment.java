package config.environment;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

/**
 * Класс ThreadEnvironment содержит все драйверы для дальнейшего использования.
 */

public class ThreadEnvironment {

    private static AppiumDriver<MobileElement> driver;
    private static AndroidDriver<MobileElement> androidDriverInstance;
    private static IOSDriver<MobileElement> iosDriverInstance;
    public static AppiumDriver<MobileElement> getAppiumDriverInstance() {
        return driver;
    }
    public static AndroidDriver<MobileElement> getAndroidDriverInstance() {
        return ThreadEnvironment.androidDriverInstance;
    }
    public static IOSDriver<MobileElement> getIosDriverInstance() {
        return ThreadEnvironment.iosDriverInstance;
    }

    public static void setAndroidDriverInstance(AndroidDriver<MobileElement> androidDriverInstance) {
        ThreadEnvironment.androidDriverInstance = androidDriverInstance;
    }

    public static void setIosDriverInstance(IOSDriver<MobileElement> iosDriverInstance) {
        ThreadEnvironment.iosDriverInstance = iosDriverInstance;
    }
}
