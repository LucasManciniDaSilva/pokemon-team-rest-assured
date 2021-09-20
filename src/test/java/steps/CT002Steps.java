package steps;

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

public class CT002Steps extends ValidationUtil {

  private final TeamResource teamResource;

  private final Logger logger = LoggerFactory.getLogger(CT002Steps.class);

  private final String pokemonHost = PropertiesUtils.extractPokemonHost();

  private static final Integer STATUS_CODE_UNPROCESSABLE_ENTITY = 422;

  private JsonPath pokemonTeamResponse;

  private final String requestTeamName = PropertiesUtils.extractPokemonTeamName();

  private Map<String, Object> requestBody = new HashMap<>();

  public CT002Steps(TeamResource teamResource) {
    this.teamResource = teamResource;
  }

  @Given("I perform a POST request for {string} informing a teamName that already exists")
  public void IPerformAPostRequestForTeamInformingATeamThatAlreadyExists(String path) {

    requestBody.put("teamName", requestTeamName);
    requestBody.put("firstPokemon", "Charmander");
    requestBody.put("secondPokemon", "Bagon");
    requestBody.put("thirdPokemon", "Riolu");
    requestBody.put("fourthPokemon", "Lucario");
    requestBody.put("fifthPokemon", "Articuno");
    requestBody.put("lastPokemon", "Rayquaza");

    pokemonTeamResponse =
        teamResource.postPokemonError(
            pokemonHost, path, requestBody, STATUS_CODE_UNPROCESSABLE_ENTITY);

    Allure.addAttachment("Request Body", requestBody.toString());
  }

  @Then("Verify if returning the message {string} and response code {string}")
  public void VerifyIfReturningTheMessageAlreadyExists(String statusDescription, String statusCode) {
    String pathBody = "[0]";

    Assert.assertEquals(pokemonTeamResponse.get(pathBody + ".code"), statusCode);
    Assert.assertEquals(pokemonTeamResponse.get(pathBody + ".description"), statusDescription);
    Allure.addAttachment("Response Message", pokemonTeamResponse.prettify());
  }
}

