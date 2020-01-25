/**
 * 
 */
package hc.downloader;

import java.util.Arrays;
import java.util.List;

import org.apache.http.impl.client.BasicCookieStore;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import hc.login.LoginDetails;
import hc.parser.PartData;
import hc.parser.PartDataError;
import hc.parser.PartDataHandler;
import hc.parser.SeriesData;
import hc.parser.VolumeInfo;
import hc.parser.VolumePartInfo;

/**
 * 
 */
public class VolumeHandlerTest {

  PartDataHandler partDataHandler;

  VolumeHandler volumeHandler;

  @BeforeEach
  void setup() {
    partDataHandler = Mockito.mock(PartDataHandler.class);
    volumeHandler = new VolumeHandler(partDataHandler);
  }

  @Test
  public void validateSkipNonExistingVolumes() throws Exception {
    LoginDetails loginDetails = new LoginDetails("", new BasicCookieStore());
    int[] volumeNos = { 0, 3, 4, 5 };

    VolumeInfo volume1Info = new VolumeInfo();
    volume1Info.setTitleslug("test-vol-1");

    VolumeInfo volume2Info = new VolumeInfo();
    volume2Info.setTitleslug("test-vol-2");

    VolumePartInfo volume1PartInfo1 = new VolumePartInfo();
    volume1PartInfo1.setTitleslug("test-vol-1-part-1");
    volume1PartInfo1.setPartNumber(1);

    VolumePartInfo volume1PartInfo2 = new VolumePartInfo();
    volume1PartInfo2.setTitleslug("test-vol-1-part-2");
    volume1PartInfo2.setPartNumber(2);

    VolumePartInfo volume2PartInfo1 = new VolumePartInfo();
    volume2PartInfo1.setTitleslug("test-vol-2-part-1");
    volume2PartInfo1.setPartNumber(1);

    SeriesData seriesData = new SeriesData();
    seriesData.setTitleslug("test");
    seriesData.setVolumes(Arrays.asList(volume1Info, volume2Info));
    seriesData.setParts(Arrays.asList(volume1PartInfo1, volume1PartInfo2, volume2PartInfo1));

    PartData partData = new PartData();
    Mockito.when(partDataHandler.get(Mockito.eq(loginDetails), Mockito.any(VolumePartInfo.class))).thenReturn(partData);

    List<Volume> volumes = volumeHandler.getVolumes(loginDetails, seriesData, volumeNos);
    Assertions.assertEquals(0, volumes.size());
  }

  @Test
  public void validateCorrectRetrieval1() throws Exception {
    LoginDetails loginDetails = new LoginDetails("", new BasicCookieStore());
    int[] volumeNos = { 1 };

    VolumeInfo volume1Info = new VolumeInfo();
    volume1Info.setTitleslug("test-vol-1");

    VolumeInfo volume2Info = new VolumeInfo();
    volume2Info.setTitleslug("test-vol-2");

    VolumePartInfo volume1PartInfo1 = new VolumePartInfo();
    volume1PartInfo1.setTitleslug("test-vol-1-part-1");
    volume1PartInfo1.setPartNumber(1);

    VolumePartInfo volume1PartInfo2 = new VolumePartInfo();
    volume1PartInfo2.setTitleslug("test-vol-1-part-2");
    volume1PartInfo2.setPartNumber(2);

    VolumePartInfo volume2PartInfo1 = new VolumePartInfo();
    volume2PartInfo1.setTitleslug("test-vol-2-part-1");
    volume2PartInfo1.setPartNumber(1);

    SeriesData seriesData = new SeriesData();
    seriesData.setTitleslug("test");
    seriesData.setVolumes(Arrays.asList(volume1Info, volume2Info));
    seriesData.setParts(Arrays.asList(volume1PartInfo1, volume1PartInfo2, volume2PartInfo1));

    PartData partData = new PartData();
    Mockito.when(partDataHandler.get(Mockito.eq(loginDetails), Mockito.any(VolumePartInfo.class))).thenReturn(partData);

    List<Volume> volumes = volumeHandler.getVolumes(loginDetails, seriesData, volumeNos);
    Assertions.assertEquals(1, volumes.size());

    Volume volume = volumes.get(0);
    List<VolumePart> parts = volume.getParts();
    Assertions.assertEquals(volume1Info, volume.getVolumeInfo());
    Assertions.assertEquals(2, parts.size());
    Assertions.assertEquals(volume1PartInfo1, parts.get(0).getPartInfo());
    Assertions.assertEquals(volume1PartInfo2, parts.get(1).getPartInfo());
  }

  @Test
  public void validateCorrectRetrieval2() throws Exception {
    LoginDetails loginDetails = new LoginDetails("", new BasicCookieStore());
    int[] volumeNos = { 2 };

    VolumeInfo volume1Info = new VolumeInfo();
    volume1Info.setTitleslug("test-volume-1");

    VolumeInfo volume2Info = new VolumeInfo();
    volume2Info.setTitleslug("test-volume-2");

    VolumePartInfo volume1PartInfo1 = new VolumePartInfo();
    volume1PartInfo1.setTitleslug("test-volume-1-part-1");
    volume1PartInfo1.setPartNumber(1);

    VolumePartInfo volume1PartInfo2 = new VolumePartInfo();
    volume1PartInfo2.setTitleslug("test-volume-1-part-2");
    volume1PartInfo2.setPartNumber(2);

    VolumePartInfo volume2PartInfo1 = new VolumePartInfo();
    volume2PartInfo1.setTitleslug("test-volume-2-part-1");
    volume2PartInfo1.setPartNumber(1);

    SeriesData seriesData = new SeriesData();
    seriesData.setTitleslug("test");
    seriesData.setVolumes(Arrays.asList(volume1Info, volume2Info));
    seriesData.setParts(Arrays.asList(volume1PartInfo1, volume1PartInfo2, volume2PartInfo1));

    PartData partData = new PartData();
    Mockito.when(partDataHandler.get(Mockito.eq(loginDetails), Mockito.any(VolumePartInfo.class))).thenReturn(partData);

    List<Volume> volumes = volumeHandler.getVolumes(loginDetails, seriesData, volumeNos);
    Assertions.assertEquals(1, volumes.size());

    Volume volume = volumes.get(0);
    List<VolumePart> parts = volume.getParts();
    Assertions.assertEquals(volume2Info, volume.getVolumeInfo());
    Assertions.assertEquals(1, parts.size());
    Assertions.assertEquals(volume2PartInfo1, parts.get(0).getPartInfo());
  }

