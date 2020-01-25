/**
 * 
 */
package hc.calibre;

import org.apache.commons.lang3.SystemUtils;

import com.google.inject.AbstractModule;

/**
 * 
 */
public class CalibreModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    if (SystemUtils.IS_OS_WINDOWS) {
      bind(CalibreAppPathProvider.class).to(WindowsCalibreAppPathProvider.class);
    }

    bind(CalibreEpubConverter.class);
  }

}
