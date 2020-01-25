/**
 * 
 */
package hc.downloader;

import java.nio.file.Path;

/**
 * 
 */
public class VolumeSaveInfo {

  private final Volume volume;
  private final Path folderPath;
  private final Path coverImagePath;
  private final Path zipPath;

  public VolumeSaveInfo(Volume volume, Path folderPath, Path coverImagePath, Path zipPath) {
    super();
    this.volume = volume;
    this.folderPath = folderPath;
    this.coverImagePath = coverImagePath;
    this.zipPath = zipPath;
  }

  public Volume getVolume() {
    return volume;
  }

  public Path getFolderPath() {
    return folderPath;
  }

  public Path getCoverImagePath() {
    return coverImagePath;
  }

  public Path getZipPath() {
    return zipPath;
  }

}