  @Test
  public void validateSkipError() throws Exception {
    LoginDetails loginDetails = new LoginDetails("", new BasicCookieStore());
    int[] volumeNos = { 1 };

    VolumeInfo volume1Info = new VolumeInfo();
    volume1Info.setTitleslug("test-vol-1");

    VolumeInfo volume2Info = new VolumeInfo();
    volume2Info.setTitleslug("test-vol-2");

    VolumePartInfo volume1PartInfo1 = new VolumePartInfo();
    volume1PartInfo1.setTitleslug("test-vol-1-part-1");
    volume1PartInfo1.setPartNumber(1);

    VolumePartInfo volume1PartInfo2 = new VolumePartInfo();
    volume1PartInfo2.setTitleslug("test-vol-1-part-2");
    volume1PartInfo2.setPartNumber(2);

    VolumePartInfo volume1PartInfo3 = new VolumePartInfo();
    volume1PartInfo3.setTitleslug("test-vol-1-part-3");
    volume1PartInfo3.setPartNumber(3);

    VolumePartInfo volume2PartInfo1 = new VolumePartInfo();
    volume2PartInfo1.setTitleslug("test-vol-2-part-1");
    volume2PartInfo1.setPartNumber(1);

    SeriesData seriesData = new SeriesData();
    seriesData.setTitleslug("test");
    seriesData.setVolumes(Arrays.asList(volume1Info, volume2Info));
    seriesData.setParts(Arrays.asList(volume1PartInfo1, volume1PartInfo2, volume1PartInfo3, volume2PartInfo1));

    PartData partData = new PartData();
    PartData failPartData = new PartData();
    failPartData.setError(new PartDataError());

    Mockito.when(partDataHandler.get(Mockito.eq(loginDetails), Mockito.any(VolumePartInfo.class))).thenReturn(partData);
    Mockito.when(partDataHandler.get(Mockito.eq(loginDetails), Mockito.eq(volume1PartInfo2))).thenReturn(failPartData);

    List<Volume> volumes = volumeHandler.getVolumes(loginDetails, seriesData, volumeNos);
    Assertions.assertEquals(1, volumes.size());

    Volume volume = volumes.get(0);
    List<VolumePart> parts = volume.getParts();
    Assertions.assertEquals(volume1Info, volume.getVolumeInfo());
    Assertions.assertEquals(1, parts.size());
    Assertions.assertEquals(volume1PartInfo1, parts.get(0).getPartInfo());
  }

  @Test
  public void validateShouldAppendVolIfNotExist() throws Exception {
    LoginDetails loginDetails = new LoginDetails("", new BasicCookieStore());
    int[] volumeNos = { 1 };

    VolumeInfo volume1Info = new VolumeInfo();
    volume1Info.setTitleslug("test");

    VolumeInfo volume2Info = new VolumeInfo();
    volume2Info.setTitleslug("test-vol-2");

    VolumePartInfo volume1PartInfo1 = new VolumePartInfo();
    volume1PartInfo1.setTitleslug("test-vol-1-part-1");
    volume1PartInfo1.setPartNumber(1);

    VolumePartInfo volume1PartInfo2 = new VolumePartInfo();
    volume1PartInfo2.setTitleslug("test-vol-1-part-2");
    volume1PartInfo2.setPartNumber(2);

    VolumePartInfo volume1PartInfo3 = new VolumePartInfo();
    volume1PartInfo3.setTitleslug("test-vol-1-part-3");
    volume1PartInfo3.setPartNumber(3);

    VolumePartInfo volume2PartInfo1 = new VolumePartInfo();
    volume2PartInfo1.setTitleslug("test-vol-2-part-1");
    volume2PartInfo1.setPartNumber(1);

    SeriesData seriesData = new SeriesData();
    seriesData.setTitleslug("test");
    seriesData.setVolumes(Arrays.asList(volume1Info, volume2Info));
    seriesData.setParts(Arrays.asList(volume1PartInfo1, volume1PartInfo2, volume1PartInfo3, volume2PartInfo1));

    PartData partData = new PartData();
    PartData failPartData = new PartData();
    failPartData.setError(new PartDataError());

    Mockito.when(partDataHandler.get(Mockito.eq(loginDetails), Mockito.any(VolumePartInfo.class))).thenReturn(partData);
    Mockito.when(partDataHandler.get(Mockito.eq(loginDetails), Mockito.eq(volume1PartInfo2))).thenReturn(failPartData);

    List<Volume> volumes = volumeHandler.getVolumes(loginDetails, seriesData, volumeNos);
    Assertions.assertEquals(1, volumes.size());

    Volume volume = volumes.get(0);
    List<VolumePart> parts = volume.getParts();
    Assertions.assertEquals(volume1Info, volume.getVolumeInfo());
    Assertions.assertEquals(1, parts.size());
    Assertions.assertEquals(volume1PartInfo1, parts.get(0).getPartInfo());
  }

}
