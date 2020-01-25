/**
 * 
 */
package hc.util;

import java.nio.charset.StandardCharsets;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.RequestBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */
public class RequestBuilderFactoryTest {

  RequestBuilderFactory requestBuilderFactory;

  @BeforeEach
  void setup() {
    this.requestBuilderFactory = new RequestBuilderFactory();
  }

  @Test
  public void validatePostMethod() {
    RequestBuilder requestBuilder = requestBuilderFactory.post("");
    Assertions.assertEquals(HttpPost.METHOD_NAME, requestBuilder.getMethod());
    Assertions.assertEquals(StandardCharsets.UTF_8, requestBuilder.getCharset());
  }

  @Test
  public void validateGetMethod() {
    RequestBuilder requestBuilder = requestBuilderFactory.get("");
    Assertions.assertEquals(HttpGet.METHOD_NAME, requestBuilder.getMethod());
    Assertions.assertEquals(StandardCharsets.UTF_8, requestBuilder.getCharset());
  }

  @Test
  public void validateNewPostInstanceEveryTime() {
    RequestBuilder builder1 = requestBuilderFactory.post("");
    RequestBuilder builder2 = requestBuilderFactory.post("");
    Assertions.assertNotEquals(builder1, builder2);
  }

  @Test
  public void validateNewGetInstanceEveryTime() {
    RequestBuilder builder1 = requestBuilderFactory.get("");
    RequestBuilder builder2 = requestBuilderFactory.get("");
    Assertions.assertNotEquals(builder1, builder2);
  }

}
