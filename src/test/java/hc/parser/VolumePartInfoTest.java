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
public class VolumePartInfoTest {

  static String json;

  JsonMapper parser;

  @BeforeAll
  public static void setupClass() throws URISyntaxException, IOException {
    String filename = "PartInfo.json";
    Path path = Paths.get(VolumePartInfoTest.class.getResource(filename).toURI());
    json = Files.readAllLines(path).stream().collect(Collectors.joining());
  }

  @BeforeEach
  public void setup() {
    JsonMapperFactory jsonMapperFactory = new JsonMapperFactory();
    parser = jsonMapperFactory.newInstance();
  }

  @Test
  public final void parseIdCorrectly() throws IOException {
    VolumePartInfo partInfo = parser.readValue(json, VolumePartInfo.class);
    Assertions.assertEquals("ID", partInfo.getId());
  }

  @Test
  public void validateSettersAndGetters() {
    PojoClass data = PojoClassFactory.getPojoClass(VolumePartInfo.class);

    Validator validator = ValidatorBuilder.create()
                                          .with(new SetterMustExistRule(), new GetterMustExistRule())
                                          .with(new SetterTester(), new GetterTester())
                                          .build();

    validator.validate(data);
  }

  @Test
  public void validateToString() {
    VolumePartInfo partInfo = new VolumePartInfo();
    String expected = "PartInfo [title=null, titleslug=null, partNumber=0, id=null]";
    Assertions.assertEquals(expected, partInfo.toString());
  }

}
