/**
 * 
 */
package hc.parser;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 */
public class PartDataParser {

  private final JsonMapper mapper;

  @Inject
  PartDataParser(JsonMapper mapper) {
    this.mapper = mapper;
  }

  public PartData parse(String data) throws JsonProcessingException {
    return mapper.readValue(data, PartData.class);
  }

}
