package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
		features = "src/test/java/cucumber", 
		glue = "binaykanthal.stepDefinitions",
		monochrome = true, 
		tags = "@ErrorValidation",
		plugin = {
				"html:target/cucumber.html",
				"json:target/cucumber.json"
		}
)

public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
