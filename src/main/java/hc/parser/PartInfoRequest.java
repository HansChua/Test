/**
 * 
 */
package hc.parser;

/**
 * 
 */
public class PartInfoRequest {

  private Where where;

  public PartInfoRequest(String titleslug) {
    this.where = new Where(titleslug);
  }

  public Where getWhere() {
    return where;
  }

  void setWhere(Where where) {
    this.where = where;
  }

  static class Where {

    private String titleslug;

    public Where(String titleslug) {
      super();
      this.titleslug = titleslug;
    }

    public String getTitleslug() {
      return titleslug;
    }

    void setTitleslug(String titleslug) {
      this.titleslug = titleslug;
    }

  }

}
