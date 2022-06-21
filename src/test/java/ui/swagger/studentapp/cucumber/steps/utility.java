package ui.swagger.studentapp.cucumber.steps;

import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import ui.swagger.studentinfo.UtilityStep;

public class utility {
    @Steps
    UtilityStep utilityStep;
    static ValidatableResponse response;

    @When("^User send a GET request to check version of application$")
    public void userSendAGETRequestToCheckVersionOfApplication() {
         response = utilityStep.gettingVersion();
    }

    @Then("^User must get return status code 200$")
    public void userMustGetReturnStatusCode() {
        response.statusCode(200);
    }

    @When("^User send a GET request to  Helth check of application$")
    public void userSendAGETRequestToHelthCheckOfApplication() {
        response = utilityStep.gettingHealthCheck();
    }

    @Then("^User must check status code 200$")
    public void userMustCheckStatusCode() {
        response.statusCode(200);
    }
}
