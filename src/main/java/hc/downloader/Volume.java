/**
 * 
 */
package hc.downloader;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import hc.parser.VolumeInfo;

/**
 * 
 */
public class Volume {

  private String volumeSlug;
  private VolumeInfo volumeInfo;
  private List<VolumePart> parts;

  public Volume() {
  }

  public Volume(String volumeSlug) {
    this.volumeSlug = volumeSlug;
    this.parts = new ArrayList<>(8);
  }

  public String getVolumeSlug() {
    return volumeSlug;
  }

  public void setVolumeSlug(String volumeSlug) {
    this.volumeSlug = volumeSlug;
  }

  public VolumeInfo getVolumeInfo() {
    return volumeInfo;
  }

  public void setVolumeInfo(VolumeInfo volumeInfo) {
    this.volumeInfo = volumeInfo;
  }

  public List<VolumePart> getParts() {
    return parts;
  }

  public void setParts(List<VolumePart> parts) {
    this.parts = parts;
  }

  public void addPart(VolumePart part) {
    this.parts.add(part);
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
