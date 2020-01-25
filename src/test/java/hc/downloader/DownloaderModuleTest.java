/**
 * 
 */
package hc.downloader;

import javax.inject.Inject;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.google.inject.Guice;
import com.google.inject.testing.fieldbinder.BoundFieldModule;

import hc.downloader.dummy.DummyParserModule;

/**
 * 
 */
public class DownloaderModuleTest {

  @Inject
  VolumeHandler volumeHandler;

  @Inject
  VolumeSaver volumeSaver;

  @BeforeEach
  void setup() {
    Guice.createInjector(new DummyParserModule(), new DownloaderModule(), BoundFieldModule.of(this))
         .injectMembers(this);
  }

  @Test
  public void validateNotNull() {
    Assertions.assertNotNull(volumeHandler);
    Assertions.assertNotNull(volumeSaver);
  }

}
