/**
 * 
 */
package hc.downloader;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.inject.Inject;

import hc.login.LoginDetails;
import hc.parser.PartData;
import hc.parser.PartDataHandler;
import hc.parser.SeriesData;
import hc.parser.VolumeInfo;
import hc.parser.VolumePartInfo;

/**
 * 
 */
public class VolumeHandler {

  private final PartDataHandler partDataHandler;

  @Inject
  VolumeHandler(PartDataHandler partDataHandler) {
    super();
    this.partDataHandler = partDataHandler;
  }

  public List<Volume> getVolumes(LoginDetails loginDetails, SeriesData seriesData, int[] volumeNos) throws Exception {
    List<VolumeInfo> volumeDatas = seriesData.getVolumes();
    List<VolumePartInfo> parts = seriesData.getParts();

    List<Volume> volumes = new ArrayList<>();

    for (int volumeNo : volumeNos) {
      int index = volumeNo - 1;
      if (index >= volumeDatas.size() || index < 0) {
        continue;
      }

      VolumeInfo volumeInfo = volumeDatas.get(index);
      String rawVolumeTitleSlug = volumeInfo.getTitleslug();

      boolean hasVolumeTitle = rawVolumeTitleSlug.contains("-vol-") || rawVolumeTitleSlug.contains("-volume-");
      String volumeTitleSlug = hasVolumeTitle ? rawVolumeTitleSlug : rawVolumeTitleSlug + "-vol-" + volumeNo;

      Volume volume = new Volume(volumeTitleSlug);
      volume.setVolumeInfo(volumeInfo);

      List<VolumePartInfo> volumePartInfos = parts.stream()
                                                  .filter(p -> p.getTitleslug().startsWith(volumeTitleSlug))
                                                  .collect(Collectors.toList());

      for (VolumePartInfo volumePartInfo : volumePartInfos) {
        PartData partData = partDataHandler.get(loginDetails, volumePartInfo);
        if (null != partData.getError()) {
          // assume no more access or not existing
          break;
        }

        volume.addPart(new VolumePart(volumePartInfo, partData));
      }

      volumes.add(volume);
    }

    return volumes;
  }

}
