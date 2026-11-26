package runners;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
	    features = "src/test/resources/Features",
	    glue = "stepdefinitions",
	    plugin  = {"pretty", "html:target/cucumber-report.html"},
	    monochrome = true,
	    //tags = "@ConnexionNeoSchool"
	    tags = "@ConnexionNeoSchool or @CreationAcademicYear or @CreationTrimestre or @CreationEtudiant or @CreationEnseignant or @CreationLevels or @CreationClasses or @SelectionAcademicYear"
	    //tags = "@CreationTrimestre"		
	)
public class   TestRunnerTest {

}