/**
 * 
 */
package hc.login;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;

/**
 * 
 */
public class LoginDetails {

  private final String authorization;
  private final CookieStore cookies;

  public LoginDetails(String authorization, CookieStore cookies) {
    this.authorization = authorization;
    this.cookies = cookies;
  }

  public String getAuthorization() {
    return authorization;
  }

  public CookieStore getCookies() {
    BasicCookieStore cookiesCopy = new BasicCookieStore();
    for (Cookie cookie : cookies.getCookies()) {
      cookiesCopy.addCookie(cookie);
    }
    return cookiesCopy;
  }

}
