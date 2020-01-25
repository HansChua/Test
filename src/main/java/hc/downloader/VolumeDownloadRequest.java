/**
 * 
 */
package hc.downloader;

import java.util.Arrays;

/**
 * 
 */
public class VolumeDownloadRequest {

  private String titleSlug;
  private int[] volumes;

  public VolumeDownloadRequest() {
    this.volumes = new int[0];
  }

  public String getTitleSlug() {
    return titleSlug;
  }

  public int[] getVolumes() {
    return volumes;
  }

  public void setTitleSlug(String titleSlug) {
    this.titleSlug = titleSlug;
  }

  public void setVolumes(int[] volumes) {
    this.volumes = volumes;
  }

  @Override
  public String toString() {
    return "VolumeDownloadRequest [titleSlug=" + titleSlug + ", volumes=" + Arrays.toString(volumes) + "]";
  }

}
