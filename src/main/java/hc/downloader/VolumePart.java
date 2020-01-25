/**
 * 
 */
package hc.downloader;

import hc.parser.PartData;
import hc.parser.VolumePartInfo;

/**
 * 
 */
public class VolumePart {

  private VolumePartInfo partInfo;
  private PartData partData;

  VolumePart() {
  }

  VolumePart(VolumePartInfo partInfo, PartData partData) {
    super();
    this.partInfo = partInfo;
    this.partData = partData;
  }

  public VolumePartInfo getPartInfo() {
    return partInfo;
  }

  public PartData getPartData() {
    return partData;
  }

  void setPartInfo(VolumePartInfo partInfo) {
    this.partInfo = partInfo;
  }

  void setPartData(PartData partData) {
    this.partData = partData;
  }

  @Override
  public String toString() {
    return "VolumePart [partInfo=" + partInfo + ", partData=" + partData + "]";
  }

}
