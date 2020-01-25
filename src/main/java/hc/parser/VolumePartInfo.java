/**
 * 
 */
package hc.parser;

/**
 */
public class VolumePartInfo {

  private String title;
  private String titleslug;
  private int partNumber;
  private String id;

  public VolumePartInfo() {
    super();
    this.title = null;
    this.titleslug = null;
    this.partNumber = 0;
    this.id = null;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getTitleslug() {
    return titleslug;
  }

  public void setTitleslug(String titleslug) {
    this.titleslug = titleslug;
  }

  public int getPartNumber() {
    return partNumber;
  }

  public void setPartNumber(int partNumber) {
    this.partNumber = partNumber;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "PartInfo [title=" + title + ", titleslug=" + titleslug + ", partNumber=" + partNumber + ", id=" + id + "]";
  }

}
