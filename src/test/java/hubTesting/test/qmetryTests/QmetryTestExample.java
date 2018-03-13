package hubTesting.test.qmetryTests;

import com.qmetry.qaf.automation.core.MessageTypes;
import com.qmetry.qaf.automation.testng.dataprovider.QAFDataProvider;
import com.qmetry.qaf.automation.ui.WebDriverTestBase;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import java.util.Map;

import static com.qmetry.qaf.automation.step.CommonStep.*;
import static com.qmetry.qaf.automation.util.Reporter.log;
import static hubTesting.steps.qmetryStepClass.QmetrySteps.*;
import static hubTesting.steps.qmetryStepClass.globalSteps.GlobalSteps.*;

public class QmetryTestExample {

    @AfterMethod
    public void closeLastTest() {
        new WebDriverTestBase().tearDown();
    }

    @QAFDataProvider(dataFile = "resources/TestData/qmetryTestData/testDataFile.json")
    @Test(description = "This is the first test of the Qmetry Documentation Website")
    public void checkFields(Map<String, String> data) {
        login();
        try {
            assertVisible("githubrepo.button");
        } catch (AssertionError e) {
            e.printStackTrace();
        }
        refreshPage();
        waitForVisible("qafreporting.menubutton");
        click("qafreporting.menubutton");
        waitForVisible("qafreporting.button");
        click("qafreporting.button");
        waitForVisible("qafreporting.header");
        try {
            assertText("qafreporting.header", data.get("Header"));
        } catch (AssertionError e) {
            e.printStackTrace();
        }
    }

    @QAFDataProvider(dataFile = "resources/TestData/qmetryTestData/testDataFile.json")
    @Test(description = "This test enters a value in the search bar, then closes the page")
    public void checkSearchField(Map<String, String> data) {
        login();
        click("input.field");
        sendKeys(data.get("Search"), "input.field");
        try {
            assertAttribute("input.field", "type", "text");
        } catch (AssertionError e) {
            e.printStackTrace();
        }

        try {
            assertAttribute("input.field", "id", "search-input");
        } catch (AssertionError e) {
            e.printStackTrace();
        }

        try {
            assertAttribute("input.field", "style", "background: rgb(255, 255, 255");
        } catch (AssertionError e) {
            e.printStackTrace();
            log("this failed because the input.field element has a style attribute, but the background value has to be aserted using assertCssStyle - see next assertion", MessageTypes.Fail);
        }

        try {
            assertCssStyle("input.field","background" ,"rgb(255, 255, 255) none repeat scroll 0% 0% / auto padding-box border-box");
        } catch (AssertionError e) {
            e.printStackTrace();
        }

    }

    @QAFDataProvider(dataFile = "resources/TestData/qmetryTestData/testDataFile.json")
    @Test(description = "This test is validating the URL of the page")
    public void checkURL() {
        login();
        waitForAjaxToComplete();
        validateURL( "https://qmetry.github.io/qaf/latest/docs.html/");

    }
}