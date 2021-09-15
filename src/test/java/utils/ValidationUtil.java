package utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import lombok.NonNull;
import lombok.SneakyThrows;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

public abstract class ValidationUtil {

  @SneakyThrows
  public void getJsonSchemaValidator(@NonNull String jsonSchema, String resourcePath,@NonNull JSONObject jsonObject) {
    final String filePath = "src/test/resources/schema/" + resourcePath + "/" + jsonSchema;

    InputStream inputStream = new FileInputStream(filePath);
    JSONObject rawSchema = new JSONObject(new JSONTokener(inputStream));

    try {
      Schema schema = SchemaLoader.load(rawSchema);
      schema.validate(jsonObject);
    } catch (IllegalArgumentException illegalArgumentException) {
      throw new FileNotFoundException(String.format("File %s was not found", "src/test/resources/"
          + filePath));
    }
  }
}
