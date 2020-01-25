/**
 * 
 */
package hc.login;

import org.apache.http.client.CookieStore;

/**
 * 
 */
public class LoginDetails {

  private String authorization;
  private CookieStore cookies;

  LoginDetails() {
  }

  public LoginDetails(String authorization, CookieStore cookies) {
    this.authorization = authorization;
    this.cookies = cookies;
  }

  public String getAuthorization() {
    return authorization;
  }

  public CookieStore getCookies() {
    return cookies;
  }

  void setAuthorization(String authorization) {
    this.authorization = authorization;
  }

  void setCookies(CookieStore cookies) {
    this.cookies = cookies;
  }

}
