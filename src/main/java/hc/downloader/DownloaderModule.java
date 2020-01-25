/**
 * 
 */
package hc.downloader;

import com.google.inject.AbstractModule;

/**
 * 
 */
public class DownloaderModule extends AbstractModule {

  @Override
  protected void configure() {
    super.configure();

    bind(VolumeHandler.class);
    bind(VolumeSaver.class);
  }

}
