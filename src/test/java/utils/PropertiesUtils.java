package utils;

import io.restassured.path.json.JsonPath;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.apache.commons.configuration.Configuration;
import org.apache.commons.configuration.PropertiesConfiguration;

public final class PropertiesUtils {

  private static final Configuration configuration = loadConfiguration();

  private PropertiesUtils() {
    // Utils can not be initialized
  }

  @SneakyThrows
  private static Configuration loadConfiguration() {
    return new PropertiesConfiguration("src/test/resources/parameters/DefaultParameters.properties");
  }

  public static String getString(@NonNull String propertyName) {
    return configuration.getString(propertyName);
  }

  public static String extractPokemonHost() {
    return getString("pokemon_host");
  }

  public static String extractPokemonTeamName() {
    return getString("pokemon_team_name");
  }
}
