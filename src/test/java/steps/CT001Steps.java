package steps;

import com.github.javafaker.Faker;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.qameta.allure.Allure;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONObject;
import org.junit.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import utils.PropertiesUtils;
import utils.TeamResource;
import utils.ValidationUtil;

public class CT001Steps extends ValidationUtil {

  private final TeamResource teamResource;

  private final Logger logger = LoggerFactory.getLogger(CT001Steps.class);

  private final String pokemonHost = PropertiesUtils.extractPokemonHost();

  private String teamName;

  private String requestTeamName;

  private String statusCodeResponse;

  private Map<String, Object> requestBody = new HashMap<>();

  private final Faker faker = new Faker();

  public CT001Steps(TeamResource teamResource) {
    this.teamResource = teamResource;
  }

  @Given("I perform a POST request for {string} to create a Pokemon Team")
  public void IPerformAPostRequestForTeamToCreateAPokemonTeam(String path){

    requestTeamName = faker.name().username();

    requestBody.put("teamName", requestTeamName);
    requestBody.put("firstPokemon", "Squirtle");
    requestBody.put("secondPokemon", "Bulbasaur");
    requestBody.put("thirdPokemon", "Zorua");
    requestBody.put("fourthPokemon", "Eevee");
    requestBody.put("fifthPokemon", "Mew");
    requestBody.put("lastPokemon", "Ditto");


    List<String> pokemonResponse = teamResource.postPokemonTeam(pokemonHost, path, requestBody);


    statusCodeResponse = pokemonResponse.get(0);
    teamName = pokemonResponse.get(1);

    logger.info("Request Body: " + requestBody.toString());
    logger.info("Get TeamName: " + teamName);
    logger.info("Get Status Code: " + statusCodeResponse);

    Allure.addAttachment("Request Body", requestBody.toString());
    Allure.addAttachment("TeamName in Response Header", teamName);
    Allure.addAttachment("Status code Response", statusCodeResponse);
  }

  @When("Verify if teamName returning in response header is equal to informed in the request")
  public void VerifyIfTeamNameReturnigInResponseHeaderIsEqualToInformedInTheRequest(){
    Allure.addAttachment("TeamName in Response Header", teamName);
    Assert.assertEquals(requestTeamName, teamName);
  }

  @Then("Verify if the status code is {string}")
  public void VerifyIftheStatusCodeIs201(String statusCode){
    Allure.addAttachment("Status code Response", statusCodeResponse);
    Assert.assertEquals(statusCode, statusCodeResponse);
  }

  @And("Veify the contract betwenn Request and {string}")
  public void veifyTheContractBetwennRequestAndSchema(String jsonSchema) {
    JSONObject jsonObject = new JSONObject(requestBody);

    getJsonSchemaValidator(jsonSchema, "pokemonTeam", jsonObject);
  }
}
