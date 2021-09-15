package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
    features = {"src/test/java/features/CT001.feature"},
    glue = {
        "steps","classpath/CT002Steps"
    },
    plugin = {"io.qameta.allure.cucumber4jvm.AllureCucumber4Jvm" },
    monochrome = true,
    snippets =  CucumberOptions.SnippetType.CAMELCASE,
    strict = true
)

public class TestPokemonTeamRunner {

}
