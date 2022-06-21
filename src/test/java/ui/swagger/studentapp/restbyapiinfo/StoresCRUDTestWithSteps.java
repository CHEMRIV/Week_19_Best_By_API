package ui.swagger.studentapp.restbyapiinfo;


import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ui.swagger.studentapp.testbase.TestBase;
import ui.swagger.studentinfo.StoresStep;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class StoresCRUDTestWithSteps extends TestBase {
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

    @Title("This will create a New Store")
    @Test
    public void test001() {
        HashMap<Object, Object> servicesData = new HashMap<>();
        ValidatableResponse response = storesSteps.createStore(name, type, address, address2, city, state, zip, lat, lng, hours,servicesData);
        response.log().all().statusCode(201);
        storeID = response.log().all().extract().path("id");
        System.out.println(storeID);
    }

    @Title("Verify if the Product was added to the application")
    @Test
    public void test002() {
        HashMap<String, ?> storeMap = storesSteps.getStoreInfoByName(storeID);
        Assert.assertThat(storeMap, hasValue(name));
        System.out.println(storeMap);
    }

    @Title("Update the product information")
    @Test
    public void test003() {
        name = name + "_updated";
        HashMap<Object, Object> servicesData = new HashMap<>();
        storesSteps.updateStore(storeID,name, type, address, address2, city, state, zip, lat, lng, hours,servicesData);
        HashMap<String, ?> productList = storesSteps.getStoreInfoByName(storeID);
        Assert.assertThat(productList, hasValue(name));
        System.out.println(productList);
    }

    @Title("Delete the product by ID")
    @Test
    public void test004() {
        storesSteps.deleteStore(storeID).statusCode(200);
        storesSteps.getStoreByID(storeID).statusCode(404);
    }


}
