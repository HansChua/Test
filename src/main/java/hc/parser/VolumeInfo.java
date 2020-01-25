/**
 * 
 */
package hc.parser;

/**
 * 
 */
public class VolumeInfo {

  private String title;
  private String titleslug;
  private String titleShort;
  private int volumeNumber;
  private String author;
  private String illustrator;
  private String publisherOriginal;
  private String tags;

  public VolumeInfo() {
    super();
    this.title = null;
    this.titleslug = null;
    this.titleShort = null;
    this.volumeNumber = 0;
    this.author = null;
    this.illustrator = null;
    this.publisherOriginal = null;
    this.tags = null;
  }

  public String getTitle() {
    return title;
  }

  public String getTitleslug() {
    return titleslug;
  }

  public String getTitleShort() {
    return titleShort;
  }

  public int getVolumeNumber() {
    return volumeNumber;
  }

  public String getAuthor() {
    return author;
  }

  public String getIllustrator() {
    return illustrator;
  }

  public String getPublisherOriginal() {
    return publisherOriginal;
  }

  public String getTags() {
    return tags;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public void setTitleslug(String titleslug) {
    this.titleslug = titleslug;
  }

  public void setTitleShort(String titleShort) {
    this.titleShort = titleShort;
  }

  public void setVolumeNumber(int volumeNumber) {
    this.volumeNumber = volumeNumber;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public void setIllustrator(String illustrator) {
    this.illustrator = illustrator;
  }

  public void setPublisherOriginal(String publisherOriginal) {
    this.publisherOriginal = publisherOriginal;
  }

  public void setTags(String tags) {
    this.tags = tags;
  }

  @Override
  public String toString() {
    return "VolumeData [title=" + title + ", titleslug=" + titleslug + ", titleShort=" + titleShort + ", volumeNumber="
        + volumeNumber + ", author=" + author + ", illustrator=" + illustrator + ", publisherOriginal="
        + publisherOriginal + ", tags=" + tags + "]";
  }

}
