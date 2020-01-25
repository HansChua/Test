/**
 * 
 */
package hc.downloader;

import java.util.ArrayList;
import java.util.List;

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
  public String toString() {
    return "Volume [volumeSlug=" + volumeSlug + ", volumeInfo=" + volumeInfo + ", parts=" + parts + "]";
  }

}
