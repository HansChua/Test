/**
 * 
 */
package hc.parser;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

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
 */
public class PartDataParserTest {

  static String jsonSuccess;
  static String jsonNoAccess;

  PartDataParser parser;

  @BeforeClass
  public static void setupClass() throws URISyntaxException, IOException {
    String filenameSuccess = "PartData.json";
    Path pathSuccess = Paths.get(PartDataParserTest.class.getResource(filenameSuccess).toURI());
    jsonSuccess = Files.readAllLines(pathSuccess).stream().collect(Collectors.joining());

    String filenameNoAccess = "PartDataNoAccess.json";
    Path pathNoAccess = Paths.get(PartDataParserTest.class.getResource(filenameNoAccess).toURI());
    jsonNoAccess = Files.readAllLines(pathNoAccess).stream().collect(Collectors.joining());
  }

  @Before
  public void setup() {
    JsonMapperFactory mapperFactory = new JsonMapperFactory();
    parser = new PartDataParser(mapperFactory.newInstance());
  }

  @Test
  public final void parseIdCorrectly() throws IOException {
    PartData partData = parser.parse(jsonSuccess);
    assertEquals("something", partData.getDataHTML());
    assertNull(partData.getError());
  }

  @Test
  public final void parseNoAccessErrorCorrectly() throws IOException {
    PartData partData = parser.parse(jsonNoAccess);
    PartDataError error = partData.getError();
    assertNull(partData.getDataHTML());
    assertNotNull(error);
    assertEquals(500, error.getStatus());
  }

}
