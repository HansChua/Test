/**
 * 
 */
package hc.parser;

/**
 * 
 */
public class PartData {

  private String dataHTML;
  private PartDataError error;

  public String getDataHTML() {
    return dataHTML;
  }

  public void setDataHTML(String dataHTML) {
    this.dataHTML = dataHTML;
  }

  public PartDataError getError() {
    return error;
  }

  public void setError(PartDataError error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return "PartData [dataHTML=" + dataHTML + ", error=" + error + "]";
  }

}
