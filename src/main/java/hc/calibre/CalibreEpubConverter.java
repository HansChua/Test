/**
 * 
 */
package hc.calibre;

import java.io.IOException;
import java.nio.file.Path;

import javax.inject.Inject;
import javax.inject.Provider;

import hc.core.AppData;
import hc.downloader.Volume;
import hc.downloader.VolumeSaveInfo;
import hc.parser.VolumeInfo;

/**
 * 
 */
public class CalibreEpubConverter {

  private static final String CALIBRE_COMMAND = "%s \"%s\" \"%s\" --cover \"%s\" --title \"%s\" --authors \"%s\" --publisher \"%s\" --tags \"%s\" --output-profile tablet";

  private final CalibreAppPathProvider calibreAppPathProvider;
  private final Provider<Runtime> runtimeProvider;

  @Inject
  CalibreEpubConverter(CalibreAppPathProvider calibreAppPathProvider, Provider<Runtime> runtimeProvider) {
    this.calibreAppPathProvider = calibreAppPathProvider;
    this.runtimeProvider = runtimeProvider;
  }

  public boolean convert(AppData appData, VolumeSaveInfo saveInfo) throws IOException, InterruptedException {
    Path epubOutputfolderPath = appData.getEpubOutputFolder();
    epubOutputfolderPath.toFile().mkdirs();

    Volume volume = saveInfo.getVolume();
    VolumeInfo volumeInfo = volume.getVolumeInfo();
    String outputEpubFile = epubOutputfolderPath.resolve(volume.getVolumeSlug() + ".epub").toAbsolutePath().toString();

    String command = String.format(CALIBRE_COMMAND,
                                   calibreAppPathProvider.get(appData),
                                   saveInfo.getZipPath().toAbsolutePath().toString(),
                                   outputEpubFile,
                                   saveInfo.getCoverImagePath().toAbsolutePath().toString(),
                                   volumeInfo.getTitle(),
                                   volumeInfo.getAuthor(),
                                   volumeInfo.getPublisherOriginal(),
                                   volumeInfo.getTags());

    Runtime rt = runtimeProvider.get();
    Process pr = rt.exec(command);
    return 0 == pr.waitFor();
  }

}
