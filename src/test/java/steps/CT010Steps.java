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

public class CT010Steps extends ValidationUtil {

  private final TeamResource teamResource;

  private final Logger logger = LoggerFactory.getLogger(CT010Steps.class);

  private final String pokemonHost = PropertiesUtils.extractPokemonHost();

  private static final Integer STATUS_CODE_BAD_REQUEST = 400;

  private  String requestTeamName;

  private JsonPath pokemonTeamResponse;

  private Map<String, Object> requestBody = new HashMap<>();

  private final Faker faker = new Faker();

  public CT010Steps(TeamResource teamResource) {
    this.teamResource = teamResource;
  }

  @Given("I perform a POST request for {string} not informing the field secondPokemon")
  public void IPerformAPostRequestForTeamNotInformingTheFieldSecondPokemon(String path) {

    requestTeamName = faker.name().username();

    requestBody.put("teamName", requestTeamName);
    requestBody.put("firstPokemon", "Quilava");
    requestBody.put("thirdPokemon", "Zorua");
    requestBody.put("fourthPokemon", "Eevee");
    requestBody.put("fifthPokemon", "Mew");
    requestBody.put("lastPokemon", "Jolteon");

    pokemonTeamResponse =
        teamResource.postPokemonError(pokemonHost, path, requestBody, STATUS_CODE_BAD_REQUEST );

    Allure.addAttachment("Request Body", requestBody.toString());
  }

  @Then(
      "Verify if returning the message not informing the field secondPokemon {string} and response code {string}")
  public void VerifyIfReturningTheMessageNotInformingTheFieldSecondPokemon(
      String statusDescription, String statusCode) {
    String pathBody = "[0]";

    Assert.assertEquals(pokemonTeamResponse.get(pathBody + ".code"), statusCode);
    Assert.assertEquals(pokemonTeamResponse.get(pathBody + ".description"), statusDescription);
    Allure.addAttachment("Response Message", pokemonTeamResponse.prettify());
  }
}