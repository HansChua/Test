/**
 * 
 */
package hc.util;

import java.nio.charset.StandardCharsets;

import org.apache.http.client.methods.RequestBuilder;

/**
 * 
 */
public class RequestBuilderFactory {

  public RequestBuilder get(String url) {
    return applyConfig(RequestBuilder.get(url));
  }

  public RequestBuilder post(String url) {
    return applyConfig(RequestBuilder.post(url));
  }

  static RequestBuilder applyConfig(RequestBuilder bldr) {
    return bldr.setCharset(StandardCharsets.UTF_8);
  }

}
