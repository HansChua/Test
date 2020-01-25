/**
 * 
 */
package hc.calibre;

import javax.inject.Inject;

import hc.core.AppData;

/**
 * 
 */
public class WindowsCalibreAppPathProvider implements CalibreAppPathProvider {

  private static final String EXECUTABLE_PATH = "ebook-convert.exe";

  @Inject
  WindowsCalibreAppPathProvider() {
    // NOOP
  }

  @Override
  public String get(AppData appData) {
    return appData.getCalibreFolder().resolve(EXECUTABLE_PATH).toAbsolutePath().toString();
  }

}
