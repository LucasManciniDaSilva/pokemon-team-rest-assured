package utils;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.http.HttpStatus;

public class TeamResource {
  private static final String POKEMON_ENDPOINT = "/pokemon-team/v1/";

  private static final List<String> pokemonResponseDatas = new ArrayList<>();

  public List<String> postPokemonTeam(String host, String path, Map<String, Object> requestBody) {

    Response response = given()
        .contentType(ContentType.JSON)
        .body(requestBody)
        .request("POST", host + POKEMON_ENDPOINT + path)
        .then()
        .log()
        .ifError()
        .statusCode(HttpStatus.SC_CREATED)
        .extract()
        .response();

    pokemonResponseDatas.add(String.valueOf(response.getStatusCode()));
    pokemonResponseDatas.add(response.getHeader("teamName"));

    return pokemonResponseDatas;
  }

  public JsonPath postPokemonTeamAlreadyExists(String host, String path, Map<String, Object> requestBody, Integer statusCode){

    Response response = given()
        .contentType("application/json")
        .body(requestBody)
        .request("POST", host + POKEMON_ENDPOINT + path)
        .then()
        .log()
        .ifError()
        .statusCode(statusCode)
        .assertThat()
        .extract()
        .response();

    return response.jsonPath();
  }

}
