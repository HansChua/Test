package hc.core;

import java.nio.file.Path;
import java.util.ArrayList;
/**
 * 
 */
import java.util.List;

import javax.inject.Inject;

import hc.downloader.Volume;
import hc.downloader.VolumeDownloadRequest;
import hc.downloader.VolumeHandler;
import hc.downloader.VolumeSaveInfo;
import hc.downloader.VolumeSaver;
import hc.login.LoginDetails;
import hc.login.LoginHandler;
import hc.login.LoginRequestPayload;
import hc.parser.SeriesData;
import hc.parser.SeriesHandler;

/**
 * 
 */
public class App {

  private final LoginHandler loginHandler;
  
  private final SeriesHandler seriesHandler;
  private final VolumeHandler volumeHandler;
  private final VolumeSaver volumeSaver;

  @Inject
  App(LoginHandler loginHandler, SeriesHandler seriesHandler, VolumeHandler volumeHandler, VolumeSaver volumeSaver) {
    super();
    this.seriesHandler = seriesHandler;
    this.loginHandler = loginHandler;
    this.volumeHandler = volumeHandler;
    this.volumeSaver = volumeSaver;
  }

  public List<VolumeSaveInfo> download(AppData appData) throws Exception {
    List<VolumeDownloadRequest> volumeDownloadReqs = appData.getRequests();

    List<VolumeSaveInfo> result = new ArrayList<>();
    if (volumeDownloadReqs.isEmpty()) {
      return result;
    }

    LoginDetails loginDetails = loginHandler.login(new LoginRequestPayload(appData.getUsername(),
                                                                           appData.getPassword()));

    Path outputFolderPath = appData.getOutputFolder();
    outputFolderPath.toFile().mkdirs();

    for (VolumeDownloadRequest volumeDownloadReq : volumeDownloadReqs) {
      SeriesData seriesData = seriesHandler.get(volumeDownloadReq.getTitleSlug());
      List<Volume> volumes = volumeHandler.getVolumes(loginDetails, seriesData, volumeDownloadReq.getVolumes());

      for (Volume volume : volumes) {
        Path folderPath = outputFolderPath.resolve(volume.getVolumeSlug());
        folderPath.toFile().mkdirs();

        result.add(volumeSaver.save(folderPath, volume));
      }
    }

    return result;
  }

}
