package hubTesting.steps.qmetryStepClass;

import com.qmetry.qaf.automation.step.QAFTestStep;
import hubTesting.steps.qmetryStepClass.globalSteps.GlobalSteps;

import static com.qmetry.qaf.automation.step.CommonStep.*;

public class QmetrySteps {

    /**
     *
     */
    @QAFTestStep(description = "go to URL", stepName = "go to URL")
    public static void login() {
        get("");
        new GlobalSteps().checkPageIsReady();
    }
}
