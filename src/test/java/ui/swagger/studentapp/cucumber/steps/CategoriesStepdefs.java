package ui.swagger.studentapp.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ui.swagger.studentinfo.CategoriesStep;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class CategoriesStepdefs {
    static ValidatableResponse response;
    static String name = "Flower" + TestUtils.getRandomValue();
    static String id = "rose987" + TestUtils.getRandomValue();
    static String categoryID;

    @Steps
    CategoriesStep categoriesStep;

    @When("^User sends a GET request to list endpoint$")
    public void userSendsAGETRequestToListEndpoint() {
        response = categoriesStep.getAllDataInfo();
    }

    @Then("^User must get back a valid status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^User sends a POST request to create endpoint$")
    public void userSendsAPOSTRequestToCreateEndpoint() {
        response = categoriesStep.createCategory(name, id);
        categoryID = response.log().all().extract().path("id");
        System.out.println(categoryID);
    }


    @Then("^User must get back a validatable status code 201$")
    public void userMustGetBackAValidableStatusCode() {
        response.statusCode(201);

    }

    @When("^User sends a GET request to get endpoint$")
    public void userSendsAGETRequestToGetEndpoint() {
        HashMap<String, ?> categoryMap = categoriesStep.getCategoryInfoByName(categoryID);
        Assert.assertThat(categoryMap, hasValue(name));
        System.out.println(categoryMap);
    }

    @Then("^User successfully added id in best by application and status code is 201$")
    public void userSuccessfullyAddedIdInBestByApplicationAndStatusCodeIs() {
        response.statusCode(201);
    }

    @When("^User sends a PATCH request to get endpoint$")
    public void userSendsAPATCHRequestToGetEndpoint() {
        name = name + "_updated";
        categoriesStep.updatingCategory(categoryID, name, id);
        HashMap<String, ?> productList = categoriesStep.getCategoryInfoByName(categoryID);
        Assert.assertThat(productList, hasValue(name));
        System.out.println(productList);

    }

    @Then("^User should update single data successfully with status code 201$")
    public void userShouldUpdateSingleDataSuccessfullyWithStatusCode() {
        response.statusCode(201);
    }

    @When("^User sends a Delete request to get endpoint$")
    public void userSendsADeleteRequestToGetEndpoint() {
        categoriesStep.deleteCategory(categoryID);
    }

    @Then("^User should delete category id successfully with status code 200$")
    public void userShouldDeleteCategoryIdSuccessfullyWithStatusCode() {
        response.statusCode(200);
    }

    @And("^User check delete should not exist in category list$")
    public void userCheckDeleteShouldNotExistInCategoryList() {
        categoriesStep.getCategoryByID(categoryID);
    }

    @And("^User check status code 404$")
    public void userCheckStatusCode() {
       response.statusCode(404);
    }


}
