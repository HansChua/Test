package hc.calibre;

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
public class CalibreModuleTest {

  @Inject
  CalibreAppPathProvider pathProvider;

  @Inject
  CalibreEpubConverter calibreEpubConverter;

  @BeforeEach
  void setup() {
    Guice.createInjector(new UtilityModule(), new CalibreModule(), BoundFieldModule.of(this)).injectMembers(this);
  }

  @Test
  public void validateNotNull() {
    Assertions.assertNotNull(pathProvider);
    Assertions.assertNotNull(calibreEpubConverter);
  }

}
