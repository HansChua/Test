/**
 * 
 */
package hc.downloader;

import java.nio.file.Path;

import javax.inject.Inject;

/**
 * 
 */
public class VolumeZipFileNamer {

  @Inject
  VolumeZipFileNamer() {
  }

  public Path get(Path folderPath, Volume volume) {
    return folderPath.resolve(volume.getVolumeSlug() + ".zip");
  }

}
