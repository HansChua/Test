/**
 * 
 */
package hc.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * 
 */
public class JsonMapperFactoryTest {

  JsonMapperFactory jsonMapperFactory;

  @BeforeEach
  void setup() {
    this.jsonMapperFactory = new JsonMapperFactory();
  }

  @Test
  public void validateConfig() {
    JsonMapper mapper = jsonMapperFactory.newInstance();
    DeserializationConfig deserializationConfig = mapper.getDeserializationConfig();
    Assertions.assertFalse(deserializationConfig.isEnabled(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES));
  }

  @Test
  public void validateNewInstanceEveryTime() {
    JsonMapper mapper1 = jsonMapperFactory.newInstance();
    JsonMapper mapper2 = jsonMapperFactory.newInstance();
    Assertions.assertNotEquals(mapper1, mapper2);
  }

}
