package ui.swagger.studentapp.restbyapiinfo;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ui.swagger.studentapp.testbase.TestBase;
import ui.swagger.studentinfo.ProductsStep;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.*;

@RunWith(SerenityRunner.class)
public class ProductsCURDTestWithSteps extends TestBase {
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

    @Steps
    ProductsStep productsSteps;

    @Title("This will create a New product")
    @Test
    public void test001() {
        ValidatableResponse response = productsSteps.createProduct(name, type, price, shipping, upc, description, manufacturer, model, url, image);
        response.log().all().statusCode(201);
        productID = response.log().all().extract().path("id");
        System.out.println(productID);
    }

    @Title("Verify if the Product was added to the application")
    @Test
    public void test002() {
        HashMap<String, ?> productMap = productsSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @Title("Update the product information")
    @Test
    public void test003() {
        name = name + "_updated";
        productsSteps.updatingProduct(productID,name, type, price, shipping, upc, description, manufacturer, model, url, image);
        HashMap<String, ?> productMap = productsSteps.getProductInfoByName(productID);
        Assert.assertThat(productMap, hasValue(name));
        System.out.println(productMap);
    }

    @Title("Delete the product by ID")
    @Test
    public void test004() {
        productsSteps.deleteProduct(productID).statusCode(200);
        productsSteps.getProductByID(productID).statusCode(404);
    }



}