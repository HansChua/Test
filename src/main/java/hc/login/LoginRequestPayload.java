/**
 * 
 */
package hc.login;

/**
 */
public class LoginRequestPayload {

  private String email;
  private String password;

  LoginRequestPayload() {
  }

  public LoginRequestPayload(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  void setPassword(String password) {
    this.password = password;
  }

}
