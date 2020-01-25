/**
 * 
 */
package hc.core;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.impl.client.BasicCookieStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;
import org.mockito.Mockito;

import hc.downloader.Volume;
import hc.downloader.VolumeDownloadRequest;
import hc.downloader.VolumeHandler;
import hc.downloader.VolumeSaveInfo;
import hc.downloader.VolumeSaver;
import hc.login.LoginDetails;
import hc.login.LoginHandler;
import hc.parser.SeriesData;
import hc.parser.SeriesHandler;

/**
 * 
 */
public class AppTest {

  LoginHandler loginHandler;
  SeriesHandler seriesHandler;
  VolumeHandler volumeHandler;
  VolumeSaver volumeSaver;

  App app;

  @BeforeEach
  void setup() {
    loginHandler = Mockito.mock(LoginHandler.class);
    seriesHandler = Mockito.mock(SeriesHandler.class);
    volumeHandler = Mockito.mock(VolumeHandler.class);
    volumeSaver = Mockito.mock(VolumeSaver.class);
    app = new App(loginHandler, seriesHandler, volumeHandler, volumeSaver);
  }

  @Test
  public void validateSkipIfNoRequests(@TempDir Path tempDir) throws Exception {
    Files.deleteIfExists(tempDir);

    AppData appData = new AppData();
    appData.setOutputFolder(tempDir);
    appData.setRequests(new ArrayList<>());

    app.download(appData);

    Assertions.assertFalse(Files.exists(tempDir));
  }

  @Test
  public void validateCreateOutputFolder(@TempDir Path tempDir) throws Exception {
    Files.deleteIfExists(tempDir);

    VolumeDownloadRequest request = new VolumeDownloadRequest();
    request.setTitleSlug("test");
    request.setVolumes(new int[] { 1 });

    ArrayList<VolumeDownloadRequest> requests = new ArrayList<>();
    requests.add(request);

    AppData appData = new AppData();
    appData.setOutputFolder(tempDir);
    appData.setRequests(requests);

    app.download(appData);

    Assertions.assertTrue(Files.exists(tempDir));
  }

  @Test
  public void validateCreateVolumeFolders(@TempDir Path tempDir) throws Exception {
    Files.deleteIfExists(tempDir);

    String seriesTitleSlug = "test";
    int[] volumeNos = { 1 };

    VolumeDownloadRequest request = new VolumeDownloadRequest();
    request.setTitleSlug(seriesTitleSlug);
    request.setVolumes(volumeNos);

    ArrayList<VolumeDownloadRequest> requests = new ArrayList<>();
    requests.add(request);

    AppData appData = new AppData();
    appData.setOutputFolder(tempDir);
    appData.setRequests(requests);

    LoginDetails loginDetails = new LoginDetails("authXyZ", new BasicCookieStore());
    Mockito.when(loginHandler.login(Mockito.any())).thenReturn(loginDetails);

    List<Volume> volumes = new ArrayList<>();
    Volume volume1 = new Volume("test-volume-1");
    volumes.add(volume1);

    SeriesData seriesData = new SeriesData();
    Mockito.when(seriesHandler.get(seriesTitleSlug)).thenReturn(seriesData);
    Mockito.when(volumeHandler.getVolumes(loginDetails, seriesData, volumeNos)).thenReturn(volumes);

    List<VolumeSaveInfo> saveInfos = app.download(appData);

    Assertions.assertTrue(Files.exists(tempDir));
    Assertions.assertTrue(Files.exists(tempDir.resolve(volume1.getVolumeSlug())));
    Assertions.assertTrue(saveInfos.size() > 0);
  }

}
