/**
 * 
 */
package hc.parser;

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
import com.openpojo.reflection.PojoClass;
import com.openpojo.reflection.impl.PojoClassFactory;
import com.openpojo.validation.Validator;
import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.rule.impl.GetterMustExistRule;
import com.openpojo.validation.rule.impl.SetterMustExistRule;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;

import hc.util.JsonMapperFactory;

/**
 */
public class PartDataTest {

  static String jsonSuccess;
  static String jsonNoAccess;

  JsonMapper parser;

  @BeforeAll
  public static void setupClass() throws URISyntaxException, IOException {
    String filenameSuccess = "PartData.json";
    Path pathSuccess = Paths.get(PartDataTest.class.getResource(filenameSuccess).toURI());
    jsonSuccess = Files.readAllLines(pathSuccess).stream().collect(Collectors.joining());

    String filenameNoAccess = "PartDataNoAccess.json";
    Path pathNoAccess = Paths.get(PartDataTest.class.getResource(filenameNoAccess).toURI());
    jsonNoAccess = Files.readAllLines(pathNoAccess).stream().collect(Collectors.joining());
  }

  @BeforeEach
  public void setup() {
    JsonMapperFactory mapperFactory = new JsonMapperFactory();
    parser = mapperFactory.newInstance();
  }

  @Test
  public final void parseIdCorrectly() throws IOException {
    PartData partData = parser.readValue(jsonSuccess, PartData.class);
    Assertions.assertEquals("something", partData.getDataHTML());
    Assertions.assertNull(partData.getError());
  }

  @Test
  public final void parseNoAccessErrorCorrectly() throws IOException {
    PartData partData = parser.readValue(jsonNoAccess, PartData.class);
    PartDataError error = partData.getError();
    Assertions.assertNull(partData.getDataHTML());
    Assertions.assertNotNull(error);
    Assertions.assertEquals(500, error.getStatus());
  }

  @Test
  public void validateSettersAndGetters() {
    PojoClass data = PojoClassFactory.getPojoClass(PartData.class);

    Validator validator = ValidatorBuilder.create()
                                          .with(new SetterMustExistRule(), new GetterMustExistRule())
                                          .with(new SetterTester(), new GetterTester())
                                          .build();

    validator.validate(data);
  }

  @Test
  public void validateToString() {
    PartData partData = new PartData();
    String expected = "PartData [dataHTML=null, error=null]";
    Assertions.assertEquals(expected, partData.toString());
  }

}
