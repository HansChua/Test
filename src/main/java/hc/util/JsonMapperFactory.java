/**
 * 
 */
package hc.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 * 
 */
public class JsonMapperFactory {

  public JsonMapper newInstance() {
    JsonMapper mapper = new JsonMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    return mapper;
  }

}
