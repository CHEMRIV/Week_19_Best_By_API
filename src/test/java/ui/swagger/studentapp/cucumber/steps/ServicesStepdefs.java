package ui.swagger.studentapp.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ui.swagger.studentinfo.ServicesStep;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ServicesStepdefs {
    static String name = "API Master" + TestUtils.getRandomValue();
    static int serviceID;
    static ValidatableResponse response;

    @Steps
    ServicesStep servicesSteps;


    @When("^User sendsa GET request to services list endpoint$")
    public void userSendsaGETRequestToServicesListEndpoint() {
        response=servicesSteps.getAllServicesListInfo();
    }

    @Then("^User should get status code 200$")
    public void userShouldGetStatusCode() {
        response.statusCode(200);
    }

    @When("^User sends a POST request to create services list endpoint$")
    public void userSendsAPOSTRequestToCreateServicesListEndpoint() {
        response = servicesSteps.createService(name);
        serviceID = response.log().all().extract().path("id");
        System.out.println(serviceID);
    }

    @Then("^User must get back a validate status code 201$")
    public void userMustGetBackAValidateStatusCode() {
        response.statusCode(201);
    }

    @When("^User sends a GET request to get services list endpoint$")
    public void userSendsAGETRequestToGetServicesListEndpoint() {
        HashMap<String, ?> storeMap = servicesSteps.getServiceInfoByName(serviceID);
        Assert.assertThat(storeMap, hasValue(name));
        System.out.println(storeMap);
    }

    @Then("^User successfully added id in services list and status code is 200$")
    public void userSuccessfullyAddedIdInServicesListAndStatusCodeIs() {
        response.statusCode(200);
    }

    @When("^User sends a PATCH request to update services list endpoint$")
    public void userSendsAPATCHRequestToUpdateServicesListEndpoint() {
        name = name + "_updated";
        HashMap<Object, Object> servicesData = new HashMap<>();
        servicesSteps.updateService(serviceID,name);
        HashMap<String, ?> productList = servicesSteps.getServiceInfoByName(serviceID);
        Assert.assertThat(productList, hasValue(name));
        System.out.println(productList);
    }

    @Then("^User should update single data in services list successfully with status code 200$")
    public void userShouldUpdateSingleDataInServicesListSuccessfullyWithStatusCode() {
        response.statusCode(200);
    }

    @When("^User sends a Delete request to delete services endpoint$")
    public void userSendsADeleteRequestToDeleteServicesEndpoint() {
        servicesSteps.deleteService(serviceID);

    }

    @Then("^User should delete services id from services list successfully with status code 200$")
    public void userShouldDeleteServicesIdFromServicesListSuccessfullyWithStatusCode() {
        response.statusCode(200);
    }

    @And("^User check delete services id should not more exist in category list$")
    public void userCheckDeleteServicesIdShouldNotMoreExistInCategoryList() {
         servicesSteps.getServiceByID(serviceID);
    }

    @And("^User finally check status code should be 404$")
    public void userCheckStatusCodeShouldBe() {
        response.statusCode(404);

    }
}
