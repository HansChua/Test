/**
 * 
 */
package hc.parser;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 */
public class PartInfoParser {

  private final ObjectMapper mapper;

  public PartInfoParser(ObjectMapper mapper) {
    this.mapper = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public PartInfo parse(String data) throws JsonProcessingException {
    return mapper.readValue(data, PartInfo.class);
  }

}
