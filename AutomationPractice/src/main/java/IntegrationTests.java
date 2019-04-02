import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(
		monochrome = true,
		features = {"src/test/resources/features"},
		plugin = {"pretty","html:target/cucumber-html-report", "junit:target/cucumber-junit-report/allcukes.xml"},
		tags = {"@Include"})

public class IntegrationTests
{

}