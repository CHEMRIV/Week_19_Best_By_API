package ui.swagger.studentapp.restbyapiinfo;

import io.restassured.response.ValidatableResponse;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import ui.swagger.studentapp.testbase.TestBase;
import ui.swagger.studentinfo.CategoriesStep;
import ui.swagger.utils.TestUtils;

import java.util.HashMap;

import static org.hamcrest.Matchers.hasValue;

@RunWith(SerenityRunner.class)
public class CategoriesCURDTestWithSteps extends TestBase {
    static String name = "Flower" + TestUtils.getRandomValue();
    static String id = "rose987" + TestUtils.getRandomValue();
    static String categoryID;

    @Steps
    CategoriesStep categoriesSteps;

    @Title("This will create a New Category")
    @Test
    public void test001() {
        ValidatableResponse response = categoriesSteps.createCategory(name, id);
        response.log().all().statusCode(201);
        categoryID = response.log().all().extract().path("id");
        System.out.println(categoryID);
    }

    @Title("Verify if the Category was added to the application")
    @Test
    public void test002() {
        HashMap<String, ?> categoryMap = categoriesSteps.getCategoryInfoByName(categoryID);
        Assert.assertThat(categoryMap, hasValue(name));
        System.out.println(categoryMap);
    }

    @Title("Update the Category information")
    @Test
    public void test003() {
        name = name + "_updated";
        categoriesSteps.updatingCategory(categoryID, name, id);
        HashMap<String, ?> productList = categoriesSteps.getCategoryInfoByName(categoryID);
        Assert.assertThat(productList, hasValue(name));
        System.out.println(productList);
    }

    @Title("Delete the product by ID")
    @Test
    public void test004() {
        categoriesSteps.deleteCategory(categoryID).statusCode(200);
        categoriesSteps.getCategoryByID(categoryID).statusCode(404);
    }
}
