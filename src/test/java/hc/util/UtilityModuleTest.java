/**
 * 
 */
package hc.util;

import javax.inject.Inject;
import javax.inject.Provider;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.fasterxml.jackson.databind.json.JsonMapper;
import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.BoundFieldModule;

import hc.util.HttpClientBuilderFactory;
import hc.util.RequestBuilderFactory;
import hc.util.UtilityModule;

/**
 * 
 */
public class UtilityModuleTest {

  @Inject
  Provider<Runtime> runtimeProvider;

  @Inject
  JsonMapper jsonMapper;

  @Inject
  RequestBuilderFactory requestBuilderFactory;

  @Inject
  HttpClientBuilderFactory httpClientBuilderFactory;

  @BeforeEach
  void setup() {
    Guice.createInjector(new UtilityModule(), BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void validateNotNull() {
    Assertions.assertNotNull(runtimeProvider);
    Assertions.assertNotNull(jsonMapper);
    Assertions.assertNotNull(requestBuilderFactory);
    Assertions.assertNotNull(httpClientBuilderFactory);
  }

}
