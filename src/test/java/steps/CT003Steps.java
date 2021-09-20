package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.qameta.allure.Allure;
import io.restassured.path.json.JsonPath;
import java.util.HashMap;
import java.util.Map;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesUtils;
import utils.TeamResource;
import utils.ValidationUtil;

public class CT003Steps extends ValidationUtil {

  private final TeamResource teamResource;

  private final Logger logger = LoggerFactory.getLogger(CT003Steps.class);

  private final String pokemonHost = PropertiesUtils.extractPokemonHost();

  private static final Integer STATUS_CODE_NOT_FOUND = 404;

  private  String requestTeamName;

  private JsonPath pokemonTeamResponse;

  private Map<String, Object> requestBody = new HashMap<>();

  private final Faker faker = new Faker();

  public CT003Steps(TeamResource teamResource) {
    this.teamResource = teamResource;
  }

  @Given("I perform a POST request for {string} informing a Pokemon that not exists in field firstPokemon")
  public void IPerformAPostRequestForTeamInformingAPokemonThatNotExistsInFieldFirstPokemon(String path) {

    requestTeamName = faker.name().username();

    requestBody.put("teamName", requestTeamName);
    requestBody.put("firstPokemon", "Teste");
    requestBody.put("secondPokemon", "Bulbasaur");
    requestBody.put("thirdPokemon", "Zorua");
    requestBody.put("fourthPokemon", "Eevee");
    requestBody.put("fifthPokemon", "Mew");
    requestBody.put("lastPokemon", "Ditto");
    pokemonTeamResponse =
        teamResource.postPokemonError(
            pokemonHost, path, requestBody, STATUS_CODE_NOT_FOUND);

    Allure.addAttachment("Request Body", requestBody.toString());
  }

  @Then("Verify if returning the message for pokemon that not exists in field firstPokemon {string} and response code {string}")
  public void VerifyIfReturningTheMessageForPokemonThatNotExistInFieldFirstPokemon(String statusDescription, String statusCode) {
    String pathBody = "[0]";

    Assert.assertEquals(pokemonTeamResponse.get(pathBody + ".code"), statusCode);
    Assert.assertEquals(pokemonTeamResponse.get(pathBody + ".description"), statusDescription);
    Allure.addAttachment("Response Message", pokemonTeamResponse.prettify());
  }
}