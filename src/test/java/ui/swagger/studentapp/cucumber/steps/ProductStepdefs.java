package ui.swagger.studentapp.cucumber.steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.ValidatableResponse;
import net.thucydides.core.annotations.Steps;
import org.junit.Assert;
import ui.swagger.studentinfo.ProductsStep;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

public class ProductStepdefs {
    static String name = "Calamine" + TestUtils.getRandomValue();
    static String type = "Cream" + TestUtils.getRandomValue();
    static Integer price = 8;
    static Integer shipping = 86;
    static String upc = "8563235";
    static String description = "Available in stock";
    static String manufacturer = "Torrent Power";
    static String model = "WD-896566";
    static String url = "www.product.com.in";
    static String image = "png.85663233";
    static int productID;

    static ValidatableResponse response;

    @Steps
    ProductsStep productsSteps;

    @When("^User sends a GET request to product list endpoint$")
    public void userSendsAGETRequestToProductListEndpoint() {
        response=productsSteps.getAllProductListInfo();

    }

    @Then("^User should get back a valid status code 200$")
    public void userShouldGetBackAValidStatusCode() {
        response.statusCode(200);
    }

    @When("^User sends a POST request to create product list endpoint$")
    public void userSendsAPOSTRequestToCreateProductListEndpoint() {
        response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        productID = response.log().all().extract().path("id");
        System.out.println(productID);
    }

    @Then("^User must get back a validable status code 200$")
    public void userMustGetBackAValidStatusCode() {
        response.statusCode(201);
    }

    @When("^User sends a GET request to get product list endpoint$")
    public void userSendsAGETRequestToGetProductListEndpoint() {
        HashMap<String, ?> productMap = productsSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @Then("^User successfully added id in product list and status code is 201$")
    public void userSuccessfullyAddedIdInProductListAndStatusCodeIs() {
        response.statusCode(201);
    }

    @When("^User sends a PATCH request to update product list endpoint$")
    public void userSendsAPATCHRequestToUpdateProductListEndpoint() {
        name = name + "_updated";
        productsSteps.updatingProduct(productID,name, type, price, shipping, upc, description, manufacturer, model, url, image);
        HashMap<String, ?> productMap = productsSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @Then("^User should update single data in product list successfully with status code 201$")
    public void userShouldUpdateSingleDataInProductListSuccessfullyWithStatusCode() {
        response.statusCode(201);
    }

    @When("^User sends a Delete request to delete endpoint$")
    public void userSendsADeleteRequestToDeleteEndpoint() {
        productsSteps.deleteProduct(productID);
    }

    @Then("^User should delete category id from product list successfully with status code 200$")
    public void userShouldDeleteCategoryIdFromProductListSuccessfullyWithStatusCode() {
       response.statusCode(200);
    }

    @And("^User check delete should not more exist in category list$")
    public void userCheckDeleteShouldNotMoreExistInCategoryList() {
        productsSteps.getProductByID(productID);
    }

    @And("^User check status code should be 404$")
    public void userCheckStatusCodeShouldBe() {
        response.statusCode(404);
    }
}

