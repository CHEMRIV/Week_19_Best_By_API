package ui.swagger.studentapp.cucumber;


import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;
import org.junit.runner.RunWith;
import ui.swagger.studentapp.testbase.TestBase;


@RunWith(CucumberWithSerenity.class)
@CucumberOptions(features = "src/test/java/resources/feature/")    //add last slash /)

public class CucumberRunner extends TestBase {

}
