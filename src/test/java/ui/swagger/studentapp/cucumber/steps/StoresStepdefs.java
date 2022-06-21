package ui.swagger.studentapp.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ui.swagger.studentinfo.StoresStep;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class StoresStepdefs {
    static ValidatableResponse response;
    static String name = "Selenium Master" + TestUtils.getRandomValue();
    static String type = "Star Student" + TestUtils.getRandomValue();
    static String address = "Star Galaxy";
    static String address2 = "Andromeda Galaxy";
    static String city = "Ahmedabad";
    static String state = "Gujarat";
    static String zip = "895674";
    static int lat = 89;
    static int lng = 12;
    static String hours = "24 hours";

    static int storeID;

    @Steps
    StoresStep storesSteps;

    @When("^User send GET request to store list endpoint$")
    public void userSendGETRequestToStoreListEndpoint() {
        response=storesSteps.getAllStoresListInfo();

    }

    @Then("^User must get status code 200$")
    public void userMustGetStatusCode() {
        response.statusCode(200);
    }

    @When("^User send a POST request to create store list endpoint$")
    public void userSendAPOSTRequestToCreateStoreListEndpoint() {
        HashMap<Object, Object> servicesData = new HashMap<>();
        ValidatableResponse response = storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours,servicesData);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }

    @Then("^User must get a validate status code 201$")
    public void userMustGetAValidateStatusCode() {
        response.statusCode(201);
    }

    @When("^User sends a GET request to get store list endpoint$")
    public void userSendsAGETRequestToGetStoreListEndpoint() {
        HashMap<String, ?> storeMap = storesSteps.getStoreInfoByName(storeID);
        Assert.assertThat(storeMap, hasValue(name));
        System.out.println(storeMap);
    }

    @Then("^User successfully added id in store list and status code is 200$")
    public void userSuccessfullyAddedIdInStoreListAndStatusCodeIs() {
        response.statusCode(200);
    }

    @When("^User sends a PATCH request to update store list endpoint$")
    public void userSendsAPATCHRequestToUpdateStoreListEndpoint() {
        name = name + "_updated";
        HashMap<Object, Object> servicesData = new HashMap<>();
        storesSteps.updateStore(storeID,name, type, address, address2, city, state, zip, lat, lng, hours,servicesData);
        HashMap<String, ?> productList = storesSteps.getStoreInfoByName(storeID);
        Assert.assertThat(productList, hasValue(name));
        System.out.println(productList);
    }

    @Then("^User should update single data in store list successfully with status code 200$")
    public void userShouldUpdateSingleDataInStoreListSuccessfullyWithStatusCode() {
        response.statusCode(200);

    }

    @When("^User sends a Delete request to delete store endpoint$")
    public void userSendsADeleteRequestToDeleteStoreEndpoint() {
        storesSteps.deleteStore(storeID);

    }

    @Then("^User should delete services id from store list successfully with status code 200$")
    public void userShouldDeleteServicesIdFromStoreListSuccessfullyWithStatusCode() {
        response.statusCode(200);
    }

    @And("^User check delete services id should not more exist in store list$")
    public void userCheckDeleteServicesIdShouldNotMoreExistInStoreList() {
        storesSteps.getStoreByID(storeID);
    }

    @And("^User check deleted id have status code should be 404$")
    public void userCheckDeletedIdHaveStatusCodeShouldBe() {
        response.statusCode(404);
    }
}
