/**
 * 
 */
package hc.parser;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 */
public class SeriesData {

  private String title;
  private String titleslug;
  private String author;
  private String illustrator;
  private String tags;
  private List<VolumeInfo> volumes;
  private List<VolumePartInfo> parts;

  public SeriesData() {
    this.volumes = new ArrayList<>();
    this.parts = new ArrayList<>();
  }

  public String getTitle() {
    return title;
  }

  public String getTitleslug() {
    return titleslug;
  }

  public String getAuthor() {
    return author;
  }

  public String getIllustrator() {
    return illustrator;
  }

  public String getTags() {
    return tags;
  }

  public List<VolumeInfo> getVolumes() {
    return volumes;
  }

  public List<VolumePartInfo> getParts() {
    return parts;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setTitleslug(String titleslug) {
    this.titleslug = titleslug;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setIllustrator(String illustrator) {
    this.illustrator = illustrator;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  public void setVolumes(List<VolumeInfo> volumes) {
    this.volumes = volumes;
  }

  public void setParts(List<VolumePartInfo> parts) {
    this.parts = parts;
  }

  @Override
  public String toString() {
    return "SeriesData [title=" + title + ", titleslug=" + titleslug + ", author=" + author + ", illustrator="
        + illustrator + ", tags=" + tags + ", volumes=" + volumes + ", parts=" + parts + "]";
  }

}
