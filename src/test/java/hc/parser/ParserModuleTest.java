/**
 * 
 */
package hc.parser;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.BoundFieldModule;

import hc.util.UtilityModule;

/**
 * 
 */
public class ParserModuleTest {

  @Inject
  PartDataHandler partDataHandler;

  @Inject
  SeriesHandler seriesHandler;

  @BeforeEach
  void setup() {
    Guice.createInjector(new UtilityModule(), new ParserModule(), BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void validateNotNull() {
    Assertions.assertNotNull(partDataHandler);
    Assertions.assertNotNull(seriesHandler);
  }

}
