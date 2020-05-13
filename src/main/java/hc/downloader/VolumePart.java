/**
 * 
 */
package hc.downloader;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

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
  public int hashCode() {
    return HashCodeBuilder.reflectionHashCode(this, false);
  }

  @Override
  public boolean equals(Object obj) {
    return EqualsBuilder.reflectionEquals(this, obj);
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
  }

}
