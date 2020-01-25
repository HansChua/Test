/**
 * 
 */
package hc.parser;

/**
 */
public class PartInfo {

  private String title;
  private String id;

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  @Override
  public String toString() {
    return "PartInfo [title=" + title + ", id=" + id + "]";
  }

}
