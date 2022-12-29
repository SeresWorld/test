package base;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;

import java.net.MalformedURLException;
import java.net.URL;

public class DriverPool {
    private static AndroidDriver<MobileElement> androidDriverInstance;
    private static IOSDriver<MobileElement> iosDriverInstance;

    public static AndroidDriver<MobileElement> getAndroidDriverInstance() {
        return DriverPool.androidDriverInstance;
    }
    public static IOSDriver<MobileElement> getIosDriverInstance() {
        return DriverPool.iosDriverInstance;
    }

    public static void setAndroidDriverInstance(AndroidDriver<MobileElement> androidDriverInstance) {
        DriverPool.androidDriverInstance = androidDriverInstance;
    }

    public static void setIosDriverInstance(IOSDriver<MobileElement> iosDriverInstance) {
        DriverPool.iosDriverInstance = iosDriverInstance;
    }
}
