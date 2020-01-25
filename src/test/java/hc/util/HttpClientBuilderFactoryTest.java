/**
 * 
 */
package hc.util;

import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * 
 */
public class HttpClientBuilderFactoryTest {

  HttpClientBuilderFactory httpClientBuilderFactory;

  @BeforeEach
  void setup() {
    this.httpClientBuilderFactory = new HttpClientBuilderFactory();
  }

  @Test
  public void validateNewInstanceEveryTime() {
    HttpClientBuilder builder1 = httpClientBuilderFactory.newInstance();
    HttpClientBuilder builder2 = httpClientBuilderFactory.newInstance();
    Assertions.assertNotEquals(builder1, builder2);
  }

}
