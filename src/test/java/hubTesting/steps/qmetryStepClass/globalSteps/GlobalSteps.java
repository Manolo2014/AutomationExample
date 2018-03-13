package hubTesting.steps.qmetryStepClass.globalSteps;

import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.step.QAFTestStep;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import com.qmetry.qaf.automation.util.Reporter;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static com.qmetry.qaf.automation.step.CommonStep.*;
import static com.qmetry.qaf.automation.util.Reporter.log;
import static com.qmetry.qaf.automation.util.Reporter.logWithScreenShot;


public class GlobalSteps {

    public static Date currentDateTimeStampDate;
    public static String currentDateTimeStampString;



//    WebDriver driver;

    @QAFTestStep(description = "This step checks if the page JavaScript has finished loading", stepName = "check page load")
    public void checkPageIsReady() {
        WebDriver thisDriver = new WebDriverTestBase().getDriver().getUnderLayingDriver();

        JavascriptExecutor js = (JavascriptExecutor) thisDriver;

//        Initially bellow given if condition will check ready state of page.
        if (js.executeScript("return document.readyState").toString().equals("complete")) {
            Reporter.log("Page Is loaded.", MessageTypes.TestStepPass);
            return;
        }

        //This loop will rotate for 25 times to check If page Is ready after every 1 second.
        //You can replace your value with 25 If you wants to Increase or decrease wait time.
        for (int i = 0; i < 25; i++) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            //To check page ready state.
            if (js.executeScript("return document.readyState").toString().equals("complete")) {
                break;
            }
        }
    }

    @QAFTestStep(description = "Page is Refreshed", stepName = "Refresh page")
    public static void refreshPage() {
        WebDriver thisDriver = new WebDriverTestBase().getDriver().getUnderLayingDriver();
        thisDriver.navigate().refresh();

    }

    @QAFTestStep(description = "User is taken to new Tab", stepName = "go to a new Tab")
    public static void goToNewTab() throws Exception {
        WebDriver thisDriver = new WebDriverTestBase().getDriver().getUnderLayingDriver();
        JavascriptExecutor js = (JavascriptExecutor) thisDriver;
        js.executeScript("window.open();");

    }

    @QAFTestStep(description = "Get Page Source", stepName = "Get Page Source")
    public static void getPageSource() {
        WebDriver thisDriver = new WebDriverTestBase().getDriver().getUnderLayingDriver();
        String pageSource = thisDriver.getPageSource();
        Reporter.log(pageSource, MessageTypes.Info);
    }

    /**
     * @param homeURL : homeURL is the value that should be compared with the current URL
     */
    @QAFTestStep(description = "Validate that the current URL matches another value", stepName = "validate URL")
    public static void validateURL( String homeURL) {
        String currentURL = new WebDriverTestBase().getDriver().getCurrentUrl();
        Reporter.logWithScreenShot("The Current URL is " + currentURL, MessageTypes.Info);
        try {
            Assert.assertEquals(currentURL, homeURL);
        } catch (AssertionError e) {
            e.printStackTrace();
            Reporter.logWithScreenShot("the current URL " + currentURL + " does not match the expected URL: " + homeURL, MessageTypes.Fail);
        }
    }


    /**
     * @param stringPattern     : This is the pattern that should be used to format the Date String value: Pattern uses Java Date Time format - example: "EEE MMM d k:mm:ss z YYYY", see http://www.java2s.com/Tutorials/Java/Java_Date_Time/0150__Java_Custom_Date_Format_Patterns.htm
     * @param datePattern     : This is the pattern that should be used to format the Date Date value: datePattern uses Java Date Time format - example: "EEE MMM u k:mm:ss z YYYY", see http://www.java2s.com/Tutorials/Java/Java_Date_Time/0150__Java_Custom_Date_Format_Patterns.htm
     */
    @QAFTestStep(description = "Gets the current date in a Date Value", stepName = "Get Current Date as a Date Value")
    public static Date getCurrentDateTimeDate(String stringPattern, String datePattern) {

        //Get Current Time and Format as a String
        currentDateTimeStampString = ZonedDateTime.now(ZoneId.of("US/Mountain")).format(DateTimeFormatter.ofPattern(stringPattern));

        try {
            //change current datetime from a String to a Date value
            SimpleDateFormat stringToDateFormat = new SimpleDateFormat(datePattern);
            currentDateTimeStampDate = stringToDateFormat.parse(currentDateTimeStampString);
            log("Current Date Time as a Date is: " + currentDateTimeStampDate, MessageTypes.Info);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return currentDateTimeStampDate;

    }


    /**
     * @param pattern      : This is the pattern that should be used to format the Date String value: Pattern uses Java Date Time format - example: "EEE MMM d k:mm:ss z YYYY", see http://www.java2s.com/Tutorials/Java/Java_Date_Time/0150__Java_Custom_Date_Format_Patterns.htm
     *
     */
    @QAFTestStep(description = "Gets the current date in a String Value", stepName = "Get Current Date as a String Value")
    public static String getCurrentDateTimeString(String pattern) {

        //Get Current Time and Format as a String
            currentDateTimeStampString = ZonedDateTime.now(ZoneId.of("US/Mountain")).format(DateTimeFormatter.ofPattern(pattern));
            log("Current Date Time as a String is: " + currentDateTimeStampString, MessageTypes.Info);

            return currentDateTimeStampString;
    }

    /**
     * @param fieldErrMsg   : this is the location of the field level error message being validated
     * @param errMsgTxt     : this is the text of the actual Field Level Error Message
     * @param prop          : this is the property that is being validated in this field level error message
     * @param ffValue       : this is the value that is validated for Firefox Browsers
     * @param chValue       : this is the value that is validated for Chrome Browsers
     */
    @QAFTestStep(description = "this validates location : {0} property {1} as {3} for Firefox or {4} for Chrome and text as {1}", stepName = "Validate Field Level Error Message")
    public static void validateErrMsgDsply(String fieldErrMsg,String prop, String ffValue, String chValue, String errMsgTxt) {
        if (BrowserVersion.browserName.equalsIgnoreCase("firefox")) {
            try {
                assertCssStyle(fieldErrMsg, prop, ffValue);
            } catch (AssertionError e) {
                e.printStackTrace();
            }
        } else if (BrowserVersion.browserName.equalsIgnoreCase("chrome")) {
            try {
                assertCssStyle(fieldErrMsg, prop, chValue);
            } catch (AssertionError e) {
                e.printStackTrace();
            }
        } else {
            BrowserVersion.getBrowserName();
            String browserName = BrowserVersion.getBrowserName();
            Reporter.log("This Browser is not handled in UsersAndGroupsSteps: " + browserName, MessageTypes.TestStepFail);
        }

        try {
            assertText(fieldErrMsg, errMsgTxt);
            logWithScreenShot(fieldErrMsg + " field level error message content is correct", MessageTypes.Info);
        } catch (AssertionError e) {
            e.printStackTrace();
            logWithScreenShot(fieldErrMsg + " field level error message content is incorrect", MessageTypes.TestStepFail);
        }
    }
}


