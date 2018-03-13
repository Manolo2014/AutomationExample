package hubTesting.steps.qmetryStepClass.globalSteps;

import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.ui.webdriver.QAFWebElement;
import com.qmetry.qaf.automation.util.Reporter;

import java.util.List;

import static com.qmetry.qaf.automation.step.CommonStep.*;

public class FindGlobalElements {
    /**
     * @param location
     * @param listSearchedFor
     *            : location is the location that will be checked and listSearchedFor is the name of the element list we are searching for
     */
    @QAFTestStep(description = "User checks: {1} for: {0}", stepName = "check: {0} for: {1}")
    public static void findListOfElements(String location, String listSearchedFor) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<QAFWebElement> elements = new WebDriverTestBase().getDriver().findElements(location);
        int size = elements.size();
        Reporter.logWithScreenShot("There are " + size + " " + listSearchedFor + " found", MessageTypes.Info);
        if (size > 1) {
            while (size > 1) {
                Reporter.logWithScreenShot("There are multiple " + listSearchedFor + " on the page", MessageTypes.Info);
                elements.get(0).click();
            }
        } else if (size == 1) {
            Reporter.logWithScreenShot("There is only one " + listSearchedFor + " on the page", MessageTypes.Info);
            elements.get(0).click();
        } else {
        }

    }

    /**
     * @param location
     * @param action
     *            : location is the location that will be checked and action is the element that will be clicked if the location exists
     */
    @QAFTestStep(description = "User checks: {1} for: {0}", stepName = "check: {0} for: {1}")
    public static void actionIfListExists(String location, String action) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<QAFWebElement> elements = new WebDriverTestBase().getDriver().findElements(location);
        int size = elements.size();
        Reporter.logWithScreenShot("the element: " + location + ",  was  found" + size +  " times", MessageTypes.Info);
        if (size != 0) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            click(action);
            Reporter.logWithScreenShot("the element: " + location + ",  was  found and " + action + " was clicked", MessageTypes.Info);

        } {

        }

    }

    //TODO method will fail if the feature is not present, so perhaps a better option is to use the findListOfElements() method instead?? similar to waitForUhOh - waitForWifi, waitForFirewall??
    private static boolean featureIsPresent;

    /**
     * @param location : location is the parameter name for the location that will be checked
     */
    @QAFTestStep(description = "This step checks if the {0} is present and if so, clicks on the button")
    public static boolean ifPresentGoToFeature(String location) {
        if (verifyPresent(location)) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            click(location);
            Reporter.logWithScreenShot("feature " + location + " is present, so user is taken to " + location, MessageTypes.Info);
            featureIsPresent = true;
        } else {
            Reporter.logWithScreenShot("feature " + location + " is not present, so user is not taken to " + location, MessageTypes.Info);
            featureIsPresent = false;
        }
        return featureIsPresent;
    }

}