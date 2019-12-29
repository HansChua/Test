/**
 * 
 */
package hc.parser;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 */
public class PartDataParserTest {

  static String json;

  PartDataParser parser;

  @BeforeClass
  public static void setupClass() throws URISyntaxException, IOException {
    String filename = "PartData.json";
    Path path = Paths.get(PartDataParserTest.class.getResource(filename).toURI());
    json = Files.readAllLines(path).stream().collect(Collectors.joining());
  }

  @Before
  public void setup() {
    parser = new PartDataParser(new JsonMapper());
  }

  @Test
  public final void parseIdCorrectly() throws IOException {
    PartData partData = parser.parse(json);
    assertEquals("something", partData.getDataHTML());
  }

}
