/**
 * 
 */
package hc.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 */
public class PartDataParser {

  private final ObjectMapper mapper;

  public PartDataParser(ObjectMapper mapper) {
    this.mapper = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public PartData parse(String data) throws JsonProcessingException {
    return mapper.readValue(data, PartData.class);
  }

}
