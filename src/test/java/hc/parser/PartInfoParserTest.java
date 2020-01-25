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

import hc.util.JsonMapperFactory;

/**
 */
public class PartInfoParserTest {

  static String json;

  PartInfoParser parser;

  @BeforeClass
  public static void setupClass() throws URISyntaxException, IOException {
    String filename = "PartInfo.json";
    Path path = Paths.get(PartInfoParserTest.class.getResource(filename).toURI());
    json = Files.readAllLines(path).stream().collect(Collectors.joining());
  }

  @Before
  public void setup() {
    JsonMapperFactory jsonMapperFactory = new JsonMapperFactory();
    parser = new PartInfoParser(jsonMapperFactory.newInstance());
  }

  @Test
  public final void parseIdCorrectly() throws IOException {
    PartInfo partInfo = parser.parse(json);
    assertEquals("ID", partInfo.getId());
  }

}
