/**
 * 
 */
package hc.login;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import hc.util.JsonMapperFactory;

/**
 * 
 */
public class LoginResponseParserTest {

  static String json;

  LoginResponseParser parser;

  @BeforeClass
  public static void setupClass() throws URISyntaxException, IOException {
    String filename = "LoginFailedResponse.json";
    Path path = Paths.get(LoginResponseParserTest.class.getResource(filename).toURI());
    json = Files.readAllLines(path).stream().collect(Collectors.joining());
  }

  @Before
  public void setup() {
    JsonMapperFactory mapperFactory = new JsonMapperFactory();
    parser = new LoginResponseParser(mapperFactory.newInstance());
  }

  @Test
  public final void parseErrorCorrectly() throws IOException {
    LoginResponse loginResponse = parser.parse(json);
    LoginResponseError error = loginResponse.getError();
    assertNotNull(error);
    assertEquals(401, error.getStatusCode());
  }

}
