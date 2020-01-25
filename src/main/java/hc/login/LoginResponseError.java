/**
 * 
 */
package hc.login;

/**
 */
public class LoginResponseError {

  private String name;
  private String status;
  private String message;
  private int statusCode;
  private String code;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  @Override
  public String toString() {
    return "LoginResponseError [name=" + name + ", status=" + status + ", message=" + message + ", statusCode="
        + statusCode + ", code=" + code + "]";
  }

}
