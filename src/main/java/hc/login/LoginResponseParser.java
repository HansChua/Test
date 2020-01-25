/**
 * 
 */
package hc.login;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 */
public class LoginResponseParser {

  private final ObjectMapper mapper;

  public LoginResponseParser(ObjectMapper mapper) {
    this.mapper = mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
  }

  public LoginResponse parse(String data) throws JsonProcessingException {
    return mapper.readValue(data, LoginResponse.class);
  }

}
