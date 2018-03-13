package hubTesting.steps.qmetryStepClass.globalSteps;

import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Reporter;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

public class BrowserVersion {
    private static WebDriver thisDriver = new WebDriverTestBase().getDriver().getUnderLayingDriver();

    public static Capabilities caps = ((RemoteWebDriver) thisDriver).getCapabilities();
    //Get Browser name
    public static String browserName = caps.getBrowserName();
    //Get Browser Version
    public static String browserVersion = caps.getVersion();
    //Get OS name
    public static String os = System.getProperty("os.name").toLowerCase();

    public static String getBrowserName() {
        Reporter.log("Browser Name = " + browserName,MessageTypes.Info);
        if (browserName.equalsIgnoreCase("firefox")) {
            return browserName;
        } else if (browserName.equalsIgnoreCase("chrome")) {
            return browserName;
        } else if (browserName.equalsIgnoreCase("internet explorer")) {
            return browserName;
        } else
        return "unknown browser";
    }

    public static void getBrowserVersion() {
        Reporter.log("Browser Version = " + browserVersion,MessageTypes.Info);
    }

    public static void getOSName() {
        Reporter.log("OS = " + os,MessageTypes.Info);
    }

    public static void getBrowserAndOs() {
        Reporter.log("OS = " + os + ", Browser = " + browserName + " "+ browserVersion,MessageTypes.Info);
    }

}