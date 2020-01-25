/**
 * 
 */
package hc.parser;

import javax.inject.Inject;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.json.JsonMapper;

/**
 */
public class PartInfoParser {

  private final JsonMapper mapper;

  @Inject
  PartInfoParser(JsonMapper mapper) {
    this.mapper = mapper;
  }

  public PartInfo parse(String data) throws JsonProcessingException {
    return mapper.readValue(data, PartInfo.class);
  }

}
