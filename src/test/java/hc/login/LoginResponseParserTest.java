/**
 * 
 */
package hc.login;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.json.JsonMapper;

import hc.util.JsonMapperFactory;

/**
 * 
 */
public class LoginResponseParserTest {

  static String json;

  JsonMapper parser;

  @BeforeAll
  public static void setupClass() throws URISyntaxException, IOException {
    String filename = "LoginFailedResponse.json";
    Path path = Paths.get(LoginResponseParserTest.class.getResource(filename).toURI());
    json = Files.readAllLines(path).stream().collect(Collectors.joining());
  }

  @BeforeEach
  public void setup() {
    JsonMapperFactory mapperFactory = new JsonMapperFactory();
    parser = mapperFactory.newInstance();
  }

  @Test
  public final void parseErrorCorrectly() throws IOException {
    LoginResponse loginResponse = parser.readValue(json, LoginResponse.class);
    LoginResponseError error = loginResponse.getError();
    Assertions.assertNotNull(error);
    Assertions.assertEquals(401, error.getStatusCode());
    Assertions.assertNull(loginResponse.getId());
  }

}
