/**
 * 
 */
package hc.login;

/**
 */
public class LoginResponse {

  private String id;
  private LoginResponseError error;

  LoginResponse() {
  }

  public String getId() {
    return id;
  }

  void setId(String id) {
    this.id = id;
  }

  public LoginResponseError getError() {
    return error;
  }

  void setError(LoginResponseError error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return "LoginResponse [id=" + id + ", error=" + error + "]";
  }

}
